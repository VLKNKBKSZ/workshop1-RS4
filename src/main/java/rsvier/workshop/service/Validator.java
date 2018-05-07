package rsvier.workshop.service;

public class Validator {

	public boolean validateEmail(String email) {

		if (email == null) {
			System.out.println("Invalid email address");
			return false;
		}

		if (!email.matches("\\w+@\\w+\\.\\w+")) {
			System.out.println("Dit is geen geldig e-mail adres");
			return false;
		}

		return true;
	}

	public boolean validatePassword(String password) {

		if (password == null) {
			System.out.println("Dit is geen geldig wachtwoord.");
			return false;
		}

		if (password.matches(".*\\s+.*")) {
			System.out.println("Uw wachtwoord mag geen spatie bevatten.");
			return false;
		}
		if (!(password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,}$"))) {
			System.out.println(
				"Uw wachtwoord moet minstens een hoofdletter, een kleine letter, een getal \nen een speciaal "
				+ "leesteken bevatten en uit minimaal 8 karakters bestaan.");
			return false;
		}

		return true;
	}
	
	public boolean validatePostalCode(String inputPostalCode) {
		
		String postalCode = inputPostalCode;
		char[] postalCodeArray = postalCode.toCharArray();
		
		int i;
		
		//check if input is made of 6 characters
		if (postalCodeArray.length != 0) {
			return false;
		}
		
		//check if first four characters are a lower case letter, and if so, convert to upper case
		for (i=0 ; i<=3 ; i++) {
			if (postalCodeArray[i] >= 'a' && postalCodeArray[i]<='z') {
				Character.toUpperCase(postalCodeArray[i]);
			}
		}
		
		//check if first four characters are a letter
		for (i=0 ; i <= 3 ; i++) {
			if (!(postalCodeArray[i] >= 'A' && postalCodeArray[i]<='Z')) {
				return false;
			}
		}
		
		//check if last two characters are a number
		for (i= 4 ; i <= 5 ; i++) {
			if (!(postalCodeArray[i] >= '0' && postalCodeArray[i]<='9')) {
				return false;
			}
		}
		
		return true;
	}
	
}
