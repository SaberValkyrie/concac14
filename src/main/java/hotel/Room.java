package hotel;

public class Room {
    private int room_id;
    private int room_type_id;
    private int hotel_id;
    private int room_number;
    private double price;
    private String description;
    private byte availability;
    private RoomType roomType;



    public Room(int roomId, int roomTypeId, int hotelId, int roomNumber, double price, String description, byte availability) {
        this.room_id = roomId;
        this.room_type_id = roomTypeId;
        this.hotel_id = hotelId;
        this.room_number = roomNumber;
        this.price = price;
        this.description = description;
        this.availability = availability;
    }

    public Room(int roomId, int roomNumber, double price, byte availability) {
        this.room_id = roomId;
        this.room_number = roomNumber;
        this.price = price;
        this.description = description;
        this.availability = availability;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getRoom_type_id() {
        return room_type_id;
    }

    public void setRoom_type_id(int room_type_id) {
        this.room_type_id = room_type_id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public int getRoom_number() {
        return room_number;
    }

    public void setRoom_number(int room_number) {
        this.room_number = room_number;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte getAvailability() {
        return availability;
    }

    public void setAvailability(byte availability) {
        this.availability = availability;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }
}
