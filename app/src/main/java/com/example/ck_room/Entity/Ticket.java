package com.example.ck_room.Entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity(tableName = "Ticket", foreignKeys = {
        @ForeignKey(entity = Passenger.class, parentColumns = "pnr", childColumns = "Passenger_Name", onUpdate = ForeignKey.CASCADE, onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Train.class, parentColumns = "train_id", childColumns = "train_id", onUpdate = ForeignKey.CASCADE, onDelete = ForeignKey.CASCADE)
})
public class Ticket {
    @PrimaryKey
    private int Ticket_No;
    private String Passenger_Name;
    private String source;
    private String destination;
    private String classType;
    private int train_id;

    // Constructor
    public Ticket()
    {

    }
    public Ticket(int ticket_No, String passenger_Name, String source, String destination, String classType, int train_id) {
        Ticket_No = ticket_No;
        Passenger_Name = passenger_Name;
        this.source = source;
        this.destination = destination;
        this.classType = classType;
        this.train_id = train_id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public int getTrain_id() {
        return train_id;
    }

    public void setTrain_id(int train_id) {
        this.train_id = train_id;
    }

    // Getters and setters
    public int getTicket_No() {
        return Ticket_No;
    }

    public void setTicket_No(int Ticket_No) {
        this.Ticket_No = Ticket_No;
    }

    public String getPassenger_Name() {
        return Passenger_Name;
    }

    public void setPassenger_Name(String Passenger_Name) {
        this.Passenger_Name = Passenger_Name;
    }

    public int getTrain_No() {
        return train_id;
    }

    public void setTrain_No(int Train_No) {
        this.train_id = Train_No;
    }


}