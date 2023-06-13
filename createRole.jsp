<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Role Form</title>
</head>
<body>
	<form:form name="createRoleForm" method="POST" action="RoleController/createRole">
		<div align="center">
			<table>
				<tr>
					<td>Role Name:</td>
					<td><input type="text" name="roleName" /></td>
				</tr>
				<tr>
					<td>Create Role</td>
					<td><input type="submit" value="Submit" /></td>
				</tr>
			</table>
			<div style="color: red">${error}</div>
		</div>
	</form:form>
</body>
</html>