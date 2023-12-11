package com.example.ck_room.Entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "Train_class", foreignKeys = {
        @ForeignKey(entity = Train.class,
                parentColumns = "train_id",
                childColumns = "train_id", onDelete = ForeignKey.CASCADE)})
public class Train_class {
        @PrimaryKey(autoGenerate = true)
        private int id;
    private int train_id;
    private double Economy_Fare;
    private int Economy_Passenger;
    private double Business_Fare;
    private int Business_Passenger;
    private double First_Fare;
    private int First_Passenger;
    private String takenSeats_Economy;
    private String takenSeats_Business;
    private String takenSeats_First;



    // Constructor
    public Train_class()
    {

    }

    public int getTrain_id() {
        return train_id;
    }

    public int getEconomy_Passenger() {
        return Economy_Passenger;
    }

    public double getBusiness_Fare() {
        return Business_Fare;
    }

    public int getBusiness_Passenger() {
        return Business_Passenger;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTrain_id(int train_id) {
        this.train_id = train_id;
    }

    public void setEconomy_Fare(double economy_Fare) {
        Economy_Fare = economy_Fare;
    }

    public void setEconomy_Passenger(int economy_Passenger) {
        Economy_Passenger = economy_Passenger;
    }

    public void setBusiness_Fare(double business_Fare) {
        Business_Fare = business_Fare;
    }

    public void setBusiness_Passenger(int business_Passenger) {
        Business_Passenger = business_Passenger;
    }

    public void setFirst_Fare(double first_Fare) {
        First_Fare = first_Fare;
    }

    public void setFirst_Passenger(int first_Passenger) {
        First_Passenger = first_Passenger;
    }

    public void setTakenSeats_Economy(String takenSeats_Economy) {
        this.takenSeats_Economy = takenSeats_Economy;
    }

    public void setTakenSeats_Business(String takenSeats_Business) {
        this.takenSeats_Business = takenSeats_Business;
    }

    public void setTakenSeats_First(String takenSeats_First) {
        this.takenSeats_First = takenSeats_First;
    }

    public double getFirst_Fare() {
        return First_Fare;
    }

    public int getFirst_Passenger() {
        return First_Passenger;
    }

    public String getTakenSeats_Economy() {
        return takenSeats_Economy;
    }

    public String getTakenSeats_Business() {
        return takenSeats_Business;
    }

    public String getTakenSeats_First() {
        return takenSeats_First;
    }

    public Train_class(int train_id, double Economy_Fare, int Economy_Passenger, double Bussiness_Fare, int Business_Passenger, double First_Fare, int First_Passenger) {
        this.train_id = train_id;
        this.Economy_Fare = Economy_Fare;
        this.Economy_Passenger = Economy_Passenger;
        this.Business_Fare = Bussiness_Fare;
        this.Business_Passenger = Business_Passenger;
        this.First_Fare = First_Fare;
        this.First_Passenger = First_Passenger;
        this.takenSeats_Economy = generateInitialSeatString(Economy_Passenger);
        this.takenSeats_Business = generateInitialSeatString(Business_Passenger);
        this.takenSeats_First = generateInitialSeatString(First_Passenger);


    }

    // Getters and setters...


    public double getEconomy_Fare() {
        return Economy_Fare;
    }

    public int getId() {
        return id;
    }

    private String generateInitialSeatString(int seatCount) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < seatCount; i++) {
            sb.append("0"); // 0 đại diện cho ghế chưa bị chặn
        }
        return sb.toString();
    }


    private static String setSeatStatus(String seatString, int seatIndex, boolean isTaken) {
        // Chuyển chuỗi ký tự thành mảng ký tự để thao tác dễ dàng
        char[] seats = seatString.toCharArray();

        // Đặt trạng thái của ghế tại seatIndex
        if (seatIndex >= 0 && seatIndex < seats.length) {
            seats[seatIndex] = isTaken ? '1' : '0';
        }

        // Chuyển mảng ký tự thành chuỗi ký tự và trả về
        return new String(seats);
    }

    public List<Integer> getAvailableSeats(String takenSeats) {
        List<Integer> availableSeats = new ArrayList<>();

        for (int i = 0; i < takenSeats.length(); i++) {
            if (takenSeats.charAt(i) == '0') {
                availableSeats.add(i + 1); // Thêm số ghế chưa bị chặn vào danh sách
            }

        }
        return availableSeats;
    }
    public List<Integer> getBookedSeats(String takenSeats) {
        List<Integer> Seats = new ArrayList<>();

        for (int i = 0; i < takenSeats.length(); i++) {
            if (takenSeats.charAt(i) != '0') {
                Seats.add(i + 1); // Thêm số ghế chưa bị chặn vào danh sách
            }

        }
        return Seats;
    }
}

/*
* public class Main {
    public static void main(String[] args) {
        // Tạo một đối tượng Train_class
        Train_class trainClass = new Train_class(1, 100.0, 10, 50.0, 20, 30.0, 30);

        // Lấy trạng thái ghế Class 1
        String takenSeatsClass1 = trainClass.getTakenSeats_Class1();
        System.out.println("Trạng thái ghế Class 1: " + takenSeatsClass1);

        // Đặt ghế thứ 5 của Class 1 thành bị chặn
        takenSeatsClass1 = setSeatStatus(takenSeatsClass1, 4, true);
        trainClass.setTakenSeats_Class1(takenSeatsClass1);

        // Lấy lại trạng thái ghế Class 1 sau khi đã đặt ghế thứ 5 thành bị chặn
        takenSeatsClass1 = trainClass.getTakenSeats_Class1();
        System.out.println("Trạng thái ghế Class 1 sau khi đặt ghế thứ 5 thành bị chặn: " + takenSeatsClass1);
    }

    // Phương thức để cập nhật trạng thái ghế trong chuỗi ký tự
    private static String setSeatStatus(String seatString, int seatIndex, boolean isTaken) {
        // Chuyển chuỗi ký tự thành mảng ký tự để thao tác dễ dàng
        char[] seats = seatString.toCharArray();

        // Đặt trạng thái của ghế tại seatIndex
        if (seatIndex >= 0 && seatIndex < seats.length) {
            seats[seatIndex] = isTaken ? '1' : '0';
        }

        // Chuyển mảng ký tự thành chuỗi ký tự và trả về
        return new String(seats);
    }
}
*
* */