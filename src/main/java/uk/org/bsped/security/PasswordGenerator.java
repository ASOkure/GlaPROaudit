package uk.org.bsped.security;

import lombok.extern.log4j.Log4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Log4j
public class PasswordGenerator {

//    public static void main(String[] args) {
//        String hashedPassword = hashPassword("");
//        log.info("Password for testing: " + hashedPassword);
//    }

    public static String hashPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }
}
