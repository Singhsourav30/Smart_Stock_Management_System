# Stock Management System

A Java-based desktop application developed using Swing and MySQL to manage vendors, products, orders, and transactions efficiently.

## 🧩 Features

- **Vendor Management**: Add, edit, delete vendors with contact information.
- **Product Management**: Manage inventory with product details, quantity, cost, retail price, and vendor association.
- **Order Handling**: Place and update customer orders, check product availability and validate vendor-product relations.
- **Transaction Management**: Calculate payments, update order status, and record transaction history.

## 🛠️ Technologies Used

- Java (Swing for GUI)
- MySQL Database
- JDBC (Java Database Connectivity)

## 📁 Project Structure
src/ ├── MainFrame.java # Main navigation menu ├── Vendor.java # Vendor CRUD operations ├── Product.java # Product CRUD with vendor association ├── Order.java # Order placement and validation ├── Transaction.java # Payment and transaction history

## 🗄️ Database Configuration

Ensure a MySQL database named `exp_learning` with the following tables:

- **VENDOR**
- **PRODUCT**
- **ORDERS**
- **TRANSACTION**

Update your MySQL credentials in the following files:
- `Order.java`
- `Product.java`
- `Transaction.java`
- `Vendor.java`

```java
String url = "jdbc:mysql://localhost:3306/exp_learning";
String username = "root";
String password = "YourPassword";
🚀 How to Run
Open the project in any Java IDE (e.g., IntelliJ IDEA, Eclipse).

Ensure MySQL server is running and the exp_learning database is set up.

Run MainFrame.java to launch the application.
