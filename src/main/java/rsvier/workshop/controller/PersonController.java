package rsvier.workshop.controller;

import rsvier.workshop.view.*;

public class PersonController extends Controller {

	PersonView personView = new PersonView();
	
	
	@Override
	public void runView() {
		personView.printHeaderMessage();
		personView.printUpdateUserDetailsMenu();
		//
	}
	
	
	public void personUpdateMenuSwitch(int menuNumber) {
		
		switch(menuNumber) {
			
		}
	}

}
