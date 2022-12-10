<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="pageTitle" value="Client" scope="application" />

<t:wrapper>
<c:choose>
		<c:when test="${empty dto.id}">
			<h1>Добавление клиентов</h1>
		</c:when>
		<c:otherwise>
			<h1>Изменить данные #${dto.id}</h1>
		</c:otherwise>
	</c:choose>
	<form class="col s12" method="post" action="/client">
		<div class="row">
			<input type="hidden" name="id" value="${dto.id}" />
			<div class="row">
				<div class="input-field col s12">  
					<input type="text" name="lastName"  required value="${dto.lastName}"  > <label for="lastName">Имя</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s12">
					<input type="text" name="firstName" required value="${dto.firstName}"> <label for="FirstName">Фамилия</label>
				</div>
				<div class="row">
				<div class="input-field col s12">  
					<input type="email" name="email" required value="${dto.email}"> <label for="email">Почта</label>
				</div>
			</div>
				<div class="input-field col s6">
					<input type="text" name="time" required minlength=8 maxlength=8 value="${dto.time}"> <label for="time">Год заказ</label>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col s12 input-field center-align">
				<a class="btn waves-effect waves-light red" href="/client"><i class="material-icons left">list</i>back</a>&nbsp;
				<button class="btn waves-effect waves-light" type="submit">
					<i class="material-icons left">save</i>save
				</button>
			</div>
		</div>
	</form>
</t:wrapper>