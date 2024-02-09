package com.jdbc.banking;

//Defines a bank account
public class Banking {
	
		// Account details
	    private long account_number;
	    private String accountHolder_name;
	    private String DateOfBirth;
	    private long adhaar_no;
	    private String panCard;
	    private String email;
	    private long phoneNumber;
	    private long accountBalance;
	    private String address;

	    // Constructor to initialize account
	    public Banking(long account_number, String accountHolder_name, String dateOfBirth, long adhaar_no, String panCard,
	                   String email, long phoneNumber, long accountBalance, String address) {
	        this.account_number = account_number;
	        this.accountHolder_name = accountHolder_name;
	        this.DateOfBirth = dateOfBirth;
	        this.adhaar_no = adhaar_no;
	        this.panCard = panCard;
	        this.email = email;
	        this.phoneNumber = phoneNumber;
	        this.accountBalance = accountBalance;
	        this.address = address;
	    }
	    
	    // Getter and setter methods
	    public long getAccount_number() {
	        return account_number;
	    }

	    public void setAccount_number(long account_number) {
	        this.account_number = account_number;
	    }

	    public String getAccountHolder_name() {
	        return accountHolder_name;
	    }

	    public void setAccountHolder_name(String accountHolder_name) {
	        this.accountHolder_name = accountHolder_name;
	    }

	    public String getDateOfBirth() {
	        return DateOfBirth;
	    }

	    public void setDateOfBirth(String dateOfBirth) {
	        DateOfBirth = dateOfBirth;
	    }

	    public long getAdhaar_no() {
	        return adhaar_no;
	    }

	    public void setAdhaar_no(long adhaar_no) {
	        this.adhaar_no = adhaar_no;
	    }

	    public String getPanCard() {
	        return panCard;
	    }

	    public void setPanCard(String panCard) {
	        this.panCard = panCard;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public long getPhoneNumber() {
	        return phoneNumber;
	    }

	    public void setPhoneNumber(long phoneNumber) {
	        this.phoneNumber = phoneNumber;
	    }

	    public long getAccountBalance() {
	        return accountBalance;
	    }

	    public void setAccountBalance(long accountBalance) {
	        this.accountBalance = accountBalance;
	    }

	    public String getAddress() {
	        return address;
	    }

	    public void setAddress(String address) {
	        this.address = address;
	    }
}
