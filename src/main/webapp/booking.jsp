<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Booking</title>
    <script>

        function updateTotalPrice() {
            var priceA = 30; // Giá A
            var priceB = 50; // Giá B
            var adults = parseInt(document.getElementById("adults").value);
            var children = parseInt(document.getElementById("children").value);
            var roomPrice = parseFloat("${param.price}"); // Giá phòng từ cart


            var totalPrice = (priceA * adults - priceA) + (priceB * children) + roomPrice;
            document.getElementById("totalPrice").value = totalPrice;
            // document.getElementById("totalPrice").value = totalPrice.toFixed(2);

        }
        document.addEventListener("DOMContentLoaded", function() {
            // Gọi hàm updateTotalPrice khi giá trị trong cột Adults hoặc Children thay đổi
            document.getElementById("adults").addEventListener("change", updateTotalPrice);
            document.getElementById("children").addEventListener("change", updateTotalPrice);

            // Gọi hàm updateTotalPrice lần đầu để hiển thị giá trị ban đầu
            updateTotalPrice();
        });
        function confirmBooking() {
            var confirmation = confirm("Bạn có chắc chắn muốn xác nhận đặt phòng?");
            if (confirmation) {
                document.getElementById("bookingForm").submit();
            }
        }

    </script>

</head>
<body>
<h1>Booking</h1>
<form action="HotelServlet" method="post">
    <input type="hidden" name="command" value="BOOK">
    <input type="hidden" name="roomId" value="${param.roomId}">
    <input type="hidden" name="userId" value="${param.userId}">
    <input type="hidden" name="hotel_id" value="${param.hotelId}">
    <%--    <input type="hidden" name="totalPrice" id="totalPrice" value="">--%>


    <label for="roomId">Room ID:</label>
    <input type="text" name="roomId" id="roomId" value="${param.roomId}" readonly>
    <br>

    <label for="userId">User ID:</label>
    <input type="text" name="userId" id="userId" value="${param.userId}" readonly>
    <br>
    <label for="email">Email:</label>
    <input type="email" name="email" id="email" required>
    <br>
    <label for="name">Name:</label>
    <input type="text" name="name" id="name" required>
    <br>
    <label for="phone">Phone:</label>
    <input type="tel" name="phone" id="phone" required>
    <br>
    <label for="checkInDate">Check-in Date:</label>
    <input type="date" name="checkInDate" id="checkInDate">
    <br>
    <label for="checkOutDate">Check-out Date:</label>
    <input type="date" name="checkOutDate" id="checkOutDate">
    <br>
    <label for="adults">Adults:</label>
    <input type="number" name="adults" id="adults" value="1" min="1" max="4" onchange="updateTotalPrice()">
    <br>
    <label for="children">Children:</label>
    <input type="number" name="children" id="children" value="0" min="0" max="4" onchange="updateTotalPrice()">
    <br>
    <label for="hotelId">Hotel ID:</label>
    <input type="text" name="hotel_id" value="${param.hotelId}" readonly id="hotelId">
    <br>
    <br>
    <label for="totalPrice">Total Price:</label>
    <input type="text" name="totalPrice" value="${param.price}" readonly id="totalPrice">
    <br>
    <input type="submit" value="Book" onclick="confirmBooking()">
</form>
</body>
</html>