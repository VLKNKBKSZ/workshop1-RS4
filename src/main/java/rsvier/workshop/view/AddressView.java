package rsvier.workshop.view;

public class AddressView extends View {

    
    
    @Override
    public void printHeaderMessage() {
    		System.out.println("\n=========== Werknemer  |  Adresbeheer ===========\n");
    }

    @Override
    public void printMenuMessage() {
    		
    }
    
    public void printUpdateAddressTypeMenu() {
    	System.out.println("\n1- Postadres wijzigen\n2- Bezorgadres wijzigen\n3- Factuuradres wijzigen\n0- Terug naar vorige menu\n");
    }
    
    public void printAskUserForStreetName() {
        System.out.print("\nVul je (nieuwe) straatnaam in: ");
    }

    public void printAskUserForHouseNumber() {
        System.out.print("\nVul je (nieuwe) huisnummer in: ");
    }

    public void printAskUserForAdditionalHouseNumber() {
        System.out.print("\nVul je (nieuwe) huisnummertoevoeging in: ");
    }

    public void printAskUserForPostalCode() {
        System.out.print("\nVul je (nieuwe) postcode in: ");
    }

    public void printAskUserForCity() {
        System.out.print("\nVul je (nieuwe) plaatsnaam in: ");
    }

    public void printAskUserForCountry() {
        System.out.print("\nVul je (nieuwe) land in: ");
    }

    public void printAskMailAndDeliverySame() {
        System.out.print("Is het bezorgadres hetzelfde als het postadres?\n");
    }

    public void printAskMailAndInvoiceSame() {
        System.out.print("\nIs het factuuradres hetzelfde als het postadres?\n");
    }

    public void printAskDeliveryAndInvoiceSame() {
        System.out.print("\nIs het factuuradres hetzelfde als het het bezorgadres?\n");
    }
    
    public void printRequestMailAddressInput() {
    		System.out.println("\nVul a.u.b uw postadres gegevens in.\n");
    }
    
    public void printRequestDeliveryAddressInput() {
    		System.out.println("\nVul a.u.b uw bezorg gegevens in.\n");
    }

    public void printRequestInvoiceAddressInput() {
    		System.out.println("\nVul a.u.b uw factuur gegevens in.\n");
    }
    
    public void printAddressAreSuccesFullyCreatedAndSaved() {
    		System.out.println("\nAl uw adresgegevens zijn succesvol aangemaakt en opgeslagen.\n");
    }
    
    public void printAddressSuccessfullyUpdated() {
    		System.out.println("\nDe gewijzigde gegevens zijn opgeslagen.");
    }
    
    public void printAddressSuccessfullyDeleted() {
		System.out.println("\nHet adres is verwijderd");
}
    
}
