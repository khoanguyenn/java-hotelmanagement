package fraus.javaproject.dao.Room;

import fraus.javaproject.model.Room;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * Connect to directly to the database and fetch all data according to the functionality.
 * @author Khoa Nguyen Dang
 * @version 1.0.5
 * @deprecated it used to directly connect to the database without connecting to web-server to retrieve data.
 * */
public interface RoomDAO {

    void insertRoom(Room room);

    void updateRoom(Room room);

    void deleteRoom(Room room);

    ObservableList<Room> getAllRoomWith(String s);

    HashMap<String, Integer> getRoomCount();
}
