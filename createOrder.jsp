<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<meta charset="utf-8">
		<title>User - Create Order</title>
	</head>
	<body>
		<jsp:include page="loginPartial.jsp" />
		<form:form name="createProductForm" method="POST" action="OrderController/createOrder">
			<div align="center">
				<table id="orderTable">
					<input type="hidden" name="hiddenBasketId" value="${model.basketId}" />
					<input type="hidden" name="hiddenUserId" value="${nameandusername}" />
					<tr>
						<td>
							Name Surname:
						</td>
						<td>
							<input type="text" name="nameSurname" />
						</td>
					</tr>
					<tr>
						<td>
							Address:
						</td>
						<td>
							<textarea name="address" />
						</td>
					</tr>
					<tr>
						<td>
							City:
						</td>
						<td>
							<input type="text" name="city" />
						</td>
					</tr>
					<tr>
						<td>
							Telephone:
						</td>
						<td>
							<input type="text" name="telephone" />
						</td>
					</tr>
					<tr>
						<td>
							Card Number:
						</td>
						<td>
							<input type="text" name="cardNumber" />
						</td>
					</tr>
					<tr>
						<td>
							Card Last Date:
						</td>
						<td>
							<input type="text" name="cardLastDate" />
						</td>
					</tr>
					<tr>
						<td>
							Card CVC:
						</td>
						<td>
							<input type="text" name="cardCvc" />
						</td>
					</tr>
					<tr>
						<td>,
							Save Order:
						</td>
						<td>
							<input type="submit" value="Submit" />
						</td>
					</tr>
				</table>
				<div style="color: red">${error}</div>
			</div>
		</form:form>
	</body>
</html>