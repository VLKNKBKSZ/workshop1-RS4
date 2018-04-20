package rsvier.workshop.view;

public class MainMenuView extends View {


	@Override
	public void printHeaderMessage() {
		System.out.println("=========== Welkom Op Evvo Meubels ===========");
		
	}

	@Override
	public void printMenuMessage() {

		System.out.println("1- Inloggen\n2- Maak een nieuw Account\n0- Verlaat Evvo Meubels");
	}

	public void requestEmailInput() {
		System.out.print("Vul email adres in: ");
	}

	public void requestPasswordInput() {
		System.out.print("Vul wachtwoord in: ");
	}


}
