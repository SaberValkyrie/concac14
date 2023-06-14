<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Booking</title>
</head>
<body>
<h1>Booking</h1>
<form action="HotelServlet" method="get">
    <input type="hidden" name="command" value="BOOK">
    <input type="hidden" name="roomId" value="${param.roomId}">
    <input type="hidden" name="userId" value="${sessionScope.userId}">
    <label for="roomId">Room ID:</label>
    <input type="text" name="roomId" id="roomId" value="${param.roomId}" readonly>
    <br>
    <label for="userId">User ID:</label>
    <input type="text" name="userId" id="userId" value="${sessionScope.userId}" readonly>
    <br>
    <label for="checkInDate">Check-in Date:</label>
    <input type="date" name="checkInDate" id="checkInDate">
    <br>
    <label for="checkOutDate">Check-out Date:</label>
    <input type="date" name="checkOutDate" id="checkOutDate">
    <br>
    <label for="totalPrice">Total Price:</label>
    <input type="text" name="totalPrice" id="totalPrice">
    <br>
    <input type="submit" value="Book">
</form>
</body>
</html>
