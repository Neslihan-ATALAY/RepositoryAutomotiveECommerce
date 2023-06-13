<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<meta charset="utf-8">
		<title>Product Detail</title>
	</head>
	<body>
		<jsp:include page="loginPartial.jsp" />
		<form:form name="viewProductForm" method="POST" action="BasketContoller/createBasket">
			<div align="center">
				<table id="productDetailTable">					
					<input type="hidden" name="hiddenProductId" value="${model.basketid}" />
					<input type="hidden" name="hiddenUserId" value="${nameandusername}" />
					<tr>
						<td>
							<label value="Product Id/Number:" />
						</td>
						<td>
							<input type="text" name="productId" value="${model.id}" disabled="true" />
						</td>
					</tr>
					<tr>
						<td>
							<label value="Product Name:" />
						</td>
						<td>
							<label name="labelName" value="${model.name}" />
						</td>
					</tr>
					<tr>
						<td>
							<label value="Product Group:" />
						</td>
						<td>
							<label name="labelProductGroup" value="${model.productGroup.name}" />
						</td>
					</tr>					
					<tr>
						<td>
							<label value="Mark:" />
						</td>
						<td>
							<label name="labelMark" value="${model.name}" />
						</td>
					</tr>
					<tr>
						<td>
							<label value="Stock Code:" />
						</td>
						<td>
							<label name="labelStockCode" value="${model.stockCode}" />
						</td>
					<tr>
					<tr>						
						<td>
							<label value="Stock Quantity:" />
						</td>
						<td>
							<label name="labelStockQuantity" value="${model.stockQuantity}" />
						</td>
					</tr>
					<tr>
						<td>
							<label value="Price (Turkish Lira):" />
						</td>
						<td>
							<label name="labelPrice" value="${model.price}" />
						</td>
					</tr>
					&nbsp;
					&nbsp;
					&nbsp;
					<tr>
						<td>
							<label value="Select Quantity:" />
						</td>
						<td>
							<input type="text" name="stockQuantity" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value=ADD BASKET" />
						</td>
					</tr>
				</table>
			</div>
		</form:form>
	</body>
</html>