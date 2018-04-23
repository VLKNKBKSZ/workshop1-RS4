package rsvier.workshop.view;

public class AccountView extends View{

	@Override
	public void printHeaderMessage() {
		System.out.println("=========== Account ===========");
		
	}

	@Override
	public void printMenuMessage() {

			System.out.println("1- Maak nieuw account\n2- Terug naar Hoofdmenu\n0- Verlaat Evvo Meubels");
		}
		
	public void requestEmailInput() {
		System.out.print("Vul email adres in: ");
	}

	public void requestPasswordInput() {
		System.out.print("Vul wachtwoord in: ");
	}

	public void printLoginDetailsWrong() {
        System.out.println("De opgegeven inloggegevens zijn onjuist!");
    }
	
	public void printLoginAccountIsSuccesfull() {
		System.out.println("Het inloggen is succesvol afgerond");
	}
	
	
}
