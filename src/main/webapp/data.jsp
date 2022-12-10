
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="pageTitle" value="Вход в личный кабинет" scope="application" />

<t:wrapper>
<div class="section no-pad-bot" id="index-banner">
		<h1>Для оформления заказа:</h1>


		<div class="row">
			<form class="col s12">
				<div class="row">
					<div class="input-field col s6">
						<input placeholder="Иванов" id="first_name" type="text" class="validate"> <label for="first_name">Фамилия</label>
					</div>
			<div class="input-field col s6">
						<input placeholder="Иван" id="last_name" type="text" class="validate"> <label for="last_name">Имя</label> 
					</div>				</div>
				
				
				 <div class="input-field col s6">
          <i class="material-icons prefix">phone</i>
          <input id="icon_telephone" type="tel" class="validate">
          <label for="icon_telephone">Номер телефона</label>
        </div>
        </div>
        
        <div class="row">
			<form class="col s12">
        	<div class="row">
					<div class="input-field col s1">
						<input placeholder="" id="kolichestvo" type="number" class="validate"> <label for="kolichestvo">Количество</label> 
					</div>
				</div>
				
				<div class="row">
					<div class="input-field col s12">
						<input placeholder="" id="adres" type="text" class="validate"> <label for="adres">Адрес доставки</label> 
					</div>
				</div>
				<div class="row">
					<div class="input-field col s12">
						<input id="email" type="email" class="validate"> <label for="email">Email</label>
					</div>
				</div>
			</form>
		</div>
		<div class="row">
			<div class="col s12 input-field center-align">
			 <a class="btn waves-effect waves-light  pink darken-1"
					href="konets.jsp"><i class="material-icons left">check</i>Оформить заказ</a>
			</div>
		</div>
	</div>
</t:wrapper>