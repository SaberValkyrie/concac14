<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Booking List</title>
</head>
<body>
<h1>Booking List</h1>

<table>
    <tr>
        <th>Booking ID</th>
        <th>User ID</th>
        <th>Người đặt</th>
        <th>Email</th>
        <th>Room ID</th>
        <th>Check-in Date</th>
        <th>Check-out Date</th>
        <th>Booking Date</th>
        <th>Total Price</th>
        <th>Status</th>
    </tr>

    <%-- Lấy danh sách booking theo userId --%>
    <c:forEach var="booking" items="${BOOKING_LIST}" varStatus="status">
        <tr>
            <td>${booking.id}</td>
            <td>${booking.user_id}</td>
            <td>${FULL_NAMES[status.index]}</td>
            <td>${EMAILS[status.index]}</td>
            <td>${booking.room_id}</td>
            <td>${booking.check_in_date}</td>
            <td>${booking.check_out_date}</td>
            <td>${booking.booking_date}</td>
            <td>${booking.total_price}</td>
            <td>${booking.status}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
