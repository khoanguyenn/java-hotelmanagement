package fraus.javaproject.dao.Booking;

import fraus.javaproject.model.Booking;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public interface BookingDAO {
    void insertBooking(int customerID, int roomNumber, LocalDate check_in, LocalDate check_out, String booking_method);
    ObservableList<Booking> getByCustomerId(int id);
    ObservableList<Booking> getAll();
}
