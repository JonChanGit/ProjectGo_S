package cn.com.jonpad.springtest;

public class Employee {
  String name;
  int age;
  double salary;
  Status statu;
  public enum Status{
    FREE,BUSY,VOCATION
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public double getSalary() {
    return salary;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }

  public Status getStatu() {
    return statu;
  }

  public void setStatu(Status statu) {
    this.statu = statu;
  }

  public Employee() {
  }

  public Employee(int age) {
    this.age = age;
  }

  public Employee(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public Employee(String name, int age, double salary, Status statu) {
    this.name = name;
    this.age = age;
    this.salary = salary;
    this.statu = statu;
  }

  @Override
  public String toString() {
    return "Employee{" +
      "name='" + name + '\'' +
      ", age=" + age +
      ", salary=" + salary +
      ", statu=" + statu +
      '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Employee employee = (Employee) o;

    if (age != employee.age) return false;
    return name.equals(employee.name);
  }

  @Override
  public int hashCode() {
    int result = name.hashCode();
    result = 31 * result + age;
    return result;
  }
}
