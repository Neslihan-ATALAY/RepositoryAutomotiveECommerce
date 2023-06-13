<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Form</title>
</head>
<body>
	<jsp:include page="loginPartial.jsp" />
	<form:form name="createUserForm" method="POST" action="UserController/createUser">
		<div align="center">
			<table>
				<tr>
					<td>User Name / Email:</td>
					<td><input type="text" name="userName" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password" /></td>
				</tr>
				<tr>
					<td>Name:</td>
					<td><input type="text" name="name" /></td>
				</tr>
				<tr>
					<td>Surname:</td>
					<td><input type="password" name="surname" /></td>
				</tr>
				<tr>
					<td>
						User Role:
					</td>
					<td>
						<select name="role">
							<option value="0">Select Please</option>
							<option value="1">Yönetici</option>
							<option value="2">Kullanıcı</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>Create User</td>
					<td><input type="submit" value="Submit" /></td>
				</tr>
			</table>
			<div style="color: red">${error}</div>
		</div>
	</form:form>
</body>
</html>