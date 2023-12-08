package com.example.ck_room.Entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import androidx.room.Entity;
import androidx.room.ForeignKey;

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
    private double Fare_Class1;
    private int Seat_Class1;
    private double Fare_Class2;
    private int Seat_Class2;
    private double Fare_Class3;
    private int Seat_Class3;
    private String takenSeats_Class1;
    private String takenSeats_Class2;
    private String takenSeats_Class3;



    // Constructor
    public Train_class()
    {

    }
    public Train_class(int train_id, double fare_Class1, int seat_Class1, double fare_Class2, int seat_Class2, double fare_Class3, int seat_Class3) {
        this.train_id = train_id;
        this.Fare_Class1 = fare_Class1;
        this.Seat_Class1 = seat_Class1;
        this.Fare_Class2 = fare_Class2;
        this.Seat_Class2 = seat_Class2;
        this.Fare_Class3 = fare_Class3;
        this.Seat_Class3 = seat_Class3;
        this.takenSeats_Class1 = generateInitialSeatString(seat_Class1);
        this.takenSeats_Class2 = generateInitialSeatString(seat_Class2);
        this.takenSeats_Class3 = generateInitialSeatString(seat_Class3);


    }

    // Getters and setters...

    public double getFare_Class1() {
        return Fare_Class1;
    }

    public void setFare_Class1(double Fare_Class1) {
        this.Fare_Class1 = Fare_Class1;
    }

    public int getSeat_Class1() {
        return Seat_Class1;
    }

    public void setSeat_Class1(int Seat_Class1) {
        this.Seat_Class1 = Seat_Class1;
    }

    public double getFare_Class2() {
        return Fare_Class2;
    }

    public void setFare_Class2(double Fare_Class2) {
        this.Fare_Class2 = Fare_Class2;
    }

    public int getSeat_Class2() {
        return Seat_Class2;
    }

    public void setSeat_Class2(int Seat_Class2) {
        this.Seat_Class2 = Seat_Class2;
    }

    public double getFare_Class3() {
        return Fare_Class3;
    }

    public void setFare_Class3(double Fare_Class3) {
        this.Fare_Class3 = Fare_Class3;
    }

    public int getSeat_Class3() {
        return Seat_Class3;
    }

    public void setSeat_Class3(int Seat_Class3) {
        this.Seat_Class3 = Seat_Class3;
    }

    public int getTrain_id() {
        return train_id;
    }

    public void setTrain_id(int train_id) {
        this.train_id = train_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String generateInitialSeatString(int seatCount) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < seatCount; i++) {
            sb.append("0"); // 0 đại diện cho ghế chưa bị chặn
        }
        return sb.toString();
    }

    public String getTakenSeats_Class1() {
        return takenSeats_Class1;
    }

    public void setTakenSeats_Class1(String takenSeats_Class1) {
        this.takenSeats_Class1 = takenSeats_Class1;
    }

    public String getTakenSeats_Class2() {
        return takenSeats_Class2;
    }

    public void setTakenSeats_Class2(String takenSeats_Class2) {
        this.takenSeats_Class2 = takenSeats_Class2;
    }

    public String getTakenSeats_Class3() {
        return takenSeats_Class3;
    }

    public void setTakenSeats_Class3(String takenSeats_Class3) {
        this.takenSeats_Class3 = takenSeats_Class3;
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