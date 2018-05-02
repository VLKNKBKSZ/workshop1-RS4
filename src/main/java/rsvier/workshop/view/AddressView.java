package rsvier.workshop.view;

public class AddressView extends View {

    public void printAskUserForStreetName() {
        System.out.print("Vul je (nieuwe) straatnaam in: ");
    }

    public void printAskUserForHouseNumber() {
        System.out.print("Vul je (nieuwe) huisnummer in: ");
    }

    public void printAskUserForAdditionalHouseNumber() {
        System.out.print("Vul je (nieuwe) huisnummertoevoeging in: ");
    }

    public void printAskUserForPostalCode() {
        System.out.print("Vul je (nieuwe) postcode in: ");
    }

    public void printAskUserForCity() {
        System.out.print("Vul je (nieuwe) plaatsnaam in: ");
    }

    public void printAskUserForCountry() {
        System.out.print("Vul je (nieuwe) land in: ");
    }

    public void printAskMailAndDeliverySame() {
        System.out.print("Is het leveradres hetzelfde als het postadres(vul 'J' in voor ja): ");
    }

    public void printAskMailAndInvoiceSame() {
        System.out.print("Is het factuuradres hetzelfde als het postadres(vul 'J' in voor ja): ");
    }

    public void printAskDeliveryAndInvoiceSame() {
        System.out.print("Is het factuuradres hetzelfde als het het leveradres(vul 'J' in voor ja): ");
    }
    
    public void printHeaderOfMailAddressInput() {
    	System.out.println("Vul a.u.b uw postadres gegevens in");
    }
    
    public void printHeaderOfDeliveryAddressInput() {
    	System.out.println("Vul a.u.b uw bezorg gegevens in");
    }

    public void printHeaderOfInvoiceInput() {
    	System.out.println("Vul a.u.b uw factuur gegevens in");
    }
    
    public void printAddressAreSuccesFullyCreatedAndSaved() {
    	System.out.println("Al uw adresgegevens zijn succesvol aangemaakt en opgeslagen.");
    }
    
    @Override
    public void printHeaderMessage() {
    	
    }

    @Override
    public void printMenuMessage() {

    }
}
