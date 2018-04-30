package rsvier.workshop.controller;

import rsvier.workshop.dao.PersonDAO;
import rsvier.workshop.dao.PersonDAOImp;
import rsvier.workshop.domain.Person;
import rsvier.workshop.domain.Person.PersonBuilder;
import rsvier.workshop.view.*;

public class PersonController extends Controller {

	PersonView personView = new PersonView();
	PersonDAO personDAO = new PersonDAOImp();
	EmployeeController employeeController = new EmployeeController();
	
	
	@Override
	public void runView() {
		personView.printHeaderMessage();
		personView.printUpdateUserDetailsMenu();
		//
	}
	

	public void personUpdateMenuSwitch(int menuNumber, Person person) {
		
		personView.printUpdateUserDetailsMenu();
		int choice = personView.getIntInput();
		
		switch(choice) {
		
		case 0: employeeController.runView();
				break;
		// pass in new name and builded person object to update the persons name
		case 1:updatePersonName(personUpdateName(), person);
		break;
			
		case 2:updatePersonLastName(personUpdateLastName(), person);
		break;
		
		case 3:updatePersondMiddleName(personUpdateMiddleName(), person);
		break;
			
		case 4:
			
		}
		
	}

	// Ask user for a new name that he wants to change
	public String personUpdateName() {
		personView.printAskUserForNewName();
		String newName = personView.getStringInput();	
		return newName;
	}
	
	public String personUpdateLastName() {
		personView.printAskUserForNewLastName();
		String lastName = personView.getStringInput();	
		return lastName;
	}
	
	public String personUpdateMiddleName() {
		personView.printAskUserForMiddleName();
		String additonalName = personView.getStringInput();	
		return additonalName;
	}
	// Use the the new name that has been given by the user to pass it in this method together with the returned person object from the search
	public void updatePersonName(String name, Person person) {
		
		PersonBuilder personBuilder = new PersonBuilder(person);
		personBuilder.name(name);
		Person newPersonWithNewName = personBuilder.build();
		personDAO.updatePerson(newPersonWithNewName);			
	}
	
	public void updatePersonLastName(String lastName, Person person) {
		
		PersonBuilder personBuilder = new PersonBuilder(person);
		personBuilder.lastName(lastName);
		Person newPersonWithNewName = personBuilder.build();
		personDAO.updatePerson(newPersonWithNewName);			
	}
	
	public void updatePersondMiddleName(String middleName, Person person) {
		
		PersonBuilder personBuilder = new PersonBuilder(person);
		personBuilder.middleName(middleName);
		Person newPersonWithNewName = personBuilder.build();
		personDAO.updatePerson(newPersonWithNewName);			
	}


}
