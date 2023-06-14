<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="js/cart.js"></script>
    <script>
        document.addEventListener("DOMContentLoaded", function() {
            // Gọi hàm redirectToBooking() khi nhấn nút "Booking"
            document.querySelector('form[action="HotelServlet"] button[type="submit"]').addEventListener("click", redirectToBooking);

            // Gọi hàm deleteSelectedItems() khi nhấn nút "Delete"
            document.querySelector('form[action="HotelServlet"] button[type="submit"][name="command"][value="DELETE_CART"]').addEventListener("click", deleteSelectedItems);
        });

        function redirectToBooking() {
            var selectedRooms = document.querySelectorAll('input[name="selectedItems"]:checked');
            if (selectedRooms.length > 0) {
                var roomId = selectedRooms[0].getAttribute("data-roomId");
                var userId = selectedRooms[0].getAttribute("data-userId");
                window.location.href = "booking.jsp?roomId=" + roomId + "&userId=" + userId;
            }
        }
    </script>
    <title>Cart</title>
</head>
<body>
<h1>Cart</h1>
<form action="HotelServlet" method="POST">
    <table>
        <thead>
        <tr>
            <th>Cart ID</th>
            <th>User ID</th>
            <th>Room ID</th>
            <th>Room Number</th>
            <th>Price</th>
            <th>Availability</th>
            <th>Hotel Name</th>
            <th>Select</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${CART_ITEMS}" var="cartItem">
            <tr>
                <td>${cartItem.id}</td>
                <td>${cartItem.userId}</td>
                <td>${cartItem.roomId}</td>
                <td>${cartItem.roomNumber}</td>
                <td>${cartItem.price}</td>
                <td>${cartItem.availability}</td>
                <td>${cartItem.hotelName}</td>
                <td>
                    <input type="checkbox" name="selectedItems" value="${cartItem.id}" data-roomId="${cartItem.roomId}" data-userId="${cartItem.userId}">
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <input type="hidden" name="command" value="DELETE_CART">
    <button type="submit">Delete</button>
    <button type="button" onclick="redirectToBooking()">Booking Now</button>
</form>
</body>
</html>
