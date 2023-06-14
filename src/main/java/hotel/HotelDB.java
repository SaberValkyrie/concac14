package hotel;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HotelDB {
    @Resource(name="jdbc/hotel_booking_system")

    private DataSource dataSource;

    public HotelDB(DataSource theDataSource) {
        dataSource = theDataSource;
    }




    public List<Cart> getCartItemsByUserId(int userId) throws Exception {
        List<Cart> cartItems = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String url = "jdbc:mysql://localhost:3306/hotel_booking_system";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(url,username,password);


            // Create SQL query to retrieve cart items by user ID
            String sql = "SELECT c.*, r.room_number, rt.room_type_name FROM cart c " +
                    "INNER JOIN rooms r ON c.room_id = r.room_id " +
                    "INNER JOIN room_types rt ON r.room_type_id = rt.room_type_id " +
                    "WHERE c.user_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, String.valueOf(userId));

            // Execute the query
            resultSet = statement.executeQuery();

            // Process the result set
            while (resultSet.next()) {
                int cartId = resultSet.getInt("id");
                int roomId = resultSet.getInt("room_id");
                int roomNumber = resultSet.getInt("room_number");
                String roomTypeName = resultSet.getString("room_type_name");

                Cart cartItem = new Cart(cartId, roomId, userId, roomNumber, roomTypeName);
                cartItems.add(cartItem);
            }

        } finally {
            // Close the database resources
            close(connection, statement, resultSet);
        }

        return cartItems;
    }


    public void deleteCartItemById(String cartItemId) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            String url = "jdbc:mysql://localhost:3306/hotel_booking_system";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(url,username,password);
            String sql = "DELETE FROM cart WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cartItemId);
            stmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            close(conn, stmt, null);
        }
    }

//    public List<Booking> getBookings(int userId) throws Exception {
//        List<Booking> bookings = new ArrayList<>();
//
//        Connection connection = null;
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//
//        try {
//            String url = "jdbc:mysql://localhost:3306/hotel_booking_system";
//            String username = "root";
//            String password = "";
//            Class.forName("com.mysql.jdbc.Driver");
//
//            connection = DriverManager.getConnection(url, username, password);
//
//            String sql = "SELECT * FROM bookings WHERE user_id = ?";
//            statement = connection.prepareStatement(sql);
//            statement.setInt(1, userId);
//
//            resultSet = statement.executeQuery();
//
//            while (resultSet.next()) {
//                int bkId = resultSet.getInt("booking_id");
//                int roomId = resultSet.getInt("room_id");
//                String checkInDate = resultSet.getString("check_in_date");
//                String checkOutDate = resultSet.getString("check_out_date");
//                String bookingDate = resultSet.getString("booking_date");
//                double totalPrice = resultSet.getDouble("total_price");
//                String status = resultSet.getString("status");
//
//                Booking booking = new Booking(bkId, userId,roomId, checkInDate, checkOutDate, bookingDate,totalPrice,status);
//                bookings.add(booking);
//            }
//        } finally {
//            close(connection, statement, resultSet);
//        }
//
//        return bookings;
//    }

    public String getFullName(int userId) throws Exception {
        String fullName = null;

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String url = "jdbc:mysql://localhost:3306/hotel_booking_system";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(url, username, password);

            String sql = "SELECT full_name FROM users WHERE user_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                fullName = resultSet.getString("full_name");
            }
        } finally {
            close(connection, statement, resultSet);
        }

        return fullName;
    }

    public String getEmail(int userId) throws Exception {
        String email = null;

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String url = "jdbc:mysql://localhost:3306/hotel_booking_system";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(url, username, password);

            String sql = "SELECT email FROM users WHERE user_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                email = resultSet.getString("email");
            }
        } finally {
            close(connection, statement, resultSet);
        }

        return email;
    }
    public String getPhone(int userId) throws Exception {
        String phone = null;

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String url = "jdbc:mysql://localhost:3306/hotel_booking_system";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(url, username, password);

            String sql = "SELECT phone FROM users WHERE user_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                phone = resultSet.getString("phone");
            }
        } finally {
            close(connection, statement, resultSet);
        }

        return phone;
    }


    public String getPrice(int room_id) throws Exception {
        String phone = null;

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String url = "jdbc:mysql://localhost:3306/hotel_booking_system";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(url, username, password);

            String sql = "SELECT price FROM rooms WHERE room_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, room_id);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                phone = resultSet.getString("price");
            }
        } finally {
            close(connection, statement, resultSet);
        }

        return phone;
    }
