package com.jdbc.bankingDaoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.jdbc.banking.Banking;
import com.jdbc.bankingDao.BankingDao;


@SuppressWarnings("serial")
class AccountAlreadyExistsException extends Exception {
    public  static void AccountAlreadyExistsExceptions() {
        System.out.println("Have a good day!");
    }
}

public class BankingDaoImpl implements BankingDao {

    String jdbcUrl = "jdbc:mysql://localhost:3306/publicdata";
    String userName = "root";
    String password = "ADMIN123";
    
    String tableName = "spaccount";
    
    public static String dateTime() {
    	LocalDateTime dt = LocalDateTime.now();
    	DateTimeFormatter d = DateTimeFormatter.ofPattern("yyyy-MM-dd  hh:MM:ss");
    	String form = dt.format(d);
    	
		return form;
    	
    }
    
    @Override
    public void create() {
        String createTable = " CREATE TABLE " + tableName
                + " ( account_number BIGINT PRIMARY KEY, accountHolder_name VARCHAR(30) NOT NULL,DateOfBirth DATE NOT NULL,"
                + "adhaar_no BIGINT NOT NULL ,panCard VARCHAR(10) NOT NULL , email  VARCHAR(30) NOT NULL,phoneNumber BIGINT NOT NULL,accountBalance BIGINT NOT NULL,address VARCHAR(30) NOT NULL );";

        try {
            Connection con = DriverManager.getConnection(jdbcUrl, userName, password);
            PreparedStatement pst = con.prepareStatement(createTable);

            pst.execute();
            System.out.println("The Table is created");
            System.out.println();

        } catch (Exception e) {
        	AccountAlreadyExistsException.AccountAlreadyExistsExceptions();
        }
    }

    @Override
    public void addUser(Banking bank) {
        String insertQuery = "INSERT INTO " + tableName
                + " (account_number, accountHolder_name, DateOfBirth, adhaar_no, panCard, email, phoneNumber, accountBalance, address) VALUES (?,?,?,?,?,?,?,?,?);";
        String createTableQuery = "CREATE TABLE " + bank.getAccountHolder_name()
                + " (Deposit_withdraw VARCHAR(20) NOT NULL , ammount BIGINT NOT NULL, dateAndTime DATETIME  NOT NULL,TotalBalance BIGINT NOT NULL);";

        try {
        	Connection con = DriverManager.getConnection(jdbcUrl, userName, password);
            // Insert user into the main table
            PreparedStatement pst = con.prepareStatement(insertQuery);
                pst.setLong(1, bank.getAccount_number());
                pst.setString(2, bank.getAccountHolder_name());
                pst.setString(3, bank.getDateOfBirth());
                pst.setLong(4, bank.getAdhaar_no());
                pst.setString(5, bank.getPanCard());
                pst.setString(6, bank.getEmail());
                pst.setLong(7, bank.getPhoneNumber());
                pst.setLong(8, bank.getAccountBalance());
                pst.setString(9, bank.getAddress());

                pst.executeUpdate();
                System.out.println("Account is created successfully");
       
            	 PreparedStatement pst1 = con.prepareStatement(createTableQuery);
                pst1.executeUpdate();
          

        } catch (Exception e) {
            e.printStackTrace();
        }
    }  

