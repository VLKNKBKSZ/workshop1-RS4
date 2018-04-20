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

	public void printRequestedAccountIsNull () {
		System.out.println("Het opgegeven email adres is onjuist!");
	}
	
	public void printLoginAccountIsSuccesfull() {
		System.out.println("Het inloggen is succesvol afgerond");
	}
	
	public void printPasswordOfAccountNotMatching() {
		System.out.println("Het passwoord komt niet overeen met het opgegeven email adres");
	}
}
