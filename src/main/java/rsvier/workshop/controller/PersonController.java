package rsvier.workshop.controller;

import rsvier.workshop.dao.PersonDAO;
import rsvier.workshop.dao.PersonDAOImp;
import rsvier.workshop.domain.Account;
import rsvier.workshop.domain.Person;
import rsvier.workshop.domain.Person.PersonBuilder;
import rsvier.workshop.view.*;

import javax.xml.ws.soap.AddressingFeature;

import rsvier.workshop.controller.MainController.TypeOfController;

public class PersonController extends Controller {

	private PersonView personView = new PersonView();
	private PersonDAO personDAO = new PersonDAOImp();
	private AddressController addressController = new AddressController();

	@Override
	public void runView() {

		personView.printHeaderMessage();
		personView.printUpdateUserDetailsMenu();
		//
	}

	public void personUpdateMenuSwitch(Person person) {

		// Switch menu for updating personal data like name and address
		boolean updating = true;

		// Presenting updating options until users chooses '0' for exit

		while (updating) {
			personView.printUpdateUserDetailsMenu();
			int choice = personView.getIntInput();

			switch (choice) {

			case 1:  // update name
					updatePersonName(personUpdateName(), person);
					break;
			case 2: // update last name
					updatePersonLastName(personUpdateLastName(), person);
					break;
			case 3: // update middle name
					updatePersonMiddleName(personUpdateMiddleName(), person);
					break;
			case 4: // update address
					addressController.updateAddressTypeSwitch(person);
					break;
			case 0: // exit and go back to employee menu
					MainController.setController(TypeOfController.EMPLOYEE);
					updating = false;
					break;
			default: // back to this menu again
					personView.printMenuInputIsWrong();
					personUpdateMenuSwitch(person);
					
					break;
			}
		}
	}

	public Person doCreatePerson(Account account) {

		PersonBuilder personBuilder = new PersonBuilder();
		personBuilder.account(account);
		personBuilder.name(personUpdateName());
		personBuilder.lastName(personUpdateLastName());
		personBuilder.middleName(personUpdateMiddleName());
		Person person = personBuilder.build();
		person.setPersonId(personDAO.createPerson(person));

		return person;
	}

	// Methods for obtaining Strings from users

	public String personUpdateName() {

		personView.printAskUserForNewName();
		return personView.getStringInput();
	}

	public String personUpdateLastName() {

		personView.printAskUserForNewLastName();
		return personView.getStringInput();
	}

	public String personUpdateMiddleName() {

		personView.printAskUserForMiddleName();
		return personView.getStringInput();
	}

	// Methods for updating the personal data with the obtained Strings

	public void updatePersonName(String name, Person person) {

		PersonBuilder personBuilder = new PersonBuilder(person);
		personBuilder.name(name);
		Person newPersonWithNewName = personBuilder.build();
		personDAO.updatePerson(newPersonWithNewName);
		personView.printNameHasBeenUpdated();
	}

	public void updatePersonLastName(String lastName, Person person) {

		PersonBuilder personBuilder = new PersonBuilder(person);
		personBuilder.lastName(lastName);
		Person newPersonWithNewName = personBuilder.build();
		personDAO.updatePerson(newPersonWithNewName);
		personView.printLastNameHasBeenUpdated();
	}

	public void updatePersonMiddleName(String middleName, Person person) {

		PersonBuilder personBuilder = new PersonBuilder(person);
		personBuilder.middleName(middleName);
		Person newPersonWithNewName = personBuilder.build();
		personDAO.updatePerson(newPersonWithNewName);
		personView.printMiddleNameHasBeenUpdated();
	}

}