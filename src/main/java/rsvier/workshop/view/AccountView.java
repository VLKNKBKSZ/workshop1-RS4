package rsvier.workshop.view;

public class AccountView extends View{

	@Override
	public void printHeaderMessage() {
		System.out.println("\n=========== Werknemer  |  Accountbeheer ===========\n");	
	}
	
	
	//Not using the printMenuMessage now
	@Override
	public void printMenuMessage() {
		System.out.println("Welke gegevens wilt u veranderen?");
		System.out.println("1- E-mail\n2- Wachtwoord\n3- Veranderingen opslaan\n0- Terug naar vorige menu");
	}
		
	
	public static void printMakeCustomerAccount() {
		System.out.println("Maak eerst een nieuw account aan voor de klant.");
	}
		
	public void printRequestEmailInput() {
		System.out.print("Vul e-mailadres in: ");
	}

	public void printRequestPasswordInput() {
		System.out.print("Vul wachtwoord in: ");
	}

	public void printLoginDetailsWrong() {
        System.out.println("De opgegeven inloggegevens zijn onjuist!");
    }
	
	public void printLoginAccountIsSuccessful() {
		System.out.println("\nU bent nu ingelogd in uw account.");
	}
	
	public void printAccountNotFound() {
		System.out.println("Geen account met dit e-mailadres gevonden.");
	}


	public void printYourAccountHasBeenCreated() {
		System.out.println("\nUw account is aangemaakt.");
		
	}
	public void printConfirmUpdateAccount() {
		System.out.println("\nUw account is met succes bijgewerkt.");
		
	}
}
