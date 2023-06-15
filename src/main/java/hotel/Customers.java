package hotel;

import java.sql.Timestamp;

public class Customers {
    private int customerId;
    private String name;
    private String phone;
    private String email;
    private int bookingId;
    private Timestamp bookingDate;

    public Customers(int customerId, String name, String phone, String email, int bookingId, Timestamp bookingDate) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
    }

    public Customers() {

    }

    // Các phương thức getter và setter của các trường

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Timestamp getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Timestamp bookingDate) {
        this.bookingDate = bookingDate;
    }
}
