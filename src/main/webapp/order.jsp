<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="pageTitle" value="order list" scope="application" />

<t:wrapper>
	<h1>Заказы</h1>
	<div class="row">
		<div class="col s12">
			<div class="center-align">
				<a class="btn-floating btn-large waves-effect pink" href="/order?view=edit"><i class="material-icons">add</i></a>
			</div>
		</div>
	</div>
	<table>
		<thead>
			<tr>
				<th><mytaglib:sort-link pageUrl="${pageUrl}" column="id">ID</mytaglib:sort-link></th>
					<th><mytaglib:sort-link pageUrl="${pageUrl}" column="ClientId">Client ID</mytaglib:sort-link></th>
						<th><mytaglib:sort-link pageUrl="${pageUrl}" column="productId">Product ID</mytaglib:sort-link></th>
						<th><mytaglib:sort-link pageUrl="${pageUrl}" column="deliveryAddressId">Address ID</mytaglib:sort-link></th>
				<th>Количество</th> <!-- can also be sortable but requires more complex SQL statement -->
				<th>Цена</th>
				
			</tr>
		</thead>
		
		<tbody>
			<c:forEach var="entity" items="${list}" varStatus="loopCounter">
				<tr>
					<td><c:out value="${entity.id}" /></td>
					<td><c:out value="${entity.clientId}" /></td>
					<td><c:out value="${entity.productId}" /></td>
					<td><c:out value="${entity.deliveryAddressId}" /></td>
					<td><c:out value="${entity.count}" /></td>
					<td><c:out value="${entity.price}" /></td>
					<td><a class="btn-small btn-floating waves-effect waves-light blue" title="редактировать" href="/order?view=edit&id=${entity.id}"><i
							class="material-icons">edit</i></a><a class="btn-small btn-floating waves-effect waves-light red" title="удалить" onclick="sendHTTPDelete('/order?id=${entity.id}')"><i class="material-icons">delete</i></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</t:wrapper>