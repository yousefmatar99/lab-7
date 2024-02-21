package org.example;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@IdClass(MyEntityPK.class)
@Table(name = "PD")
public class PD {
    @Id
    private int patientID;
    @Id
    private int docID;
    private String description;
    private LocalDateTime dateOp;

    public PD() { }
    public PD (int patientID, int docID, LocalDateTime dateOp, String description) {
        super();
        this.patientID = patientID;
        this.docID = docID;
        this.dateOp = dateOp;
        this.description = description;
    }
    public String getDescription () {return description;}
    public void setDescription (String str) {description = str;}
    public LocalDateTime getDateOp () {return dateOp;}
    public void setDateOp (LocalDateTime date) {dateOp = date;}
    public int getPatientID() {return patientID;}
    public int getDocID () {return docID;}
}