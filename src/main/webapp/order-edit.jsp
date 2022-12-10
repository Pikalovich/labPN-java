<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="pageTitle" value="Client" scope="application" />

<t:wrapper>
	<c:choose>
		<c:when test="${empty dto.id}">
			<h1>Create order</h1>
		</c:when>
		<c:otherwise>
			<h1>Edit order #${dto.id}</h1>
		</c:otherwise>
	</c:choose>
	<form class="col s12" method="post" action="/order">
		<div class="row">
			<input type="hidden" name="id" value="${dto.id}" />
			<div class="row">
				<div class="col s6">
					<label for="clientId">Client ID</label> 
					<select name="clientId" class="browser-default" required>
						<option value="">--select order client--</option>
						<c:forEach items="${allClients}" var="client">
							<option value="${client.id}" <c:if test="${client.id eq dto.clientId}">selected="selected"</c:if>>${client.name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col s6">
					<label for="productId">Product ID</label> 
					<select name="productId" class="browser-default" required>
						<option value="">--select order product--</option>
						<c:forEach items="${allProducts}" var="product">
							<option value="${product.id}" <c:if test="${product.id eq dto.productId}">selected="selected"</c:if>>${product.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		<div class="row">
				<div class="col s6">
					<label for="deliveryAddressId">Address ID</label> 
					<select name="deliveryAddressId" class="browser-default" required>
						<option value="">--select order address--</option>
						<c:forEach items="${allAddresses}" var="deliveryAddress">
							<option value="${deliveryAddress.id}" <c:if test="${deliveryAddress.id eq dto.deliveryAddressId}">selected="selected"</c:if>>${deliveryAddress.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>	
		</div>
			<div class="row">
				<div class="input-field col s12">  
					<input type="text" name="count" required minlength=1 maxlength=10 value="${dto.count}" > <label for="count">Количество</label>
				</div>
			</div>
				<div class="input-field col s6">
					<input type="text" name="price" minlength=1 maxlength=3 required value="${dto.price}"> <label for="time">Цена</label>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col s12 input-field center-align">
				<a class="btn waves-effect waves-light red" href="/order"><i class="material-icons left">list</i>back</a>&nbsp;
				<button class="btn waves-effect waves-light" type="submit">
					<i class="material-icons left">save</i>save
				</button>
			</div>
		</div>
	</form>
</t:wrapper>

