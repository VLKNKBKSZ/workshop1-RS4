package rsvier.workshop.view;

public class MainMenuView extends View {

	public void requestEmailInput() {
		System.out.print("Vul email adres in: ");
	}

	public void requestPasswordInput() {
		System.out.print("Vul wachtwoord in: ");
	}

	@Override
	public void printHeaderMessage() {
		System.out.println("\"Welkom Op Evvo Meubels\\r\\n\\r\\n----------------------------------\\r\\n\\r\\n\"");
		
	}

	@Override
	public void printMenuMessage() {

		System.out.println("1 - Inloggen\r\n2 - Maak een nieuw Account\r\n0 - Verlaat Evvo Meubels\r\nSelecteer een optie: ");
	}

}
