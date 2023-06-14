<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>


<main>
    <section class="py-5 text-center container">
        <div class="row py-lg-5">
            <div class="col-lg-6 col-md-8 mx-auto">
                <h1 class="fw-light">HomePage</h1>
            </div>
        </div>
    </section>
    <div class="py-5 bg-light">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <form action="HotelServlet" method="GET">
                        <input type="hidden" name="command" value="SEARCH">
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" name="search" placeholder="Search by Hotel Name">
                            <button class="btn btn-primary" type="submit">Search</button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="row row-cols-1 row-cols-ms-2 row-cols-md-4 g-4">
                <c:forEach var="hotel" items="${requestScope.HOTEL_LIST}">
                    <div class="col">
                        <div class= "card shadow-sm">
                            <img src="img/${hotel.img}" alt="example">
                            <div class="card-body">
                                <p class="card-text">
                                    <label>Hotel name: </label> ${hotel.hotel_name}
                                    <br>
                                    <label>Address: </label> ${hotel.address}
                                    <br>
                                    <label>Phone: </label> ${hotel.phone}
                                    <br>
                                    <label>Email: </label> ${hotel.email}
                                </p>
                                <div class="btn-group">
                                    <a type="button" href="HotelServlet?command=DETAILS&hotel_id=${hotel.hotel_id}" class="btn btn-sm btn-outline-secondary">Details</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <c:if test="${empty requestScope.HOTEL_LIST}">
                    <div class="col">
                        <div class="card shadow-sm">
                            <div class="card-body">
                                <p class="card-text">No results found.</p>
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</main>
</body>
</html>
