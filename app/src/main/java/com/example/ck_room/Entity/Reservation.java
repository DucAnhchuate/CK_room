package com.example.ck_room.Entity;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Reservation",
        foreignKeys = {
                @ForeignKey(entity = Train_status.class,
                        parentColumns = {"id"},
                        childColumns = {"Train_Id_class"},
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = User.class,
                        parentColumns = "EmailID",
                        childColumns = "EmailID",
                        onDelete = ForeignKey.CASCADE)
        })
public class Reservation {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String PNR;
    private int Train_Id_class;
    private String available_date;
    private String EmailID;
    private String Reservation_Date;
    private String Reservation_Status;
    public Reservation()
    {

    }

    public Reservation(String PNR, int Train_ID, String Available_Date, String EmailID, String Reservation_Date, String Reservation_Status) {
        this.PNR = PNR;
        this.Train_Id_class = Train_ID;
        this.available_date = Available_Date;
        this.EmailID = EmailID;
        this.Reservation_Date = Reservation_Date;
        this.Reservation_Status = Reservation_Status;
    }

    // Getters and setters...

    public String getPNR() {
        return PNR;
    }

    public void setPNR(String PNR) {
        this.PNR = PNR;
    }

    public int getTrain_ID() {
        return Train_Id_class;
    }

    public void setTrain_ID(int Train_ID) {
        this.Train_Id_class = Train_ID;
    }

    public String getAvailable_Date() {
        return available_date;
    }

    public int getTrain_Id_class() {
        return Train_Id_class;
    }

    public void setTrain_Id_class(int train_Id_class) {
        Train_Id_class = train_Id_class;
    }

    public void setAvailable_Date(String Available_Date) {
        this.available_date = Available_Date;
    }

    public String getEmailID() {
        return EmailID;
    }

    public void setEmailID(String EmailID) {
        this.EmailID = EmailID;
    }

    public String getReservation_Date() {
        return Reservation_Date;
    }

    public void setReservation_Date(String Reservation_Date) {
        this.Reservation_Date = Reservation_Date;
    }

    public String getReservation_Status() {
        return Reservation_Status;
    }

    public void setReservation_Status(String Reservation_Status) {
        this.Reservation_Status = Reservation_Status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvailable_date() {
        return available_date;
    }

    public void setAvailable_date(String available_date) {
        this.available_date = available_date;
    }
}