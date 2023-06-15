<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
        }
    </style>
</head>
<body>
<div class="unavailable-container">
    <div class="unavailable-message">
        We're sorry, but the selected room is currently unavailable.
    </div>
    <a href="HotelServlet" class="home-button">Go back to Home</a>
</div>
</body>
</html>
