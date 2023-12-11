package com.example.ck_room.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")

public class User {
    @PrimaryKey
    @ColumnInfo(name = "EmailID")
    @NonNull
    private String userName;

    @ColumnInfo (name = "firstName")
    private String firstName;

    @ColumnInfo (name = "lastName")
    private String lastName;
    @ColumnInfo(name = "pass")
    private String pass;

    @ColumnInfo(name = "phone")
    private String phone;

    @ColumnInfo(name = "dob")
    private String dob;

    @ColumnInfo(name = "gender")
    private String gender;

    public User()
    {

    }
    public User(String userName,String pass, String phone,String dob, String gender, String firstName, String lastName) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.userName = userName;
        this.phone = phone;
        this.dob = dob;
        this.gender = gender;
        this.pass = pass;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", pass='" + pass + '\'' +
                ", phone='" + phone + '\'' +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                '}';
    }
}