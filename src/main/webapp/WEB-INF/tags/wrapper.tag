<%@tag description="Page template" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<title><c:out value="${pageTitle}" /></title>

<!-- Compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">

<script src="js/helpers.js"></script>
</head>


<nav class="pink lighten-1" role="navigation">
	<div class="nav-wrapper container">
		<img
			src="https://scontent-waw1-1.xx.fbcdn.net/v/t1.6435-9/61989203_354310341893911_2221791358224433152_n.jpg?_nc_cat=108&ccb=1-7&_nc_sid=973b4a&_nc_ohc=R0mkUY2yKS4AX_Xbdgb&_nc_ht=scontent-waw1-1.xx&oh=00_AT9hFMNrOb2QSR5G3NcFcp9JJqpLxPfpyIkHmjyz8lhVww&oe=637B761A"
			width="65" height="65">
		<ul class="right hide-on-med-and-down pink">
			<li><a href="centre.jsp">M Y L O V E R B E R R Y</a></li>
			<li><a href="/product">Каталог</a></li>
			<li><a href="data.jsp">Оформление заказа</a></li>
			<li><a href="/client">Клиенты</a></li>
			<li><a href="/address">Доставки</a></li>
			<li><a href="/order">Заказ</a></li>
			<li><a href="/login">Вход</a></li>
				<li class="active"><a onclick="sendHTTPDelete('/login')">Logout</a></li>
		</ul>
	</div>
</nav>



<div class="section no-pad-bot" id="index-banner">
	<div class="container">
		<jsp:doBody />
		<!-- Page body will be here -->
	</div>
</div>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>