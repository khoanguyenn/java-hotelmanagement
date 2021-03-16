package fraus.javaproject.model;

import java.time.LocalDate;
/**
 * Encapsulating all information related to Booking's information, utilizes as model to map all booking's information retrieve from database
 * Contains
 * @author Phuong Nhu Truong Hoang
 * @version 1.0
 * */
public class Booking  {
    private String id;
    private String roomNumber;
    private String customerID;
    private LocalDate checkinDate;
    private LocalDate checkoutDate;
    private String bookingMethod;

    /**
     * Default constructor
     * */
    public Booking() {
        this(
                null,
                null,
                null,
                null,
                null,
                null);
    }

    /**
     * Booking constructor
     * */
    public Booking(String id,
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
