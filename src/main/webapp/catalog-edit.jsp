<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="pageTitle" value="Магазин Myloverberry" scope="application" />

<t:wrapper>
<c:choose>
		<c:when test="${empty dto.id}">
			<h1>Добавление товара</h1>
		</c:when>
		<c:otherwise>
			<h1>Edit product #${dto.id}</h1>
		</c:otherwise>
	</c:choose>
	<form class="col s12" method="post" action="/product">
		<div class="row">
			<input type="hidden" name="id" value="${dto.id}" />
			<div class="row">
				<div class="input-field col s12">  
					<input type="text" name="title" required value="${dto.title}"> <label for="title"></label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s6">
					<input type="text" name="price" required  minlength=1 maxlength=4 value="${dto.price}"> <label for="price">Price</label>
				</div>
				<div class="input-field col s6">
					<input type="text" name="description" required value="${dto.description}"> <label for="description">description</label>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col s12 input-field center-align">
				<a class="btn waves-effect waves-light red" href="/product"><i class="material-icons left">list</i>back</a>&nbsp;
				<button class="btn waves-effect waves-light" type="submit">
					<i class="material-icons left">save</i>save
				</button>
			</div>
		</div>
	</form>
</t:wrapper>