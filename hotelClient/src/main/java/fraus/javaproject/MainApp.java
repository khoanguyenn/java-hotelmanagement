package fraus.javaproject;

import fraus.javaproject.controller.*;
import fraus.javaproject.model.Customer;
import fraus.javaproject.model.Room;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;

/**
 * JavaFX Main application, contains all of the view method to display the root page, dialog, and other pages.
 * This application uses Model-View-Controller pattern to the efficiently display information to users.
 * @author Khoa Nguyen Dang, Huy Ha Xuan
 * @version 2.0.0
 */
public class MainApp extends Application {
    private Stage primaryStage;
    private AnchorPane rootLayout;
    private AnchorPane rightAnchorPane;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Hotel Room Management");
        primaryStage.getIcons().add(new Image("icon/house.png"));
        initRootLayout();
        showHomepage();
    }

    /**
     * Adding fading transition to the loading progress
     * @param node as the page to wrap the transition*/
    private void makeFadeTransition(Node node) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(500));
        fadeTransition.setNode(node);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }
    /**
     * This member method is utilized to load the required resource to the main view
     * @param resource as URL of the resource to be loaded
     * @return controller to the the specific page*/
    private Object layoutLoader(URL resource) throws IOException {
            //Load the fxml room management file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(resource);
            AnchorPane theAnchorPane = (AnchorPane) loader.load();

            //Set transition
            makeFadeTransition(theAnchorPane);

            rightAnchorPane.getChildren().setAll(theAnchorPane);

            return loader.getController();
    }
    /**
     * Initializes the root layout,
     * make the window and load the root view to the left-hand as menu.
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
            URL roomManagementURL = MainApp.class.getResource("RoomManagement.fxml");
            RoomManagementController controller = (RoomManagementController) layoutLoader(roomManagementURL);
            controller.setMainApp(this);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    public void showCustomerManagement() {
        try {
            URL customerManagementURL = MainApp.class.getResource("CustomerManagement.fxml");
            CustomerManagementController controller = (CustomerManagementController) layoutLoader(customerManagementURL);
            controller.setMainApp(this);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void showBookingForm() {
        try {
            URL bookingFormURL = MainApp.class.getResource("BookingPage.fxml");
            BookingPageController controller = (BookingPageController) layoutLoader(bookingFormURL);
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showHomepage() {
        try {
            URL homepageURL = MainApp.class.getResource("HomePage.fxml");
            HomepageController controller = (HomepageController) layoutLoader(homepageURL);
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showRoomEditDialog(Room room, boolean isDisable, String dialogName) {
        try {
            //Load the fxml file and create a new stage for the popup dialog
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("RoomEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            //Create the dialog stage
            Stage dialogStage = new Stage();
            dialogStage.setTitle(dialogName);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            dialogStage.getIcons().add(new Image("icon/house.png"));

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
            customerDialog.getIcons().add(new Image("icon/house.png"));

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
            showBookingDialog.getIcons().add(new Image("icon/house.png"));

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

    public Stage getPrimaryStage() {
        return primaryStage;
    }
    public static void main(String[] args) {
        launch();
    }

}