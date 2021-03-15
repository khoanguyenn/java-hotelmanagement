package fraus.javaproject.controller;

import fraus.javaproject.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Root layout, which is first initialized when opening the window, load the right-hand navigation bar
 * @author Xuan Huy Ha, Dang Khoa Nguyen
 * @version 1.0.1
 * */
public class RootLayoutController {
    private static MainApp mainApp;

    @FXML
    void onNavigate(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String buttonID = clickedButton.getId();
        switch (buttonID) {
            case "homePage":
                mainApp.showHomepage();
                break;
            case "roomInfoPage":
                mainApp.showRoomOverview();
                break;
            case "customerInfoPage":
                mainApp.showCustomerManagement();
                break;
            case "reservationPage":
                mainApp.showBookingForm();
                break;
        }


    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
