package a2_1901040026;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NewModule implements ActionListener {
    JFrame frame ;
    JPanel buttons, titlePanel;
    JLabel title, depart ;
    JButton ok, cancel;
    JTextField tf1, tf2, tf3, tf4;
    JComboBox modules;
    Module newModule;
    boolean f = false;
    public NewModule(){
        frame = new JFrame("Add new Module");

        titlePanel = new JPanel();
        titlePanel.setBackground(Color.yellow);
        title = new JLabel();
        title.setText("Enter the details");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 15f));
        titlePanel.add(title);
        frame.add(titlePanel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 2, 2));
        inputPanel.add( new JLabel("Name:"));
        tf1 = new JTextField(19);
        inputPanel.add(tf1);

        inputPanel.add( new JLabel("Semester: "));
        tf2 = new JTextField(19);
        inputPanel.add(tf2);

        inputPanel.add( new JLabel("Credit: "));
        tf3 = new JTextField(19);
        inputPanel.add(tf3);

        String[] moduleTypes = {"Compulsory Module","Elective Module"};
        inputPanel.add( new JLabel("Module Type: "));
        modules = new JComboBox(moduleTypes);
        modules.setSelectedIndex(0);
        modules.setEditable(true);

        depart = new JLabel("Department: ");
        tf4 = new JTextField(19);

        modules.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                JComboBox comboBox = (JComboBox) e.getSource();
                Object selected = comboBox.getSelectedItem();
                if (selected.toString().equals("Elective Module")) {
                    {
                        inputPanel.add(depart);
                        inputPanel.add(tf4);
                    }
                }else{
                    inputPanel.remove(depart);
                    inputPanel.remove(tf4);

                }
                display();
            }
        });
        inputPanel.add(modules);
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
                addModule();
                JOptionPane.showMessageDialog(null,"Successfully Inserted");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else if (click.equals("Cancel")){
            frame.dispose();
            NewModule newStudent = new NewModule();
        }
    }
    public void addModule() throws SQLException {
        CourseManProg crm = new CourseManProg();
            Connection conn = DriverManager.getConnection("jdbc:sqlite:database.sqlite3");
            String name = tf1.getText();
            String semester = tf2.getText();
        int semester1 = Integer.parseInt(semester);
            String credit = tf3.getText();
        int credit1 = Integer.parseInt(credit);
            String type = modules.getSelectedItem().toString();
            String department = tf4.getText();

        if(type.equalsIgnoreCase("Compulsory Module")) {
            newModule = new CompulsoryModule(name, semester1, credit1);
        }else  if(type.equalsIgnoreCase("Elective Module")) {
            newModule = new ElectiveModule(name, semester1, credit1, department);
        }
        crm.getModules().add(newModule);
            String sq = "INSERT INTO Modules(code,m_name,semester,credits,department) VALUES (?,?,?,?,?)";
            PreparedStatement pr = conn.prepareStatement(sq);
            pr.setString(1, newModule.getCode());
            pr.setString(2,newModule.getName());
            pr.setInt(3,newModule.getSemester());
            pr.setInt(4, newModule.getCredit());
            pr.setString(5, newModule.getDepartment());
            pr.executeUpdate();

        }
    }


