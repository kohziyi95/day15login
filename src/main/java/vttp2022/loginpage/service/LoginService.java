package vttp2022.loginpage.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2022.loginpage.repository.LoginRepo;

@Service
public class LoginService {
    
    @Autowired
    private LoginRepo repo;

    public boolean authenticateUser(String username, String password){
        Optional<String> opt = repo.getUser(username);
        if (opt.isEmpty()){
            return false;
        }
        String authPassword = opt.get();

        String sha1 = "";
        try {
        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(password.getBytes("UTF-8"));
        sha1 = new BigInteger(1, crypt.digest()).toString(16);
        } catch (Exception e){
            e.printStackTrace();
        }
        return authPassword.equals(sha1);
    }
}
