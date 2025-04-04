package com.explearning;


import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.awt.Color;

public class Order extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtOrderId;
    private JTextField txtCustomerName;
    private JTextField txtCustomerPhone;
    private JTextField txtOrderDate;
    private JTextField txtCustomerAddress;
    private JTextField txtStatus;
    private JTextField txtProductId;
    private JTextField txtQuantity;
    private JTextField txtVendorId;
    private Connection connection;
    private JTextField textField;
    private JTable table;
    private DefaultTableModel model;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Order frame = new Order();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Order() {
        setTitle("ORDER");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1057, 539);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/exp_learning";
            String username = "root";
            String password = "Abinash@Mysql3";
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error connecting to database: " + e.getMessage());
        }

        JPanel panel = new JPanel();
        panel.setBackground(Color.MAGENTA);
        panel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
        panel.setBounds(10, 10, 522, 368);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblOrderId = new JLabel("Order ID");
        lblOrderId.setBounds(21, 21, 120, 24);
        lblOrderId.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblOrderId.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(lblOrderId);

        txtOrderId = new JTextField();
        txtOrderId.setBounds(151, 24, 293, 24);
        panel.add(txtOrderId);
        txtOrderId.setColumns(10);

        JLabel lblCustomerName = new JLabel("Customer Name");
        lblCustomerName.setHorizontalAlignment(SwingConstants.LEFT);
        lblCustomerName.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblCustomerName.setBounds(21, 55, 120, 24);
        panel.add(lblCustomerName);

        txtCustomerName = new JTextField();
        txtCustomerName.setColumns(10);
        txtCustomerName.setBounds(151, 58, 293, 24);
        panel.add(txtCustomerName);

        JLabel lblCustomerPhone = new JLabel("Customer Phone");
        lblCustomerPhone.setHorizontalAlignment(SwingConstants.LEFT);
        lblCustomerPhone.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblCustomerPhone.setBounds(21, 89, 120, 24);
        panel.add(lblCustomerPhone);

        txtCustomerPhone = new JTextField();
        txtCustomerPhone.setColumns(10);
        txtCustomerPhone.setBounds(151, 92, 293, 24);
        panel.add(txtCustomerPhone);

        JLabel lblOrderDate = new JLabel("Order Date");
        lblOrderDate.setHorizontalAlignment(SwingConstants.LEFT);
        lblOrderDate.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblOrderDate.setBounds(21, 123, 120, 24);
        panel.add(lblOrderDate);

        txtOrderDate = new JTextField();
        txtOrderDate.setColumns(10);
        txtOrderDate.setBounds(151, 126, 293, 24);
        panel.add(txtOrderDate);

        JLabel lblCustomerAddress = new JLabel("Customer Address");
        lblCustomerAddress.setHorizontalAlignment(SwingConstants.LEFT);
        lblCustomerAddress.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblCustomerAddress.setBounds(21, 157, 130, 24);
        panel.add(lblCustomerAddress);

        txtCustomerAddress = new JTextField();
        txtCustomerAddress.setColumns(10);
        txtCustomerAddress.setBounds(151, 160, 293, 24);
        panel.add(txtCustomerAddress);

        JLabel lblStatus = new JLabel("Status");
        lblStatus.setHorizontalAlignment(SwingConstants.LEFT);
        lblStatus.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblStatus.setBounds(21, 191, 120, 24);
        panel.add(lblStatus);

        txtStatus = new JTextField();
        txtStatus.setColumns(10);
        txtStatus.setBounds(151, 194, 293, 24);
        panel.add(txtStatus);

        JLabel lblProductId = new JLabel("Product ID");
        lblProductId.setHorizontalAlignment(SwingConstants.LEFT);
        lblProductId.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblProductId.setBounds(21, 228, 120, 24);
        panel.add(lblProductId);

        txtProductId = new JTextField();
        txtProductId.setColumns(10);
        txtProductId.setBounds(151, 228, 293, 24);
        panel.add(txtProductId);

        JLabel lblQuantity = new JLabel("Quantity");
        lblQuantity.setHorizontalAlignment(SwingConstants.LEFT);
        lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblQuantity.setBounds(21, 262, 120, 24);
        panel.add(lblQuantity);

        txtQuantity = new JTextField();
        txtQuantity.setColumns(10);
        txtQuantity.setBounds(151, 262, 293, 24);
        panel.add(txtQuantity);
        
        JLabel lblVendorId = new JLabel("Vendor ID"); // Changed label name to Vendor ID
        lblVendorId.setHorizontalAlignment(SwingConstants.LEFT);
        lblVendorId.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblVendorId.setBounds(21, 298, 120, 24);
        panel.add(lblVendorId);

        txtVendorId = new JTextField(); // Added JTextField for VendorId
        txtVendorId.setColumns(10);
        txtVendorId.setBounds(151, 296, 293, 24);
        panel.add(txtVendorId);
        
        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(151, 296, 293, 24);
        panel.add(textField);

        JButton btnAdd = new JButton("ADD");
        btnAdd.setBackground(Color.CYAN);
        btnAdd.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnAdd.setBounds(10, 408, 111, 33);
        contentPane.add(btnAdd);
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addOrderToDatabase();
            }
        });

        JButton btnEdit = new JButton("EDIT");
        btnEdit.setBackground(Color.CYAN);
        btnEdit.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnEdit.setBounds(127, 408, 111, 33);
        contentPane.add(btnEdit);
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editOrderInDatabase();
            }
        });

        JButton btnDelete = new JButton("DELETE");
        btnDelete.setBackground(Color.CYAN);
        btnDelete.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnDelete.setBounds(248, 408, 111, 33);
        contentPane.add(btnDelete);
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteOrderFromDatabase();
            }
        });

        JButton btnCancel = new JButton("CANCEL");
        btnCancel.setBackground(Color.CYAN);
        btnCancel.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnCancel.setBounds(369, 408, 121, 33);
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
        scrollPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                updateFieldsFromSelectedRow();
            }
        });
        scrollPane.setBounds(542, 10, 501, 368);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
        table.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] { "OrderId", "Cust_Name", "Cust_Phone", "Order Date", "Cust_Address", "Status", "Quantity", "P_Id", "V_Id" }
        ));

        model = new DefaultTableModel(
                new Object[][] {},
                new String[] { "OrderId", "Cust_Name", "Cust_Phone", "Order Date", "Cust_Address", "Status", "Quantity", "P_Id", "V_Id" }
        );
        table.setModel(model);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBackground(new Color(128, 0, 64));
        lblNewLabel.setOpaque(true);
        lblNewLabel.setBounds(0, 0, 1043, 502);
        contentPane.add(lblNewLabel);

        fetchDataAndPopulateTable();
    }
    private void fetchDataAndPopulateTable() {
        try {
            String query = "SELECT * FROM Orders";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            model.setRowCount(0); // Clear existing rows

            while (resultSet.next()) {
                Object[] row = {
                    resultSet.getInt("OrderID"),
                    resultSet.getString("CustomerName"),
                    resultSet.getString("CustomerContactPhone"),
                    resultSet.getString("OrderDate"),
                    resultSet.getString("CustomerAddress"),
                    resultSet.getString("Status"),
                    resultSet.getInt("ProductID"),
                    resultSet.getInt("Quantity"),
                    resultSet.getInt("VendorID") // Added VendorID to the row
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
    
    private void updateFieldsFromSelectedRow() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0 && selectedRow < table.getRowCount()) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            txtOrderId.setText(model.getValueAt(selectedRow, 0).toString());
            txtCustomerName.setText(model.getValueAt(selectedRow, 1).toString());
            txtCustomerPhone.setText(model.getValueAt(selectedRow, 2).toString());
            txtOrderDate.setText(model.getValueAt(selectedRow, 3).toString());
            txtCustomerAddress.setText(model.getValueAt(selectedRow, 4).toString());
            txtStatus.setText(model.getValueAt(selectedRow, 5).toString());
            txtProductId.setText(model.getValueAt(selectedRow, 6).toString());
            txtQuantity.setText(model.getValueAt(selectedRow, 7).toString());
            txtVendorId.setText(model.getValueAt(selectedRow, 8).toString());
        }
    }

    private void addOrderToDatabase() {
        try {
            // Get input values from UI
            int productId = Integer.parseInt(txtProductId.getText());
            int orderQuantity = Integer.parseInt(txtQuantity.getText());
            int productQuantity = getProductQuantity(productId);
            int vendorId = Integer.parseInt(txtVendorId.getText());

            // Check product availability
            if (productQuantity < orderQuantity) {
                JOptionPane.showMessageDialog(this, "Sorry, this much product is not available.");
                return;
            }

            // Check product availability for vendor
            if (!isProductAvailableForVendor(productId, vendorId)) {
                JOptionPane.showMessageDialog(this, "The product is not available under this vendor.");
                return;
            }

            // Calculate the new product quantity after subtraction
            int updatedProductQuantity = productQuantity - orderQuantity;

            // Insert order into Orders table
            String query = "INSERT INTO Orders (OrderID, CustomerName, CustomerContactPhone, OrderDate, CustomerAddress, Status, ProductID, Quantity, VendorID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, Integer.parseInt(txtOrderId.getText()));
            statement.setString(2, txtCustomerName.getText());
            statement.setString(3, txtCustomerPhone.getText());
            statement.setString(4, txtOrderDate.getText());
            statement.setString(5, txtCustomerAddress.getText());
            statement.setString(6, txtStatus.getText());
            statement.setInt(7, productId);
            statement.setInt(8, orderQuantity);
            statement.setInt(9, vendorId);

            int rowsInserted = statement.executeUpdate();

            // Only update product quantities if the order was successfully inserted
            if (rowsInserted > 0) {
                // Perform subtraction only after confirming the insertion was successful
                updateProductQuantity(productId, updatedProductQuantity);
                updateVendorProductQuantity(productId, vendorId, updatedProductQuantity);

                // Show success message and refresh table
                JOptionPane.showMessageDialog(this, "Order added successfully!");
                fetchDataAndPopulateTable();
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add the order. Please try again.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private int getProductQuantity(int productId) {
        int quantity = 0;
        try {
            String query = "SELECT Quantity FROM Product WHERE ProductID=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, productId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                quantity = resultSet.getInt("Quantity");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quantity;
    }

    private void updateProductQuantity(int productId, int newQuantity) {
        try {
            String query = "UPDATE Product SET Quantity=? WHERE ProductID=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, newQuantity);
            statement.setInt(2, productId);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean isProductAvailableForVendor(int productId, int vendorId) {
        try {
            String query = "SELECT * FROM Product WHERE ProductID=? AND VendorID=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, productId);
            statement.setInt(2, vendorId);
            ResultSet resultSet = statement.executeQuery();
            boolean available = resultSet.next();
            resultSet.close();
            statement.close();
            return available;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void updateVendorProductQuantity(int productId, int vendorId, int newQuantity) {
        try {
            String query = "UPDATE Product SET Quantity=? WHERE ProductID=? AND VendorID=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, newQuantity);
            statement.setInt(2, productId);
            statement.setInt(3, vendorId);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void editOrderInDatabase() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0 && selectedRow < table.getRowCount()) {
            try {
                int orderId = Integer.parseInt(txtOrderId.getText());
                int productId = Integer.parseInt(txtProductId.getText());
                int vendorId = Integer.parseInt(txtVendorId.getText()); // Fetch VendorID

                if (!isProductAvailableForVendor(productId, vendorId)) {
                    JOptionPane.showMessageDialog(this, "The product is not available under this vendor.");
                    return; // Stop processing further if product not available for this vendor
                }

                String query = "UPDATE Orders SET CustomerName=?, CustomerContactPhone=?, OrderDate=?, CustomerAddress=?, Status=?, ProductID=?, Quantity=?, VendorID=? WHERE OrderID=?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, txtCustomerName.getText());
                statement.setString(2, txtCustomerPhone.getText());
                statement.setString(3, txtOrderDate.getText());
                statement.setString(4, txtCustomerAddress.getText());
                statement.setString(5, txtStatus.getText());
                statement.setInt(6, productId);
                statement.setInt(7, Integer.parseInt(txtQuantity.getText()));
                statement.setInt(8, vendorId);
                statement.setInt(9, orderId);
                statement.executeUpdate();

                JOptionPane.showMessageDialog(this, "Order updated successfully!");
                fetchDataAndPopulateTable(); // Refresh the table after editing
                clearFields();
            } catch (SQLException | NumberFormatException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error updating order: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to edit.");
        }
    }

    private void deleteOrderFromDatabase() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0 && selectedRow < table.getRowCount()) {
            try {
                int orderId = Integer.parseInt(txtOrderId.getText());
                String query = "DELETE FROM Orders WHERE OrderID=?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, orderId);
                statement.executeUpdate();

                JOptionPane.showMessageDialog(this, "Order deleted successfully!");
                fetchDataAndPopulateTable(); // Refresh the table after deletion
                clearFields();
            } catch (SQLException | NumberFormatException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error deleting order: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.");
        }
    }

  

    private void clearFields() {
        txtOrderId.setText("");
        txtCustomerName.setText("");
        txtCustomerPhone.setText("");
        txtOrderDate.setText("");
        txtCustomerAddress.setText("");
        txtStatus.setText("");
        txtProductId.setText("");
        txtQuantity.setText("");
        txtVendorId.setText("");
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
