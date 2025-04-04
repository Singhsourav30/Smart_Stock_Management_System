package com.explearning;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.awt.Color;

public class Product extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtProductId;
    private JTextField txtProductName;
    private JTextField txtCategory;
    private JTextField txtPrice;
    private JTextField txtQuantity;
    private JTextField txtRetailPrice;
    private JTextField textField;
    private JTable table;
    private Connection connection;
    private DefaultTableModel model;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Product frame = new Product();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Product() {
        setTitle("PRODUCT");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1057, 539);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/exp_learning", "root", "Abinash@Mysql3");
        } catch (Exception e) {
            e.printStackTrace();
        }

        JPanel panel = new JPanel();
        panel.setBackground(Color.ORANGE);
        panel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
        panel.setBounds(10, 10, 522, 368);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblProductId = new JLabel("Product ID");
        lblProductId.setBounds(21, 35, 120, 24);
        lblProductId.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblProductId.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(lblProductId);

        txtProductId = new JTextField();
        txtProductId.setBounds(151, 35, 293, 24);
        panel.add(txtProductId);
        txtProductId.setColumns(10);

        JLabel lblProductName = new JLabel("Product Name");
        lblProductName.setHorizontalAlignment(SwingConstants.LEFT);
        lblProductName.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblProductName.setBounds(21, 73, 120, 24);
        panel.add(lblProductName);

        JLabel lblCategory = new JLabel("Category");
        lblCategory.setHorizontalAlignment(SwingConstants.LEFT);
        lblCategory.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblCategory.setBounds(21, 107, 120, 24);
        panel.add(lblCategory);

        JLabel lblPrice = new JLabel("Cost Price");
        lblPrice.setHorizontalAlignment(SwingConstants.LEFT);
        lblPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblPrice.setBounds(21, 141, 120, 24);
        panel.add(lblPrice);

        JLabel lblQuantity = new JLabel("Quantity");
        lblQuantity.setHorizontalAlignment(SwingConstants.LEFT);
        lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblQuantity.setBounds(21, 209, 120, 24);
        panel.add(lblQuantity);

        JLabel lblRetailPrice = new JLabel("Retail Price");
        lblRetailPrice.setHorizontalAlignment(SwingConstants.LEFT);
        lblRetailPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblRetailPrice.setBounds(21, 175, 120, 24);
        panel.add(lblRetailPrice);

        txtProductName = new JTextField();
        txtProductName.setColumns(10);
        txtProductName.setBounds(151, 73, 293, 24);
        panel.add(txtProductName);

        txtCategory = new JTextField();
        txtCategory.setColumns(10);
        txtCategory.setBounds(151, 107, 293, 24);
        panel.add(txtCategory);

        txtPrice = new JTextField();
        txtPrice.setColumns(10);
        txtPrice.setBounds(151, 141, 293, 24);
        panel.add(txtPrice);

        txtQuantity = new JTextField();
        txtQuantity.setColumns(10);
        txtQuantity.setBounds(151, 209, 293, 24);
        panel.add(txtQuantity);

        txtRetailPrice = new JTextField();
        txtRetailPrice.setColumns(10);
        txtRetailPrice.setBounds(151, 175, 293, 24);
        panel.add(txtRetailPrice);
        
        JLabel lblVendorid = new JLabel("VendorId");
        lblVendorid.setHorizontalAlignment(SwingConstants.LEFT);
        lblVendorid.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblVendorid.setBounds(21, 246, 120, 24);
        panel.add(lblVendorid);
        
        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(151, 251, 293, 24);
        panel.add(textField);

        JButton btnAdd = new JButton("ADD");
        btnAdd.setBackground(Color.CYAN);
        btnAdd.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnAdd.setBounds(10, 408, 111, 33);
        contentPane.add(btnAdd);
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });

        JButton btnEdit = new JButton("EDIT");
        btnEdit.setBackground(Color.CYAN);
        btnEdit.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnEdit.setBounds(127, 408, 111, 33);
        contentPane.add(btnEdit);
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editProduct();
            }
        });

        JButton btnDelete = new JButton("DELETE");
        btnDelete.setBackground(Color.CYAN);
        btnDelete.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnDelete.setBounds(248, 408, 111, 33);
        contentPane.add(btnDelete);
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteProduct();
            }
        });

        JButton btnCancel = new JButton("CANCEL");
        btnCancel.setBackground(Color.CYAN);
        btnCancel.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnCancel.setBounds(369, 408, 134, 33);
        contentPane.add(btnCancel);
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        JButton btnClose = new JButton("CLOSE");
        btnClose.setBackground(Color.CYAN);
        btnClose.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnClose.setBounds(513, 408, 111, 33);
        contentPane.add(btnClose);
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(542, 10, 502, 388);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "ProductId", "Product Name", "Category", "Cost Price", "Retail Price", "Quantity", "VendorId" }
        ));

        model = new DefaultTableModel(
            new Object[][] {},
            new String[] { "ProductId", "Product Name", "Category", "Cost Price", "Retail Price", "Quantity", "VendorId" }
        );
        table.setModel(model); // Set the model to the table
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBackground(new Color(128, 0, 128));
        lblNewLabel.setOpaque(true);
        lblNewLabel.setBounds(0, 0, 1043, 502);
        contentPane.add(lblNewLabel);

        fetchDataAndPopulateTable();

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                updateTextFieldsFromSelectedRow();
            }
        });
    }

    private void fetchDataAndPopulateTable() {
        try {
            String query = "SELECT * FROM Product";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            model.setRowCount(0); // Clear existing rows

            while (resultSet.next()) {
                Object[] row = {
                    resultSet.getInt("ProductID"),
                    resultSet.getString("ProductName"),
                    resultSet.getString("Category"),
                    resultSet.getDouble("CostPrice"),
                    resultSet.getDouble("RetailPrice"),
                    resultSet.getInt("Quantity"),
                    resultSet.getInt("VendorId")
                };
                model.addRow(row);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching data from database: " + e.getMessage());
        }
    }
    
    private void updateTextFieldsFromSelectedRow() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0 && selectedRow < table.getRowCount()) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            txtProductId.setText(model.getValueAt(selectedRow, 0).toString());
            txtProductName.setText(model.getValueAt(selectedRow, 1).toString());
            txtCategory.setText(model.getValueAt(selectedRow, 2).toString());
            txtPrice.setText(model.getValueAt(selectedRow, 3).toString());
            txtRetailPrice.setText(model.getValueAt(selectedRow, 4).toString());
            txtQuantity.setText(model.getValueAt(selectedRow, 5).toString());
            textField.setText(model.getValueAt(selectedRow, 6).toString());
        }
    }

    private boolean isVendorIdValid(int vendorId) {
        try {
            String query = "SELECT COUNT(*) FROM Vendor WHERE VendorId=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, vendorId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0; // VendorId exists if count > 0
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Default to false if an error occurs
    }

    private void addProduct() {
        try {
            int vendorId = Integer.parseInt(textField.getText());
            if (!isVendorIdValid(vendorId)) {
                JOptionPane.showMessageDialog(this, "Vendor not available.");
                return; // Exit the method if VendorId is not valid
            }

            String query = "INSERT INTO Product (ProductID, ProductName, Category, CostPrice, RetailPrice, Quantity, VendorId) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, Integer.parseInt(txtProductId.getText()));
            statement.setString(2, txtProductName.getText());
            statement.setString(3, txtCategory.getText());
            statement.setDouble(4, Double.parseDouble(txtPrice.getText()));
            statement.setDouble(5, Double.parseDouble(txtRetailPrice.getText()));
            statement.setInt(6, Integer.parseInt(txtQuantity.getText()));
            statement.setInt(7, vendorId);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Product added successfully!");
            fetchDataAndPopulateTable(); // Refresh the table after editing
            clearFields();

        } catch (SQLException | NumberFormatException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding product: " + ex.getMessage());
        }
    }


    private void editProduct() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0 && selectedRow < table.getRowCount()) {
            try {
                String query = "UPDATE Product SET ProductName=?, Category=?, CostPrice=?, RetailPrice=?, Quantity=?, VendorId=? WHERE ProductID=?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, txtProductName.getText());
                statement.setString(2, txtCategory.getText());
                statement.setDouble(3, Double.parseDouble(txtPrice.getText()));
                statement.setDouble(4, Double.parseDouble(txtRetailPrice.getText()));
                statement.setInt(5, Integer.parseInt(txtQuantity.getText()));
                statement.setInt(6, Integer.parseInt(textField.getText())); // Assuming VendorId is an integer
                statement.setInt(7, Integer.parseInt(txtProductId.getText()));
                statement.executeUpdate();
                JOptionPane.showMessageDialog(this, "Product updated successfully!");
                fetchDataAndPopulateTable(); // Refresh the table after editing
                clearFields();

            } catch (SQLException | NumberFormatException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error updating product: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to edit.");
        }
    }

    private void deleteProduct() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0 && selectedRow < table.getRowCount()) {
            try {
                int productId = Integer.parseInt(txtProductId.getText());
                String query = "DELETE FROM Product WHERE ProductID=?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, productId);
                statement.executeUpdate();
                JOptionPane.showMessageDialog(this, "Product deleted successfully!");
                fetchDataAndPopulateTable(); // Refresh the table after deletion
                clearFields();
            } catch (SQLException | NumberFormatException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error deleting product: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.");
        }
    }

    private void clearFields() {
        txtProductId.setText("");
        txtProductName.setText("");
        txtCategory.setText("");
        txtPrice.setText("");
        txtRetailPrice.setText("");
        txtQuantity.setText("");
        textField.setText(""); // Clear VendorId field
    }

    @Override
    public void dispose() {
        super.dispose();
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
