<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Unavailable</title>
  <style>
    .unavailable-container {
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      height: 200px;
    }
    .unavailable-message {
      font-size: 24px;
      margin-bottom: 20px;
    }
    .home-button {
      padding: 10px 20px;
      font-size: 18px;
      background-color: #428bca;
      color: white;
      text-decoration: none;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      margin-right: 10px;
    }
  </style>
</head>
<body>
<div class="unavailable-container">
  <div class="unavailable-message">
    The room already exists in your list
  </div>
  <a class="home-button" href="HotelServlet?command=CART_DETAILS&userId=${sessionScope.userId}">Check Cart</a>
 <br>
  <a class="home-button" href="javascript:history.back()">Go back to Room List</a>
</div>
</body>
</html>
