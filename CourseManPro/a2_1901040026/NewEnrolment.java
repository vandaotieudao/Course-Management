package a2_1901040026;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class NewEnrolment implements ActionListener {
    JFrame frame;
    JPanel buttons, titlePanel;
    JLabel title;
    JButton ok, cancel;
    JComboBox students, modules;
    JTextField tf1, tf2;
    ArrayList<String> studentId = new ArrayList<>();
    ArrayList<String> moduleCode = new ArrayList<>();


    public NewEnrolment() {
        frame = new JFrame("Add new Enrolment");

        titlePanel = new JPanel();
        titlePanel.setBackground(Color.yellow);
        title = new JLabel();
        title.setText("Enter the details (please click cancel to refresh)");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 15f));
        titlePanel.add(title);
        frame.add(titlePanel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 2, 2));
        inputPanel.add(new JLabel("Student:"));
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:" + CourseManProg.SQL_DB);
            Statement st = conn.createStatement();
            Statement st2 = conn.createStatement();
            String qr = "SELECT * FROM Student";
            String qr2 = "SELECT * FROM Modules";
            ResultSet rs = st.executeQuery(qr);
            ResultSet rs2 = st2.executeQuery(qr2);
            String idName, codeName;

            while (rs2.next()) {
                codeName = rs2.getString(1)+"-"+rs2.getString(2);
                moduleCode.add(codeName);
            }
            while (rs.next()) {
                idName = rs.getString(1)+"-"+rs.getString(2);
                studentId.add(idName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        students = new JComboBox(studentId.toArray());
        inputPanel.add(students);

        inputPanel.add(new JLabel("Module: "));
        modules = new JComboBox(moduleCode.toArray());
        inputPanel.add(modules);

        inputPanel.add(new JLabel("Inter Mark: "));
        tf1 = new JTextField(19);
        inputPanel.add(tf1);

        inputPanel.add(new JLabel("Exam Mark: "));
        tf2 = new JTextField(19);
        inputPanel.add(tf2);

        frame.add(inputPanel);

        buttons = new JPanel();
        frame.add(buttons, BorderLayout.SOUTH);
        ok = new JButton("Save");
        ok.addActionListener(this);
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        buttons.add(ok);
        buttons.add(cancel);


        frame.pack();
        frame.setVisible(true);
    }

    public void display() {
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String click = e.getActionCommand();
        if (click.equals("Save")) {
            try {
                addEnrolment();
                JOptionPane.showMessageDialog(null, "Successfully Inserted");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (click.equals("Cancel")) {
            frame.dispose();
            NewEnrolment newEnrolment = new NewEnrolment();
        }
    }

    public String splitSpecific(String s){
        String[] splits = s.split("-");
          return splits[0];
    }
    public void addEnrolment() throws SQLException {
CourseManProg crm = new CourseManProg();
        Connection conn = DriverManager.getConnection("jdbc:sqlite:database.sqlite3");

        String s_id = students.getSelectedItem().toString();
        String code = modules.getSelectedItem().toString();
        String inter_mark = tf1.getText();
        Double inter = Double.parseDouble(inter_mark);
        String exa_mark = tf2.getText();
        Double exam = Double.parseDouble(exa_mark);

        String originalId = splitSpecific(s_id);
        String originalCode = splitSpecific(code);

        Student s =crm.getStuById(crm.getStudents(),originalId);
        Module m = crm.getModByCode(crm.getModules(), originalCode);

        EnrolmentManager e = new EnrolmentManager();
        Enrolment newE =  e.createEnrolment(s,m);
        e.addEnrolment(newE);
        e.setMarks(s,m,inter,exam);


        String sq = "INSERT INTO Enrolment(s_id, code, inter_mark, exam_mark, final_mark) VALUES (?,?,?,?,?)";
        PreparedStatement pr = conn.prepareStatement(sq);
        pr.setString(1, originalId );
        pr.setString(2,splitSpecific(code));
        pr.setDouble(3,inter);
        pr.setDouble(4, exam);
        pr.setObject(5, newE.getFinalGrade());
        pr.executeUpdate();
    }
}

