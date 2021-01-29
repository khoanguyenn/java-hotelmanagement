package fraus.javaproject;

import com.google.gson.reflect.TypeToken;
import fraus.javaproject.api.Client;
import fraus.javaproject.controller.*;
import fraus.javaproject.model.Customer;
import fraus.javaproject.model.Room;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.util.List;

/**
 * JavaFX App
 */
public class MainApp extends Application {
    private Stage primaryStage;
    private AnchorPane rootLayout;
    private AnchorPane rightAnchorPane;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Hotel Room Management");

        initRootLayout();
        showHomepage();
    }

    private void makeFadeTransition(Node node) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(500));
        fadeTransition.setNode(node);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    /**
     * Initializes the root layout
     * */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("RootLayout.fxml"));
            rootLayout = (AnchorPane) loader.load();

            //Set transition
            makeFadeTransition(rootLayout);

            //Set left pane
            rightAnchorPane = (AnchorPane) rootLayout.getChildren().get(1);

            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            scene.setFill(Color.TRANSPARENT);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showRoomOverview() {
        try {
            //Load the fxml room management file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("RoomManagement.fxml"));
            Pane roomOverview = (Pane) loader.load();

            //Set transition
            makeFadeTransition(roomOverview);

            rightAnchorPane.getChildren().setAll(roomOverview);

            // Give the controller access to the main app.
            RoomManagementController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean showRoomEditDialog(Room room, boolean isDisable) {
        try {
            //Load the fxml file and create a new stage for the popup dialog
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("RoomEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            //Create the dialog stage
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit room");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);

            //Create the dialog scene
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            //Set the room into the controller
            RoomEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setRoom(room);
            controller.setDisableTextField(isDisable);

            //Show the dialog and wait until the user close it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public void showCustomerManagement() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("CustomerManagement.fxml"));
            AnchorPane displayBooking = (AnchorPane) loader.load();

            //Set transition
            makeFadeTransition(displayBooking);

            rightAnchorPane.getChildren().setAll(displayBooking);

            // Give the controller access to the main app.
            CustomerManagementController controller = loader.getController();
            controller.setMainApp(this);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void showBookingForm() {
        try {
            //Load the booking page fxml file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("BookingPage.fxml"));
            AnchorPane bookingForm = (AnchorPane) loader.load(); //Casting AnchorPane -> Pane

            //Set transition
            makeFadeTransition(bookingForm);


            rightAnchorPane.getChildren().setAll(bookingForm);

            // Give the controller access to the main app.
            BookingPageController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Customer showCustomerDialog() {
        try {
            //Load the fxml file and create a new stage for the popup dialog
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("CustomerList.fxml"));
            AnchorPane customerScene = loader.load();

            //Create the dialog stage
            Stage customerDialog = new Stage();
            customerDialog.setTitle("Choose available customer");
            customerDialog.initModality(Modality.WINDOW_MODAL);
            customerDialog.initOwner(primaryStage);

            //Create the dialog scene
            Scene scene = new Scene(customerScene);
            customerDialog.setScene(scene);

            //Set the room into the controller
            CustomerListController controller = loader.getController();
            controller.setDialogStage(customerDialog);

            //Show the dialog and wait until the user close it
            customerDialog.showAndWait();

            return controller.getSelectedCustomer();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public boolean showBookingDialog(Customer selectedCustomer) {
        try {
            //Load the fxml file and create a new stage for the popup dialog
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("BookingDialog.fxml"));
            AnchorPane customerScene = (AnchorPane) loader.load();

            //Create the dialog stage
            Stage showBookingDialog = new Stage();
            showBookingDialog.setTitle("Booking information");
            showBookingDialog.initModality(Modality.WINDOW_MODAL);
            showBookingDialog.initOwner(primaryStage);

            //Create the dialog scene
            Scene scene = new Scene(customerScene);
            showBookingDialog.setScene(scene);

            //Set the room into the controller
            BookingDialogController controller = loader.getController();
            controller.setDialogStage(showBookingDialog);
            controller.setBooking(selectedCustomer);

            //Show the dialog and wait until the user close it
            showBookingDialog.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public void showHomepage() {
        //TODO: Load BookingPage.fxml
        try {
            //Load the booking page fxml file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("HomePage.fxml"));
            AnchorPane homepage = (AnchorPane) loader.load(); //Casting AnchorPane -> Pane

            //Set transition
            makeFadeTransition(homepage);


            rightAnchorPane.getChildren().setAll(homepage);

            // Give the controller access to the main app.
            HomepageController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Stage getPrimaryStage() {
        return primaryStage;
    }
    public static void main(String[] args) {
        launch();
    }

}