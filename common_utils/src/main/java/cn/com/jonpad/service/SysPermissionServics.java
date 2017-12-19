package cn.com.jonpad.service;

import cn.com.jonpad.dto.MeunDetails;
import cn.com.jonpad.entity.SysGroupRolePermission;
import cn.com.jonpad.entity.SysPermission;
import cn.com.jonpad.mybatis.SysGroupPermissionDao;
import cn.com.jonpad.mybatis.SysGroupRolePermissionDao;
import cn.com.jonpad.mybatis.SysRolePermissionDao;
import cn.com.jonpad.repository.SysPermissionRepository;
import cn.com.jonpad.util.ValidateTool;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jon75 on 2017/6/4.
 */
@Service
public class SysPermissionServics {

  @Autowired
  private SysRolePermissionDao srpDao;
  @Autowired
  private SysGroupPermissionDao sgpDao;
	@Autowired
	private SysPermissionRepository spr;
	@Autowired
	private SysGroupRolePermissionDao sgrpDao;

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

	@Transactional(rollbackFor = Exception.class)
	public boolean addPermission(SysPermission permission) {
    if (!ValidateTool.isEmptyString(permission.getName())) {
			// if
			// (sysPermissionDAO.findPermissionByNameAndType(permission.getName().trim(),
			// permission.getType()) == null) {
			if ("root".equals(permission.getType())) {
        spr.save(permission);
        return true;
			} else {
				// 直接父亲节点
				SysPermission parentSp = spr.findById(permission.getParentid());
				// 最终父亲节点
				SysPermission rootParentsp = this.getRootPparentByParentPermission(parentSp);
				if (rootParentsp != null) {
					permission.setRootParentid(rootParentsp.getId());
				} else {
					permission.setRootParentid(parentSp.getId());
				}
				if (!ValidateTool.isEmptyString(parentSp.getParentids())) {
					permission.setParentids(parentSp.getParentids() + "," + parentSp.getId());
				} else {
					permission.setParentids(parentSp.getId() + "");
				}
        permission.setType(SysPermission.MENU_TREE_TYPE_MENU);
        spr.save(permission);
        return true;
			}
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

  @Transactional(rollbackFor = Exception.class)
	public boolean edit(long permissionId, String name,String percode,String sortstring,String url){
    SysPermission permission = spr.findById(permissionId);
    if (permission == null) {
      return false;
    }
    if(!StringUtils.isEmpty(name)){
      permission.setName(name);
    }
    if(!StringUtils.isEmpty(percode)){
      permission.setPercode(percode);
    }
    if(!StringUtils.isEmpty(sortstring)){
      permission.setSortstring(sortstring);
    }
    if(!StringUtils.isEmpty(url)){
      permission.setUrl(url);
    }
    spr.saveAndFlush(permission);
    return true;
  }

	@Transactional(rollbackFor = Exception.class)
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
      List<SysPermission> list = spr.getRoot();
      return list.get(0);
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
		List<SysPermission> buttons = spr.findChildPermission(item.getId());
    List<SysPermission> buttonsre = new ArrayList<>();

    for (SysPermission itemis : buttons) {
      long cSize = spr.countByParentid(itemis.getId());
      SysPermission i = new SysPermission(itemis);
      i.setLeaf(cSize==0?true:false);
      buttonsre.add(i);
    }

		ms.setChildren(buttonsre);
		return ms;
	}

	@Transactional(rollbackFor = Exception.class)
	public boolean modifypermissionState(long permissionId) {
		SysPermission hPer = spr.findById(permissionId);
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

  /**
   * 获取分配给资源得角色，组，角色+组得数据
   * @param permissionId
   * @return
   */
	public JSONObject getAccessPermission(long permissionId){
    JSONObject jo = new JSONObject(3);
    List<Long> roleList = srpDao.findRoleListByPermission(permissionId);
    List<Long> groupList = sgpDao.findGroupListByPermission(permissionId);
    List<HashMap> grList = sgrpDao.findViewByPermissionId(permissionId);
    jo.put("roleList",roleList);
    jo.put("groupList",groupList);
    jo.put("grList",grList);
    return jo;
  }

}
