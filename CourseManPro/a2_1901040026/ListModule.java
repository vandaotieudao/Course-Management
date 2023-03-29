package a2_1901040026;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ListModule implements ActionListener {
    JFrame frame;
    JPanel titlePanel;
    JLabel text;
    public ListModule() throws SQLException{

        frame = new JFrame("Module List");

        titlePanel = new JPanel();
        titlePanel.setBackground(Color.yellow);
        text = new JLabel();
        text.setText("The List of Modules");
        text.setFont(text.getFont().deriveFont(Font.BOLD, 15f));
        titlePanel.add(text);
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.setSize(500,300);
        JTable table = new JTable();

        JScrollPane sp = new JScrollPane(table);

        frame.add(sp);

        Connection conn = DriverManager.getConnection("jdbc:sqlite:" + CourseManProg.SQL_DB );
        Statement st = conn.createStatement();
        String qr = "SELECT * FROM Modules";
        ResultSet rs = st.executeQuery(qr);
        ResultSetMetaData rsmd = rs.getMetaData();
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        int col = rsmd.getColumnCount();

        String[] colName = new String[col];
        for (int i = 0; i < col; i++) {
            colName[i] = rsmd.getColumnName(i + 1);
            model.setColumnIdentifiers(colName);
            String s_id, s_name, dob, address, email;
            while (rs.next()) {

                s_id = rs.getString(1);
                s_name = rs.getString(2);
                dob = rs.getString(3);
                address = rs.getString(4);
                email = rs.getString(5);
                String[] row = {s_id, s_name, dob, address, email};
                model.addRow(row);
            }

            frame.setVisible(true);
        }

    }
    public void display(){
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
