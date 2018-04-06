package rsvier.workshop.dao;

import java.util.*;
import rsvier.workshop.domain.*;


public interface PersonDAO {

	public List<Person> getAllPersons();
	public List<Person> getAllPersons(String personType);
	public Person getPersonByLastName(String lastname);
	public Person getPersonByAccountId(int accountId);
	public Person getPersonById(int personId);
	public int createPerson(Person person);
	public void updatePerson(Person person);
	public void deletePerson(Person person);

}