    @Override
    public void display() {
        try {
            Connection con = DriverManager.getConnection(jdbcUrl, userName, password);
            Statement stat = con.createStatement();
            String displayQuery = "SELECT * FROM " + tableName + ";";
            ResultSet result = stat.executeQuery(displayQuery);
            

            System.out.printf("%-15s %-20s %-15s %-15s %-15s %-20s %-20s %-15s %-15s%n",
                    "account_number", "accountHolder_name", "DateOfBirth", "adhaar_no",
                    "panCard", "address", "email", "phoneNumber", "accountBalance");
            while (result.next()) {
                long account_no = result.getLong("account_number");
                String holder_name = result.getString("accountHolder_name");
                String DOB = result.getString("DateOfBirth");
                long userAdhaar = result.getLong("adhaar_no");
                String userPanCard = result.getString("panCard");
                String userAddress = result.getString("address");
                String userEmail = result.getString("email");
                long phoneNo = result.getLong("phoneNumber");
                long balance = result.getLong("accountBalance");
                
                System.out.printf("%-15s %-20s %-15s %-15s %-15s %-20s %-20s %-15s %-15s%n",
                        account_no, holder_name, DOB, userAdhaar, userPanCard, userAddress,
                        userEmail, phoneNo, balance);
            }

            System.out.println();
            stat.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void displayTransaction(String accountHolder_name) {
        try {
            Connection con = DriverManager.getConnection(jdbcUrl, userName, password);
            Statement stat = con.createStatement();
            String displayQuery = "SELECT * FROM " + accountHolder_name + ";";
            ResultSet result = stat.executeQuery(displayQuery);
            

            System.out.printf("%-20s %-20s %-20s %-20s%n",
                    "Deposit_withdraw", "ammount", "dateAndTime", "TotalBalance");
            while (result.next()) {
                String dw = result.getString("Deposit_withdraw");
                long ammount = result.getLong("ammount");
                String dAT = result.getString("dateAndTime");
                long totalAmount = result.getLong("TotalBalance");
               
                System.out.printf("%-20s %-20s %-20s %-20s%n",dw ,ammount ,dAT, totalAmount );
            }

            System.out.println();
            stat.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Banking bank, String column_name, String value, long account_number) {
        String updateQuery = "UPDATE " + tableName + " SET " + column_name + " = ? WHERE account_number = ?;";
        try {
            Connection con = DriverManager.getConnection(jdbcUrl, userName, password);
            PreparedStatement pst = con.prepareStatement(updateQuery);
            pst.setString(1, value);
            pst.setLong(2, account_number);
            pst.executeUpdate();
            System.out.println("The data is updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAccount(Banking bank, long account_number) {
        String DeleteQuery = "DELETE FROM  " + tableName + " WHERE account_number = ?;";
        try {
            Connection con = DriverManager.getConnection(jdbcUrl, userName, password);
            PreparedStatement pst = con.prepareStatement(DeleteQuery);
            pst.setLong(1, account_number);
            pst.executeUpdate();
            System.out.println("The Account is Delete Successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deposit(long account_number,String accoutHolder_name, long deposit_amount) {
        String start = "START TRANSACTION;";
        String depositQuery = "UPDATE " + tableName + " SET accountBalance = accountBalance + ? WHERE account_number = ?;";
        String commitQuery = "COMMIT;";
        String addinfo="INSERT INTO "+accoutHolder_name+" (Deposit_withdraw,ammount,dateAndTime,TotalBalance) VALUES(?,?,?,?);";
        String getBalanceQuery = "SELECT accountBalance FROM " + tableName + " WHERE account_number = ?;";
        String dt= dateTime();
        String type = "Deposit";
        try {
            Connection con = DriverManager.getConnection(jdbcUrl, userName, password);
            PreparedStatement pst1 = con.prepareStatement(start);
            pst1.executeUpdate();
            PreparedStatement pst2 = con.prepareStatement(depositQuery);
            pst2.setLong(1, deposit_amount);
            pst2.setLong(2, account_number);
            pst2.executeUpdate();
            System.out.println("The Amount is deposited successfully");
            
            PreparedStatement getBalanceStatement = con.prepareStatement(getBalanceQuery);
            getBalanceStatement.setLong(1, account_number);
            ResultSet resultSet = getBalanceStatement.executeQuery();

            long updatedBalance = 0;
            if (resultSet.next()) {
                updatedBalance = resultSet.getLong("accountBalance");
            }
            
            PreparedStatement pst3 = con.prepareStatement(commitQuery);
            pst3.executeUpdate();
            
            PreparedStatement pst4 = con.prepareStatement(addinfo);
            pst4.setString(1, type);
            pst4.setLong(2, deposit_amount);
            pst4.setString(3, dt);
            pst4.setLong(4, updatedBalance);
            pst4.executeUpdate();
            
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void withDraw(long account_number, String accoutHolder_name, long withdraw_amount) {
        String start = "START TRANSACTION;";
        String withdrawQuery = "UPDATE " + tableName + " SET accountBalance = accountBalance - ? WHERE account_number = ?;";
        String commitQuery = "COMMIT;";
        String getBalanceQuery = "SELECT accountBalance FROM " + tableName + " WHERE account_number = ?;";
        String addinfo = "INSERT INTO " + accoutHolder_name + " (Deposit_withdraw, ammount, dateAndTime, TotalBalance) VALUES (?, ?, ?, ?);";
        String dt = dateTime();
        String type = "WithDraw";

        try {
            Connection con = DriverManager.getConnection(jdbcUrl, userName, password);
            PreparedStatement pst1 = con.prepareStatement(start);
            pst1.executeUpdate();

            
            PreparedStatement checkBalanceStatement = con.prepareStatement(getBalanceQuery);
            checkBalanceStatement.setLong(1, account_number);
            ResultSet balanceResultSet = checkBalanceStatement.executeQuery();

            long currentBalance = 0;
            if (balanceResultSet.next()) {
                currentBalance = balanceResultSet.getLong("accountBalance");
            }

            if (currentBalance < withdraw_amount) {
                System.out.println("Insufficient balance. Withdrawal not allowed.");
                return; 
            }

            
            PreparedStatement pst2 = con.prepareStatement(withdrawQuery);
            pst2.setLong(1, withdraw_amount);
            pst2.setLong(2, account_number);
            pst2.executeUpdate();
            System.out.println("The Amount is withdrawn successfully");

            
            PreparedStatement getBalanceStatement = con.prepareStatement(getBalanceQuery);
            getBalanceStatement.setLong(1, account_number);
            ResultSet resultSet = getBalanceStatement.executeQuery();

            long updatedBalance = 0;
            if (resultSet.next()) {
                updatedBalance = resultSet.getLong("accountBalance");
            }

            PreparedStatement pst3 = con.prepareStatement(commitQuery);
            pst3.executeUpdate();

            
            PreparedStatement pst4 = con.prepareStatement(addinfo);
            pst4.setString(1, type);
            pst4.setLong(2, withdraw_amount);
            pst4.setString(3, dt);
            pst4.setLong(4, updatedBalance);
            pst4.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }


    @Override
    public void checkBalance(long account_number) {
        try {
            Connection con = DriverManager.getConnection(jdbcUrl, userName, password);
            Statement stat = con.createStatement();
            String get = "SELECT accountBalance FROM " + tableName + " WHERE account_number =" + account_number + " ; ";
            ResultSet result = stat.executeQuery(get);
 
            while (result.next()) {

                long balance = result.getLong("accountBalance");

                System.out.println("The remaining account balance: " + balance);
            }
            System.out.println();
            stat.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void transfer(long your_account_number, long transferAccountNumber, long transferAmount) {
        String start = "START TRANSACTION;";
        String transferQuery1 = "UPDATE " + tableName + " SET accountBalance = accountBalance - ? WHERE account_number = ?;";
        String transferQuery2 = "UPDATE " + tableName + " SET accountBalance = accountBalance + ? WHERE account_number = ?;";
        String end = "COMMIT;";
        try {
            Connection con = DriverManager.getConnection(jdbcUrl, userName, password);
            PreparedStatement pst1 = con.prepareStatement(start);
            pst1.executeUpdate();
            PreparedStatement pst2 = con.prepareStatement(transferQuery1);
            pst2.setLong(1, transferAmount);
            pst2.setLong(2, your_account_number);
            pst2.executeUpdate();
            PreparedStatement pst3 = con.prepareStatement(transferQuery2);
            pst3.setLong(1, transferAmount);
            pst3.setLong(2, transferAccountNumber);
            pst3.executeUpdate();
            System.out.println("The Amount is transferred successfully");
            PreparedStatement pst4 = con.prepareStatement(end);
            pst4.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



	@Override
	public void accountHolderDetails(long account_number) {
		String detailQuery= "SELECT * FROM " + tableName + " WHERE account_number = ?;";
		try {
			 Connection con = DriverManager.getConnection(jdbcUrl, userName, password);
			 PreparedStatement pst = con.prepareStatement(detailQuery) ;
			 pst.setLong(1, account_number);
			 
			 ResultSet result = pst.executeQuery();
	         System.out.printf("%-15s %-20s %-15s %-15s %-15s %-20s %-20s %-15s %-15s%n",
	                    "account_number", "accountHolder_name", "DateOfBirth", "adhaar_no",
	                    "panCard", "address", "email", "phoneNumber", "accountBalance");
	          while (result.next()) {
	                long account_no = result.getLong("account_number");
	                String holder_name = result.getString("accountHolder_name");
	                String DOB = result.getString("DateOfBirth");
	                long userAdhaar = result.getLong("adhaar_no");
	                String userPanCard = result.getString("panCard");
	                String userAddress = result.getString("address");
	                String userEmail = result.getString("email");
	                long phoneNo = result.getLong("phoneNumber");
	                long balance = result.getLong("accountBalance");
	                
	                System.out.printf("%-15s %-20s %-15s %-15s %-15s %-20s %-20s %-15s %-15s%n",
	                        account_no, holder_name, DOB, userAdhaar, userPanCard, userAddress,
	                        userEmail, phoneNo, balance);
	            }

	            System.out.println();
	           
	            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
