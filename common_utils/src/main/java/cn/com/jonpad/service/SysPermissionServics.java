package cn.com.jonpad.service;

import cn.com.jonpad.dto.MeunDetails;
import cn.com.jonpad.entity.SysPermission;
import cn.com.jonpad.repository.SysPermissionRepository;
import cn.com.jonpad.util.ValidateTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jon75 on 2017/6/4.
 */
@Service
public class SysPermissionServics {
	@Autowired
	private SysPermissionRepository spr;

	/**
	 * 根据用户ID获取用户菜单
	 * @param userid
	 * @return
	 */
	public Map<String, List<SysPermission>> findMenuTreeByUserId(String userid){
		List<SysPermission> menuTree = spr.findMenuTreeByUserIdAndType(SysPermission.MENU_TREE_TYPE_MENU, userid);
		List<SysPermission> rootTree = spr.findMenuTreeByUserIdAndType(SysPermission.MENU_TREE_TYPE_ROOT, userid);
		Map<String, List<SysPermission>> map = new HashMap<String, List<SysPermission>>();
		map.put("menu", menuTree);
		map.put("root", rootTree);
		return map;
	}


	public List<SysPermission> getAllRootSysPermission() {
		return spr.getAllRootSysPermission();
	}

	public Map<Long,List<SysPermission>> getAllChildPermission(List<SysPermission> rootList) {
		if (rootList != null && rootList.size() > 0) {
			Map<Long, List<SysPermission>> map = new HashMap<Long, List<SysPermission>>();
			for (SysPermission item : rootList) {
				map.put(item.getId(), spr.getMenuByRoot(item.getId()));
			}
			return map;
		}
		return null;
	}

	@Transactional
	public boolean addPermission(SysPermission permission) {
    if (!ValidateTool.isEmptyString(permission.getName())) {
			// if
			// (sysPermissionDAO.findPermissionByNameAndType(permission.getName().trim(),
			// permission.getType()) == null) {
			if ("root".equals(permission.getType())) {
        spr.save(permission);
			} else {
				// 直接父亲节点
				SysPermission parentSp = spr.findById(permission.getParentid());
				// 最终父亲节点
				SysPermission rootParentsp = this.getRootPparentByParentPermission(parentSp);
				if (rootParentsp != null) {
					permission.setRootPparentid(rootParentsp.getId());
				} else {
					permission.setRootPparentid(parentSp.getId());
				}
				if (parentSp.getParentids() != null) {
					permission.setParentids(parentSp.getParentids() + "," + parentSp.getId());
				} else {
					permission.setParentids(parentSp.getId() + "");
				}
			}
      permission.setType(SysPermission.MENU_TREE_TYPE_MENU);
			spr.save(permission);
			return true;
		}

		return false;
	}

	public SysPermission getRootPparentByParentPermission(SysPermission parentSp) {
		if (parentSp != null) {
			String[] tmpRootArr = null;
			if (parentSp.getParentids() != null) {
				tmpRootArr = parentSp.getParentids().split(",");
			}
			if (tmpRootArr != null && tmpRootArr.length > 0) {
				long tmpRootId = Integer.parseInt(tmpRootArr[0]);
				return spr.findById(tmpRootId);
			}

		}
		return null;
	}

	@Transactional
	public boolean deletePermission(long id) {
		// 获取节点
		SysPermission permission = spr.findById(id);
		if (permission == null) {
			return false;
		}
		// 先看是不是根节点，
		if ("root".equals(permission.getType()) || "menu".equals(permission.getType())) {
			// 是，看有没有子节点
			List<SysPermission> permissionList = spr.findChildPermission(permission.getId());
			if (permissionList != null && permissionList.size() > 0) {
				// 有，不删除，报错
				return false;
			}
		}

		// 没有 || 不是 删除
		spr.delete(id);
		return  true;
	}

	public SysPermission getOne(long id){
	  if(id == 0){
      return spr.getRoot().get(0);
    }else {
	    return spr.findOne(id);
    }
  }

  public long countSize(){
    return spr.count();
  }


	public MeunDetails getMeunDetails(long id) {
		MeunDetails ms = new MeunDetails();

		SysPermission item = spr.findById(id);
		if (item == null) {
			return null;
		}
		ms.setDetails(item);
		List<SysPermission> buttons = spr.getButtonsByMeun(item.getId());
		ms.setButtons(buttons);
		return ms;
	}

	@Transactional
	public boolean modifypermissionState(SysPermission permission) {
		SysPermission hPer = spr.findById(permission.getId());
		if (hPer == null) {
			//
			return false;
		} else {
			if (hPer.getAvailable() == 0) {
				hPer.setAvailable(1);
			} else {
				//先查是否包含子节点
				List<SysPermission> list = spr.findChildrenSysPermission(hPer.getId());
				if(list.size() > 0){
					//大于0 把里面所有值都改为0 不可用；
					for (SysPermission sysPermission : list) {
						sysPermission.setAvailable(0);
						spr.saveAndFlush(sysPermission);
					}
				}
				hPer.setAvailable(0);
			}
		}
		spr.saveAndFlush(hPer);
		return true;
	}

	public List<SysPermission> getAllEnableSysPermission() {
		return  spr.findByAvailable(SysPermission.AVAILABLE_TREU);
	}
}
