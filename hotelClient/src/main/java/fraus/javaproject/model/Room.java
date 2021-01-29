package fraus.javaproject.model;

public class Room {
    private String type;

    private String number;

    private String status;
    /**
     * Default constructor
     * */
    public Room() {
        this(null, null, null);
    }

    /**
     * Booking constructor
     * */
    public Room(String roomNumber,
                   String roomType,
                   String status) {
        this.number = roomNumber;
        this.type = roomType;
        this.status = status;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
