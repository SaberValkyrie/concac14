<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hotel Details</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        /* Thêm CSS tùy chỉnh của bạn ở đây */

        /* Thay đổi màu nền và màu chữ */
        body {
            background-color: #f8f9fa;
            color: #333;
            padding-left: 100px;
        }

        /* Định dạng tiêu đề */
        h1, h2, h3 {
            font-family: "Arial", sans-serif;
            font-weight: bold;
            color: #333;
        }

        /* Thay đổi màu và độ rộng của nút */
        .btn {
            background-color: #007bff;
            color: #fff;
            padding: 10px 20px;
        }

        /* Thêm đường viền và đổ bóng cho hình ảnh */
        img {
            border: 1px solid #ddd;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.3);
        }

        /* Thay đổi màu chữ và đường viền của bảng */
        table {
            color: #333;
            width: 1111px;
            text-align: center;
            border: 1px solid #ccc;
        }

        /* Thay đổi màu và độ rộng của thanh điều hướng */
        .navbar {
            background-color: #333;
            color: #fff;
            padding: 10px;
        }

        /* Thay đổi màu và kích thước của chân trang */
        .footer {
            background-color: #f8f9fa;
            color: #333;
            padding: 20px;
            text-align: center;
        }
    </style>
</head>
<body>

<h1>Hotel Details</h1>

<c:if test="${not empty HOTEL}">

    <p>
        Hotel ID: ${HOTEL.hotel_id}<br>
        Hotel Name: ${HOTEL.hotel_name}<br>
        Address: ${HOTEL.address}<br>
        Phone: ${HOTEL.phone}<br>
        Email: ${HOTEL.email}
    </p>

    <h2>Room List</h2>
    <c:if test="${not empty ROOMS}">
        <table>
            <tr>
                <th>Room ID</th>
                <th>Images</th>
                <th>Room Type</th>
                <th>Room Number</th>
                <th>Price</th>
                <th>Description</th>
                <th>Availability</th>
                <th>Action</th>
            </tr>
            <c:forEach var="room" items="${ROOMS}">
                <tr>
                    <td>${room.room_id}</td>
                    <td><img src="img/${room.img}" alt="Room Images">
                    <td>${room.roomType.room_type_name}</td>
                    <td>${room.room_number}</td>
                    <td>${room.price}</td>
                    <td>${room.description}</td>
                    <td>${room.availability}</td>

                    <td>
                        <a href="HotelServlet?command=ADD_TO_CART&roomId=${room.room_id}">Add to List</a> |
                        <input type="checkbox" name="selectedItems" value="${cartItem.id}" data-roomId="${cartItem.roomId}" data-userId="${cartItem.userId}">
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${empty ROOMS}">
        <p>No rooms available for this hotel.</p>
    </c:if>
</c:if>

<c:if test="${empty HOTEL}">
    <p>Hotel not found.</p>
</c:if>

<hr>

<%--<h2>Booking Details</h2>--%>
<%--<c:forEach var="booking" items="${BOOKINGS}">--%>
<%--    <p>--%>
<%--        Booking ID: ${booking.id}<br>--%>
<%--        Status: ${booking.status}--%>
<%--    </p>--%>
<%--</c:forEach>--%>
</body>
</html>
