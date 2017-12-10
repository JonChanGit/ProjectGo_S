package cn.com.jonpad.springtest;

import org.junit.Test;

import java.io.PrintStream;
import java.util.*;
import java.util.function.*;

/**
 * 一 Lambda 表达式基础语法：Java8引入新的操作符“->”↓
 * <p>
 * 左侧：λ表达式的参数列表
 * 右侧：λ表达式所需要执行的功能，即方法体
 * <p>
 * <p>
 * 二 λ表达式需要函数式接口支持
 * 函数式接口: 接口中只能有一个抽象方法！
 * 可以使用@FunctionalInterface注解进行标识
 */
public class TestLambda {
  /*---------------------------|
  | Lambda表达式基本概念
   ----------------------------*/

  /**
   * 语法格式一 -> 污参数，无返回值：
   */
  @Test
  public void testLambda01() {

    int count = 1;//在被匿名内部类调用时，自动认为是final修饰的

    Runnable r = new Runnable() {
      @Override
      public void run() {
        System.out.println("hello Java 7" + count);
      }
    };
    r.run();

    System.out.println("==========================");

    Runnable re = () -> System.out.println("Hello Java 8" + count);

    re.run();

  }


  /**
   * 语法格式二 -> 一个参数，无返回值
   */
  @Test
  public void testLambda02() {
    Consumer<String> con1 = (x) -> System.out.println(x);
    con1.accept("Hello Lambda λ");
  }

  /**
   * 语法格式三 -> 一个参数，无返回值，简化写法
   * 不写小括号
   */
  @Test
  public void testLambda03() {
    Consumer<String> con1 = x -> System.out.println(x);
    con1.accept("Hello Lambda λ");
  }

  /**
   * 语法格式四 -> 两个或以上个参数，有返回值，方法体有多条语句
   */
  @Test
  public void testLambda04() {
    Comparator<Integer> com = (x, y) -> {
      System.out.println(x + "" + y);
      return Integer.compare(x, y);
    };
  }

  /**
   * 语法格式五 -> 两个或以上个参数，有返回值，方法体有一条语句，可以不写return
   */
  @Test
  public void testLambda05() {
    Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
  }

  /**
   * 语法格式六 -> 你如果想写参数类型，那么参数列表全部的参数类型都要写出来
   */
  @Test
  public void testLambda06() {
    Comparator<Integer> com = (Integer x, Integer y) -> Integer.compare(x, y);
  }

  @Test
  public void testLambda06_1() {

    List<Employee> list = Arrays.asList(
      new Employee("c", 3),
      new Employee("a", 2),
      new Employee("a", 1),
      new Employee("d", 4)
    );

    Collections.sort(list, (a, b) -> {
      if (a.getAge() == b.getAge()) {
        return a.getName().compareTo(b.getName());
      } else {
        return Integer.compare(a.getAge(), b.getAge());
      }
    });

    for (Employee item : list) {
      System.out.println(item);
    }

  }

  /**
   * 用于处理字符串
   */
  public String strHandler(String str, MyFun fun) {
    return fun.getValue(str);
  }


  @Test
  public void testLambda06_2() {
    String str = strHandler("  Test String  \t\t\"\"  ", (_str) -> _str.toUpperCase());
    System.out.println(str);
  }


  public void longHandler(Long l1, Long l2, MyFun2<Long, Long> fun) {
    Object value = fun.getValue(l1, l2);
    System.out.println(value);
  }

  @Test
  public void testLambda06_3() {
    longHandler(1L, 2L, (x, y) -> x + y);
    longHandler(1L, 2L, (x, y) -> x * y);
  }

  /*---------------------------|
  | 内置函数接口的使用
   ----------------------------*/

  public void happy(double money, Consumer<Double> com) {
    com.accept(money);
  }

  /**
   * 消费接口，特点：无返回值
   */
  @Test
  public void testLambdaFunction01() {
    happy(1000, m -> System.out.println("消费大保健一次：" + m + "元"));
  }

