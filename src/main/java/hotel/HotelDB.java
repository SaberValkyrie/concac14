package hotel;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
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
                    "INNER JOIN hotels h ON h.hotel_id = r.hotel_id " +
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


    public String getHotelName(int hotelId) throws Exception {
        String hotel_name = null;

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String url = "jdbc:mysql://localhost:3306/hotel_booking_system";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(url, username, password);

            String sql = "SELECT hotel_name FROM hotels WHERE hotel_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, hotelId);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                hotel_name = resultSet.getString("hotel_name");
            }
        } finally {
            close(connection, statement, resultSet);
        }

        return hotel_name;
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

            String sql = "SELECT total_price FROM bookings WHERE room_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, room_id);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                phone = resultSet.getString("total_price");
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

    public void updateRoomAvailability(int roomId, boolean isAvailable) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String url = "jdbc:mysql://localhost:3306/hotel_booking_system";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(url, username, password);
            String updateQuery = "UPDATE rooms SET availability = ? WHERE room_id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setBoolean(1, isAvailable);
            updateStatement.setInt(2, roomId);
            updateStatement.executeUpdate();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            close(connection, statement, resultSet);
        }
    }

    // Trong lớp HotelDB
    public boolean isRoomInCart(String userId, int roomId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String url = "jdbc:mysql://localhost:3306/hotel_booking_system";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(url, username, password);

            String query = "SELECT * FROM cart WHERE user_id = ? AND room_id = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, userId);
            statement.setInt(2, roomId);

            resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
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
        return false;
    }

    public int getRoomAvailability(int roomId) {
        int availability = 0;
        Connection connection = null;
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
        try {
            String url = "jdbc:mysql://localhost:3306/hotel_booking_system";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(url,username,password);
            // Tạo kết nối đến cơ sở dữ liệu

            // Chuẩn bị câu truy vấn
            String query = "SELECT availability FROM rooms WHERE room_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, roomId);

            // Thực hiện truy vấn
            ResultSet resultSet = statement.executeQuery();

            // Xử lý kết quả truy vấn
            if (resultSet.next()) {
                availability = resultSet.getInt("availability");
            }

            // Đóng kết nối và các tài nguyên
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return availability;
    }

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

            connection = DriverManager.getConnection(url, username, password);
            String sql = "SELECT b.booking_id, b.user_id, b.room_id, b.check_in_date, b.check_out_date, " +
                    "b.booking_date, SUM(b.total_price) AS total_price, b.status, b.adults, b.children, r.price, r.hotel_id, h.hotel_name, " +
                    "c.name, c.email, c.phone " +
                    "FROM bookings b " +
                    "JOIN rooms r ON b.room_id = r.room_id " +
                    "JOIN hotels h ON r.hotel_id = h.hotel_id " +
                    "JOIN customers c ON b.user_id = c.user_id " +
                    "WHERE b.user_id = ? " +
                    "GROUP BY b.booking_id";


            statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int bkId = resultSet.getInt("booking_id");
                int roomId = resultSet.getInt("room_id");
                int htID = resultSet.getInt("hotel_id");
                String checkInDate = resultSet.getString("check_in_date");
                String checkOutDate = resultSet.getString("check_out_date");
                String bookingDate = resultSet.getString("booking_date");
                double totalPrice = resultSet.getDouble("total_price"); // Lấy giá trị total_price từ bảng bookings
                String status = resultSet.getString("status");
                int adults = resultSet.getInt("adults");
                int children = resultSet.getInt("children");
                String hotelName = resultSet.getString("hotel_name");
                String name = resultSet.getString("name"); // Lấy giá trị name từ bảng customers
                String email = resultSet.getString("email"); // Lấy giá trị email từ bảng customers
                String phone = resultSet.getString("phone"); // Lấy giá trị phone từ bảng customers

                Booking booking = new Booking(bkId, userId, roomId, htID, checkInDate, checkOutDate, bookingDate, totalPrice, adults, children, status, hotelName);
                booking.setFull_name(name); // Đặt giá trị name cho đối tượng Booking
                booking.setEmail(email); // Đặt giá trị email cho đối tượng Booking
                booking.setPhone(phone); // Đặt giá trị phone cho đối tượng Booking
                bookings.add(booking);
            }
        } finally {
            close(connection, statement, resultSet);
        }

        return bookings;
    }





    public boolean isRoomAvailable(int roomId, String checkInDate, String checkOutDate) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String url = "jdbc:mysql://localhost:3306/hotel_booking_system";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(url,username,password);

            String sql = "SELECT COUNT(*) AS count FROM " +
                    "bookings WHERE room_id = ? " +
                    "AND ((check_in_date BETWEEN ? AND ?) " +
                    "OR (check_out_date BETWEEN ? AND ?))";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, roomId);
            statement.setString(2, checkInDate);
            statement.setString(3, checkOutDate);
            statement.setString(4, checkInDate);
            statement.setString(5, checkOutDate);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                return count == 0;
            }

            return true;
        } finally {
            close(connection, statement, resultSet);
        }
    }


    public void addBooking(Booking booking) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            String url = "jdbc:mysql://localhost:3306/hotel_booking_system";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(url, username, password);

            String sql = "INSERT INTO bookings (user_id, room_id, hotel_id, check_in_date, check_out_date, booking_date, total_price, status, adults, children) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, booking.getUser_id());
            statement.setInt(2, booking.getRoom_id());
            statement.setInt(3, booking.getHotel_id());
            statement.setString(4, booking.getCheck_in_date());
            statement.setString(5, booking.getCheck_out_date());
            statement.setString(6, booking.getBooking_date());
            statement.setDouble(7, booking.getTotal_price()); // Lưu giá trị total price
            statement.setString(8, booking.getStatus());
            statement.setInt(9, booking.getAdults());
            statement.setInt(10, booking.getChildren());

            statement.executeUpdate();
        } finally {
            close(connection, statement, null);
        }
    }


    public void saveCustomer(String userId, String name, String phone, String email, int bookingId) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            String url = "jdbc:mysql://localhost:3306/hotel_booking_system";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(url,username,password);
            String query = "INSERT INTO customers " +
                    "(user_id, name, phone, email, booking_id) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, userId);
            stmt.setString(2, name);
            stmt.setString(3, phone);
            stmt.setString(4, email);
            stmt.setInt(5, bookingId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            close(conn, stmt,null);
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

    public Customers getCustomerById(int userId) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Customers customer = null;

        try {
            // Get database connection
            String url = "jdbc:mysql://localhost:3306/hotel_booking_system";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);

            // Prepare SQL statement
            String sql = "SELECT * FROM customers WHERE user_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);

            // Execute query
            rs = stmt.executeQuery();

            // Process result set
            if (rs.next()) {
                // Lấy các thông tin từ result set
                String fullName = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");

                // Tạo đối tượng Customer với thông tin lấy từ result set
                customer = new Customers();
                customer.setName(fullName);
                customer.setEmail(email);
                customer.setPhone(phone);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng các đối tượng ResultSet, PreparedStatement và Connection
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return customer;
    }

    public int addBookings(Booking booking) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int bookingId = 0;

        try {
            // Get database connection
            String url = "jdbc:mysql://localhost:3306/hotel_booking_system";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(url,username,password);
            String query = "INSERT INTO bookings (user_id, room_id, hotel_id, check_in_date, check_out_date, total_price, status, adults, children) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, booking.getUser_id());
            stmt.setInt(2, booking.getRoom_id());
            stmt.setInt(3, booking.getHotel_id());
            stmt.setString(4, booking.getCheck_in_date());
            stmt.setString(5, booking.getCheck_out_date());
            stmt.setDouble(6, booking.getTotal_price());
            stmt.setString(7, booking.getStatus());
            stmt.setInt(8, booking.getAdults());
            stmt.setInt(9, booking.getChildren());

            stmt.executeUpdate();

            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                bookingId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            close(conn, stmt, rs);
        }
        return bookingId;
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
                String img = myRs.getString("img");
                Hotel hotel = new Hotel(id, hotel_name, address, phone,email,img);

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

            String sql = "SELECT * FROM hotels WHERE hotel_name LIKE ? OR address LIKE ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + hotelName + "%");
            stmt.setString(2, "%" + hotelName + "%");

            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("hotel_id");
                String name = rs.getString("hotel_name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String img = rs.getString("img");
                Hotel hotel = new Hotel(id, name, address, phone, email,img);
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
                String img = resultSet.getString("img");
                hotel = new Hotel(hotelId, hotelName, address, phone, email,img);
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
                String img = rs.getString("img");

                RoomType roomType = new RoomType(roomTypeId, roomTypeName, description);
                Room room = new Room(roomId, roomType.getRoom_type_id(), hotel_id, roomNumber, price, description, availability,img);
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