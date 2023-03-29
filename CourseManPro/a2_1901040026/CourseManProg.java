package a2_1901040026;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseManProg {
    EnrolmentManager e = new EnrolmentManager();
    protected Connection conn;
    protected static final String DBMS = "sqlite";
    protected static final String SQL_DB = "database.sqlite3";
    public static ArrayList<Student> students = new ArrayList<>();

    public ArrayList<Student> getStudents() {
        return students;
    }
    protected static ArrayList<Module> modules = new ArrayList<>();

    public ArrayList<Module> getModules() {
        return modules;
    }


    public void connect(String db) throws SQLException{
        conn = DriverManager.getConnection("jdbc:" + DBMS + ":" + db);
    }
    public void populateStudentTable(ArrayList<Student> students)  {

        try {
            for (Student student : students) {
                String sq = "INSERT or IGNORE INTO Student(s_id, s_name, s_dob, address, email) VALUES (?,?,?,?,?)";
                PreparedStatement pr = conn.prepareStatement(sq);
                pr.setString(1, student.getId());
                pr.setString(2, student.getName());
                pr.setString(3, student.getDob());
                pr.setString(4, student.getAddress());
                pr.setString(5, student.getEmail());
                pr.executeUpdate();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public void populateModuleTable(ArrayList<Module> modules) throws SQLException {

        for (Module module: modules) {
            String sq = "INSERT or REPLACE INTO MODULES(code, m_name, semester, credits, department) VALUES (?,?,?,?,?)";
            PreparedStatement pr = conn.prepareStatement(sq);
            pr.setString(1, module.getCode());
            pr.setString(2,module.getName());
            pr.setInt(3,module.getSemester());
            pr.setInt(4, module.getCredit());
            pr.setString(5, module.getDepartment());
            pr.executeUpdate();
        }

    }
    public void populateEnrolTable(ArrayList<Enrolment> enrolments) throws SQLException {

        for (Enrolment enrolment : enrolments) {
            String sq = "INSERT or REPLACE INTO Enrolment(s_id, code, inter_mark, exam_mark, final_mark) VALUES (?,?,?,?,?)";
            PreparedStatement pr = conn.prepareStatement(sq);
            pr.setString(1, enrolment.getStudent().getId());
            pr.setString(2,enrolment.getModule().getCode());
            pr.setDouble(3,enrolment.getInteralMark());
            pr.setDouble(4, enrolment.getExaminationMark());
            pr.setObject(5, enrolment.getFinalGrade());
            pr.executeUpdate();
        }

    }
    public Student getStuById(ArrayList<Student> arrayList, String id){
        Student result = null;
        for (Student s: arrayList
             ) {
            if (s.getId().equals(id)){
                result =s;
            }
        }
        return result;
    }
    public Module getModByCode(ArrayList<Module> array,String code){
        Module result = null;
        for (Module m: array){
            if (m.getCode().equals(code)){
                result= m;
            }
        }
        return result;
    }

public void perform(){

    CompulsoryModule m1 = new CompulsoryModule("ATI", 1,3);
    CompulsoryModule m2 = new CompulsoryModule("DBS", 1,3);
    CompulsoryModule m3 = new CompulsoryModule("MLA", 1,3);
    ElectiveModule m4 = new ElectiveModule("CGR", 1,3, "MUL");
    ElectiveModule m5 = new ElectiveModule("JSD", 1,3, "FIT");
    modules.add(m1);
    modules.add(m2);
    modules.add(m3);
    modules.add(m4);
    modules.add(m5);

    Student s1 = new Student("Tran Ngoc Anh", "14/05/2001","Nam Dinh","190140026@s.hanu.edu.vn");
    Student s2 = new Student("Luong Hoang An", "15/08/2001","Cao Phuong","190140206@s.hanu.edu.vn");
    Student s3 = new Student("Tran Duy Khanh", "1/06/2001","Cho Gao","190140126@s.hanu.edu.vn");
    Student s4 = new Student("Bui Trung Hieu", "25/03/2001","Trinh Xuyen","190140020@s.hanu.edu.vn");
    Student s5 = new Student("Nguyen Van Khanh", "30/10/2001","Dac Luc","190140006@s.hanu.edu.vn");
    students.add(s1);
    students.add(s2);
    students.add(s3);
    students.add(s4);
    students.add(s5);



        Enrolment e1 = e.createEnrolment(s1, m1 );
        e.addEnrolment(e1);
        Enrolment e2 = e.createEnrolment(s2, m2);
        e.addEnrolment(e2);
        Enrolment e3 = e.createEnrolment(s3, m3 );
        e.addEnrolment(e3);
        Enrolment e4 = e.createEnrolment(s4, m4);
        e.addEnrolment(e4);
        Enrolment e5 = e.createEnrolment(s5, m5 );
        e.addEnrolment(e5);
        Enrolment e6 = e.createEnrolment(s1, m5 );
        e.addEnrolment(e6);
        Enrolment e7 = e.createEnrolment(s2, m4 );
        e.addEnrolment(e7);
        Enrolment e8 = e.createEnrolment(s3, m2 );
        e.addEnrolment(e8);
        Enrolment e9 = e.createEnrolment(s4, m3 );
        e.addEnrolment(e9);
        Enrolment e10 = e.createEnrolment(s5, m1 );
        e.addEnrolment(e10);

    e.setMarks(s1, m1, 5,8);
    e.setMarks(s2, m2, 6,8);
    e.setMarks(s3, m3, 7,6);
    e.setMarks(s4, m4, 8,9);
    e.setMarks(s5, m5, 9,7);
    e.setMarks(s1, m5 , 10,9);
    e.setMarks(s2, m4, 9,5);
    e.setMarks(s3, m2, 8,9);
    e.setMarks(s4, m3, 7,3);
    e.setMarks(s5, m1, 6,1);

    try {
        connect(SQL_DB);
        populateStudentTable(students);
        populateModuleTable(modules);
        populateEnrolTable(e.enrolments);
    } catch (SQLException a) {
        a.printStackTrace();
    }
}

}

