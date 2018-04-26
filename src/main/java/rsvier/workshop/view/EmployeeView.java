package rsvier.workshop.view;

public class EmployeeView extends View {

	@Override
	public void printHeaderMessage() {
		System.out.println("\n=========== Werknemer ===========\n");
		
	}

	@Override
	public void printMenuMessage() {
		System.out.println("1- Klanten beheren\n2- Producten beheren\n3- Bestellingen beheren\n0- Verlaat Evvo Meubels");
		
	}

}
