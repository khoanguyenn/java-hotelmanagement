package fraus.javaproject.controller;

import fraus.javaproject.model.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * Room dialog, display create, edit, delete a room dialog to interact with
 * @author Dang Khoa Nguyen
 * @version 1.0.5
 * */
public class RoomEditDialogController {
    private boolean okClicked;
    private Stage dialogStage;
    private Room room;

    @FXML
    private TextField roomNumberField;

    @FXML
    private ChoiceBox roomTypeChoice;

    @FXML
    private ChoiceBox roomStatusChoice;

    @FXML
    void handleCancel(ActionEvent event) {
        dialogStage.close();
    }

    @FXML
    void handleOk(ActionEvent event) {
        if (isInputValid()) {
            room.setNumber(roomNumberField.getText());
            room.setType(roomTypeChoice.getValue().toString());
            room.setStatus(roomStatusChoice.getValue().toString());
            
            okClicked = true;
            dialogStage.close();
        }
    }
    public boolean isOkClicked() {
        return okClicked;
    }
    /**
     * Sets the stage of this dialog
     *
     * @param dialogStage
     * */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the room of to this dialog
     *
     * @param room
     * */
    public void setRoom(Room room) {
        this.room = room;

        roomNumberField.setText(room.getNumber());
        roomTypeChoice.setValue(room.getType());
        roomStatusChoice.setValue(room.getStatus());
    }

    /**
     * Set the room number textfield editable
     *
     * @param state
     *
     * */
    public void setDisableTextField(boolean state) {
        roomNumberField.setDisable(state);
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (roomNumberField.getText() == null || roomNumberField.getText().length() == 0) {
            errorMessage += "No valid room number! \n";
        }
        if (roomTypeChoice.getValue() == null || roomTypeChoice.getValue().toString().length() == 0) {
            errorMessage += "No valid room type! \n";
        }
        if (roomStatusChoice.getValue() == null || roomStatusChoice.getValue().toString().length() == 0) {
            errorMessage += "No valid room status! \n";
        }
        //Print error message if any

        if (errorMessage.isBlank()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();

            return false;
        }
    }
}
