package a2_1901040026;

public class CompulsoryModule extends Module {
    public CompulsoryModule(String name, int semester, int credit) {
        super(name, semester, credit);

    }

    @Override
    public String getDepartment() {
        return "Compulsory";
    }

    @Override
    public void setDepartment(String department) {

    }

}
