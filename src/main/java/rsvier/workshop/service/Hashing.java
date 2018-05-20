package rsvier.workshop.service;

import org.mindrot.jbcrypt.BCrypt;

public class Hashing {

	/*
	 * Creates a new hashed password with the String provided by the user, the
	 * gensalt(12) number indicates the algorithm structure
	 */

	public String createHash(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt(12));
	}

	/*
	 * Checks if the password entered by the user matches the hash saved in the
	 * databse.
	 */

	public boolean checkPassword(String password, String hash) {
		return BCrypt.checkpw(password, hash);
	}
}
