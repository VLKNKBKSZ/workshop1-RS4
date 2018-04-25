package rsvier.workshop.domain;

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
}
