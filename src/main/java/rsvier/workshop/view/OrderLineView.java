package rsvier.workshop.view;

public class OrderLineView extends View {
    @Override
    public void printHeaderMessage() {

    }

    @Override
    public void printMenuMessage() {
        System.out.println("1- Voeg product toe aan bestelling\n2- Bekijk huidige bestelling\n3- Bestelling plaatsen\n4- Annuleer bestelling\n");
    }
}
