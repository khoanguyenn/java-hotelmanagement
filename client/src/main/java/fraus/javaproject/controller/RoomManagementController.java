package fraus.javaproject.controller;

import com.google.gson.reflect.TypeToken;
import fraus.javaproject.MainApp;
import fraus.javaproject.api.Client;
import fraus.javaproject.model.Room;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

/**
 * Room management page, display all rooms information in tabular list, includes features such as create new, edit or delete
 * the rooms on the list
 * @author Dang Khoa Nguyen
 * @version 1.0.8
 * */
public class RoomManagementController {
    private MainApp mainApp;
    private FilteredList<Room> filteredRoomList;
    private Client client = new Client("rooms");
    @FXML
    private TableView<Room> roomTable;

    @FXML
    private TableColumn<Room, String> roomNumberCol;

    @FXML
    private TableColumn<Room, String> roomTypeCol;

    @FXML
    private TableColumn<Room, String> statusCol;

    @FXML
    private ComboBox searchRoomType;

    @FXML
    private ComboBox searchStatus;

    @FXML
    private void initialize() {
        showRooms();
    }

    //Handle the search bar
    @FXML
    void onFilter(ActionEvent event) {
        filteredRoomList.setPredicate(
                filterCondition(
                        searchRoomType.getValue().toString(),
                        searchStatus.getValue().toString()
                )
        );
    }

    //Handle the delete button when clicked
    @FXML
    void handleDeleteRoom(ActionEvent event) {
        Room selectedRoom = roomTable.getSelectionModel().getSelectedItem();
        if (selectedRoom != null) {
            //Put temporary room into HashMap
            HashMap<String, String> params = new HashMap<>();
            params.put("number", selectedRoom.getNumber());
            params.put("type", selectedRoom.getType());
            params.put("status", selectedRoom.getStatus());

            //Make request to back-end server
            client.setMethod("DELETE").setParams(params).sendRequest();
            showRooms();
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

    //Handle the edit button when clicked
    @FXML
    void handleEditRoom(ActionEvent event) {
        //Get selected room from room's table
        Room selectedRoom = roomTable.getSelectionModel().getSelectedItem();
        if (selectedRoom != null) {
            boolean okClicked = mainApp.showRoomEditDialog(selectedRoom, true);
            if (okClicked) {
                //Put temporary room into HashMap
                HashMap<String, String> params = new HashMap<>();
                params.put("number", selectedRoom.getNumber());
                params.put("type", selectedRoom.getType());
                params.put("status", selectedRoom.getStatus());

                //Make request to back-end server
                client.setMethod("PUT").setParams(params).sendRequest();
                showRooms();
            }
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

    //Handle the new button when clicked
    @FXML
    void handleNewRoom(ActionEvent event) {
        Room tempRoom = new Room();
        boolean okClicked = mainApp.showRoomEditDialog(tempRoom, false);
        if (okClicked) {
            //Put temporary room into HashMap
            HashMap<String, String> params = new HashMap<>();
            params.put("number", tempRoom.getNumber());
            params.put("type", tempRoom.getType());
            params.put("status", tempRoom.getStatus());

            //Make request to back-end server
            client.setMethod("POST").setParams(params).sendRequest();

            //Rerender the scene
            showRooms();
        }
    }

    //Display all of the room's information
    public void showRooms() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        //Wrap list of room into FilteredList
                        List<Room> listOfRoom = new Client("rooms")
                                .setMethod("GET")
                                .sendRequest()
                                .mappingTo(new TypeToken<List<Room>>(){}.getType());
                        filteredRoomList = new FilteredList<>(FXCollections.observableArrayList(listOfRoom));

                        //Data settings
                        roomNumberCol.setCellValueFactory(new PropertyValueFactory<Room, String>("number"));
                        roomTypeCol.setCellValueFactory(new PropertyValueFactory<Room, String>("type"));
                        statusCol.setCellValueFactory(new PropertyValueFactory<Room, String>("status"));

                        //Render all data to corresponding table
                        roomTable.setItems(filteredRoomList);
                    }
                });
            }
        }).start();
    }


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

    }
    private Predicate<Room> filterCondition(String roomType, String roomStatus) {
        return room -> {
            if (roomType.equals("All") && roomStatus.equals("All")) return true;

            if (roomType.equals("All") && roomStatus.equals(room.getStatus())) return true;
            if (roomStatus.equals("All") && roomType.equals(room.getType())) return true;

            if (roomType.equals(room.getType())
                    && roomStatus.equals(room.getStatus())) return true;
            return false;
        };
    }
}
