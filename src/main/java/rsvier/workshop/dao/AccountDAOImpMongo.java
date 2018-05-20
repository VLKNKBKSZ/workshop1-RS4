package rsvier.workshop.dao;

import java.util.*;
import java.util.logging.*;
import com.mongodb.*;
import rsvier.workshop.domain.*;
import rsvier.workshop.utility.*;

public class AccountDAOImpMongo implements AccountDAO {

	private DBCollection collection;
	private Logger logger = LogConnection.getLogger();

	public AccountDAOImpMongo() {
		try {
			DB db = DatabaseConnectionXML.getConnectionMongoDB();
			collection = db.getCollection("account");
		} catch (Exception e) {
			logger.log(Level.WARNING, "SQL exception occured. Connection with MongoDB failed", e);
		}

	}

	@Override
	public List<Account> getAllAccounts() {
		List<Account> addressList = new ArrayList<>();
		// skip(1) means Find every document except the first document
		try (DBCursor cursor = collection.find().skip(1);) {
			while (cursor.hasNext()) {
				DBObject object = cursor.next();
				BasicDBObject accountObj = (BasicDBObject) object;

				int accountId = accountObj.getInt("_id");
				int accountType = accountObj.getInt("account_type");
				String email = accountObj.getString("email");
				String password = accountObj.getString("password");

				Account account = new Account();
				account.setAccountId(accountId);
				account.setAccountType(accountType);
				account.setEmail(email);
				account.setPassword(password);
				addressList.add(account);
			}

			return addressList;

		} catch (MongoException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);

		}

		return null;
	}

	@Override
	public int createAccount(Account account) {

		int accountType = account.getAccountType();
		String email = account.getEmail();
		String password = account.getPassword();

		// getNextSequence() return the generatedId as a Double object. Have to cast
		// object to double
		double generatedIdDouble = (Double) getNextSequence("account_id");
		// Cast double to int. Then you generatedId as int
		int generatedIdInteger = (int) generatedIdDouble;

		DBObject newAccount = new BasicDBObject("_id", generatedIdInteger).append("account_type", accountType)
				.append("email", email).append("password", password);

		try {
			collection.insert(newAccount);
			logger.log(Level.INFO, "Account successfully created.");

		} catch (MongoException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);

		}

		return generatedIdInteger;
	}

	@Override
	public void updateAccount(Account account) {

		int accountId = account.getAccountId();
		int accountType = account.getAccountType();
		String email = account.getEmail();
		String password = account.getPassword();

		DBObject updateAccount = new BasicDBObject("_id", accountId).append("account_type", accountType)
				.append("email", email).append("password", password);

		try {
			collection.update(new BasicDBObject("_id", account.getAccountId()), updateAccount);
			logger.log(Level.INFO, "Account successfully updated.");
		} catch (MongoException e) {
			logger.log(Level.WARNING, "SQL exception occured. Connection with MongoDB failed", e);

		}
	}

	@Override
	public void deleteAccount(Account account) {

		try {
			collection.remove(new BasicDBObject("_id", account.getAccountId()));
			logger.log(Level.INFO, "Account successfully deleted.");
		} catch (MongoException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);

		}
	}

	@Override
	public Account getAccountByEmail(String email) {

		Account account = null;
		DBObject query = new BasicDBObject("email", email);

		try (DBCursor cursor = collection.find(query);) {
			if (cursor.hasNext()) {
				DBObject object = cursor.next();
				BasicDBObject accountObj = (BasicDBObject) object;

				int accountId = accountObj.getInt("_id");
				int accountType = accountObj.getInt("account_type");
				String retrievedEmail = accountObj.getString("email");
				String password = accountObj.getString("password");

				account = new Account();
				account.setAccountId(accountId);
				account.setAccountType(accountType);
				account.setEmail(retrievedEmail);
				account.setPassword(password);

			}
			return account;

		} catch (MongoException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);

		}

		return null;
	}

	@Override
	public Account getAccountById(int accountId) {
		Account account = null;
		DBObject query = new BasicDBObject("_id", accountId);

		try (DBCursor cursor = collection.find(query);) {
			if (cursor.hasNext()) {
				DBObject object = cursor.next();
				{
					BasicDBObject accountObj = (BasicDBObject) object;

					int retrievedAccountId = accountObj.getInt("_id");
					int accountType = accountObj.getInt("account_type");
					String email = accountObj.getString("email");
					String password = accountObj.getString("password");

					account = new Account();
					account.setAccountId(retrievedAccountId);
					account.setAccountType(accountType);
					account.setEmail(email);
					account.setPassword(password);

				}
			}
			return account;
		}

		catch (MongoException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);

		}

		return null;
	}

	// Method of auto-increment Id
	public Object getNextSequence(String accountId) {

		BasicDBObject find = new BasicDBObject();
		find.put("_id", accountId);
		BasicDBObject update = new BasicDBObject();
		update.put("$inc", new BasicDBObject("seq", 1));
		DBObject obj = collection.findAndModify(find, update);
		// return Object
		return obj.get("seq");
	}

}
