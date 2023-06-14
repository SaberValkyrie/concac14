package hotel;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/HotelServlet")
@MultipartConfig
public class HotelServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private HotelDB hotelDB;
    private List<Hotel> hotelList;

    private List<Booking> bookingList;
    @Resource(name = "jdbc/hotel_booking_system")

    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();
        bookingList = new ArrayList<>(); // Khởi tạo bookingList trong phương thức init()

        try {
            hotelDB = new HotelDB(dataSource);
        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String theCommand = request.getParameter("command");
            if (theCommand == null) {
                listHotels(request, response);
            } else {
                switch (theCommand) {
                    case "LOAD":
                        loadBookingDetails(request, response);
                        break;
                    case "UPDATE":
                        updateBooking(request, response);
                        break;
                    case "DELETE":
                        deleteBooking(request, response);
                        break;

                    case "BOOK":
                        int roomId = Integer.parseInt(request.getParameter("roomId"));
                        bookRoom(request, response,roomId);
                        break;

                    case "LOGIN":
                        login(request, response);
                        break;
                    case "DETAILS":
                        loadHotelDetails(request, response);
                        break;
                    case "SEARCH":
                        searchHotels(request, response);
                        break;
                    case "ADD_TO_CART":
                        addToCart(request, response);
                        break;
                    case "CART_DETAILS":
                        loadCartDetails(request, response);
                        break;
                    case "DELETE_CART":
                        deleteCartById(request, response);
                        break;
                    default:
                        listHotels(request, response);
                }
            }
        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void bookRoom(HttpServletRequest request, HttpServletResponse response, int roomId) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            String userId = (String) session.getAttribute("userId");
            if (userId != null) {
                String checkInDate = request.getParameter("checkInDate");
                String checkOutDate = request.getParameter("checkOutDate");
                double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
                String status = "PENDING";

                Booking booking = new Booking(Integer.parseInt(userId), roomId, checkInDate, checkOutDate, totalPrice, status);
                hotelDB.addBooking(booking);

                hotelDB.deleteCartItemsByUserId(Integer.parseInt(userId),roomId);

                response.sendRedirect(request.getContextPath() + "/HotelServlet?command=LOAD");
            } else {
                // Redirect to login page or display an error message
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
        } catch (NumberFormatException e) {
            throw new ServletException("Invalid number format", e);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }




    private String getCurrentDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return currentDateTime.format(formatter);
    }




    private void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            String userId = (String) session.getAttribute("userId");
            if (userId != null) {
                int roomId = Integer.parseInt(request.getParameter("roomId"));
                hotelDB.saveRoomToCart(userId, roomId);
            }
            else {
                // Redirect to login page or display an error message
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
            String currentURL = request.getRequestURL().toString();

            // Chuyển hướng người dùng đến chính trang hiện tại
            response.sendRedirect(currentURL);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }








    private void listHotels(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Hotel> hotels = hotelDB.getHotel();
        request.setAttribute("HOTEL_LIST", hotels);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/homepage.jsp");
        dispatcher.forward(request, response);
    }
    private void loadHotelDetails(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int hotelId = Integer.parseInt(request.getParameter("hotel_id"));
        Hotel hotel = hotelDB.getHotelById(hotelId);
        List<Room> rooms = hotelDB.getRoomsByHotelId(hotelId);

        for (Room room : rooms) {
            int roomTypeId = room.getRoom_type_id();
            RoomType roomType = (RoomType) hotelDB.getRoomTypeById(roomTypeId);
            room.setRoomType(roomType);
        }
        request.setAttribute("HOTEL", hotel);
        request.setAttribute("ROOMS", rooms);

        // Forward to the hotel-details.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/hotel-details.jsp");
        dispatcher.forward(request, response);
    }

    private void loadBookingDetails(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");

        List<Booking> bookings = hotelDB.getBookings(Integer.parseInt(userId));

        // Lấy danh sách thông tin khách hàng (full_name và email) dựa trên user_id
        List<String> fullNames = new ArrayList<>();
        List<String> rooms = new ArrayList<>();
        List<String> emails = new ArrayList<>();

        for (Booking booking : bookings) {
            String fullName = hotelDB.getFullName(booking.getUser_id());
            String email = hotelDB.getEmail(booking.getUser_id());
            Double price = Double.parseDouble(hotelDB.getPrice(booking.getRoom_id()));

            fullNames.add(fullName);
            emails.add(email);
            rooms.add(String.valueOf(price));

        }

        request.setAttribute("BOOKING_LIST", bookings);
        request.setAttribute("FULL_NAMES", fullNames);
        request.setAttribute("EMAILS", emails);
        request.setAttribute("PRICE", rooms);

        // Forward to the booking-list.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/booking-list.jsp");
        dispatcher.forward(request, response);
    }


