package rsvier.workshop.domain;

public class Account {

	private int accountId;
	private String email;
	private String password;
	

	public Account() {

		
	}
	
	public Account(String email, String password) {
		this.email = email;
		this.password = password;
	}


	public int getAccountId() {
		return accountId;
	}


	public void setAccountId(int id) {
		this.accountId = id;
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
<<<<<<< HEAD
		return "Account ID = " + getAccountId()+ " Email = "  + getEmail() + " Password = " + getPassword();
=======
		return " Email = "  + getEmail() + " Password = " + getPassword();
>>>>>>> refs/remotes/origin/master
	
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
