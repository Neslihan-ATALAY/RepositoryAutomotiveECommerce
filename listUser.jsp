<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html>
	<head>
		<meta charset="utf-8">
		<title>Admin - User List Form</title>
	</head> 
	<body>
		<jsp:include page="loginPartial.jsp" />
		<form:form name="userListForm">
			<div align="center">
				<table id="userListTable">
					<tr>
						<th>User Name (Email)</th>
						<th>Name</th>
						<th>Surname</th>
						<th>User Roles</th>
						<th>Confirm</th>
						<th>Not Confirm</th>
					</tr>
					<c:forEach var="model" items="${modelList}">
						<tr>
							<td>
								<label name="labelUsername" value="<c:out value="${model.userName}/>" />
							</td>
							<td>
								<label name="labelName" value="<c:out value="${model.name}/>" />
							</td>
							<td>
								<label name="labelSurname" value="<c:out value="${model.surname}/>" />
							</td>
							<td>
								<c:forEach var="modelRole" items="${model.roles}">
									<label name="labelRole" value="<c:out value="${modelRole.roleName}/>" />
								</c:foreach>
							</td>
							<td>
								<a href="${UserController/checkUser?userId=model.id}">Confirm</a>
							</td>
							<td>
								<a href="${UserController/notCheckUser?userId=model.id}">Not Confirm</a>
							</td>
						</tr>
					</c:foreach>
				</table>
			</div>
		</form:form>
	</body>
</html>