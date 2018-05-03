package rsvier.workshop.view;

public class AccountView extends View{

	@Override
	public void printHeaderMessage() {
		System.out.println("\n=========== Werknemer  |  Accountbeheer ===========\n");	
	}
	
	
	//Not using the printMenuMessage now
	@Override
	public void printMenuMessage() {
		System.out.println("1- Maak nieuw account\n2- Terug naar Hoofdmenu\n0- Verlaat Nevvo Meubels");
	}
		
	
	public static void printMakeCustomerAccount() {
		System.out.println("Maak eerst een nieuw account aan voor de klant.");
	}
		
	public void printRequestEmailInput() {
		System.out.print("Vul email adres in: ");
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
	
	
}
