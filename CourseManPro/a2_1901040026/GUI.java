package a2_1901040026;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;

public class GUI extends WindowAdapter implements ActionListener {
private JFrame gui;
private ImageIcon imageIcon;
private JMenuBar menuBar;
private JMenu fileMenu, stuMenu, modMenu, enrMenu;
private JMenuItem exit, newStu, newMod, newEnr, listStu, listMod, iniReport, asReport;
private NewStudent newStudent;
private NewModule newModule;
private NewEnrolment newEnrolment;
private ListStudent listStudent;
private ListModule listModule;
private InitialReport initialReport;
private AssessmentReport assessmentReport;
    public GUI () {

    gui = new JFrame();
    gui.setTitle("Course Management Program");
    gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    gui.setSize(400,400);
    gui.getContentPane().setBackground(Color.green);

    imageIcon = new ImageIcon( "3.jpg");
    gui.setIconImage(imageIcon.getImage());

    menuBar = new JMenuBar();
    fileMenu = new JMenu("File");
    stuMenu = new JMenu("Student");
    modMenu = new JMenu("Module");
    enrMenu = new JMenu("Enrolment");

        menuBar.add(fileMenu);
        menuBar.add(stuMenu);
        menuBar.add(modMenu);
        menuBar.add(enrMenu);
        gui.setJMenuBar(menuBar);

    exit = new JMenuItem("Exit");
    newStu = new JMenuItem("New Student");
    listStu= new JMenuItem("List Student");
    newMod = new JMenuItem("New Module");
    listMod = new JMenuItem("List Module");
    newEnr = new JMenuItem("New Enrolment");
    iniReport = new JMenuItem("Initial Report");
    asReport = new JMenuItem("Assessment Report");

        fileMenu.add(exit);
        stuMenu.add(newStu);
        stuMenu.add(listStu);
        modMenu.add(newMod);
        modMenu.add(listMod);
        enrMenu.add(newEnr);
        enrMenu.add(iniReport);
        enrMenu.add(asReport);

        exit.addActionListener(this);
        newStu.addActionListener(this);
        newMod.addActionListener(this);
        newEnr.addActionListener(this);
        listStu.addActionListener(this);
        listMod.addActionListener(this);
        iniReport.addActionListener(this);
        asReport.addActionListener(this);

        gui.setVisible(true);
}
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("Exit")) {
            System.exit(0);
        }
        else if (cmd.equals("New Student")){
            if(newStudent == null )
                newStudent = new NewStudent();
            newStudent.display();
        } else if (cmd.equals("New Module")) {
            if(newModule == null)
           newModule = new NewModule();
            newModule.display();
        }else if (cmd.equals("New Enrolment")) {
            if(newEnrolment == null)
                newEnrolment = new NewEnrolment();
            newEnrolment.display();
        }else if (cmd.equals("List Student")) {
                try {
                    listStudent = new ListStudent();
                    listStudent.display();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
        }else if (cmd.equals("List Module")) {
            try {
                listModule = new ListModule();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            listModule.display();
        }else if (cmd.equals("Initial Report")) {
            try {
                initialReport = new InitialReport();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            initialReport.display();
        }else if (cmd.equals("Assessment Report")) {
            try {
                assessmentReport= new AssessmentReport();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            assessmentReport.display();
        }
    }

    public static void main(String[] args) {
     CourseManProg courseManProg = new CourseManProg();
     courseManProg.perform();
        GUI gui = new GUI();
    }
}
