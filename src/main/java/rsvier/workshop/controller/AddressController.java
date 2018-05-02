package rsvier.workshop.controller;

import java.util.ArrayList;
import java.util.List;

import rsvier.workshop.controller.MainController.TypeOfController;
import rsvier.workshop.dao.AddressDAO;
import rsvier.workshop.dao.AddressDAOImp;
import rsvier.workshop.domain.Address;
import rsvier.workshop.domain.Person;
import rsvier.workshop.domain.Address.AddressBuilder;
import rsvier.workshop.view.AddressView;
import rsvier.workshop.view.View;

public class AddressController {

	private AddressView addressView = new AddressView();
	private AddressDAO addressDAO = new AddressDAOImp();

	public void doCreateAddresses(Person person) {

		addressView.printHeaderOfMailAddressInput();
		Address address = createNewAddress("mail", person);
		Address addressDelivery;
		addressDAO.createAddress(address);

		// Setting the types of addresses (mail/invoice/delivery):

		addressView.printAskMailAndDeliverySame();

		if (addressView.confirmYesOrNo().equalsIgnoreCase("J")) {

			address.setAddressType("delivery");
			addressDAO.createAddress(address);
			addressDelivery = address;

		} else {
			addressView.printHeaderOfDeliveryAddressInput();
			addressDelivery = createNewAddress("delivery", person);
			addressDAO.createAddress(addressDelivery);

		}

		addressView.printAskMailAndInvoiceSame();

		if (addressView.confirmYesOrNo().equalsIgnoreCase("J")) {

			address.setAddressType("invoice");
			addressDAO.createAddress(address);
			addressView.printAddressAreSuccesFullyCreatedAndSaved();
			return;
		}

		addressView.printAskDeliveryAndInvoiceSame();

		if (addressView.confirmYesOrNo().equalsIgnoreCase("J")) {

			addressDelivery.setAddressType("invoice");
			addressDAO.createAddress(addressDelivery);

		} else {
			addressView.printHeaderOfInvoiceInput();
			addressDAO.createAddress(createNewAddress("invoice", person));

		}
	}

	public void updateAddressTypeSwitch(Person person) {

		boolean updating = true;

		while (updating) {

			addressView.printUpdateAddressType();
			int choice = View.getIntInput();

			switch (choice) {

			case 1:
				updateAddress(person, "mail");

				break;

			case 2:
				updateAddress(person, "delivery");
				break;

			case 3:
				updateAddress(person, "invoice");
				break;

			case 0:
				updating = false;
				MainController.setController(TypeOfController.CUSTOMER);
				break;

			}
		}
	}

	public void updateAddress(Person person, String addressType) {

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

	// Methods for updating address:

	public String addressUpdateStreetName() {

		addressView.printAskUserForStreetName();
		return View.getStringInput();
	}

	public int addressUpdateHouseNumber() {

		addressView.printAskUserForHouseNumber();
		return View.getIntInput();
	}

	public String addressUpdateAdditionalHouseNumber() {

		addressView.printAskUserForAdditionalHouseNumber();
		return View.getStringInput();
	}

	public String addressUpdatePostalCode() {

		addressView.printAskUserForPostalCode();
		return View.getStringInput();
	}

	public String addressUpdateCity() {

		addressView.printAskUserForCity();
		return View.getStringInput();
	}

	public String addressUpdateCountry() {

		addressView.printAskUserForCountry();
		return View.getStringInput();
	}

}

