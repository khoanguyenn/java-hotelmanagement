package fraus.javaproject.dao.Room;

import fraus.javaproject.model.Room;
import fraus.javaproject.utils.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
/**
 * @author Dang Khoa Nguyen
 * @see fraus.javaproject.dao.Room.RoomDAO
 * @deprecated
 * */
public class RoomDAOImpl implements RoomDAO {
    private static Connection connection = DBUtil.getConnection();
    private ObservableList<Room> roomList = FXCollections.observableArrayList();

    private static String getAllQuery = "SELECT room_number, room_type, room_status FROM room";
    private static String roomStatusQuery = "SELECT room_number, room_type, room_status FROM room WHERE room_status='%s'";
    private static String addQuery = "INSERT INTO room (room_number, room_type, room_status) VALUES ('%s', '%s', '%s')";
    private static String updateQuery = "UPDATE room SET room_type = '%s', room_status = '%s' WHERE room_number = '%s'";
    private static String deleteQuery = "DELETE FROM room WHERE room_number = '%s'";
    private static String countRoomQuery = "SELECT room_type, COUNT(*) as room_count FROM room GROUP BY room_type ORDER BY room_type";

    /**
     * @param condition (String) conditional room type, leave NULL if retrieving all room
     * @return roomList (FXCollection)
     * */
    public ObservableList<Room> getAllRoomWith(String condition) {
        try {
            Statement statement = connection.createStatement();
            ResultSet res = null;

            //Check if any condition required
            if (condition != null) {
                res = statement.executeQuery(String.format(roomStatusQuery, condition));
            } else {
                res = statement.executeQuery(getAllQuery);
            }

            while (res.next()) {
                //Get all customers infos
                String roomNumber = res.getString("room_number");
                String roomType = res.getString("room_type");
                String roomStatus = res.getString("room_status");

                //Create a new customer
                Room room = new Room(roomNumber, roomType, roomStatus);

                //Add to the list
                roomList.add(room);
            }
            return roomList;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public HashMap<String, Integer> getRoomCount() {
        try {
            HashMap<String, Integer> roomCount = new HashMap<String, Integer>();

            Statement statement = connection.createStatement();
            ResultSet res = null;
            res = statement.executeQuery(countRoomQuery);

            int index = 0;
            Integer roomTypeCount[] = new Integer[3];
            while (res.next()) {
                roomTypeCount[index] = res.getInt("room_count");
                index++;
            }

            roomCount.put("triple_room", roomTypeCount[1]);
            roomCount.put("twin_room", roomTypeCount[2]);
            roomCount.put("queen_room", roomTypeCount[0]);

            return roomCount;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public void insertRoom(Room room) {
        String sqlString;

        //Get all room information
        String roomNumber = room.getNumber();
        String roomType = room.getType();
        String roomStatus = room.getStatus();

        sqlString = String.format(addQuery, roomNumber, roomType, roomStatus);

        DBUtil.executeQuery(sqlString);
    }

    @Override
    public void updateRoom(Room room) {
        try(
                Statement statement = connection.createStatement();
                ) {
            String sqlString;

            //Get all room information
            String roomNumber = room.getNumber();
            String roomType = room.getType();
            String roomStatus = room.getStatus();

            sqlString = String.format(updateQuery, roomType, roomStatus, roomNumber);
            statement.executeUpdate(sqlString);
        } catch (SQLException sqlException) {
                sqlException.printStackTrace();
        }
    }

    @Override
    public void deleteRoom(Room room) {
        String sqlString;

        //Get all room information
        String roomNumber = room.getNumber();

        sqlString = String.format(deleteQuery, roomNumber);

       DBUtil.executeQuery(sqlString);

    }

}
