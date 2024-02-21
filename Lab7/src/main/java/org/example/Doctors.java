package org.example;
import javax.persistence.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
@Entity
@Table(name = "Doctors")
public class Doctors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String first;
    private String last;
    private String email;
    private String password;
    private String salt;

    public Doctors() { }
    public Doctors(String first, String last, String email, String password) {
        super();
        this.first = first;
        this.last = last;
        this.email = email;
        //now lets save the password in a hash table with salt :)
        //first generate salt:
        this.salt= generateSalt();
       try{
           this.password = hashPassword(password, salt);
       }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    public String getFirst () {return first;}
    public void setFirst (String str) {first = str;}
    public String getLast () {return last;}
    public void setLast (String str) {last = str;}
    public String getEmail () {return email;}
    public void setEmail (String str) {email = str;}
    public String getPassword () {return password;}
    public void setPassword (String str) {
        this.salt= generateSalt();
        try{
            this.password = hashPassword(str, salt);
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    public int getId() {
        return id;
    }
    @Override
    public String toString() {
        return "Id: " + id+ ", First Name: "+first+", Last name "+ last+", Email: "+email+", Password: "+password;
    }


    public static String hashPassword(String password, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");

        // Combine the password and salt
        String passwordWithSalt = password + salt;
        byte[] hashBytes = md.digest(passwordWithSalt.getBytes());

        // Convert the byte array to a base64-encoded string
        return Base64.getEncoder().encodeToString(hashBytes);
    }

    public static String generateSalt() {
        // Generate a random salt using SecureRandom
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);

        // Convert the salt byte array to a base64-encoded string
        return Base64.getEncoder().encodeToString(saltBytes);
    }
}