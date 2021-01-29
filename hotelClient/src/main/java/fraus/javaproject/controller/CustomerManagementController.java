package fraus.javaproject.controller;

import com.google.gson.reflect.TypeToken;
import fraus.javaproject.MainApp;
import fraus.javaproject.api.Client;
import fraus.javaproject.dao.Customer.CustomerDAO;
import fraus.javaproject.dao.Customer.CustomerDAOImpl;
import fraus.javaproject.model.Booking;
import fraus.javaproject.model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.List;
import java.util.function.Predicate;

public class CustomerManagementController {
    private FilteredList<Customer> customerFilteredList;
    private ObservableList<Customer> customerData;
    private ObservableList<Booking> matchedIDs = FXCollections.observableArrayList();
    private MainApp mainApp;

    public ObservableList<Customer> getCustomerData() {
        List<Customer> customerList = new Client("customers")
                .setMethod("GET")
                .sendRequest()
                .mappingTo(new TypeToken<List<Customer>>(){}.getType());

        //CustomerDAO customerDAO = new CustomerDAOImpl();
        //return customerDAO.getAllCustomer();
        return FXCollections.observableArrayList(customerList);
    }

    @FXML
    private TableView<Customer> bookingTable;

    @FXML
    private TableColumn<Customer, String> customerID_col;

    @FXML
    private TableColumn<Customer, String> firstName_col;

    @FXML
    private TableColumn<Customer, String> lastName_col;

    @FXML
    private TextField searchBar;

    @FXML
    private Label firstName_label;

    @FXML
    private Label lastName_label;

    @FXML
    private Label dob_label;

    @FXML
    private Label gender_label;

    @FXML
    private Label telephoneNumber_label;

    @FXML
    private Label address_label;

    @FXML
    private Label email_label;


    @FXML
    void handleShowBooking(ActionEvent actionEvent) {
        Customer selectedItem = bookingTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            mainApp.showBookingDialog(selectedItem);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
    @FXML
    void handleFilteredSearch(KeyEvent event) {
        customerFilteredList.setPredicate(customerPredicate(searchBar.getText()));
    }

    @FXML
    void handleShowDetails(MouseEvent event) {
        Customer selectedItem = bookingTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            showCustomerDetails(selectedItem);
        }
    }

    @FXML
    private void initialize() {
        showBookingTable();
    }

    public void showBookingTable() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                customerData = getCustomerData();
                customerFilteredList = new FilteredList<>(customerData);

                customerID_col.setCellValueFactory(new PropertyValueFactory<Customer, String>("id"));
                firstName_col.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
                lastName_col.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));

                bookingTable.setItems(customerFilteredList);
            }
        }).start();


    }

    public void showCustomerDetails(Customer customer) {
        if (customer != null) {
            firstName_label.setText(customer.getFirstName());
            lastName_label.setText(customer.getLastName());
            dob_label.setText(customer.getDateOfBirth().toString());
            gender_label.setText(customer.getGender());
            telephoneNumber_label.setText(customer.getTelephoneNumber());
            address_label.setText(customer.getAddress());
            email_label.setText(customer.getEmail());
        }
    }

    Predicate<Customer> customerPredicate(String searchText) {
        return customer -> {
            if (searchText == null || searchText.isEmpty()) return true;

            if (customer.getFirstName().toLowerCase().contains(searchText.toLowerCase())
                    || customer.getLastName().toLowerCase().contains(searchText.toLowerCase())
                    || Integer.toString(customer.getId()).contains(searchText.toLowerCase())) return true;

            return false;
        };
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
