package org.example;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Patients")
public class Patients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int patientNum;
    private String first;
    private String last;
    private LocalDateTime dateOp;
    private int departmentID;

    public Patients() { }
    public Patients(int patientNum, String first, String last, LocalDateTime dateOp, int departmentID) {
        super();
        this.patientNum = patientNum;
        this.first = first;
        this.last = last;
        this.dateOp = dateOp;
        this.departmentID = departmentID;
    }
    public int getPatientNum () {return patientNum;}
    public void setPatientNum (int num) {patientNum = num;}
    public String getFirst () {return first;}
    public void setFirst (String str) {first = str;}
    public String getLast () {return last;}
    public void setLast (String str) {last = str;}
    public LocalDateTime getDateOp () {return dateOp;}
    public void setDateOp (LocalDateTime date) {dateOp = date;}
    public int getDepartmentID () {return departmentID;}
    public void setDepartmentID (int num) {departmentID = num;}
    public int getId() {
        return id;
    }
}