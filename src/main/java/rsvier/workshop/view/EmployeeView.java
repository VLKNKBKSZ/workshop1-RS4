package rsvier.workshop.view;

public class EmployeeView extends View {

	@Override
	public void printHeaderMessage() {
		System.out.println("=========== Werknemer ===========");
		
	}

	@Override
	public void printMenuMessage() {
		System.out.println("1- Klanten beheren\n 2- Producten beheren\n 3- Bestellingen beheren\n 0- Verlaat Evvo Meubels");
		
	}

}
