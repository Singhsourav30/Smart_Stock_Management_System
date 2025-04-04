package com.explearning;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MainFrame() {
        setTitle("StockManagementSystem");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1263, 614);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        
        JButton btnNewButton = new JButton("Vendor");
        btnNewButton.setBounds(541, 102, 176, 50);
        btnNewButton.setBorder(new LineBorder(new Color(0, 0, 0)));
        btnNewButton.setBackground(Color.CYAN);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Vendor vendorFrame = new Vendor();
                vendorFrame.setVisible(true);
            }
        });
        contentPane.setLayout(null);
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 24));
        contentPane.add(btnNewButton);
        
        JButton btnProduct = new JButton("Product");
        btnProduct.setBounds(541, 162, 176, 50);
        btnProduct.setBorder(new LineBorder(new Color(0, 0, 0)));
        btnProduct.setBackground(Color.CYAN);
        btnProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 Product productFrame = new Product();
                 productFrame.setVisible(true);
            }
        });
        btnProduct.setFont(new Font("Tahoma", Font.BOLD, 24));
        contentPane.add(btnProduct);
        
        JButton btnOrder = new JButton("Order\r\n");
        btnOrder.setBounds(541, 222, 176, 50);
        btnOrder.setBorder(new LineBorder(new Color(0, 0, 0)));
        btnOrder.setBackground(Color.CYAN);
        btnOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 Order orderFrame = new Order();
                 orderFrame.setVisible(true);
            }
        });
        btnOrder.setFont(new Font("Tahoma", Font.BOLD, 24));
        contentPane.add(btnOrder);
        
        
        JButton btnTransaction = new JButton("Transaction");
        btnTransaction.setBounds(541, 282, 176, 50);
        btnTransaction.setBorder(new LineBorder(new Color(0, 0, 0)));
        btnTransaction.setBackground(Color.CYAN);
        btnTransaction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 Transaction transactionFrame = new  Transaction();
                 transactionFrame.setVisible(true);
            }
        });
        btnTransaction.setFont(new Font("Tahoma", Font.BOLD, 24));
        contentPane.add(btnTransaction);
        
         JButton btnLogout = new JButton("Close");
         btnLogout.setBounds(560, 370, 143, 50);
         btnLogout.setBorder(new LineBorder(new Color(0, 0, 0)));
         btnLogout.setBackground(Color.CYAN);
            btnLogout.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose(); // Close the current JFrame
                }
            });
            
        btnLogout.setFont(new Font("Tahoma", Font.BOLD, 24));
        contentPane.add(btnLogout);
        
        JLabel lblNewLabel = new JLabel("Main Menu");
        lblNewLabel.setBackground(new Color(0, 255, 0));
        lblNewLabel.setOpaque(true);
        lblNewLabel.setBounds(560, 50, 131, 26);
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        contentPane.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Abhisek\\Downloads\\stock1.png"));
        lblNewLabel_1.setBounds(0, 10, 1239, 567);
        contentPane.add(lblNewLabel_1);
        
       
    }
}