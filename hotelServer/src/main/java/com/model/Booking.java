package com.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;
/**
 * Encapsulating all information related to Booking's information, utilizes as model to map all booking's information retrieve from database
 * Contains
 * @author Nhu Truong Hoang Phuong
 * @version 1.0
 * */
@Entity
@Table(name = "booking")
public class Booking  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private int id;

    @Column(name = "room_number")
    private String roomNumber;

    @Column(name = "customer_id")
    private String customerID;

    @Column(name = "check_in")
    private LocalDate checkinDate;

    @Column(name = "check_out")
    private LocalDate checkoutDate;

    @Column(name = "booking_method")
    private String bookingMethod;

    /**
     * Default constructor
     * */
    public Booking() {
        this(
                0,
                null,
                null,
                null,
                null,
                null);
    }

    /**
     * Booking constructor
     * */
    public Booking(int id,
                   String roomNumber,
                   String customerID,
                   LocalDate checkinDate,
                   LocalDate checkoutDate,
                   String bookingMethod) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.customerID = customerID;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.bookingMethod = bookingMethod;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public LocalDate getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(LocalDate checkinDate) {
        this.checkinDate = checkinDate;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public String getBookingMethod() {
        return bookingMethod;
    }

    public void setBookingMethod(String bookingMethod) {
        this.bookingMethod = bookingMethod;
    }

}
