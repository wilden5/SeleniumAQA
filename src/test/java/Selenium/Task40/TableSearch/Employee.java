package Selenium.Task40.TableSearch;

public class Employee {

    private String name;
    private String position;
    private String office;

    public Employee(String name, String position, String office) {
        this.name = name;
        this.position = position;
        this.office = office;
    }

    @Override
    public String toString() {
        String print = String.format(
                "Name: '%s', Position: '%s', Office: '%s'",
                name,
                position,
                office
        );
        return print;
    }
}
