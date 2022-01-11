<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>qUpdateForm</title>
<script type="text/javascript">
	function quJoinCheck(){
		if(document.quf.name.value.length == 0){
			alert("Name none");
			document.quf.name.focus();
			return false;
		}
		if(document.quf.pwd.value.length == 0){
			alert("Password none");
			document.quf.pwd.focus();
			return false;
		}
		if(document.quf.pwd.value != document.quf.pwd_chk.value){
			alert("Password not matched");
			document.quf.pwd_chk.focus();
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<h2>Update member</h2>
	<form action="qmem.do" method="post" name="quf">
		<input type="hidden" name="cmd" value="qUpdate">
		<table>
			<tr>
				<td>Name</td>
				<td><input type="text" name="name" value="${qLoginUser.name }"></td> 
			</tr>
			<tr>
				<td>ID</td>
				<td>${qLoginUser.userid }<input type="hidden" name="id" value="${qLoginUser.userid }"></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="pwd" value="${qLoginUser.pwd}"></td> 
			</tr>
			<tr>
				<td>Password_Check</td>
				<td><input type="password" name="pwd_chk" size="20"></td> 
			</tr>
			<tr>
				<td>Email</td>
				<td><input type="text" name="email" value="${qLoginUser.email}"></td> 
			</tr>
			<tr>
				<td>Phone</td>
				<td><input type="text" name="phone" value="${qLoginUser.phone}"></td> 
			</tr>
			<tr>
				<td>Grade</td>
				<td>
					<c:choose>
						<c:when test="${qmdto.admin == 0 }">
							<input type="radio" name="admin" value="0" checked="checked">User
							<input type="radio" name="admin" value="1">Admin
						</c:when>
						<c:otherwise>
							<input type="radio" name="admin" value="0">User
							<input type="radio" name="admin" value="1" checked="checked">Admin
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="update confirm" onClick="return quJoinCheck();">
					<input type="reset" value="reset">
					<input type="button" value="to Main" onClick="location.href='qmem.do?cmd=qMain'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>