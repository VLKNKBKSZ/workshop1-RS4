package rsvier.workshop.controller;

import rsvier.workshop.dao.AddressDAO;
import rsvier.workshop.dao.AddressDAOImp;
import rsvier.workshop.domain.Address;
import rsvier.workshop.domain.Person;
import rsvier.workshop.view.AddressView;
import rsvier.workshop.view.View;

public class AddressController {

    private AddressView addressView = new AddressView();
    private AddressDAO addressDAO = new AddressDAOImp();

    
    public void doCreateAddresses(Person person) {

        Address address = createNewAddress("mail", person);
        Address addressDelivery;
        addressDAO.createAddress(address);

        
        //Setting the types of addresses (mail/invoice/delivery):
        
        addressView.printAskMailAndDeliverySame();

        if (addressView.confirmYesOrNo().equals("J")) {

            address.setAddressType("delivery");
            addressDAO.createAddress(address);
            addressDelivery = address;

        } else {

            addressDelivery = createNewAddress("delivery", person);
            addressDAO.createAddress(addressDelivery);

        }

        addressView.printAskMailAndInvoiceSame();

        if (addressView.confirmYesOrNo().equals("J")) {

            address.setAddressType("invoice");
            addressDAO.createAddress(address);
            return;
        }

        addressView.printAskDeliveryAndInvoiceSame();

        if (addressView.confirmYesOrNo().equals("J")){

            addressDelivery.setAddressType("invoice");
            addressDAO.createAddress(addressDelivery);

        } else {

            addressDAO.createAddress(createNewAddress("invoice", person));

        }
    }
    

    private Address createNewAddress(String addressType, Person person) {
    	
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
    
    
    //Methods for updating address:

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

