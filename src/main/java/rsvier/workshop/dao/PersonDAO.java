package rsvier.workshop.dao;

import java.util.*;
import rsvier.workshop.domain.*;


public interface PersonDAO {

	public List<Person> getAllPersons();
	public List<Person> getAllPersons(String personType);
	public Person getPersonByLastName(String lastname);
	public Person getPersonById(int accountId);
	public void createPerson(Person person);
	public void updatePerson(Person person);
	public void deletePerson(Person person);

}
