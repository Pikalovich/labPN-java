	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="pageTitle" value="User edit" scope="application" />
<t:wrapper>
	<c:choose>
		<c:when test="${empty dto.id}">
			<h1>Create address</h1>
		</c:when>
		<c:otherwise>
			<h1>Edit address #${dto.id}</h1>
		</c:otherwise>
	</c:choose>
	<form class="col s12" method="post" action="/address">
		<div class="row">
			<input type="hidden" name="id" value="${dto.id}" />
			<div class="row">
			<div class="row">
				<div class="input-field col s12">  
					<input type="text" name="town" required value="${dto.town}" > <label for="town">Town</label>
				</div>
			</div>
				<div class="input-field col s12">  
					<input type="text" name="street" required value="${dto.street}" > <label for="street">Street</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s12">  
					<input type="number" name="house" minlength=4 maxlength=4 required value="${dto.house}" > <label for="house">House</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s6">
					<input type="number" name="flat" minlength=3 maxlength=3 required value="${dto.flat}"> <label for="flat">Flat</label>
				</div>
				
			</div>
		</div>
		<div class="row">
			<div class="col s12 input-field center-align">
				<a class="btn waves-effect waves-light red" href="/address"><i class="material-icons left">list</i>back</a>&nbsp;
				<button class="btn waves-effect waves-light" type="submit">
					<i class="material-icons left">save</i>save
				</button>
			</div>
		</div>
	</form>
</t:wrapper>
	