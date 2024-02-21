package org.example;
import javax.persistence.*;

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

    public Doctors() { }
    public Doctors(String first, String last, String email, String password) {
        super();
        this.first = first;
        this.last = last;
        this.email = email;
        // mshan alla ef7so sho m3 el cryptografia
        // makle 5ra
        this.password = password;
    }
    public String getFirst () {return first;}
    public void setFirst (String str) {first = str;}
    public String getLast () {return last;}
    public void setLast (String str) {last = str;}
    public String getEmail () {return email;}
    public void setEmail (String str) {email = str;}
    public String getPassword () {return password;}
    public void setPassword (String str) {password = str;}
    public int getId() {
        return id;
    }
    @Override
    public String toString() {
        return "Id: " + id+ ", First Name: "+first+", Last name "+ last+", Email: "+email+", Password: "+password;
    }
}