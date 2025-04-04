package com.explearning;


import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Vendor extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtTxtvendorid;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTable table;
    Connection con;
    PreparedStatement pst;
    DefaultTableModel df;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Vendor frame = new Vendor();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Vendor() {
        setTitle("VENDOR");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1057, 539);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.YELLOW);
        panel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
        panel.setBounds(10, 10, 522, 368);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("VendorId");
        lblNewLabel.setBounds(21, 89, 120, 24);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(lblNewLabel);

        txtTxtvendorid = new JTextField();
        txtTxtvendorid.setBounds(151, 89, 293, 24);
        panel.add(txtTxtvendorid);
        txtTxtvendorid.setColumns(10);

        JLabel lblVendorname = new JLabel("VendorName");
        lblVendorname.setHorizontalAlignment(SwingConstants.LEFT);
        lblVendorname.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblVendorname.setBounds(21, 123, 120, 24);
        panel.add(lblVendorname);

        JLabel lblNewLabel_1_1 = new JLabel("Phone");
        lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_1_1.setBounds(21, 157, 120, 24);
        panel.add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_1_1 = new JLabel("Address");
        lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_1_1_1.setBounds(21, 191, 120, 24);
        panel.add(lblNewLabel_1_1_1);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(151, 123, 293, 24);
        panel.add(textField_1);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(151, 157, 293, 24);
        panel.add(textField_2);

        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(151, 191, 293, 24);
        panel.add(textField_3);

        JButton btnAdd = new JButton("ADD");
        btnAdd.setBackground(Color.CYAN);
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String vendorId = txtTxtvendorid.getText();
                    String name = textField_1.getText();
                    String phone = textField_2.getText();
                    String address = textField_3.getText();

                    pst = con.prepareStatement("INSERT INTO VENDOR(VendorId, Name, Phone, Address) VALUES (?, ?, ?, ?)");
                    pst.setString(1, vendorId);
                    pst.setString(2, name);
                    pst.setString(3, phone);
                    pst.setString(4, address);
                    pst.executeUpdate();

                    // Update table
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.addRow(new Object[]{vendorId, name, phone, address});

                    JOptionPane.showMessageDialog(null, "Vendor Added");

                    clearFields();
                } catch (SQLException ex) {
                    System.out.println(ex);
                } finally {
                    closeConnection(pst, null);
                }
            }
        });
        btnAdd.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnAdd.setBounds(10, 408, 111, 33);
        contentPane.add(btnAdd);

        JButton btnEdit = new JButton("EDIT");
        btnEdit.setBackground(Color.CYAN);
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    String vendorId = txtTxtvendorid.getText();
                    String name = textField_1.getText();
                    String phone = textField_2.getText();
                    String address = textField_3.getText();

                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.setValueAt(name, row, 1);
                    model.setValueAt(phone, row, 2);
                    model.setValueAt(address, row, 3);

                    try {
                        pst = con.prepareStatement("UPDATE VENDOR SET Name=?, Phone=?, Address=? WHERE VendorId=?");
                        pst.setString(1, name);
                        pst.setString(2, phone);
                        pst.setString(3, address);
                        pst.setString(4, vendorId);
                        pst.executeUpdate();

                        clearFields();
                    } catch (SQLException ex) {
                        System.out.println(ex);
                    } finally {
                        closeConnection(pst, null);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Select a row to edit");
                }
            }
        });
        btnEdit.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnEdit.setBounds(127, 408, 111, 33);
        contentPane.add(btnEdit);

        JButton btnDelete = new JButton("DELETE");
        btnDelete.setBackground(Color.CYAN);
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    String vendorId = model.getValueAt(row, 0).toString();
                    model.removeRow(row);

                    try {
                        pst = con.prepareStatement("DELETE FROM VENDOR WHERE VendorId=?");
                        pst.setString(1, vendorId);
                        pst.executeUpdate();
                    } catch (SQLException ex) {
                        System.out.println(ex);
                    } finally {
                        closeConnection(pst, null);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Select a row to delete");
                }
            }
        });
        btnDelete.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnDelete.setBounds(248, 408, 111, 33);
        contentPane.add(btnDelete);

        JButton btnCancel = new JButton("CANCEL");
        btnCancel.setBackground(Color.CYAN);
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
        btnCancel.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnCancel.setBounds(369, 408, 128, 33);
        contentPane.add(btnCancel);

        JButton btnClose = new JButton("CLOSE");
        btnClose.setBackground(Color.CYAN);
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current frame
            }
        });
        btnClose.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnClose.setBounds(504, 408, 111, 33);
        contentPane.add(btnClose);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(594, 10, 439, 391);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
        table.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"VendorId", "Name", "Phone", "Address"}
        ));
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setBackground(Color.GREEN);
        lblNewLabel_1.setOpaque(true);
        lblNewLabel_1.setBounds(-20, 0, 1103, 534);
        contentPane.add(lblNewLabel_1);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                txtTxtvendorid.setText(table.getValueAt(selectedRow, 0).toString());
                textField_1.setText(table.getValueAt(selectedRow, 1).toString());
                textField_2.setText(table.getValueAt(selectedRow, 2).toString());
                textField_3.setText(table.getValueAt(selectedRow, 3).toString());
            }
        });

        connect();
        loadData();
    }

    public void connect() {
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/exp_learning", "root", "Abinash@Mysql3");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void loadData() {
        try {
            pst = con.prepareStatement("SELECT * FROM VENDOR");
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            while (rs.next()) {
                String vendorId = rs.getString("VendorId");
                String name = rs.getString("Name");
                String phone = rs.getString("Phone");
                String address = rs.getString("Address");
                model.addRow(new Object[]{vendorId, name, phone, address});
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            closeConnection(pst, null);
        }
    }

    private void clearFields() {
        txtTxtvendorid.setText("");
        textField_1.setText("");
        textField_2.setText("");
        textField_3.setText("");
    }

    private void closeConnection(PreparedStatement pst, ResultSet rs) {
        try {
            if (pst != null) pst.close();
            if (rs != null) rs.close();
            // Closing connection is usually not done here as it is used across the class.
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
