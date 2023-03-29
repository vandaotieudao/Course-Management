package a2_1901040026;

public class Enrolment implements  Comparable<Enrolment>{
    private Student student;
    private Module module;
    private Double internalMark;
    private Double examinationMark;
    private FinalGrade finalGrade;

    public Enrolment() {
    }

    public Enrolment(Student student, Module module, double internalMark, double examinationMark) {
        this.student = student;
        this.module = module;
        this.internalMark = internalMark;
        this.examinationMark = examinationMark;
        this.finalGrade = calFinalGrade(this.internalMark, this.examinationMark);
    }
    public Enrolment(Student student, Module module) {
        this.student = student;
        this.module = module;
//        this.internalMark = internalMark;
//        this.examinationMark = examinationMark;
//        this.finalGrade = calFinalGrade(this.internalMark, this.examinationMark);
    }
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public double getInteralMark() {
        if (internalMark == null) {
            return 0;
        } else {
            return internalMark;
        }
    }

    public void setInteralMark(double internalMark) {
        this.internalMark = internalMark;
    }

    public double getExaminationMark() {
        if (examinationMark == null) {
            return 0;
        } else {
            return examinationMark;
        }
    }

    public void setExaminationMark(double examinationMark) {
        this.examinationMark = examinationMark;
    }

    public FinalGrade getFinalGrade() {
        if (finalGrade == null){
            return FinalGrade.F;
        }
        return finalGrade;
    }

    public void setFinalGrade(double i, double z) {
        this.finalGrade = calFinalGrade(i, z);
    }

    public FinalGrade calFinalGrade(double internalMark, double examinationMark) {
        FinalGrade score;

        double finalGrade = (0.4 * internalMark + 0.6 * examinationMark); // calculate the final mark

        if (finalGrade < 5) {
            score = FinalGrade.F;   // set final grade
        }else {
            if (finalGrade >= 5 && finalGrade < 7.5) {
                score = FinalGrade.P;   // set final grade
            } else {
                if (finalGrade >= 7.5 && finalGrade < 9) {
                    score = FinalGrade.G;   // set final grade
                } else {
                    score = FinalGrade.E;   // set final grade
                }
            }
        }

        return score;
    }

    @Override
    public String toString() {
        return "Enrolment{" +
                "student=" + student.toString() +
                ", module=" + module.toString() +
                ", interMark=" + internalMark +
                ", examMark=" + examinationMark +
                ", finalGra=" + finalGrade +
                '}';
    }

    @Override
    public int compareTo(Enrolment o) {     // comparable - to sort in descending order
        int compare = ((Enrolment) o).getStudent().getIntId();
        return this.getStudent().getIntId() - compare ;
    }
}
