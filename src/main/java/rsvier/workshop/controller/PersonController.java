package rsvier.workshop.controller;

import rsvier.workshop.dao.PersonDAO;
import rsvier.workshop.dao.PersonDAOImp;
import rsvier.workshop.domain.Person;
import rsvier.workshop.domain.Person.PersonBuilder;
import rsvier.workshop.view.*;

public class PersonController extends Controller {

	PersonView personView = new PersonView();
	PersonDAO personDAO = new PersonDAOImp();
	
	
	@Override
	public void runView() {
		
		personView.printHeaderMessage();
		personView.printUpdateUserDetailsMenu();
		//
	}
	

	public void personUpdateMenuSwitch(Person person) {
		
		//Switch menu for updating personal data like name and address
		
		personView.printUpdateUserDetailsMenu();
		int choice = personView.getIntInput();
		
		switch(choice) {
		
			case 0: //exit and go back to employee menu

				MainController.setController(2);
				break;
		
			case 1:
				updatePersonName(personUpdateName(), person);
				break;
			
			case 2:	
				updatePersonLastName(personUpdateLastName(), person);
				break;
		
			case 3:	
				updatePersondMiddleName(personUpdateMiddleName(), person);
				break;
			
			case 4:
				//Update address
				break;
		}
		
	}
	
	
	//Methods for obtaining Strings from users
	
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
	
	//Methods fo updating the personal data with the obtained Strings
	
	
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
