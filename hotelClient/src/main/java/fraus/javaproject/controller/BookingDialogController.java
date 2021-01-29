package fraus.javaproject.controller;


import com.google.gson.reflect.TypeToken;
import fraus.javaproject.api.Client;
import fraus.javaproject.dao.Booking.BookingDAO;
import fraus.javaproject.dao.Booking.BookingDAOImpl;
import fraus.javaproject.model.Booking;
import fraus.javaproject.model.Customer;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
public class BookingDialogController {
    private boolean okClicked = false;
    private Booking selectedBooking;
    private Stage dialogStage;
    //private BookingDAO bookingDAO = new BookingDAOImpl();
    private ObservableList<Booking> bookingList;
    private FilteredList<Booking> bookingFilteredList;

    @FXML
    private DatePicker checkin_picker;

    @FXML
    private DatePicker checkout_picker;

    @FXML
    private TableView<Booking> bookingTable;
    @FXML
    private TableColumn<Booking, String> bookingID_col;

    @FXML
    private TableColumn<Booking, String> checkin_col;

    @FXML
    private TableColumn<Booking, String> checkout_col;

    @FXML
    private TableColumn<Booking, String> bookingMethod_col;

    @FXML
    private TableColumn<Booking, String> roomNumber_col;

    @FXML
    void handleOk(ActionEvent event) {
            dialogStage.close();
    }

    @FXML
    void onFilter(ActionEvent event) {
        LocalDate checkinDatePicker = checkin_picker.getValue();
        LocalDate checkoutDatePicker = checkout_picker.getValue();
        bookingFilteredList.setPredicate(bookingPredicate(checkinDatePicker, checkoutDatePicker));
    }

    @FXML
    void showAllDate(ActionEvent actionEvent) {
        checkin_picker.setValue(null);
        checkout_picker.setValue(null);
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setDialogStage(Stage bookingDialog) {
        this.dialogStage = bookingDialog;
    }

    public void setBooking(Customer selectedCustomer) {

        String customerID = String.valueOf(selectedCustomer.getId());

        var customerBookingParams = new HashMap<String, String>() {{
           put("customer_id", customerID);
        }};

        //bookingList = bookingDAO.getByCustomerId(customerID);
        List<Booking> listOfBooking = new Client("bookings")
                .setMethod("GET")
                .setParams(customerBookingParams)
                .sendRequest()
                .mappingTo(new TypeToken<List<Booking>>(){}.getType());

        bookingList = FXCollections.observableArrayList(listOfBooking);

        //Wraps "bookingList" with FilteredList
        bookingFilteredList = new FilteredList<>(bookingList);

        renderBookingTable();
    }
    private void renderBookingTable() {
        bookingID_col.setCellValueFactory(new PropertyValueFactory<Booking, String>("id"));
        checkin_col.setCellValueFactory(new PropertyValueFactory<Booking, String>("checkinDate"));
        checkout_col.setCellValueFactory(new PropertyValueFactory<Booking, String>("checkoutDate"));
        bookingMethod_col.setCellValueFactory(new PropertyValueFactory<Booking, String>("bookingMethod"));
        roomNumber_col.setCellValueFactory(new PropertyValueFactory<Booking, String>("roomNumber"));

        bookingTable.setItems(bookingFilteredList);
    }

    private Predicate<Booking> bookingPredicate(LocalDate checkinDate, LocalDate checkoutDate) {
        return booking -> {
            if (checkinDate == null && checkoutDate == null) return true;

            else if (checkinDate != null && checkoutDate != null) {
                if ((booking.getCheckinDate().isAfter(checkinDate)
                        || booking.getCheckinDate().isEqual(checkinDate))
                        && (booking.getCheckoutDate().isBefore(checkoutDate)
                        || booking.getCheckoutDate().isEqual(checkoutDate))) {return true;};
            }
            else if (checkinDate != null && checkoutDate == null) {
                    if (booking.getCheckinDate().isAfter(checkinDate)
                            || booking.getCheckinDate().isEqual(checkinDate)) {return true;};
            }
            else if (checkoutDate != null && checkinDate == null) {
                        if (booking.getCheckoutDate().isBefore(checkoutDate)
                                || booking.getCheckoutDate().isEqual(checkoutDate)) {return true;};
            }
            return false;
        };
    }
}
