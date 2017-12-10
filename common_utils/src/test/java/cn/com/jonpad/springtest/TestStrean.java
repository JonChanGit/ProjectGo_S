package cn.com.jonpad.springtest;

import cn.com.jonpad.util.JsonTool;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream的三个步骤
 *
 * 1. 创建Stream
 *
 * 2. 中间操作
 *
 * 3. 终止操作
 */
public class TestStrean {
  /*---------------------------------
  |创建Stream
   ---------------------------------*/
  @Test
  public void testStrean01(){
    //1.可以通过Collection集合提供的stream（） 或parallelStream（）
    List<String> list = new ArrayList<>();
    Stream<String> stream = list.stream();

    //2. 通过Arrays中的静态方法获取流
    Employee[] emps = new Employee[10];
    Stream<Employee> stream1 = Arrays.stream(emps);

    //3. 通过Stream类中的静态方法of（）获取
    Stream<String> stream2 = Stream.of("1", "2", "c");

    //4.创建无限流
    //迭代
    Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 2);
    //limit->中间操作 | forEach->终止操作
    stream3.limit(10).forEach(System.out::println);
    System.out.println("===============================");
    //生成
    Stream.generate(()->Math.random())
      .limit(10)
      .forEach(System.out::println);
  }


  /*---------------------------------
  |Stream 筛选与切片
  |
  |多个中间操作可以链接层一个流水线，除非流水线上出发了终止操作，后者中间操作不会执行任何处理！
  |而在终止操作时一次性处理全部，称为“惰性求值”
   ---------------------------------*/

  /**
   * 1.filter -接收Lambda，从流中排除某些元素
   */
  @Test
  public void testStrean02_01(){
    list.stream()
      .filter(e->e.getAge()>2)
      //内部迭代，由StreamAPI完成
      .forEach(System.out::println);
  }
  /**
   * 2.limit-截断流，使其元素不超过给定的数量
   */
  @Test
  public void testStrean02_02(){
    list.stream()
      .filter(e->{
        System.out.println("短路,单找到需要个数的数据后，后续操作全部取消");
        return e.getAge()>2;
      })
      .limit(1)
      .forEach(System.out::println);
  }
  /**
   * 3.skip（n）-跳过元素，返回一个扔掉了前n个元素的流，
   * 若流中的元素不足n个，则返回空流，与limit互补
   */
  @Test
  public void testStrean02_03(){
    list.stream()
      .filter(e->e.getAge()>2)
      .skip(1)
      .forEach(System.out::println);
  }
  /**
   * 4.distinct-筛选，通过流生成元素的hashCode()和equals()去除重复元素。
   */
  @Test
  public void testStrean02_04(){
    list.stream()
      .filter(e->e.getAge()>2)
      //你可能需要重写hashCode与和equals方法
      .distinct()
      .forEach(System.out::println);
  }

   /*---------------------------------
  |Stream 映射
  |
  |map -> 接收Lambda，讲原始股转换成其他形式或提取信息。
  |       接受一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
  |
  |flatMap -> 接收一个函数作为参数，将流中的每个值都转换成另外一个Stream，然后把室友Stream链接成一个流
   ---------------------------------*/

  @Test
  public void testStrean03_01(){
    List<String> list = Arrays.asList("aa","bb","cc","dd","ee");
    list.stream().map(str->str.toUpperCase())
      .forEach(System.out::println);
    //------------------------
    this.list.stream().map(Employee::getName)
      .forEach(System.out::println);
  }

  /**
   * 把字符串转换成字符Stream
   * @param str
   * @return
   */
  public static Stream<Character> filterCharater(String str){
    List<Character> list = new ArrayList<>();
    for (Character c : str.toCharArray()) {
      list.add(c);
    }
    return list.stream();
  }

  @Test
  public void testStrean03_02(){
    List<String> list = Arrays.asList("aa","bb","cc","dd","ee");
    //map 返回是一个流，filterCharater返回的返回是Stream<Character>，因此最终返回如下结果
    //{{a,a},{b,b},{c,c},{d,d}}
    Stream<Stream<Character>> streamStream = list.stream().map(TestStrean::filterCharater);
    //返回结果两层嵌套流，需要双层循环读取
    streamStream.forEach(sm->{
      sm.forEach(System.out::println);
    });
  }

  /**
   * flatMap，详细说明：
   * https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#flatMap-java.util.function.Function-
   */
  @Test
  public void testStrean03_03(){
    List<String> list = Arrays.asList("aa","bb","cc","dd","ee");
    //flatMap将返回的每个嵌套的流拆开整合为只有一层的流
    //{ a,a,b,b,c,c,d,d}
    Stream<Character> characterStream = list.stream().flatMap(TestStrean::filterCharater);
    characterStream.forEach(System.out::println);
  }


  /*---------------------------------
  |Stream 排序
  |
  |sorted -> 自然排序（Comparable）
  |
  |sorted(Comparator) -> 定制排序（Comparator）
   ---------------------------------*/
    @Test
    public void testStrean04_01(){
      List<String> list = Arrays.asList("aa","bb","cc","dd","ee");
      list.stream()
        .sorted()
        .forEach(System.out::println);

      this.list.stream().sorted((e1,e2)->{
        if(e1.getAge() == e2.getAge()){
          return e1.getName().compareTo(e2.getName());
        }else {
          return Integer.compare(e1.getAge(),e2.getAge());
        }
      }).forEach(System.out::println);
    }



  List<Employee> list = Arrays.asList(
    new Employee("a", 11,1234.1, Employee.Status.BUSY),
    new Employee("c", 18,2394.2, Employee.Status.FREE),
    new Employee("a", 22,49832.3, Employee.Status.VOCATION),
    new Employee("a", 25,9384.2, Employee.Status.BUSY),
    new Employee("d", 40,9472, Employee.Status.FREE)
  );

  /*---------------------------------
  |Stream 查找与匹配
  |
  |boolean allMatch -> 检查是否匹配所有元素
  |
  |boolean anyMatch -> 检查是否匹配至少一个元素
  |
  |boolean noneMatch -> 检查是否没有匹配任意一个元素
  |
  |Optional<T> findFirst -> 查找第一个
  |
  |Optional<T> findAny -> 查找任意元素
  |
  |long count -> 获取流中的个数
  |
  |Optional<T> max -> 流中的最大值
  |
  |Optional<T> min -> 流中的最小值
   ---------------------------------*/
  @Test
  public void testStrean05_01(){
    boolean b = list.stream().anyMatch(e -> e.getStatu().equals(Employee.Status.VOCATION));
    System.out.println(b);

  }
  @Test
  public void testStrean05_02(){
    Optional<Employee> first = list.stream().sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
      .findFirst();
    System.out.println(first.get());

    Optional<Employee> any1 = list.stream().//串行流
      filter(e -> e.getStatu().equals(Employee.Status.FREE))
      .findAny();
    System.out.println(any1.get());

    Optional<Employee> any2 = list.parallelStream().//并行流（多线程），使得获取到的结果更加随机
      filter(e -> e.getStatu().equals(Employee.Status.FREE))
      .findAny();
    System.out.println(any2.get());
  }

  @Test
  public void testStrean05_03(){
    long count = list.stream().count();

    Optional<Employee> max = list.stream()
      .max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));

    //获取Employee对象中salary的最小值是多少
    //先用Map遍历提取出所有的salary
    //然后在调用min接口提取最小值
    Optional<Double> min = list.stream().map(Employee::getSalary)
      .min(Double::compare);

  }


  /*---------------------------------
  |Stream 归约（整合）
  | 将流中的元素反复结合起来，得到最终的一个值。
  |T reduce (T identity,BinaryOperator)
  |Optional<T> reduce (BinaryOperator)
  |
   ---------------------------------*/
  @Test
  public void testStrean06(){
    List<Integer> lis = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
    // 出处λ表达式参数x代表前一个值，初始值为identity指定的值0，y表示当前累加的值，
    // 最终做连续加法得到最终值
    Integer reduce1 = lis.stream().reduce(0, (x, y) -> x + y);
    System.out.println(reduce1);

    //获取Employee对象中salary的总和
    Optional<Double> reduce2 = list.stream().map(Employee::getSalary)
      .reduce(Double::sum);
    System.out.println(reduce2.get());
  }

  /*---------------------------------
  |Stream 收集
  |collect(collector) 将流转化为其他形式。
  | 接收一个collector收集器接口的实现（λ）.
  | Collectors工具类中提供了大量的静态方法可以方便的创建常用的收集器,
  |  例如有以下方法：
  |  toSet()
  |  toMap(Function<? super T,? extends K> keyMapper, Function<? super T,? extends U> valueMapper)
  |  toList()
  |  toCollection(Supplier<C> collectionFactory)
  |  counting()    返回计数输入元素数的Collector类型的接受T元素
  |  averagingDouble(***) averagingInt(***) averagingLong(***) 算术平均值
  |  summingInt(***) ... 总和
  |  maxBy(Comparator<? super T> comparator)
   ---------------------------------*/
  @Test
  public void testStrean07(){
    List<String> stringList = list.stream()
      .map(Employee::getName)
      .collect(Collectors.toList());
    stringList.forEach(System.out::println);


    Double avg = list.stream()
      .collect(Collectors.averagingDouble(Employee::getSalary));


    Optional<Employee> maxVal = list.stream()
      .collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));

    //分组
    Map<Employee.Status, List<Employee>> map = list.stream()
      .collect(Collectors.groupingBy(Employee::getStatu));
    System.out.println(JsonTool.toFastjson(map));

    //多级分组
    Map<Employee.Status, Map<String, List<Employee>>> collect = list.stream().collect(Collectors.groupingBy(Employee::getStatu,
      Collectors.groupingBy(e -> {
        if (((Employee) e).getAge() < 16) {
          return "少年";
        } else {
          return "老年";
        }
      })
    ));
    System.out.println(JsonTool.toFastjson(collect));

    //分区
    Map<Boolean, List<Employee>> collectPart = list.stream()
      .collect(Collectors.partitioningBy(e -> e.getSalary() > 4000));
    System.out.println(JsonTool.toFastjson(collectPart));

    //摘要统计信息
    DoubleSummaryStatistics collectSummarizing = list.stream()
      .collect(Collectors.summarizingDouble(Employee::getSalary));
    System.out.println(JsonTool.toFastjson(collectSummarizing));

    //合并
    String collectJoin = list.stream()
      .map(e -> e.getName())
      .collect(Collectors.joining());
    System.out.println(JsonTool.toFastjson(collectJoin));

  }

  /*---------------------------------
  |Stream 练习
  | 给定应该数字数组，返回一个由每个数的平方构成的列表
  | 例如给定[1,2,3,4,5]，返回[1,4,9,16,25]
   ---------------------------------*/
  @Test
  public void testStrean08_01(){
    Integer[] nums = new Integer[]{1,2,3,4,5};
    List<Integer> collectNums = Arrays.stream(nums).map(i -> i * i).collect(Collectors.toList());
    System.out.println(JsonTool.toFastjson(collectNums));
  }

  /*---------------------------------
  |Stream 练习
  | 用map和reduce方法数一数流中有多少个Employee
   ---------------------------------*/
  @Test
  public void testStrean08_02(){
    Integer sum1 = list.stream().map(e -> 1).reduce(0, Integer::sum);

    long count = list.stream().count();

  }

}
