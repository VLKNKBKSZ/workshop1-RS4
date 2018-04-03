package rsvier.workshop.dao;

import java.util.*;
import rsvier.workshop.domain.Person;

public interface PersonDAO {

	public List<Person> getAllPersons();
    public List<Person> getAllPersons(String personType);
    public Person getPerson(String lastname);
    public void createPerson(Person person);
    public void updatePerson(Person person);
    public void deletePerson(Person person);
}