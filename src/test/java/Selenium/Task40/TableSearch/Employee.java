package Selenium.Task40.TableSearch;

public class Employee {

    private String name;
    private String position;
    private String office;
    private int age;
    private int salary;

    public Employee(String name, String position, String office, int age, int salary) {
        this.name = name;
        this.position = position;
        this.office = office;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public String toString() {
        String print = String.format(
                "Name: '%s', Position: '%s', Office: '%s', Age: '%s', Salary: '%s'",
                name,
                position,
                office,
                age,
                salary
        );
        return print;
    }
}
