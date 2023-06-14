package hotel;

public class Cart {
    private int id;
    private int userId;
    private int roomId;
    private int roomNumber;
    private String roomTypeName;

    private double price;
    private byte availability;
    private String hotelName;
    private Room room;
    private Hotel hotel;
    private User user;

    public Cart(int id, int userId, int roomId) {
        this.id = id;
        this.userId = userId;
        this.roomId = roomId;
    }

    public Cart(int cartId, int roomId, int userId, int roomNumber, String roomTypeName) {
        this.id = cartId;
        this.roomId = roomId;
        this.userId = userId;
        this.roomNumber = roomNumber;
        this.roomTypeName = roomTypeName;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public double getPrice() {
        return price;
    }

    public byte getAvailability() {
        return availability;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAvailability(byte availability) {
        this.availability = availability;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public Cart(int id, int roomId, int userId, int roomNumber, String roomTypeName, double price, byte availability, String hotelName) {
        this.id = id;
        this.roomId = roomId;
        this.userId = userId;
        this.roomNumber = roomNumber;
        this.roomTypeName = roomTypeName;
        this.price = price;
        this.availability =  availability;
        this.hotelName = hotelName;
    }
}
