package com.jdbc.bankingDao;
import com.jdbc.banking.Banking;


//Interface defining methods for banking data access operations
public interface BankingDao {
 
 // Method to create a new record in the database
 void create();
 
 // Method to add a user's banking details to the database
 void addUser(Banking bank);
 
 // Method to display all banking records
 void display();
 
 // Method to display transactions for a specific account holder
 void displayTransaction(String accountHolder_name);
 
 // Method to update a specific column value for a given account
 void update(Banking bank, String column_name, String value, long account_id);
 
 // Method to retrieve and display details of an account holder
 void accountHolderDetails(long account_number);
 
 // Method to delete a user account from the database
 void deleteAccount(Banking bank, long account_number);
 
 // Method to deposit money into an account
 void deposit(long account_number, String accoutHolder_name, long deposit_amount);
 
 // Method to withdraw money from an account
 void withDraw(long account_number, String accoutHolder_name, long withdraw_amount);
 
 // Method to check and display the account balance
 void checkBalance(long account_number);
 
 // Method to transfer money between two accounts
 void transfer(long your_account_number, long transferAccountNumber, long transferAmount);
}