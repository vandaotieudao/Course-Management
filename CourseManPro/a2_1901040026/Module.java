package a2_1901040026;
import java.util.concurrent.atomic.AtomicInteger;

public class Module {
    private static final AtomicInteger count = new AtomicInteger(0);
    private String code;
    private String name;
    private int semester;
    private int credit;
    private String department;

    public Module() {
    }

    public Module(String name, int semester, int credit) {
        int c1 = semester * 100;
        int c = count.incrementAndGet(); // Auto - increment
        code = "M" + (c1 + c);
        this.name = name;
        this.semester = semester;
        this.credit = credit;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }


    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {

    }


    @Override
    public String toString() {
        return "Module{" +
                " code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", semester=" + semester +
                ", credit=" + credit +
                '}';
    }

    public String report() {
        return String.format(" %5s | %12s | %5d | %5d | ", code, name, semester, credit);
    }


}