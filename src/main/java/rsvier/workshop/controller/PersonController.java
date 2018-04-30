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
		case 1:updatePersonNameWithNewName(personUpdateName(), person);
			
		case 2:
		
		case 3:
			
		case 4:
			
		}
		
	}

	// Ask user for a new name that he wants to change
	public String personUpdateName() {
		personView.printAskUserForName();
		String newName = personView.getStringInput();	
		return newName;
	}
	
	// Use the the new name that has been given by the user to pass it in this method together with the returned person object from the search
	public void updatePersonNameWithNewName(String newName, Person person) {
		
		PersonBuilder personBuilder = new PersonBuilder(person);
		personBuilder.name(newName);
		Person newPersonWithNewName = personBuilder.build();
		personDAO.updatePerson(newPersonWithNewName);			
	}


}
