package org.example;
import javax.persistence.*;

@Entity
@Table(name = "Patients")
public class Patients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int patientNum;
    private String first;
    private String last;
    private String dateOp;
    @ManyToOne
    @JoinColumn(name = "departmentID", referencedColumnName = "id")
    private Departments department;

    public Patients() { }
    public Patients(int patientNum, String first, String last, String dateOp, Departments department) {
        super();
        this.patientNum = patientNum;
        this.first = first;
        this.last = last;
        this.dateOp = dateOp;
        this.department=department;
    }
    public int getPatientNum () {return patientNum;}
    public void setPatientNum (int num) {patientNum = num;}
    public String getFirst () {return first;}
    public void setFirst (String str) {first = str;}
    public String getLast () {return last;}
    public void setLast (String str) {last = str;}
    public String getDateOp () {return dateOp;}
    public void setDateOp (String date) {dateOp = date;}
    public Departments getDepartment () {return department;}
    public void setDepartment (Departments dep) {department=dep;}
    public int getId() {
        return id;
    }
    @Override
    public String toString() {
        return "Id: " + id+ ", Patient's num: "+patientNum+", First Name: "+ first+ ", Last Name: "+last+
                ", Treatment Date: "+dateOp;
    }
}
