<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<meta charset="utf-8">
		<title>Admin - Create Product</title>
	</head>
	<body>
		<jsp:include page="loginPartial.jsp" />
		<form:form name="createProductForm" method="POST" action="ProductController/createProduct">
			<div align="center">
				<table id="productTable">
					<tr>
						<td>
							Product Name:
						</td>
						<td>
							<input type="text" name="name" />
						</td>
					</tr>
					<tr>
						<td>
							Product Mark:
						</td>
						<td>
							<input type="text" name="mark" />
						</td>
					</tr>
					<tr>
						<td>
							Product Stock Code:
						</td>
						<td>
							<input type="text" name="stockCode" />
						</td>
					</tr>
					<tr>
						<td>
							Product Group:
						</td>
						<td>
							<select name="productGroup">
								<option value="0">Select Please</option>
								<option value="1">Fren ve Debriyaj</option>
								<option value="2">Motor ve Yakıt</option>
								<option value="3">Süspansiyon ve Direksiyon</option>
								<option value="4">Elektrik ve Aydınlatma</option>
								<option value="5">Kaporta ve Trim</option>
								<option value="6">Şanzıman ve Diferansiyel</option>
								<option value="7">Soğutma ve Filtreler</option>
								<option value="8">Oto Bakım ve Yağlar</option>
								<option value="9">Aksesuar ve Tuning</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>
							Product Price:
						</td>
						<td>
							<input type="text" name="price" />
						</td>
					</tr>
					<tr>
						<td>
							Product Stock Quantity:
						</td>
						<td>
							<input type="text" name="stockQuantity" />
						</td>
					</tr>
					<tr>
						<td>,
							Save Product:
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