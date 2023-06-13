<c:if test=${nameandusername == ""}>
	<div>
		<a href="${LoginController/login}">Log In</a>
	<div>
<c/:if>
<c:if test=${nameandusername != ""}>
	<div>
		<font color="green"><h3>${nameandusername}</h3></font>
		<a href="${LoginController/logout?userId=userid}">Log Out</a>
	<div>
<c/:if>