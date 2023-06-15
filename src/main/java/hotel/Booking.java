package hotel;

public class Booking {
    private int adults;
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private double total_price_from_web;

    public double getTotal_price_from_web() {
        return total_price_from_web;
    }

    public void setTotal_price_from_web(double total_price_from_web) {
        this.total_price_from_web = total_price_from_web;
    }

    public Booking(int bkId, int userId, int roomId, int htID,
                   String checkInDate, String checkOutDate,
                   String bookingDate, double totalPrice,
                   int adults, int children, String status, String hotelName) {
        this.id = bkId;
        this.user_id = userId;
        this.room_id = roomId;
        this.hotel_id = htID;
        this.hotelName = hotelName;
        this.check_in_date = checkInDate;
        this.check_out_date = checkOutDate;
        this.booking_date = bookingDate;
        this.total_price = totalPrice;
        this.adults = adults;
        this.children = children;
        this.status = status;
    }

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    private int children;
    public Booking(int bookingId, String status) {
        this.id = bookingId;
        this.status = status;
    }


    public Booking(int bkId, int userId, int roomId, int htID, String checkInDate, String checkOutDate, String bookingDate, double totalPrice, String status, String hotelName) {
        this.id = bkId;
        this.user_id = userId;
        this.room_id = roomId;
        this.hotel_id = htID;
        this.hotelName = hotelName;
        this.check_in_date = checkInDate;
        this.check_out_date = checkOutDate;
        this.booking_date = bookingDate;
        this.total_price = totalPrice;
        this.status = status;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    private String hotelName;

    private User user;
    private Room room;
    private int hotel_id;

    public Booking(int bkId, int userId, int roomId, int htID,String hotelName, String checkInDate, String checkOutDate, String bookingDate, double totalPrice, String status) {
        this.id = bkId;
        this.user_id = userId;
        this.room_id = roomId;
        this.hotel_id = htID;
        this.hotelName = hotelName;
        this.check_in_date = checkInDate;
        this.check_out_date = checkOutDate;
        this.booking_date = bookingDate;
        this.total_price = totalPrice;
        this.status = status;
    }

    public Booking(int parseInt, int roomId, int hotelId, String checkInDate, String checkOutDate, double totalPrice, String status) {
        this.user_id = parseInt;
        this.room_id = roomId;
        this.hotel_id = hotelId;
        this.check_in_date = checkInDate;
        this.check_out_date = checkOutDate;
        this.total_price = totalPrice;
        this.status = status;
    }


    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public Booking(int bookingId, int userId, int roomId, int htID,String checkInDate, String checkOutDate, double totalPrice, String status) {
        this.id = bookingId;
        this.user_id = userId;
        this.room_id = roomId;
        this.hotel_id = htID;
        this.check_in_date = checkInDate;
        this.check_out_date = checkOutDate;
        this.total_price = totalPrice;
        this.status = status;
    }

    public Booking() {

    }
    private String name;
    private String email;

    // Constructor và các phương thức getter đã tồn tại

    public void setFull_name(String full_name) {
        this.name = full_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public Booking(int userId, int roomId, String checkInDate, String checkOutDate,String bookingDate, double totalPrice, String status) {
        this.user_id = userId;
        this.room_id = roomId;
        this.check_in_date = checkInDate;
        this.check_out_date = checkOutDate;
        this.booking_date = bookingDate;
        this.status = status;
    }


    public Booking(int userId, int roomId, String checkInDate, String checkOutDate, double totalPrice, String status) {
        this.user_id = userId;
        this.room_id = roomId;
        this.check_in_date = checkInDate;
        this.check_out_date = checkOutDate;
this.total_price = totalPrice;
        this.status = status;
    }

    public Booking(int bookingId, int userId, int roomId, String checkInDate, String checkOutDate, double totalPrice) {
        this.user_id = userId;
        this.room_id = roomId;
        this.check_in_date = checkInDate;
        this.check_out_date = checkOutDate;
        this.total_price = totalPrice;
    }

    public Booking(int bookingId, int roomId, String checkInDate, String checkOutDate, String bookingDate, double totalPrice) {
        this.user_id = bookingId;
        this.room_id = roomId;
        this.check_in_date = checkInDate;
        this.check_out_date = checkOutDate;
        this.booking_date = bookingDate;
        this.total_price = totalPrice;
    }

    public Booking(int bkId, int userId, int roomId, String checkInDate, String checkOutDate, String bookingDate, double totalPrice) {
        this.id = bkId;
        this.user_id = userId;
        this.room_id = roomId;
        this.check_in_date = checkInDate;
        this.check_out_date = checkOutDate;
        this.booking_date = bookingDate;
        this.total_price = totalPrice;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getCheck_in_date() {
        return check_in_date;
    }

    public void setCheck_in_date(String check_in_date) {
        this.check_in_date = check_in_date;
    }

    public String getCheck_out_date() {
        return check_out_date;
    }

    public void setCheck_out_date(String check_out_date) {
        this.check_out_date = check_out_date;
    }

    public String getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(String booking_date) {
        this.booking_date = booking_date;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private int id;
    private int user_id;
    private int room_id;
    private String check_in_date;
    private String check_out_date;
    private String booking_date;
    private double total_price;
    private String status;
    public Booking(int id,int user_id,int room_id,String check_in_date,String check_out_date,String booking_date,double total_price,String status){
        this.id = id;
        this.user_id = user_id;
        this.room_id = room_id;
        this.check_in_date = check_in_date;
        this.check_out_date = check_out_date;
        this.booking_date = booking_date;
        this.total_price = total_price;
        this.status = status;
    }
}
