<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<c:set var="pageTitle" value="Магазин Myloverberry" scope="application" />
<c:set var="pageUrl" value="/product" scope="page" />
<t:wrapper>
	<h1>Каталог</h1>
	<div class="row">
		<div class="col s12">
			<div class="center-align">
				<a class="btn-floating btn-large waves-effect pink" href="/product?view=edit${entity.id}"><i class="material-icons">add</i></a>
			</div>
		</div>
	</div>
	
				<table>
			<thead>
				<th><mytaglib:sort-link pageUrl="${pageUrl}" column="id">DB ID</mytaglib:sort-link></th>
				<th>Фото (описание)</th>
				<th>Название</th>
				<th><mytaglib:sort-link pageUrl="${pageUrl}" column="price">Цена</mytaglib:sort-link></th>
			</thead>
			
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
<t:paging />
</t:wrapper>