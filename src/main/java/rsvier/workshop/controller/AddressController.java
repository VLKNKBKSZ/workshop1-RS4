package rsvier.workshop.controller;


import java.util.List;
import rsvier.workshop.controller.MainController.TypeOfController;
import rsvier.workshop.dao.AddressDAO;
import rsvier.workshop.dao.AddressDAOImp;
import rsvier.workshop.domain.Address;
import rsvier.workshop.domain.Person;
import rsvier.workshop.service.Validator;
import rsvier.workshop.domain.Address.AddressBuilder;
import rsvier.workshop.view.AddressView;

public class AddressController extends Controller{

	private AddressView addressView = new AddressView();
	private AddressDAO addressDAO = new AddressDAOImp();
	private Validator validator = new Validator();

	
	public void runView() {
		// TODO Auto-generated method stub
		
	}
	
	
	public void doCreateAddresses(Person person) {

		//First create mail address
		
		addressView.printRequestMailAddressInput();
		Address address = createNewAddress("mail", person);
		Address addressDelivery;
		addressDAO.createAddress(address);
		addressView.printAddressAreSuccessFullyCreatedAndSaved();
		
		// Setting the types of addresses (mail/invoice/delivery):

		addressView.printAskMailAndDeliverySame();

		if (addressView.confirmYesOrNo().equalsIgnoreCase("J")) {

			address.setAddressType("delivery");
			addressDAO.createAddress(address);
			addressView.printAddressAreSuccessFullyCreatedAndSaved();
			addressDelivery = address;

		} else {
			addressView.printRequestDeliveryAddressInput();
			addressDelivery = createNewAddress("delivery", person);
			addressDAO.createAddress(addressDelivery);
			addressView.printAddressAreSuccessFullyCreatedAndSaved();
		}

		addressView.printAskMailAndInvoiceSame();

		if (addressView.confirmYesOrNo().equalsIgnoreCase("J")) {

			address.setAddressType("invoice");
			addressDAO.createAddress(address);
			addressView.printAddressAreSuccessFullyCreatedAndSaved();
			return;
		}

		addressView.printAskDeliveryAndInvoiceSame();

		if (addressView.confirmYesOrNo().equalsIgnoreCase("J")) {

			addressDelivery.setAddressType("invoice");
			addressDAO.createAddress(addressDelivery);
			addressView.printAddressAreSuccessFullyCreatedAndSaved();
			
		} else {
			addressView.printRequestInvoiceAddressInput();
			addressDAO.createAddress(createNewAddress("invoice", person));
			addressView.printAddressAreSuccessFullyCreatedAndSaved();
		}
	}

	
	public void updateAddressTypeSwitch(Person person) {

		boolean updating = true;

		while (updating) {

			addressView.printUpdateAddressTypeMenu();
			int choice = addressView.getIntInput();

			switch (choice) {

				case 1: // update mail address
						updateAddressInDatabase(person, "mail");
						break;
				case 2: // update delivery address
						updateAddressInDatabase(person, "delivery");
						break;
				case 3: // update invoice address
						updateAddressInDatabase(person, "invoice");
						break;
				case 0: // back to previous menu
						updating = false;
						MainController.setController(TypeOfController.CUSTOMER);
						break;
				default: // back to this menu again
						addressView.printMenuInputIsWrong();
						updateAddressTypeSwitch( person);
						break;

			}
		}
	}

	
	public void updateAddressInDatabase(Person person, String addressType) {

		List<Address> addressList = addressDAO.getAllAddressesForPerson(person.getPersonId());
		Address oldAddress;
		
		for (Address address : addressList) {
			
			if (address.getAddressType().equals(addressType)) {
				
				oldAddress = address;
			
				AddressBuilder addressBuilder = new AddressBuilder(oldAddress);
				addressBuilder.person(person);
				addressBuilder.addressType(addressType);
				addressBuilder.streetName(addressUpdateStreetName());
				addressBuilder.houseNumber(addressUpdateHouseNumber());
				addressBuilder.additionalHouseNumber(addressUpdateAdditionalHouseNumber());
				addressBuilder.postalCode(addressUpdatePostalCode());
				addressBuilder.city(addressUpdateCity());
				addressBuilder.country(addressUpdateCountry());
				Address updatedAddress = addressBuilder.build();
				
				addressDAO.updateAddress(updatedAddress);
				addressView.printAddressSuccessfullyUpdated();
			}

		}
	}
	

	public Address createNewAddress(String addressType, Person person) {

		Address.AddressBuilder addressBuilder = new Address.AddressBuilder();
		addressBuilder.person(person);
		addressBuilder.addressType(addressType);
		addressBuilder.streetName(addressUpdateStreetName());
		addressBuilder.houseNumber(addressUpdateHouseNumber());
		addressBuilder.additionalHouseNumber(addressUpdateAdditionalHouseNumber());
		addressBuilder.postalCode(addressUpdatePostalCode());
		addressBuilder.city(addressUpdateCity());
		addressBuilder.country(addressUpdateCountry());

		return addressBuilder.build();
	}

	
	// Methods for input request for updating address:

	public String addressUpdateStreetName() {

		addressView.printAskUserForStreetName();
		return addressView.getStringInput();
	}

	public int addressUpdateHouseNumber() {

		addressView.printAskUserForHouseNumber();
		return addressView.getIntInput();
	}

	public String addressUpdateAdditionalHouseNumber() {

		addressView.printAskUserForAdditionalHouseNumber();
		return addressView.getStringInputCanBeNull();
	}

	public String addressUpdatePostalCode() {

		return requestAndValidatePostalCode();

	}

	public String addressUpdateCity() {

		addressView.printAskUserForCity();
		return addressView.getStringInput();
	}

	public String addressUpdateCountry() {

		addressView.printAskUserForCountry();
		return addressView.getStringInput();
	}

	public String requestAndValidatePostalCode() {
		
		String postalCode;
		
		do {
			addressView.printAskUserForPostalCode();
			postalCode = addressView.getStringInput();
			}
		
		while(!validator.validatePostalCode(postalCode));	
		
		return postalCode;
		
	}


}