//    public List<Booking> getBookings(int userId) throws Exception {
//        List<Booking> bookings = new ArrayList<>();
//
//        Connection connection = null;
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//
//        try {
//            String url = "jdbc:mysql://localhost:3306/hotel_booking_system";
//            String username = "root";
//            String password = "";
//            Class.forName("com.mysql.jdbc.Driver");
//
//            connection = DriverManager.getConnection(url, username, password);
//
//            String sql = "SELECT bookings.booking_id, bookings.user_id, bookings.room_id, bookings.check_in_date, bookings.check_out_date, bookings.booking_date, bookings.total_price, bookings.status, users.full_name, users.email " +
//                    "FROM bookings " +
//                    "JOIN users ON bookings.user_id = users.user_id " +
//                    "WHERE bookings.user_id = ?";
//            statement = connection.prepareStatement(sql);
//            statement.setInt(1, userId);
//
//            resultSet = statement.executeQuery();
//
//            while (resultSet.next()) {
//                int bkId = resultSet.getInt("booking_id");
//                int roomId = resultSet.getInt("room_id");
//                String checkInDate = resultSet.getString("check_in_date");
//                String checkOutDate = resultSet.getString("check_out_date");
//                String bookingDate = resultSet.getString("booking_date");
//                double totalPrice = resultSet.getDouble("total_price");
//                String status = resultSet.getString("status");
//                String fullName = resultSet.getString("full_name");
//                String email = resultSet.getString("email");
//
//                Booking booking = new Booking(bkId, userId, roomId, checkInDate, checkOutDate, bookingDate, totalPrice, status);
//                booking.setFull_name(fullName);
//                booking.setEmail(email);
//                bookings.add(booking);
//            }
//        } finally {
//            close(connection, statement, resultSet);
//        }
//
//        return bookings;
//    } //chạy dc

    public List<Booking> getBookings(int userId) throws Exception {
        List<Booking> bookings = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String url = "jdbc:mysql://localhost:3306/hotel_booking_system";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(url,username,password);
            String sql = "SELECT b.booking_id, b.user_id, b.room_id, b.check_in_date, b.check_out_date, b.booking_date, b.status, r.price "
                    + "FROM bookings b "
                    + "JOIN rooms r ON b.room_id = r.room_id "
                    + "WHERE b.user_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int bkId = resultSet.getInt("booking_id");
                int roomId = resultSet.getInt("room_id");
                String checkInDate = resultSet.getString("check_in_date");
                String checkOutDate = resultSet.getString("check_out_date");
                String bookingDate = resultSet.getString("booking_date");
                double totalPrice = resultSet.getDouble("price");
                String status = resultSet.getString("status");

                Booking booking = new Booking(bkId, userId, roomId, checkInDate, checkOutDate, bookingDate, totalPrice, status);
                bookings.add(booking);
            }
        } finally {
            close(connection, statement, resultSet);
        }

        return bookings;
    }



    public void addBooking(Booking booking) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            String url = "jdbc:mysql://localhost:3306/hotel_booking_system";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(url,username,password);


            String checkInDateString = booking.getCheck_in_date(); // Chuỗi đại diện cho ngày check-in
            String checkOutDateString = booking.getCheck_out_date(); // Chuỗi đại diện cho ngày check-out

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date checkInDate = dateFormat.parse(checkInDateString);
            java.util.Date checkOutDate = dateFormat.parse(checkOutDateString);


            String sql = "INSERT INTO bookings (user_id, room_id, check_in_date, check_out_date, total_price) " +
                    "VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, booking.getUser_id());
            stmt.setInt(2, booking.getRoom_id());
            stmt.setDate(3, new java.sql.Date(checkInDate.getTime()));
            stmt.setDate(4, new java.sql.Date(checkOutDate.getTime()));
            stmt.setDouble(5, booking.getTotal_price());

            stmt.executeUpdate();
        } finally {
            close(conn, stmt, null);
        }
    }


    public void updateTotalPrice(List<Booking> bookings, double totalPrice) throws SQLException {
            // Kết nối tới cơ sở dữ liệu
            Connection conn = null;
            PreparedStatement stmt = null;

            try {
                String url = "jdbc:mysql://localhost:3306/hotel_booking_system";
                String username = "root";
                String password = "";
                Class.forName("com.mysql.jdbc.Driver");

                conn = DriverManager.getConnection(url,username,password);
                // Cập nhật giá trị total_price cho các bản ghi đặt phòng
                String sql = "UPDATE bookings SET total_price = ? WHERE booking_id = ?";
                stmt = conn.prepareStatement(sql);

                // Thực hiện cập nhật total_price cho từng bản ghi đặt phòng
                for (Booking booking : bookings) {
                    stmt.setDouble(1, totalPrice);
                    stmt.setInt(2, booking.getId());
                    stmt.executeUpdate();
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } finally {
                // Đóng kết nối và tài nguyên
                close(conn, stmt, null);
            }
        }

    public void deleteCartItemsByUserId(int userId,int roomId) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Get database connection
            String url = "jdbc:mysql://localhost:3306/hotel_booking_system";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(url,username,password);
            String sql = "DELETE FROM cart WHERE user_id = ? AND room_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setInt(2,roomId);
            stmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            close(conn, stmt, null);
        }
    }





    public Room getRoomById(int roomId) throws Exception {
        Room room = null;

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Get database connection
            String url = "jdbc:mysql://localhost:3306/hotel_booking_system";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(url,username,password);

            // Create SQL query to retrieve room by ID
            String sql = "SELECT r.*, rt.room_type_name, rt.description FROM rooms r " +
                    "INNER JOIN room_types rt ON r.room_type_id = rt.room_type_id " +
                    "WHERE r.room_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, roomId);

            // Execute the query
            resultSet = statement.executeQuery();

            // Process the result set
            if (resultSet.next()) {
                int roomTypeId = resultSet.getInt("room_type_id");
                int hotelId = resultSet.getInt("hotel_id");
                int roomNumber = resultSet.getInt("room_number");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");
                byte availability = resultSet.getByte("availability");
                String roomTypeName = resultSet.getString("room_type_name");

                RoomType roomType = new RoomType(roomTypeId, roomTypeName, description);
                room = new Room(roomId, roomTypeId, hotelId, roomNumber, price, description, availability);
            }

        } finally {
            // Close the database resources
            close(connection, statement, resultSet);
        }

        return room;
    }




    public List<Hotel> getHotel() throws Exception {
        List<Hotel> students = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try{
            String url = "jdbc:mysql://localhost:3306/hotel_booking_system";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            myConn = DriverManager.getConnection(url,username,password);

            String sql = "select * from hotels";

            myStmt = myConn.createStatement();

            myRs = myStmt.executeQuery(sql);

            while (myRs.next()){
                int id = myRs.getInt("hotel_id");
                String hotel_name = myRs.getString("hotel_name");
                String address = myRs.getString("address");
                String phone = myRs.getString("phone");
                String email = myRs.getString("email");

                Hotel hotel = new Hotel(id, hotel_name, address, phone,email);

                students.add(hotel);
            }
            return students;
        }
        finally {
            close(myConn, myStmt, myRs);
        }
    }

    public List<Hotel> searchHotelsByName(String hotelName) throws Exception {
        List<Hotel> hotelList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String url = "jdbc:mysql://localhost:3306/hotel_booking_system";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, username, password);

            String sql = "SELECT * FROM hotels WHERE hotel_name LIKE ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + hotelName + "%");

            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("hotel_id");
                String name = rs.getString("hotel_name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String email = rs.getString("email");

                Hotel hotel = new Hotel(id, name, address, phone, email);
                hotelList.add(hotel);
            }
        } finally {
            close(conn, stmt, rs);
        }

        return hotelList;
    }

    public List<Booking> getBooking(int bookingId) throws Exception {
        List<Booking> bookings = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Get a database connection
            connection = dataSource.getConnection();

            // Create SQL query to retrieve booking details
            String sql = "SELECT * FROM bookings WHERE booking_id = ?";
            statement = connection.prepareStatement(sql);

            // Set the parameter values
            statement.setInt(1, bookingId);

            // Execute the query
            resultSet = statement.executeQuery();

            // Process the result set
            while (resultSet.next()) {
                int id = resultSet.getInt("booking_id");
                String status = resultSet.getString("status");

                // Create a Booking object
                Booking booking = new Booking(id, status);

                // Add the booking to the list
                bookings.add(booking);
            }

        } finally {
            // Close the database resources
            close(connection, statement, resultSet);
        }

        return bookings;
    }

    private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
        try{
            if (myRs != null){
                myRs.close();
            }

            if (myStmt != null){
                myStmt.close();
            }

            if (myConn != null){
                myConn.close();
            }
        }catch (Exception exc){
            exc.printStackTrace();
        }
    }

    public Hotel getHotelById(int hotelId) throws SQLException, ClassNotFoundException {
        Hotel hotel = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Load driver
            Class.forName("com.mysql.jdbc.Driver");

            // Thiết lập thông tin kết nối
            String url = "jdbc:mysql://localhost:3306/hotel_booking_system";
            String username = "root";
            String password = "";

            // Kết nối tới cơ sở dữ liệu
            connection = DriverManager.getConnection(url, username, password);

            // Chuẩn bị câu truy vấn SQL
            String sql = "SELECT * FROM hotels WHERE hotel_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, hotelId);

            // Thực hiện truy vấn
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String hotelName = resultSet.getString("hotel_name");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");

                hotel = new Hotel(hotelId, hotelName, address, phone, email);
            }
        } finally {
            // Đảm bảo đóng tất cả các tài nguyên (connection, statement, resultSet)
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return hotel;
    }






    public void saveRoomToCart(String userId, int roomId) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Get database connection
            // Load driver
            Class.forName("com.mysql.jdbc.Driver");

            // Thiết lập thông tin kết nối
            String url = "jdbc:mysql://localhost:3306/hotel_booking_system";
            String username = "root";
            String password = "";

            connection = DriverManager.getConnection(url, username, password);

            // Create SQL statement
            String sql = "INSERT INTO cart (user_id, room_id) VALUES (?, ?)";

            // Prepare statement
            statement = connection.prepareStatement(sql);

            // Set parameters
            statement.setString(1, userId);
            statement.setInt(2, roomId);

            // Execute statement
            statement.executeUpdate();
        } finally {
            // Close database resources
            close(connection, statement, null);
        }
    }

    public RoomType getRoomTypeById(int typeID) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        RoomType roomType = null;

        try {
            String url = "jdbc:mysql://localhost:3306/hotel_booking_system";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, username, password);

            String sql = "SELECT * FROM room_types WHERE room_type_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, typeID);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int roomTypeId = rs.getInt("room_type_id");
                String roomTypeName = rs.getString("room_type_name");
                String description = rs.getString("description");

                roomType = new RoomType(roomTypeId, roomTypeName, description);
            }
        } finally {
            close(conn, stmt, rs);
        }

        return roomType;
    }


    public int authenticateUser(String username, String password) throws SQLException {
        int userId = -1;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String url = "jdbc:mysql://localhost:3306/hotel_booking_system";
            String us = "root";
            String up = "";
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(url, us, up);
            String sql = "SELECT user_id FROM users WHERE username = ? AND password = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                userId = resultSet.getInt("user_id");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            // Đảm bảo đóng tất cả các kết nối và tài nguyên
            close(connection, statement, resultSet);
        }

        return userId;
    }


    public List<Room> getRoomsByHotelId(int hotelId) throws Exception {
        List<Room> roomList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String url = "jdbc:mysql://localhost:3306/hotel_booking_system";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, username, password);

            String sql = "SELECT r.*, rt.room_type_name, rt.description FROM rooms r " +
                    "INNER JOIN room_types rt ON r.room_type_id = rt.room_type_id " +
                    "WHERE r.hotel_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, hotelId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int roomId = rs.getInt("room_id");
                int roomTypeId = rs.getInt("room_type_id");
                int hotel_id = rs.getInt("hotel_id");
                int roomNumber = rs.getInt("room_number");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                byte availability = rs.getByte("availability");
                String roomTypeName = rs.getString("room_type_name");

                RoomType roomType = new RoomType(roomTypeId, roomTypeName, description);
                Room room = new Room(roomId, roomType.getRoom_type_id(), hotel_id, roomNumber, price, description, availability);
                roomList.add(room);
            }
        } finally {
            close(conn, stmt, rs);
        }

        return roomList;
    }


    public void bookingRoomInHotel(Hotel hotel) throws Exception {

    }



    public void updateBooking(Booking theBooking) throws Exception { //update cho admin
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            String url = "jdbc:mysql://localhost:3306/hotel_booking_system";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");
            myConn = DriverManager.getConnection(url, username, password);

            String sql = "UPDATE bookings SET status = ? WHERE id=?";

            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, theBooking.getStatus());
            myStmt.setInt(2, theBooking.getId());
            myStmt.execute();
        }
        finally {
            close(myConn, myStmt, null);
        }
    }

    public void deleteBooking(String theStudentId) throws Exception{
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            int studentId = Integer.parseInt(theStudentId);

            String url = "jdbc:mysql://localhost:3306/hotel_booking_system";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");
            myConn = DriverManager.getConnection(url, username, password);

            String sql = "delete from bookings where id=?";

            myStmt = myConn.prepareStatement(sql);

            myStmt.setInt(1, studentId);

            myStmt.execute();
        }
        finally {
            close(myConn, myStmt, null);
        }
    }



}