package fraus.javaproject.dao.Room;

import fraus.javaproject.model.Room;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashMap;

public interface RoomDAO {

    void insertRoom(Room room);

    void updateRoom(Room room);

    void deleteRoom(Room room);

    ObservableList<Room> getAllRoomWith(String s);

    HashMap<String, Integer> getRoomCount();
}
