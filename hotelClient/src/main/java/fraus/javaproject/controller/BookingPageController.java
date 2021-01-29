package fraus.javaproject.controller;

import com.google.gson.reflect.TypeToken;
import fraus.javaproject.MainApp;
import fraus.javaproject.api.Client;
import fraus.javaproject.dao.Booking.BookingDAO;
import fraus.javaproject.dao.Booking.BookingDAOImpl;
import fraus.javaproject.dao.Customer.CustomerDAO;
import fraus.javaproject.dao.Customer.CustomerDAOImpl;
import fraus.javaproject.dao.Room.RoomDAO;
import fraus.javaproject.dao.Room.RoomDAOImpl;
import fraus.javaproject.model.Customer;
import fraus.javaproject.model.Room;
import fraus.javaproject.utils.DateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class BookingPageController {
    private MainApp mainApp;
    private ObservableList<Room> roomList;

    @FXML
    private TextField firstNameTF;

    @FXML
    private TextField lastNameTF;

    @FXML
    private DatePicker dobTF;

    @FXML
    private TextField genderTF;

    @FXML
    private TextField telephoneNumberTF;

    @FXML
    private TextField addressTF;

    @FXML
    private TextField emailTF;

    @FXML
    private ComboBox roomTypeChoice;

    @FXML
    private ComboBox roomNumberChoice;

    @FXML
    private DatePicker checkinPicker;

    @FXML
    private DatePicker checkoutPicker;

    @FXML
    private ComboBox bookingMethodChoice;

    @FXML
    private Button addNew_button;
    private Customer selectedCustomer;

    @FXML
    void handleExistedCustomer() {
        selectedCustomer = mainApp.showCustomerDialog();
        if (selectedCustomer != null) {
            loadSelectedCustomer(selectedCustomer);
            addNew_button.setDisable(false);
        }
    }

    @FXML
    void handleaddNewCustomer() {
        clearAll();
        customerSelectedFromDatabase(false);
        selectedCustomer = null;
        addNew_button.setDisable(true);
    }
    @FXML
    void onRoomTypeChange(ActionEvent actionEvent) {
        //Create temporary room list
        ObservableList<String> newRoomList = FXCollections.observableArrayList();
        String selectedRoomType = roomTypeChoice.getSelectionModel().getSelectedItem().toString();

        //Clear all the previous values
        roomNumberChoice.getItems().clear();

        //Filter all room number according to room type
        for (Room room : roomList) {
            if (selectedRoomType.equals(room.getType())) {
                newRoomList.add(room.getNumber());
            }
        }

        //Set items to room number
        roomNumberChoice.setItems(newRoomList);

        //Set the first item to combo-box
        roomNumberChoice.setValue(newRoomList.get(0));
    }
    @FXML
    void onSubmit(ActionEvent event) {
        if (isInputValid()) {
            //Create a new customer to submit
            HashMap<String, String> customerParams;
            Customer insertCustomer;
            if (selectedCustomer == null) {
                customerParams = new HashMap<String, String>() {{
                    put("firstName", firstNameTF.getText());
                    put("lastName", lastNameTF.getText());
                    put("gender", genderTF.getText());
                    put("telephoneNumber", telephoneNumberTF.getText());
                    put("address", addressTF.getText());
                    put("email", emailTF.getText());
                    put("dob", DateUtil.format(dobTF.getValue()));
                }};
                insertCustomer = new Client("customers")
                        .setMethod("POST")
                        .setParams(customerParams)
                        .sendRequest().mappingTo(Customer.class);
            } else {
                insertCustomer = selectedCustomer;
            }
            //Insert & retrieve customer_id from customer table
            //CustomerDAO customerDAO = new CustomerDAOImpl();
            //int insertedID = customerDAO.insertCustomer(newCustomer);



            //Get update room type depends on BOOKING METHOD
            String updatedRoomStatus = "";
            if (bookingMethodChoice.getValue().toString().equals("On desk")) updatedRoomStatus = "Staying";
            if (bookingMethodChoice.getValue().toString().equals("Via telephone")) updatedRoomStatus = "Reserved";

            //Prepare a new updating room to update
            //Room updatedRoom = new Room(
            //        roomNumberChoice.getValue().toString(),
            //        roomTypeChoice.getValue().toString(),
            //        updatedRoomType
            //        );

            String finalUpdatedRoomStatus = updatedRoomStatus;
            var updatedRoom = new HashMap<String, String>() {{
                put("number", roomNumberChoice.getValue().toString());
                put("type", roomTypeChoice.getValue().toString());
                put("status", finalUpdatedRoomStatus);
            }};

            new Client("rooms")
                    .setMethod("PUT")
                    .setParams(updatedRoom)
                    .sendRequest();

            //Update new room
            //RoomDAO roomDAO = new RoomDAOImpl();
            //roomDAO.updateRoom(updatedRoom);

            //Upload all data to booking table
            //BookingDAO bookingDAO = new BookingDAOImpl();
            //bookingDAO.insertBooking(insertCustomer.getId(),
            //        roomNumberChoice.getValue().toString(),
            //        checkinPicker.getValue(),
            //        checkoutPicker.getValue(),
            //        bookingMethodChoice.getValue().toString());

            var updateBooking = new HashMap<String, String>() {{
                put("customerId", Integer.toString(insertCustomer.getId()));
                put("roomNumber", roomNumberChoice.getValue().toString());
                put("checkin", DateUtil.format(checkinPicker.getValue()));
                put("checkout", DateUtil.format(checkoutPicker.getValue()));
                put("method", bookingMethodChoice.getValue().toString());
            }};

            new Client("bookings")
                    .setMethod("POST")
                    .setParams(updateBooking)
                    .sendRequest();

            //Clear all textfield
            clearAll();
        }
    }

    private void loadSelectedCustomer(Customer selectedCustomer) {
        firstNameTF.setText(selectedCustomer.getFirstName());
        lastNameTF.setText(selectedCustomer.getLastName());
        dobTF.setValue(selectedCustomer.getDateOfBirth());
        telephoneNumberTF.setText(selectedCustomer.getTelephoneNumber());
        genderTF.setText(selectedCustomer.getGender());
        addressTF.setText(selectedCustomer.getAddress());
        emailTF.setText(selectedCustomer.getEmail());

        //Customer is selected from database
        customerSelectedFromDatabase(true);
    }

    private void customerSelectedFromDatabase(boolean isSelected) {
        if (isSelected) {
            firstNameTF.setEditable(false);
            lastNameTF.setEditable(false);
            dobTF.setEditable(false);
            telephoneNumberTF.setEditable(false);
            genderTF.setEditable(false);
            addressTF.setEditable(false);
            emailTF.setEditable(false);
        } else {
            firstNameTF.setEditable(true);
            lastNameTF.setEditable(true);
            dobTF.setEditable(true);
            telephoneNumberTF.setEditable(true);
            genderTF.setEditable(true);
            addressTF.setEditable(true);
            emailTF.setEditable(true);
        }
    };
    private void clearAll() {
        //Clear all customer info inputs
        emailTF.clear();
        addressTF.clear();
        telephoneNumberTF.clear();
        genderTF.clear();
        firstNameTF.clear();
        lastNameTF.clear();
        dobTF.setValue(null);

        //Clear all booking inputs
        bookingMethodChoice.setValue(null);
        checkoutPicker.setValue(null);
        checkinPicker.setValue(null);
        bookingMethodChoice.setValue(null);
    }

    @FXML
    public void initialize() {
        HashMap<String, String> availableParams = new HashMap<String, String>();
        availableParams.put("get_available", "true");
        //Get all AVAILABLE room when load fxml file
        //RoomDAO roomDAO = new RoomDAOImpl();
        //roomList = roomDAO.getAllRoomWith("Available");
        List<Room> roomListData = new Client("rooms")
                .setParams(availableParams)
                .setMethod("GET")
                .sendRequest()
                .mappingTo(new TypeToken<List<Room>>(){}.getType());
        roomList = FXCollections.observableArrayList(roomListData);

        //Disable add new customer button
        addNew_button.setDisable(true);

        //Hide datepicker
        dobTF.setOnMouseClicked(e -> {
            if(!dobTF.isEditable())
                dobTF.hide();
        });
    }


    private boolean isInputValid() {
        String emailPatternStr = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern emailPattern = Pattern.compile(emailPatternStr);

        String errorMessage = "";

        if (firstNameTF.getText() == null
                || firstNameTF.getText().length() == 0) {
            errorMessage += "No valid first name! \n";
        }
        if (lastNameTF.getText() == null
                || lastNameTF.getText().length() == 0) {
            errorMessage += "No valid last name! \n";
        }
        if (dobTF.getValue() == null
                || dobTF.getValue().isAfter(LocalDate.now())) {
            errorMessage += "No valid date of birth! \n";
        }
        if (genderTF.getText() == null
                || genderTF.getText().length() == 0) {
            errorMessage += "No valid gender! \n";
        }
        if (telephoneNumberTF.getText() == null
                || telephoneNumberTF.getText().length() != 10)
        {
            errorMessage += "No valid telephone number! \n";
        }
        if (addressTF.getText() == null
                || addressTF.getText().length() == 0) {
            errorMessage += "No valid address! \n";
        }
        if (emailTF.getText() == null
                || emailTF.getText().length() == 0
                || !emailPattern.matcher(emailTF.getText()).matches()) {
            errorMessage += "No valid email address! \n";
        }
        if (roomNumberChoice.getValue() == null) {
            errorMessage += "No valid room number \n";
        }
        if (roomTypeChoice.getValue() == null) {
            errorMessage += "No valid room type \n";
        }
        if (checkinPicker.getValue() == null) {
            errorMessage += "No valid check in date \n";
        }
        if (checkoutPicker.getValue() == null) {
            errorMessage += "No valid check out date \n";
        }
        if (bookingMethodChoice.getValue() == null) {
            errorMessage += "No valid booking method \n";
        }
        if (checkinPicker.getValue() == null) {
            errorMessage += "No check in date \n";
        } else if (checkinPicker.getValue().isBefore(LocalDate.now())) {
            errorMessage += "Check in date error \n";
        }
        if (checkoutPicker.getValue() == null) {
            errorMessage += "No check out date \n";
        } else if (checkoutPicker.getValue().isBefore(LocalDate.now())) {
            errorMessage += "Check out date error \n";
        }
        //Print error message if any

        if (errorMessage.isBlank()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
