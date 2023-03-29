package a2_1901040026;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class AssessmentReport {  JFrame frame;
    JPanel titlePanel;
    JLabel text;

    public AssessmentReport() throws SQLException {

        frame = new JFrame("Assessment Report");

        titlePanel = new JPanel();
        titlePanel.setBackground(Color.yellow);
        text = new JLabel();
        text.setText("Assessment Report");
        text.setFont(text.getFont().deriveFont(Font.BOLD, 15f));
        titlePanel.add(text);
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.setSize(500, 300);
        JTable table = new JTable();

        JScrollPane sp = new JScrollPane(table);

        frame.add(sp);

        Connection conn = DriverManager.getConnection("jdbc:sqlite:" + CourseManProg.SQL_DB);
        Statement st = conn.createStatement();
        String qr = "SELECT * FROM Enrolment" ;
        ResultSet rs = st.executeQuery(qr);
        ResultSetMetaData rsmd = rs.getMetaData();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        String[] colName = {"ID", "Student ID", "Code", "Internal Mark", "Examination Mark", "Final Mark"};
        model.setColumnIdentifiers(colName);
        String e_id, s_id, exam_mark, code,inter_mark, final_mark;
        for (int i = 0; i < colName.length; i++) {
            while (rs.next()) {
                s_id = rs.getString(1);
                e_id = rs.getString(2);
                code= rs.getString(3);
                inter_mark = rs.getString(4);
                exam_mark = rs.getString(5);
                final_mark = rs.getString(6);
                String[] row = {e_id, s_id, code,inter_mark,exam_mark,final_mark};
                model.addRow(row);
            }
        }
        frame.setVisible(true);
    }

    public void display(){
        frame.setVisible(true);
    }
}
