package rsvier.workshop.domain;

public class Validator {
	
	public boolean validateEmail(String email) {
		
		if(email == null) {
			System.out.println("Invalid email address");
			return false;
		}
		if(!email.matches("\\w+@\\w+\\.\\w+")) {  
			System.out.println("Invalid email address");
		return false;
		}
		
		return true;
	}

public boolean validatePassword(String password) {
		
		if(password == null) {
			System.out.println("Invalid password");
			return false;
		}
		
		if(password.matches(".*\\s+.*")) {
			System.out.println("Password can not contain space");
			return false;
		}
		if(!(password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,}$"))){  
			System.out.println("Password must contain at least one uppercase letter, one lowercase letter, one number and one special character." + 
					" It must also be at least 8 characters long.");
		return false;
		}
		
		return true;
	}
}
