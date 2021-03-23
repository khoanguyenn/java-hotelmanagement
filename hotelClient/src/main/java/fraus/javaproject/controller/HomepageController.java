package fraus.javaproject.controller;

import com.google.gson.reflect.TypeToken;
import fraus.javaproject.MainApp;
import fraus.javaproject.api.Client;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.HashMap;
/**
 * Desktop homepage, display general hotel's information about the available rooms
 * @author Huy Ha Xuan
 * @version 1.0.5
 * */
public class HomepageController {
    private MainApp mainApp;
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