  public List<Integer> getNumList(int num, Supplier<Integer> sup) {
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < num; i++) {
      Integer n = sup.get();
      list.add(n);
    }
    return list;
  }

  /**
   * 生产接口，特点：无返参数，返回需要的对象
   */
  @Test
  public void testLambdaFunction02() {
    List<Integer> numList = getNumList(1000, () -> (int) (Math.random() * 100));
    for (Integer integer : numList) {
      System.out.println(integer);
    }
  }

  public String testFunction(String ste, Function<String, String> fun) {
    return fun.apply(ste);
  }

  /**
   * 函数型接口
   */
  @Test
  public void testLambdaFunction03() {
    String qwertyuiop = testFunction("qwertyuiop", str -> str.substring(1, 5));
    System.out.println(qwertyuiop);
  }

  public List<String> filterStr(List<String> list, Predicate<String> pre) {
    List<String> strList = new ArrayList<>();
    for (String str : list) {
      if (pre.test(str)) {
        strList.add(str);
      }
    }
    return strList;
  }

  /**
   * 断言型接口
   */
  @Test
  public void testLambdaFunction04() {
    List<String> strings = filterStr(
      Arrays.asList("123", "1234", "13456"),
      s -> s.length() > 3
    );

    for (String s : strings) {
      System.out.println(s);
    }
  }



   /*---------------------------|
  | 方法引用：若λ体中的内容有方法已经实现了，则可以使用“方法引用”
  |          （可以理解为是Lambda表达式的另一种表现形式）
  | 主要有三种语法：
  |  1. 对象::实例方法名
  |  2. 类::静态方法名
  |  3. 类::实例方法名
   ----------------------------*/

  /**
   * 对象::实例方法名
   */
  @Test
  public void testMethodRef01() {
    Consumer<String> con1 = x -> System.out.println(x);
    PrintStream ps = System.out;
    //引用的方法签名需要和方法接口中定义的方法签名一直（参数列表和返回值）
    Consumer<String> con2 = ps::println;
    Consumer<String> con3 = System.out::println;
  }

  /**
   * 类::实例方法名
   */
  @Test
  public void testMethodRef02() {
    Employee emp = new Employee("小丽",16);
    Supplier<String> sup1 = () -> emp.getName();
    System.out.println(sup1.get());
    Supplier<Integer> sup2 = emp::getAge;
    System.out.println(sup2.get());
  }

  /**
   *  类::静态方法名
   */
  @Test
  public void testMethodRef03() {
    Comparator<Integer> co1 = (x,y) -> Integer.compare(x,y);
    System.out.println(co1.compare(2,5));
    Comparator<Integer> co2 = Integer::compare;
    System.out.println(co2.compare(2,5));
  }

  /**
   * 若Lambda参数列表中第一个参数是实例方法的调用者，而第二个参数是实例方法的参数时，可以使用ClassName::methodName
   */
  @Test
  public void testMethodRef04() {
    BiPredicate<String,String> bp1 = (x,y)->x.equals(y);
    System.out.println(bp1.test("1","b"));

    BiPredicate<String,String> bp2 = String::equals;
    System.out.println(bp2.test("1","1"));
  }

   /*---------------------------|
  | 构造器引用：若λ体中的内容有方法已经实现了，则可以使用“方法引用”
  | 语法：
  |  ClassName::new
  | 注意：
  |   调用的构造器的参数列表要和函数接口的参数列表一致
   ----------------------------*/
   @Test
   public void testConstructorRef01() {
     Supplier<Employee> sup1 = ()->new Employee();

     //构造器引用
     //由于Supplier函数接口的定义是无参数，所以调用的构造器也是无参数的
     Supplier<Employee> sup2 = Employee::new;

     /**
      * 声明一个一个参数的构造函数，可以使用Function函数接口进行调用
      */
     Function<Integer ,Employee> fun1 = (x)->new Employee(x);
     Function<Integer ,Employee> fun2 = Employee::new;

   }


    /*---------------------------|
  | 数组引用：若λ体中的内容有方法已经实现了，则可以使用“方法引用”
  | 语法：
  |  Type::new
  | 观察下面例子，可以看得出和构造器引用的原理是一样的
   ----------------------------*/
  @Test
  public void testArrayRef01() {
    Function<Integer,String[]> fun1 = (x)->new String[x];
    String[] arr1 = fun1.apply(10);

    Function<Integer,String[]> fun2 = String[]::new;
    String[] apply = fun2.apply(20);
  }
}
