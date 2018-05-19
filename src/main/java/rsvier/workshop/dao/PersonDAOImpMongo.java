
package rsvier.workshop.dao;

import java.util.*;
import java.util.logging.*;
import com.mongodb.*;
import rsvier.workshop.domain.*;
import rsvier.workshop.utility.*;



public class PersonDAOImpMongo implements PersonDAO{
	
	private Logger logger = LogConnection.getLogger();
	private DBCollection collection;
	
	public PersonDAOImpMongo() {
	
			try {
				DB db = DatabaseConnectionXML.getConnectionMongoDB();
				collection = db.getCollection("person");
			} catch (Exception e) {
				logger.log(Level.WARNING, "Host is unknown.", e);
				e.printStackTrace();
			}		
		
	}

	@Override
	public List<Person> getCustomerByLastName(String lastName) {
		List<Person> personList = new ArrayList<>();
		
		DBObject query = new BasicDBObject("last_name",lastName);
		
		try(DBCursor cursor = collection.find(query)){
			while(cursor.hasNext()) {
				DBObject object = cursor.next();
				BasicDBObject personObj = (BasicDBObject) object;
				
				int personId = personObj.getInt("_id");
				Account account = DAOFactory.getAccountDAO().getAccountById(personObj.getInt("account_id"));
				String name = personObj.getString("name");
				String retrievedLastName = personObj.getString("last_name");
				String middleName = personObj.getString("middle_name");
				
				Person.PersonBuilder personBuilder = new Person.PersonBuilder();
				personBuilder.personId(personId);
				personBuilder.account(account);
				personBuilder.name(name);
				personBuilder.lastName(retrievedLastName);
				personBuilder.middleName(middleName);
				Person person = personBuilder.build();
				personList.add(person);
				
			}
			
			return personList;
			
		} catch (MongoException e) {
			e.printStackTrace();

		}
		
		return null;
	}

	@Override
	public Person getPersonByAccountId(int accountId) {
		
		Person person = null;
		DBObject query = new BasicDBObject("account_id",accountId);
		
		try(DBCursor cursor = collection.find(query);){
			if(cursor.hasNext()) {
				DBObject object = cursor.next();
				BasicDBObject personObj = (BasicDBObject) object;
				int personId = personObj.getInt("_id");
				Account account = DAOFactory.getAccountDAO().getAccountById(personObj.getInt("account_id"));
				String name = personObj.getString("name");
				String retrievedLastName = personObj.getString("last_name");
				String middleName = personObj.getString("middle_name");
				
				Person.PersonBuilder personBuilder = new Person.PersonBuilder();
				personBuilder.personId(personId);
				personBuilder.account(account);
				personBuilder.name(name);
				personBuilder.lastName(retrievedLastName);
				personBuilder.middleName(middleName);
				 person = personBuilder.build();
			}
			
			return person;
			
		} catch (MongoException e) {
			e.printStackTrace();

		}
		
		return null;
	}

	@Override
	public Person getPersonById(int personId) {
		Person person = null;
		DBObject query = new BasicDBObject("_id",personId);
		
		try(DBCursor cursor = collection.find(query);){
			if(cursor.hasNext()) {
				DBObject object = cursor.next();
				BasicDBObject personObj = (BasicDBObject) object;
				int retrievedPersonId = personObj.getInt("_id");
				Account account = DAOFactory.getAccountDAO().getAccountById(personObj.getInt("_id"));
				String name = personObj.getString("name");
				String retrievedLastName = personObj.getString("last_name");
				String middleName = personObj.getString("middle_name");
				
				Person.PersonBuilder personBuilder = new Person.PersonBuilder();
				personBuilder.personId(retrievedPersonId);
				personBuilder.account(account);
				personBuilder.name(name);
				personBuilder.lastName(retrievedLastName);
				personBuilder.middleName(middleName);
				 person = personBuilder.build();
			}
			
			return person;
			
		} catch (MongoException e) {
			e.printStackTrace();

		}
		
		return null;
	}

	@Override
	public int createPerson(Person person) {
		
		int accountId = person.getAccount().getAccountId();
		String name = person.getName();
		String lastName = person.getLastName();
		String middleName = person.getMiddleName();
		
		double generatedIdDouble = (Double)getNextSequence("person_id");
		int generatedIdInt = (int)generatedIdDouble;
		
		DBObject newPerson = new BasicDBObject("_id",generatedIdInt)
				.append("account_id", accountId)
				.append("name", name)
				.append("last_name", lastName)
				.append("middle_name", middleName);
		
		try {
		collection.insert(newPerson);
		
		} catch (MongoException e) {
			e.printStackTrace();

		}
	
		return generatedIdInt;
		
	}

	@Override
	public void updatePerson(Person person) {
		
		int personId = person.getPersonId();
		int accountId = person.getAccount().getAccountId();
		String name = person.getName();
		String lastName = person.getLastName();
		String middleName = person.getMiddleName();
		
		DBObject updatePerson = new BasicDBObject("_id",personId)
				.append("account_id", accountId)
				.append("name", name)
				.append("last_name", lastName)
				.append("middle_name", middleName);
		try {
		
		collection.update(new BasicDBObject("_id",person.getPersonId()), updatePerson);
		
		} catch (MongoException e) {
			e.printStackTrace();

		}
		
	}

	@Override
	public void deletePerson(Person person) {
		try {
		collection.remove(new BasicDBObject("_id",person.getPersonId()));
		
		} catch (MongoException e) {
			e.printStackTrace();

		}
		
	}
	
	// Method of auto-increment Id
	public Object getNextSequence(String person_id) {
		
		BasicDBObject find = new BasicDBObject();
		find.put("_id", person_id);
		BasicDBObject update = new BasicDBObject();
		update.put("$inc", new BasicDBObject("seq",1));
		DBObject obj = collection.findAndModify(find, update);
		return obj.get("seq");
	}

}

