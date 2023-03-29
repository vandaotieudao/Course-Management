package a2_1901040026;

public class ElectiveModule extends Module {
    private String department;


    public ElectiveModule(String name, int semester, int credit, String department) {
        super(name, semester, credit);
        this.department = department;
    }
@Override
    public String getDepartment() {
        return department;
    }
@Override
    public void setDepartment(String department) {
        this.department = department;
    }
}
