<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html>
	<head>
		<meta charset="utf-8">
		<title>Welcome</title>
	</head>
	<body>
		<jsp:include page="loginPartial.jsp" />
		<a href="${BasketController/listBasketByUser?userId=userid}">View Basket</a>
		<form:form name="indexForm">
			<div align="center">
				<table id="productListTable">
					<tr>
						<th>Name</th>
						<th>Mark</th>
						<th>Product Group</th>
						<th>Price</th>
						<th></th>
					</tr>
					<c:forEach var="model" items="${modelList}">
						<tr>
							<td>
								<label name="labelName" value="<c:out value="${model.name}/>" />
							</td>
							<td>
								<label name="labelMark" value="<c:out value="${model.mark}/>" />
							</td>
							<td>
								<label name="labelProductGroup" value="<c:out value="${model.productGroup}/>" />
							</td>
							<td>
								<label name="labelPrice" value="<c:out value="${model.price}/>" />
							</td>
							<td>
								<a href="${ProductController/viewProduct?productId=model.id}">View</a>
							</td>
						</tr>
					</c:foreach>
				</table>
			</div>
		</form:form>
	</body>
</html>