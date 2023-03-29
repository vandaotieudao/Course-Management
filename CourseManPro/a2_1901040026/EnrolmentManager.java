package a2_1901040026;

import java.util.ArrayList;
import java.util.Collections;

public class EnrolmentManager {
    public static ArrayList<Enrolment> enrolments = new ArrayList<>();

    public ArrayList<Enrolment> getEnrolments() {
        return enrolments;
    }

    // create a new Enrollment without the mark
    public Enrolment createEnrolment (Student student, Module module){
        Enrolment enrolment = new Enrolment(student,module);   // create new instance
        return enrolment;
    }

    // add to the list of enrolments
    public void addEnrolment (Enrolment enrolment){
        enrolments.add(enrolment);
    }
    // get the enrolment
    public Enrolment getEnrolment(Student student, Module module){
        Enrolment result = null;
        for (Enrolment e:
                enrolments) {
            if (e.getStudent() == student && e.getModule() == module){
                result = e;
            }
        }
        return result;
    }
    // change the mark of student in module
    public void setMarks(Student student, Module module, double interalMark, double examinationMark){
        for (Enrolment e:
                enrolments) {
            if (e.getStudent() == student && e.getModule() == module){  // check if same
                e.setInteralMark(interalMark);      // change the internal mark
                e.setExaminationMark(examinationMark);  // change the examination mark
                e.setFinalGrade(interalMark, examinationMark); // change the final Grade
            }
        }
    }
    //make initial report
    public String report(){
        String title="                                                           ENROLLMENT REPORT \n";
        String table= "---------------------------------------------------------------------------------------------------------------------------------\n";
        String name = String.format("| %5s | %20s | %10s | %15s | %22s | %6s | %12s |%5s| %5s|",
                "ID", "Name", " DoB", " Address", "Email","Code", "Subject", "Semeste","Credit" );
        for (Enrolment e:
                enrolments) {
            String enString1 =  e.getStudent().report()+  e.getModule().report()+"\n";
            table = table+enString1;
        }
        return title+"\n"+name+"\n"+table+"\n";

    }
    // make a report with mark
    public  String reportAssessment() {
        String title="                                                           ENROLLMENT REPORT \n";
        String table= "--------------------------------------------------------------------------------------------------------------------------------------------------------\n";
        String name = String.format("| %5s | %20s | %10s | %15s | %22s | %6s | %12s |%5s| %5s| %4s | %5s |%5s |", "ID", "Name", " DoB", " Address", "Email","Code", "Subject", "Semeste","Credit","Inter","Exam","Final" );
        for (Enrolment e:
                enrolments) {
            String enString1 =  e.getStudent().report()+  e.getModule().report();
            String enString2 = String.format("%5.2f | %5.2f | %4s |", e.getInteralMark(), e.getExaminationMark(), e.getFinalGrade() );
            String enString = enString1+enString2+"\n";
            table = table+enString;
        }
        return title+"\n"+name+"\n"+table+"\n";
    }
    //sort with collection Comparable
    public  void sort() {
        Collections.sort(enrolments, Collections.reverseOrder());
    }

}
