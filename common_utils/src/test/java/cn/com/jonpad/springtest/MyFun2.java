package cn.com.jonpad.springtest;

@FunctionalInterface
public interface MyFun2<T,E> {
  E getValue(T o1,T o2);
}
