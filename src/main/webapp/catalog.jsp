<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="pageTitle" value="Магазин Myloverberry" scope="application" />

<t:wrapper>
<div class="section no-pad-bot" id="index-banner">
	<div class="container">
		<h2>Товары</h2>
	<div class="row">
			<div class="col s12">
			<div class="center-align">
			<a class="btn-floating btn-large waves-effect pink" href="/product?view=edit${entity.id}"><i class="material-icons">add</i></a>
			</div>
				</div>
		</div>
		<table class="striped highlight">
			<thead>
				<tr>
					<th>Фото</th>
					<th>Композиция</th>
					<th>Цена</th>
					<th>Добавить в корзину</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><img src="https://sun9-west.userapi.com/sun9-56/s/v1/ig2/CcVayHeuYmO9lZHud27YbirhRvXzfU5S3z3Gzv3vQWIiJjVLJgkkyEajme3XlOM7NeIZ6f4M857-nK4ToWO0zUs-.jpg?size=750x731&quality=95&type=album" 
  width="200" height="190"></td>
					<td>Бокс Диана</td>
					<td>165р</td>
					<td><div class="center-align"><a class="btn-floating btn-large waves-effect waves-light brown darken-1" href="data.jsp"><i class="material-icons">shopping_cart</i></a>
					</div></td>
				</tr>
				<tr>
					<td><img src="https://sun9-east.userapi.com/sun9-59/s/v1/ig2/DcIEDzVVa7LbVSOkXWEr_cqHe9H19NmMVOjDtU1FiifBxwUJ0VH8JApPYHl1-qKCrAzLjKYvKkDSAXube_uOzRir.jpg?size=620x715&quality=95&type=album" 
  width="200" height="190"> <front color="pink"> </td>
					<td>Букет Мэдди</td>
					<td>100р</td>
					<td><div class="center-align"><a class="btn-floating btn-large waves-effect waves-light brown darken-1" href="data.jsp"><i class="material-icons">shopping_cart</i></a>
					</div></td>
				</tr>
				<tr>
					<td><img src="https://sun9-east.userapi.com/sun9-35/s/v1/ig2/PQFkDnuu2MSrMm2Eb3WyTakJLYyf4ZSiPZNgr_1Q7ZjpzKcDUwPDUHN7ZNU_G7CcWu2KNafFtmZkzKimdCe25hWs.jpg?size=750x735&quality=95&type=album" 
  width="200" height="190"></td>
					<td>Набор Молли</td>
					<td>100р</td>
					<td><div class="center-align"><a class="btn-floating btn-large waves-effect waves-light brown darken-1" href="data.jsp"><i class="material-icons">shopping_cart</i></a>
					</div></td>
				</tr>
				<tr>
				<td><img src="https://sun9-west.userapi.com/sun9-56/s/v1/ig2/3Lm2tdaGLP8tlALI366q_fE3Bom37gdvBkHgh1u5D2ce0ajkPFgl2k-TO9ztskxdXEalhxpedfsqrEAgt7dhhgra.jpg?size=686x736&quality=95&type=album" 
  width="200" height="190"></td>
                <td>Бокс Ксения</td>
                <td>145р</td>
                <td><div class="center-align"><a class="btn-floating btn-large waves-effect waves-light brown darken-1" href="data.jsp"><i class="material-icons">shopping_cart</i></a>
					</div></td>
				</tr>
				<tr>
				 <td><img src="https://sun9-north.userapi.com/sun9-78/s/v1/ig2/cg1PjWwnhVr93yJsbuf72Xk3PuSHYUo0pPBYskG75G0m6su-JJmXs1UYbM_v5ev4ZNChpqzEcyeQtAnuRMDpfn7V.jpg?size=750x727&quality=95&type=album" 
  width="200" height="190"></td>
				<td>Набор Diamond</td>
				<td>64р</td>
				<td><div class="center-align"><a class="btn-floating btn-large waves-effect waves-light brown darken-1" href="data.jsp"><i class="material-icons">shopping_cart</i></a>
					</div></td>
				</tr>
				<c:forEach var="entity" items="${list}" varStatus="loopCounter">
				<tr>
					<td><c:out value="${entity.id}" /></td>
					<td><c:out value="${entity.title}" /></td>
					<td><c:out value="${entity.description}" /></td>
					<td><c:out value="${entity.price}" /></td>
					<td><a class="btn-small btn-floating waves-effect waves-light blue" title="редактировать" href="/product?view=edit&id=${entity.id}"><i
							class="material-icons">edit</i></a><a class="btn-small btn-floating waves-effect waves-light red" title="удалить" onclick="sendHTTPDelete('/product?id=${entity.id}')"><i class="material-icons">delete</i></a></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
</t:wrapper>