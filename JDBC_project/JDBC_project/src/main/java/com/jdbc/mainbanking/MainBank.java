package com.jdbc.mainbanking;

import java.util.Scanner;

import com.jdbc.banking.Banking;
import com.jdbc.bankingDao.BankingDao;
import com.jdbc.bankingDaoImpl.BankingDaoImpl;



public class MainBank {
	

    public static void main(String[] args) {
        System.out.println("Welcome to Praveen International Royal Bank");

        BankingDao bank = new BankingDaoImpl();
       
        bank.create();
		
        
        Scanner scanner = new Scanner(System.in);
        
        while(true){
        
        System.out.println("Create new account:- 1" + "     " + "Account already exist:- 2" + "     " + " Admin :- 3");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        
        if(choice==3) {
	       
        }

        switch (choice) {
            case 1:
            	
            	
            	System.out.print("Enter the account number: ");
                long account_number =scanner.nextLong(); ;
                System.out.print("Enter the name of the account holder: ");
                String accountHolder_name = scanner.next();
                System.out.print("Enter the Date of birth yyyy/mm/dd: ");
                String DateOfBirth = scanner.next();
                scanner.nextLine();
                System.out.print("Enter the Adhaar number: ");
                long adhaar_no = scanner.nextLong();
                scanner.nextLine();
                System.out.print("Enter the Pan Number: ");
                String panCard = scanner.next();

                System.out.print("Enter the Phone Number: ");
                long phoneNumber = scanner.nextLong();
                System.out.print("Enter the Email: ");
                String email = scanner.next();

                System.out.print("Enter the how much amount will deposit (min-500 Rs): ");
                long accountBalance = scanner.nextLong();

                scanner.nextLine();
                System.out.print("Enter the Address as per Adhaar: ");
                String address = scanner.nextLine();

                Banking add = new Banking(account_number, accountHolder_name, DateOfBirth, adhaar_no, panCard, email, phoneNumber, accountBalance, address);

                bank.addUser(add);
                

                break;
            case 2:

                System.out.printf("%-20s %-20s %-20s  %-20s %-20s%n","Deposit:- 1","Withdraw:- 2"," Check Balance :- 3","Transfer  :- 4","AccountHolder Details:- 5");
                System.out.print("Enter your choice:");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.print("Enter the Account Number: ");
                        account_number = scanner.nextLong();
                        System.out.print("Enter the Account Holder Name: ");
                        accountHolder_name = scanner.next();

                        System.out.print("Enter the Deposit amount: ");
                        long amount = scanner.nextLong();

                        bank.deposit(account_number,accountHolder_name, amount);

                        break;
                    case 2:
                        System.out.print("Enter the Account Number: ");
                        account_number = scanner.nextLong();
                        System.out.print("Enter the Account Holder Name: ");
                        accountHolder_name = scanner.next();
                        System.out.print("Enter the withdraw amount: ");
                        amount = scanner.nextLong();

                        bank.withDraw(account_number,accountHolder_name, amount);

                        break;
                    case 3:
                        System.out.print("Enter the Account Number: ");
                        account_number = scanner.nextLong();

                        bank.checkBalance(account_number);

                        break;
                    case 4:
                        System.out.print("Enter the Account Number who transferring : ");
                        account_number = scanner.nextLong();
                        System.out.print("Enter the Account Number where to transfer: ");
                        long transferAccount_number = scanner.nextLong();
                        System.out.print("Enter the Deposit amount: ");
                        amount = scanner.nextLong();

                        bank.transfer(account_number, transferAccount_number, amount);
                        break;
                    case 5:
                    	 System.out.print("Enter the Account Number : ");
                         account_number = scanner.nextLong();
                         bank.accountHolderDetails(account_number);

                }

                break;
                
         
            case 3:
            	  final String correctPassword = "9848";
                  System.out.print("Enter admin password: ");
                  String enteredPassword = scanner.next(); 

                  if (enteredPassword.equals(correctPassword)) {
          
	                System.out.println("Update user data:- 1" + "   " + "Display all customer accounts:- 2" + "   " + " Delete any Account :- 3"+ "  "+"Check Transaction history:- 4");
	                System.out.print("Enter your choice:");
	                choice = scanner.nextInt();
	
	                switch (choice) {
	                    case 1:
	                        System.out.print("Enter column name in the table: accountHolder_name, DateOfBirth, adhaar_no, panCard, address, email, phoneNumber");
	                        System.out.print("Enter your choice:");
	                        String column = scanner.next();
	                        System.out.print("Enter what is the change value: ");
	                        String value = scanner.next();
	                        System.out.print("Enter Account Number: ");
	                        account_number = scanner.nextLong();
	
	                        bank.update(null, column, value, account_number);
	
	                        break;
	                    case 2:
	                        bank.display();
	                        System.out.println();
	                        break;
	                    case 3:
	                        System.out.print("Enter Account Number you want to Delete: ");
	                        account_number = scanner.nextLong();
	                        bank.deleteAccount(null, account_number);
	                        break;
	                    case 4:
	                    	System.out.print("Enter Account Holder name you want see Transations: ");
	                        accountHolder_name = scanner.next();
	                        bank.displayTransaction( accountHolder_name);
	                }
                  }else {
                	  System.out.println("Access denied. Incorrect password.");
                  
                  }
                break;

           	}
       
        }
        
    }
}

