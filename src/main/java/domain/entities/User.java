package domain.entities;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import domain.dtos.requests.CreateUserRequest;
import domain.valueobjects.StringGenerator;

public class User implements Serializable {
    private static final int defaultSaltLength = 32;

    private String emailAddress;
    private byte[] passwordHash;
    private String passwordSalt;

    public User(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.passwordSalt = generatePasswordSalt();
        this.passwordHash = getPasswordHash(password, passwordSalt);
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public static User fromRequest(CreateUserRequest request) {
        User user = new User(request.emailAddress, request.password);
        return user;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    private static String generatePasswordSalt() {
        return StringGenerator.generate(defaultSaltLength);
    }

    private static byte[] getPasswordHash(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            return md.digest(password.getBytes(StandardCharsets.UTF_8));
        }
        catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }
}