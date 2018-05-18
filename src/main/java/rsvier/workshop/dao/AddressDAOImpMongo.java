package rsvier.workshop.dao;

import java.net.UnknownHostException;
import java.util.*;
import java.util.logging.*;
import com.mongodb.*;
import rsvier.workshop.domain.*;
import rsvier.workshop.utility.*;

public class AddressDAOImpMongo implements AddressDAO {

	private Logger logger = LogConnection.getLogger();
	private DB db;
	private DBCollection collection;
	PersonDAO personDao = DAOFactory.getPersonDAO();

	public AddressDAOImpMongo() {

		try {
			DB db = DatabaseConnectionXML.getConnectionMongoDB();
			collection = db.getCollection("address");
		} catch (UnknownHostException e) {

			e.printStackTrace();
		}

	}

	@Override
	public List<Address> getAllAddresses() {

		List<Address> addressList = new ArrayList<>();

		try (DBCursor cursor = collection.find().skip(1)) {
			while (cursor.hasNext()) {
				DBObject object = cursor.next();
				BasicDBObject addressObj = (BasicDBObject) object;

				Address.AddressBuilder addressBuilder = new Address.AddressBuilder();
				addressBuilder.addressId(addressObj.getInt("_id"));
				addressBuilder.person(personDao.getPersonById(addressObj.getInt("person_id")));
				addressBuilder.addressType(addressObj.getString("address_type"));
				addressBuilder.streetName(addressObj.getString("street_name"));
				addressBuilder.houseNumber(addressObj.getInt("house_number"));
				addressBuilder.additionalHouseNumber(addressObj.getString("additional_house_number"));
				addressBuilder.postalCode(addressObj.getString("postal_code"));
				addressBuilder.city(addressObj.getString("city"));
				addressBuilder.country(addressObj.getString("country"));
				Address address = addressBuilder.build();
				addressList.add(address);
			}

		}
		return addressList;
	}

	@Override
	public List<Address> getAllAddressesForPerson(int personId) {
		List<Address> addressList = new ArrayList<>();
		DBObject query = new BasicDBObject("person_id",personId);

		try (DBCursor cursor = collection.find(query)) {
			while (cursor.hasNext()) {
				DBObject object = cursor.next();
				BasicDBObject addressObj = (BasicDBObject) object;

				Address.AddressBuilder addressBuilder = new Address.AddressBuilder();
				addressBuilder.addressId(addressObj.getInt("_id"));
				addressBuilder.person(personDao.getPersonById(addressObj.getInt("person_id")));
				addressBuilder.addressType(addressObj.getString("address_type"));
				addressBuilder.streetName(addressObj.getString("street_name"));
				addressBuilder.houseNumber(addressObj.getInt("house_number"));
				addressBuilder.additionalHouseNumber(addressObj.getString("additional_house_number"));
				addressBuilder.postalCode(addressObj.getString("postal_code"));
				addressBuilder.city(addressObj.getString("city"));
				addressBuilder.country(addressObj.getString("country"));
				Address address = addressBuilder.build();
				addressList.add(address);
			}

		}
		return addressList;
	}

	@Override
	public void createAddress(Address address) {
		
		double generatedIdDouble = (Double)getNextSequence("address_id");
		int generatedIdInt = (int)generatedIdDouble;
		
		DBObject newAddress = new BasicDBObject("_id",generatedIdInt)
				.append("person_id", address.getPerson().getPersonId())
				.append("address_type", address.getAddressType())
				.append("street_name", address.getStreetName())
				.append("house_number", address.getHouseNumber())
				.append("additional_house_number", address.getAdditionalHouseNumber())
				.append("postal_code", address.getPostalCode())
				.append("city", address.getCity())
				.append("country", address.getCountry());
				collection.insert(newAddress);

	}

	@Override
	public void updateAddress(Address address) {
		DBObject updateAddress = new BasicDBObject("_id",address.getAddressId())
				.append("person_id", address.getPerson().getPersonId())
				.append("address_type", address.getAddressType())
				.append("street_name", address.getStreetName())
				.append("house_number", address.getHouseNumber())
				.append("additional_house_number", address.getAdditionalHouseNumber())
				.append("postal_code", address.getPostalCode())
				.append("city", address.getCity())
				.append("country", address.getCountry());
				collection.update(new BasicDBObject("_id",address.getAddressId()), updateAddress);

	}

	@Override
	public void deleteAddressByPersonId(int personId) {
		collection.remove(new BasicDBObject("person_id",personId));

	}

	@Override
	public void deleteAdressByAddressId(Address address) {
		collection.remove(new BasicDBObject("_id",address.getAddressId()));

	}
	
	public Object getNextSequence(String address_id) {
		
		BasicDBObject find = new BasicDBObject();
		find.put("_id", address_id);
		BasicDBObject update = new BasicDBObject();
		update.put("$inc", new BasicDBObject("seq",1));
		DBObject obj = collection.findAndModify(find, update);
		return obj.get("seq");
	}

}
