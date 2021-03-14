package com.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Encapsulating all information related to Room's information, utilizes as model to map all booking's information retrieve from database
 * @author Phuong Nhu Truong Hoang, Dang Khoa Nguyen
 * @version 1.0
 * */
@Entity
@Table(name = "room")
public class Room {

    @Id
    @Column(name = "room_number")
    private String number;

    @Column(name = "room_type")
    private String type;

    @Column(name = "room_status")
    private String status;

    public Room() {
        this.number = null;
        this.type = null;
        this.status = null;
    };

    public Room(String number, String type, String status) {
        this.number = number;
        this.type = type;
        this.status = status;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("(Number= %s, Type= %s, Status= %s)", number, type, status);
    }
    public String toJSON() {
        return String.format("{\"number\":\"%s\", \"type\":\"%s\", \"status\":\"%s\"}"
        , number, type, status);
    }
}
