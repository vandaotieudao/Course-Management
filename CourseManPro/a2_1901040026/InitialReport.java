package a2_1901040026;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class InitialReport {
    JFrame frame;
    JPanel titlePanel;
    JLabel text;

    public InitialReport() throws SQLException {

        frame = new JFrame("Initial Report");

        titlePanel = new JPanel();
        titlePanel.setBackground(Color.yellow);
        text = new JLabel();
        text.setText("Initial Report");
        text.setFont(text.getFont().deriveFont(Font.BOLD, 15f));
        titlePanel.add(text);
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.setSize(500, 300);
        JTable table = new JTable();

        JScrollPane sp = new JScrollPane(table);

        frame.add(sp);

        Connection conn = DriverManager.getConnection("jdbc:sqlite:" + CourseManProg.SQL_DB);
        Statement st = conn.createStatement();
        String qr = "SELECT Enrolment.e_id, Student.s_id, Student.s_name, Modules.code, Modules.m_name FROM Enrolment" +
                "   INNER JOIN Student" +
                "   ON Enrolment.s_id = Student.s_id" +
                "   INNER JOIN Modules" +
                "   ON Enrolment.code = Modules.code";
        ResultSet rs = st.executeQuery(qr);
        ResultSetMetaData rsmd = rs.getMetaData();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        String[] colName = {"ID", "Student ID", "Student Name", "Code", "Module"};
        model.setColumnIdentifiers(colName);
        String e_id, s_id, s_name, code, m_name;

        for (int i = 0; i < colName.length; i++) {
            while (rs.next()) {

                e_id = rs.getString(1);
                s_id = rs.getString(2);
                s_name= rs.getString(3);
                code = rs.getString(4);
                m_name= rs.getString(5);
                String[] row = {e_id,s_id, s_name, code,m_name};
                model.addRow(row);
            }
        }
            frame.setVisible(true);
        }

    public void display(){
        frame.setVisible(true);
    }

}
