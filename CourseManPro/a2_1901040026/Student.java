package a2_1901040026;

import java.util.concurrent.atomic.AtomicInteger;

public class Student  {

    private static final AtomicInteger count = new AtomicInteger(2022);
    private String id;
    private String name;
    private String dob;
    private String address;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }
    //    get the integer number part in the ID
    public int getIntId() {
        String i = id.substring(1);
        int intId = Integer.parseInt(i);
        return intId;
    }


    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {return id;}



    public Student() {
    }

    public Student(String name, String dob, String address, String email) throws IllegalArgumentException {
        if (!validateEmail(email)){
            throw new IllegalArgumentException("Invalid Email");
        }
        int c = count.incrementAndGet(); // Auto - increment
        id = "S" + c;
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.email = email;

    }

    @Override
    public String toString() {
        return "Student{" +
                " id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", dob='" + dob + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
    public String report(){
        return String.format("| %5s | %20s | %10s | %15s | %20s| ", id, name, dob, address,email);
    }

    private boolean validateEmail(String email) {
        if (email == null || email.length() > 200) {
            return false;
        } else {
            final String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            return email.matches(emailPattern);
        }
    }
}
