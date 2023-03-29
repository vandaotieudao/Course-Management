package a2_1901040026;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NewStudent implements ActionListener {
    JFrame frame ;
    JPanel buttons, titlePanel;
    JLabel title ;
    JButton ok, cancel;
    JTextField tf1, tf2, tf3, tf4;
    public NewStudent(){
        frame = new JFrame("Add new Student");

        titlePanel = new JPanel();
        titlePanel.setBackground(Color.yellow);
        title = new JLabel();
        title.setText("Enter the details");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 15f));
        titlePanel.add(title);
        frame.add(titlePanel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 2, 2));
        inputPanel.add( new JLabel("Name:"));
        tf1 = new JTextField(19);
        inputPanel.add(tf1);

        inputPanel.add( new JLabel("DoB: "));
        tf2 = new JTextField(19);
        inputPanel.add(tf2);

        inputPanel.add( new JLabel("Address: "));
        tf3 = new JTextField(19);
        inputPanel.add(tf3);

        inputPanel.add( new JLabel("Email: "));
        tf4 = new JTextField(19);
        inputPanel.add(tf4);

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
    public void display(){
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String click = e.getActionCommand();
        if(click.equals("Save")){
            try {
                addStudent();
                JOptionPane.showMessageDialog(null, "Successfully Inserted");

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else if (click.equals("Cancel")){
            frame.dispose();
            NewStudent newStudent = new NewStudent();
        }
    }
    public void addStudent() throws SQLException {
        CourseManProg crm = new CourseManProg();
        Connection conn = DriverManager.getConnection("jdbc:sqlite:database.sqlite3");
        String name = tf1.getText();
        String dob = tf2.getText();
        String address = tf3.getText();
        String email = tf4.getText();
        Student student = new Student(name,dob,address,email);
        crm.getStudents().add(student);
        String sq = "INSERT INTO Student(s_id, s_name, s_dob, address, email) VALUES (?,?,?,?,?)";
        PreparedStatement pr = conn.prepareStatement(sq);
        pr.setString(1, student.getId());
        pr.setString(2,student.getName());
        pr.setString(3,student.getDob());
        pr.setString(4, student.getAddress());
        pr.setString(5, student.getEmail());
        pr.executeUpdate();
    }

}
