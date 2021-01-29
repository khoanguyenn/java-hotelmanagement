package fraus.javaproject.controller;

import com.google.gson.reflect.TypeToken;
import fraus.javaproject.MainApp;
import fraus.javaproject.api.Client;
import fraus.javaproject.dao.Room.RoomDAO;
import fraus.javaproject.dao.Room.RoomDAOImpl;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
public class HomepageController {
    private MainApp mainApp;
    //private HashMap<String, Integer> roomCount = new HashMap<String, Integer>();
    //private HashMap<String, Integer> getRoomCount() {
    //    RoomDAO roomDAO = new RoomDAOImpl();
    //    return roomDAO.getRoomCount();
    //}
    private HashMap<String, String> countParams = new HashMap<String, String>();

    @FXML
    private Label tripleRoomCount;

    @FXML
    private Label queenRoomCount;

    @FXML
    private Label twinRoomCount;

    @FXML
    private void initialize() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        countParams.put("get_count", "true");
                        HashMap<String, String> roomCount = new Client("rooms")
                                .setMethod("GET")
                                .setParams(countParams)
                                .sendRequest()
                                .mappingTo(new TypeToken<HashMap<String, String>>(){}.getType());

                        tripleRoomCount.setText(roomCount.get("Triple Room"));
                        queenRoomCount.setText(roomCount.get("Queen Room"));
                        twinRoomCount.setText(roomCount.get("Twin Room"));
                    }
                });
            }
        }).start();
    }

    public HomepageController() {
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
