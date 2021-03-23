package fraus.javaproject.dao.Booking;

import fraus.javaproject.model.Booking;
import javafx.collections.ObservableList;

import java.time.LocalDate;
/**
 * Connect to directly to the database and fetch all data according to the functionality.
 * @author Khoa Nguyen Dang
 * @version 1.0.5
 * @deprecated it used to directly connect to the database without connecting to web-server to retrieve data
 * */
public interface BookingDAO {
    void insertBooking(int customerID, int roomNumber, LocalDate check_in, LocalDate check_out, String booking_method);
    ObservableList<Booking> getByCustomerId(int id);
    ObservableList<Booking> getAll();
}
