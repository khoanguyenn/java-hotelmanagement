package fraus.javaproject.controller;

import com.google.gson.reflect.TypeToken;
import fraus.javaproject.api.Client;
import fraus.javaproject.dao.Customer.CustomerDAO;
import fraus.javaproject.dao.Customer.CustomerDAOImpl;
import fraus.javaproject.model.Customer;
import fraus.javaproject.utils.DateUtil;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.List;
import java.util.function.Predicate;
/**
 * Show a simple version of available customers stored in the database
 * @author Xuan Huy Ha
 * @version 1.0.0
 * */
public class CustomerListController {
    private FilteredList<Customer> customerFilteredList;
    public Customer selectedCustomer;

    @FXML
    private TextField searchBar;

    @FXML
    private TableView<Customer> customerTable;

    @FXML
    private TableColumn<Customer, String> firstName_col;

    @FXML
    private TableColumn<Customer, String> lastName_col;

    @FXML
    private Label firstName_label;

    @FXML
    private Label lastName_label;

    @FXML
    private Label dob_label;

    @FXML
    private Label telephoneNumber_label;

    @FXML
    private Label gender_label;

    @FXML
    private Label address_label;

    @FXML
    private Label email_label;



    @FXML
    void handleOk(ActionEvent actionEvent) {
        selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            dialogStage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please select a customer");
            alert.setContentText("Select a customer from the table");
            alert.showAndWait();
        }
    }

    @FXML
    void handleCancel(ActionEvent actionEvent) {
        dialogStage.close();
    }

    @FXML
    void handleFilteredSearch(KeyEvent event) {
        String searchString = searchBar.getText();
        if (searchString != null) {
            customerFilteredList.setPredicate(customerPredicate(searchString));
        }
    }

    @FXML
    void showCustomerDetails(MouseEvent event) {
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        showCustomerDetails(selectedCustomer);
    }

    public CustomerListController() {
    }

    @FXML
    private void initialize() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ObservableList<Customer> customerData = getCustomerData();

                        //Wrap customer's data with filtered list
                        customerFilteredList = new FilteredList<>(customerData);

                        showCustomerTable(customerFilteredList);
                    }
                });
            }
        }).start();
    }

    private void showCustomerTable(ObservableList<Customer> customerList) {
        firstName_col.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
        lastName_col.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));

        customerTable.setItems(customerList);
    };

    private void showCustomerDetails(Customer toShowCustomer) {
        if (toShowCustomer != null) {
            firstName_label.setText(toShowCustomer.getFirstName());
            lastName_label.setText(toShowCustomer.getLastName());
            dob_label.setText(DateUtil.format(toShowCustomer.getDateOfBirth()));
            telephoneNumber_label.setText(toShowCustomer.getTelephoneNumber());
            gender_label.setText(toShowCustomer.getGender());
            address_label.setText(toShowCustomer.getAddress());
            email_label.setText(toShowCustomer.getEmail());
        }
    }
    private ObservableList<Customer> getCustomerData() {
        List<Customer> customerList =
                new Client("customers")
                        .setMethod("GET")
                        .sendRequest()
                        .mappingTo(new TypeToken<List<Customer>>(){}.getType());

        return FXCollections.observableArrayList(customerList);
    }

    Predicate<Customer> customerPredicate(String searchText) {
        return customer -> {
            if (searchText == null || searchText.isEmpty()) {
                return true;
            }
            if (customer.getFirstName().toLowerCase().contains(searchText.toLowerCase())
                    || customer.getLastName().toLowerCase().contains(searchText.toLowerCase())) {
                return true;
            }
            return false;
        };
    };
    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    private Stage dialogStage;
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


}
