package rsvier.workshop.controller;

import rsvier.workshop.domain.Address;
import rsvier.workshop.domain.Person;
import rsvier.workshop.view.AddressView;
import rsvier.workshop.view.View;

public class AddressController {

    private AddressView addressView = new AddressView();

    public void doCreateAddresses(Person person) {
        Address address = createAddress("mail", person);

    }

    private Address createAddress(String addressType, Person person) {
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

    public String addressUpdateStreetName () {

        addressView.printAskUserForStreetName();
        return View.getStringInput();
    }

    public int addressUpdateHouseNumber () {

        addressView.printAskUserForHouseNumber();
        return View.getIntInput();
    }

    public String addressUpdateAdditionalHouseNumber () {

        addressView.printAskUserForAdditionalHouseNumber();
        return View.getStringInput();
    }

    public String addressUpdatePostalCode () {

        addressView.printAskUserForPostalCode();
        return View.getStringInput();
    }

    public String addressUpdateCity () {

        addressView.printAskUserForCity();
        return View.getStringInput();
    }

    public String addressUpdateCountry () {

        addressView.printAskUserForCountry();
        return View.getStringInput();
    }
}

