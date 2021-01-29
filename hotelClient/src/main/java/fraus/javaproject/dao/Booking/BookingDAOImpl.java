package fraus.javaproject.dao.Booking;

import fraus.javaproject.model.Booking;
import fraus.javaproject.model.Customer;
import fraus.javaproject.utils.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;

public class BookingDAOImpl implements BookingDAO {
    private ObservableList<Booking> bookingList = FXCollections.observableArrayList();
    private static Connection connection = DBUtil.getConnection();
    private static String getAllBooking = "SELECT booking.*, " +
            "customer.first_name," +
            "customer.last_name," +
            "customer.dob," +
            "customer.gender," +
            "customer.telephone_number," +
            "customer.address," +
            "customer.email"  +
            " from booking" +
            " join customer" +
            " on customer.customer_id = booking.customer_id";
    private static String insertQuery = "INSERT INTO booking (booking_id, room_number, customer_id, check_in, check_out, booking_method) " +
            "VALUE (UUID(), ?, ?, ?, ?, ?)";
    private static String getByCustomerIDQuery = "SELECT * FROM booking WHERE customer_id=%s";
    public ObservableList<Booking> getAll() {
        try(
                Statement statement = connection.createStatement();
                ResultSet res = statement.executeQuery(getAllBooking);
                ) {
               while (res.next()) {
                   //Booking parameters
                   String bookingID = res.getString("booking_id");
                   String roomNumber = res.getString("room_number");
                   String customerID = res.getString("customer_id");
                   LocalDate checkinDate = res.getDate("check_in").toLocalDate();
                   LocalDate checkoutDate = res.getDate("check_out").toLocalDate();
                   String bookingMethod = res.getString("booking_method");

                   //Customer parameters
                   int id = res.getInt("customer_id");
                   String firstName = res.getString("first_name");
                   String lastName = res.getString("last_name");
                   LocalDate dateOfBirth = res.getDate("dob").toLocalDate();
                   String gender = res.getString("gender");
                   String telephoneNumber = res.getString("telephone_number");
                   String address = res.getString("address");
                   String email = res.getString("email");

                   Customer customer = new Customer(id, firstName, lastName, gender, telephoneNumber, address, email, dateOfBirth);
                   Booking booking = new Booking(bookingID, roomNumber, customerID, checkinDate, checkoutDate, bookingMethod);

                   bookingList.add(booking);
               }
               return bookingList;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }
    public void insertBooking(int customerID, int roomNumber, LocalDate check_in, LocalDate check_out, String booking_method) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            //Prepare all parameters to insert query
            preparedStatement.setString(1, Integer.toString(roomNumber));
            preparedStatement.setString(2, Integer.toString(customerID));
            preparedStatement.setDate(3, Date.valueOf(check_in));
            preparedStatement.setDate(4, Date.valueOf(check_out));
            preparedStatement.setString(5, booking_method);

            preparedStatement.executeUpdate();

        } catch (SQLException sqlException) {
                sqlException.printStackTrace();
        }

    }

    @Override
    public ObservableList<Booking> getByCustomerId(int id) {
        ObservableList<Booking> bookingList = FXCollections.observableArrayList();
        try(
                Statement statement = connection.createStatement();
                ResultSet res = statement.executeQuery(String.format(getByCustomerIDQuery, id));
        ) {
            while(res.next()) {
                //Booking parameters
                String bookingID = res.getString("booking_id");
                String roomNumber = res.getString("room_number");
                String customerID = res.getString("customer_id");
                LocalDate checkinDate = res.getDate("check_in").toLocalDate();
                LocalDate checkoutDate = res.getDate("check_out").toLocalDate();
                String bookingMethod = res.getString("booking_method");

                Booking booking = new Booking(bookingID, roomNumber, customerID,checkinDate,checkoutDate, bookingMethod);
                bookingList.add(booking);
            }
            return bookingList;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }
}
