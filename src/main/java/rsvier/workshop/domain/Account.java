package rsvier.workshop.domain;

public class Account {

	private int accountId;
	private int accountType;
	private String email;
	private String password;
	

	public Account() {

		
	}
	
	public Account(String email, String password) {
		accountType = 1;
		this.email = email;
		this.password = password;
	}


	public int getAccountId() {
		return accountId;
	}
	

	public void setAccountId(int id) {
		this.accountId = id;
	}

	
	public int getAccountType() {
		return accountType;
	}

	
	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}
	
	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
	@Override

	public String toString() {
		return "Account ID = " + getAccountId() + "Account type = " + getAccountType() + 
				" Email = "  + getEmail() + " Password = " + getPassword();
	
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountId != other.accountId)
			return false;
		return true;
	}

}