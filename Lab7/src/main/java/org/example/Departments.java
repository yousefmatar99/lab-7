package org.example;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Departments")
public class Departments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String depName;
    private int doctorID;
    public Departments () {}
    public Departments(String depName, int doctorID) {
        super();
        this.depName = depName;
        this.doctorID = doctorID;
    }

    public String getDepName () {return depName;}
    public void setDepName (String str) {depName = str;}
    public int getDoctorID () {return doctorID;}
    public void setDoctorID (int num) {doctorID = num;}
    public int getId() {
        return id;
    }
}