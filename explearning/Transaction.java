package com.explearning;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.awt.Color;

public class Transaction extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtOrderId;
    private JTextField txtAmount;
    private JTextField txtPaymentType;
    private Connection connection;
    private DefaultTableModel model;
    private JTable table;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Transaction frame = new Transaction();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Transaction() {
        setTitle("TRANSACTION");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1057, 539);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        try {
            // Establish MySQL connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/exp_learning", "root", "Abinash@Mysql3");
        } catch (Exception e) {
            e.printStackTrace();
        }

        JPanel panel = new JPanel();
        panel.setBackground(Color.GREEN);
        panel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
        panel.setBounds(10, 10, 522, 368);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblOrderId = new JLabel("Order ID");
        lblOrderId.setBounds(21, 35, 120, 24);
        lblOrderId.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(lblOrderId);

        txtOrderId = new JTextField();
        txtOrderId.setBounds(151, 35, 293, 24);
        panel.add(txtOrderId);

        JLabel lblAmount = new JLabel("Amount");
        lblAmount.setBounds(21, 73, 120, 24);
        lblAmount.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(lblAmount);

        JLabel lblPaymentType = new JLabel("Payment Type");
        lblPaymentType.setBounds(21, 107, 120, 24);
        lblPaymentType.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(lblPaymentType);

        txtAmount = new JTextField();
        txtAmount.setBounds(151, 73, 293, 24);
        panel.add(txtAmount);

        txtPaymentType = new JTextField();
        txtPaymentType.setBounds(151, 107, 293, 24);
        panel.add(txtPaymentType);

        JButton btnCalculate = new JButton("CALCULATE AMOUNT");
        btnCalculate.setBounds(10, 408, 248, 33);
        btnCalculate.setFont(new Font("Tahoma", Font.BOLD, 20));
        contentPane.add(btnCalculate);
        btnCalculate.addActionListener(e -> calculateAmount());

        JButton btnPay = new JButton("PAY");
        btnPay.setBounds(264, 408, 111, 33);
        btnPay.setFont(new Font("Tahoma", Font.BOLD, 20));
        contentPane.add(btnPay);
        btnPay.addActionListener(e -> payAction());

        JButton btnDeleteHistory = new JButton("DELETE HISTORY");
        btnDeleteHistory.setBounds(522, 408, 214, 33);
        btnDeleteHistory.setFont(new Font("Tahoma", Font.BOLD, 20));
        contentPane.add(btnDeleteHistory);
        btnDeleteHistory.addActionListener(e -> deleteHistoryAction());

        JButton btnCancel = new JButton("CANCEL");
        btnCancel.setBounds(385, 408, 127, 33);
        btnCancel.setFont(new Font("Tahoma", Font.BOLD, 20));
        contentPane.add(btnCancel);
        btnCancel.addActionListener(e -> clearFields());

        JButton btnClose = new JButton("CLOSE");
        btnClose.setBounds(743, 408, 111, 33);
        btnClose.setFont(new Font("Tahoma", Font.BOLD, 20));
        contentPane.add(btnClose);
        btnClose.addActionListener(e -> dispose());

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(542, 10, 475, 368);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
        model = new DefaultTableModel(new Object[][]{}, new String[]{"TransactionId", "OrderId", "Type", "Amount"});
        table.setModel(model);

        fetchDataAndPopulateTable();
    }

    private void fetchDataAndPopulateTable() {
        try {
            String query = "SELECT TransactionID, OrderID, Type, Amount FROM Transaction";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            model.setRowCount(0);

            while (resultSet.next()) {
                model.addRow(new Object[]{
                        resultSet.getInt("TransactionID"),
                        resultSet.getInt("OrderID"),
                        resultSet.getString("Type"),
                        resultSet.getDouble("Amount")
                });
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void calculateAmount() {
        try {
            int orderId = Integer.parseInt(txtOrderId.getText());
            String query = "SELECT p.RetailPrice, o.Quantity FROM Product p " +
                    "INNER JOIN Orders o ON p.ProductID = o.ProductID " +
                    "WHERE o.OrderID=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                double amount = resultSet.getDouble("RetailPrice") * resultSet.getInt("Quantity");
                txtAmount.setText(String.valueOf(amount));
                txtPaymentType.requestFocus();
            } else {
                JOptionPane.showMessageDialog(this, "Order ID not found.");
            }

            resultSet.close();
            statement.close();
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void payAction() {
        try {
            int orderId = Integer.parseInt(txtOrderId.getText());
            double amount = Double.parseDouble(txtAmount.getText());
            String paymentType = txtPaymentType.getText();

            // Check order status
            String checkStatusQuery = "SELECT Status FROM Orders WHERE OrderID = ?";
            PreparedStatement checkStatusStatement = connection.prepareStatement(checkStatusQuery);
            checkStatusStatement.setInt(1, orderId);
            ResultSet statusResultSet = checkStatusStatement.executeQuery();

            if (statusResultSet.next() && "PaymentDone".equalsIgnoreCase(statusResultSet.getString("Status"))) {
                JOptionPane.showMessageDialog(this, "Order already paid.");
                return;
            }

            // Update order status
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE Orders SET Status = 'PaymentDone' WHERE OrderID = ?");
            updateStatement.setInt(1, orderId);
            updateStatement.executeUpdate();
            updateStatement.close();

            // Insert into Transaction table
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO Transaction (Type, Amount, OrderID) VALUES (?, ?, ?)");
            insertStatement.setString(1, paymentType);
            insertStatement.setDouble(2, amount);
            insertStatement.setInt(3, orderId);
            insertStatement.executeUpdate();
            insertStatement.close();

            statusResultSet.close();
            checkStatusStatement.close();

            fetchDataAndPopulateTable();
            JOptionPane.showMessageDialog(this, "Payment successful!");

        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void deleteHistoryAction() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0 && selectedRow < table.getRowCount()) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            String orderId = model.getValueAt(selectedRow, 1).toString(); // Assuming Order ID is in the second column
            model.removeRow(selectedRow);

            try {
                // Delete from Transaction table based on Order ID
                String deleteQuery = "DELETE FROM Transaction WHERE OrderID = ?";
                PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
                deleteStatement.setInt(1, Integer.parseInt(orderId));
                deleteStatement.executeUpdate();
                deleteStatement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error deleting from Transaction table: " + ex.getMessage());
            }
        }
    }

    private void clearFields() {
        txtOrderId.setText("");
        txtAmount.setText("");
        txtPaymentType.setText("");
    }

    @Override
    public void dispose() {
        super.dispose();
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