//    private void loadBookingDetails(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        HttpSession session = request.getSession();
//        String userId = (String) session.getAttribute("userId");
//
//
//        List<Booking> bookings = hotelDB.getBookings(Integer.parseInt(userId));
//
//        request.setAttribute("BOOKING_LIST", bookings);
//        // Forward to the hotel-details.jsp
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/booking-list.jsp");
//        dispatcher.forward(request, response);
//    }


    private void loadCartDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            String userId = (String) session.getAttribute("userId");

            if (userId != null) {
                List<Cart> cartItems = hotelDB.getCartItemsByUserId(Integer.parseInt(userId));

                // Lấy thông tin từ các bảng và gán vào cartItems
                for (Cart cartItem : cartItems) {
                    int roomId = cartItem.getRoomId();

                    // Lấy thông tin từ bảng rooms
                    Room room = hotelDB.getRoomById(roomId);
                    cartItem.setRoomNumber(room.getRoom_number());
                    cartItem.setPrice(room.getPrice());
                    cartItem.setAvailability(room.getAvailability());

                    // Lấy thông tin từ bảng hotels
                    int hotelId = room.getHotel_id();
                    Hotel hotel = hotelDB.getHotelById(hotelId);
                    cartItem.setHotelName(hotel.getHotel_name());

                    // Lấy thông tin từ bảng users (từ session)
                    cartItem.setUserId(Integer.parseInt(userId));
                }

                String hotelId = request.getParameter("hotelId");
                String roomId = request.getParameter("roomId");
                if (roomId != null && !roomId.isEmpty()) {
                    int room_id = Integer.parseInt(roomId);

                    List<Room> rooms = hotelDB.getRoomsByHotelId(Integer.parseInt(hotelId));
                    for (Room room : rooms) {
                        if (room.getRoom_id() == room_id) {
                            int roomTypeId = room.getRoom_type_id();
                            RoomType roomType = hotelDB.getRoomTypeById(roomTypeId);
                            room.setRoomType(roomType);
                            break;
                        }
                    }

                    request.setAttribute("ROOMS", rooms);
                }

                // Lưu danh sách cartItems vào thuộc tính của request
                request.setAttribute("CART_ITEMS", cartItems);

                // Forward đến trang cart.jsp
                RequestDispatcher dispatcher = request.getRequestDispatcher("/cart.jsp");
                dispatcher.forward(request, response);



            } else {
                // Nếu người dùng chưa đăng nhập, chuyển hướng đến trang đăng nhập
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }















    private void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Kiểm tra thông tin đăng nhập từ cơ sở dữ liệu
        int userId = hotelDB.authenticateUser(username, password);

        if (userId != -1) {
            // Lưu user_id vào session
            HttpSession session = request.getSession();
            session.setAttribute("userId", String.valueOf(userId));

            System.out.println("userID : " + userId);


            // Tiến hành các hành động tiếp theo
            response.sendRedirect(request.getContextPath() + "/HotelServlet");
        } else {
            System.out.println("bú");
        }
    }



    private void deleteBooking(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String bookingId = request.getParameter("bookingId");
        hotelDB.deleteBooking(bookingId);
        response.sendRedirect(request.getContextPath() + "/HotelServlet");
    }

    public void updateBooking(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int bookingId = Integer.parseInt(request.getParameter("bookingId"));
        String status = request.getParameter("status");
        Booking booking = new Booking(bookingId, status);
        hotelDB.updateBooking(booking);
        response.sendRedirect(request.getContextPath() + "/HotelServlet");
    }

    private void loadBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            String userId = (String) session.getAttribute("userId");


            String bookingIdParam = request.getParameter("bookingId");
            if (bookingIdParam != null && !bookingIdParam.isEmpty()) {
                List<Booking> bookings = hotelDB.getBookings(Integer.parseInt(userId));
                request.setAttribute("BOOKING_LIST", bookings);
            }
//            response.sendRedirect(request.getContextPath() + "/HotelServlet?command=LOAD?&userId=" + userId);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/booking-list.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }


    private void searchHotels(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String searchQuery = request.getParameter("search");
        List<Hotel> hotels = hotelDB.searchHotelsByName(searchQuery);
        request.setAttribute("HOTEL_LIST", hotels);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/homepage.jsp");
        dispatcher.forward(request, response);
    }











    private void deleteCartById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String[] selectedItems = request.getParameterValues("selectedItems");
        if (selectedItems != null && selectedItems.length > 0) {
            for (String itemId : selectedItems) {
                hotelDB.deleteCartItemById(itemId);
            }
        }
        response.sendRedirect(request.getContextPath() + "/HotelServlet?command=CART_DETAILS");

    }
}

