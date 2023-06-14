<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Header</title>
</head>
<body>
<header>
  <nav>
    <ul>
      <li><a href="homepage.jsp">Home</a></li>
      <li><a href="hotels.jsp">Hotels</a></li>
      <li><a href="HotelServlet?command=LOAD&userId=${sessionScope.userId}">Booking</a></li>
      <li><a href="HotelServlet?command=CART_DETAILS&userId=${sessionScope.userId}">Cart</a></li>
      <li><a href="logout.jsp">Logout</a></li>
    </ul>
  </nav>
</header>
</body>
</html>
