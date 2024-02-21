package org.example;
import javax.persistence.*;

@Entity
@Table(name = "Departments")
public class Departments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String depName;
    @OneToOne
    @JoinColumn(name = "doctorID", referencedColumnName = "id")
    private Doctors doctor;
    public Departments () {}
    public Departments(String depName, Doctors doctor) {
        super();
        this.depName = depName;
        this.doctor = doctor;
    }

    public String getDepName () {return depName;}
    public void setDepName (String str) {depName = str;}
    public Doctors getDoctor () {return doctor;}
    public void setDoctor (Doctors  doc) {doctor=doc;}
    public int getId() {
        return id;
    }
    @Override
    public String toString() {
        return "Id: " + id+", Department name: "+depName;
    }
}