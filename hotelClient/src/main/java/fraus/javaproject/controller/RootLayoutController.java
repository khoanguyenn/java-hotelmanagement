package fraus.javaproject.controller;

import fraus.javaproject.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
/**
 * Root layout, which is first initialized when opening the window, load the right-hand navigation bar
 * @author Xuan Huy Ha, Dang Khoa Nguyen
 * @version 1.0.1
 * */
public class RootLayoutController {
    private boolean isClicked = false;
    private Background buttonBg = new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY));
    private static MainApp mainApp;

    @FXML
    private Button homePage;

    @FXML
    private Button roomInfoPage;

    @FXML
    private Button customerInfoPage;

    @FXML
    private Button reservationPage;


    @FXML
    void onNavigate(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String buttonID = clickedButton.getId();



        if (buttonID.equals("homePage")) {
            mainApp.showHomepage();
        }
        if (buttonID.equals("roomInfoPage")) {
            mainApp.showRoomOverview();
        }
        if (buttonID.equals("customerInfoPage")) {
            mainApp.showCustomerManagement();
        }
        if (buttonID.equals("reservationPage")) {
            mainApp.showBookingForm();
        }

    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
