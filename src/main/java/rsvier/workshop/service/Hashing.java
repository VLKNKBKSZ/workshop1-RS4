package rsvier.workshop.service;

import org.mindrot.jbcrypt.BCrypt;

public class Hashing {

    public String createHash(String password) {
     return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public boolean checkPassword(String password, String hash){
        return BCrypt.checkpw(password, hash);
    }
}
