<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>qmain</title>
<script type="text/javascript">
	function qDeleteM(){
		var bool = confirm("Are you sure?");
		if(bool){
			location.href="qmem.do?cmd=qDeleteM";
		}else{
			
		}
	}
</script>
</head>
<body>
	<table>
		<tr>
			<td>Hello ${qLoginUser.name }(${qLoginUser.userid })</td>
		</tr>
		<tr>
			<td>Email : ${qLoginUser.email }</td>
		</tr>
		<tr>
			<td>Phone : ${qLoginUser.phone}</td>
		</tr>
		<tr>
			<td colspan="2">${message }</td>		
		</tr>
		<tr>
			<td>
				<input type="button" value="Logout" onClick="location.href='qmem.do?cmd=qLogout'">		
				<input type="button" value="Update Info" onClick="location.href='qmem.do?cmd=qUpdateForm'">		
				<input type="button" value="WithDrawal" onClick="qDeleteM();">		
			</td>
		</tr>
	</table> <br><br><br>
	<c:if test="${qLoginUser.admin == 1 }">
		<table align="center" width="800" bgcolor="black" cellspacing="1">
			<tr bgcolor="white">
				<th>ID</th>
				<th>Name</th>
				<th>Phone</th>
				<th>Email</th>
				<th>Grade</th>
				<th>Change Admin</th>
			</tr>
			<c:forEach items="${QML }" var="qm">
				<tr align="center" bgcolor="white">
					<td>${qm.userid }</td>
					<td>${qm.name }</td>
					<td>${qm.phone }</td>
					<td>${qm.email }</td>
					<td>
						<c:if test="${qm.admin == 0 }">User</c:if>	
						<c:if test="${qm.admin == 1 }">Admin</c:if>	
					</td>
					<td width="200">
<%-- 						test ${qm.userid} ${qLoginUser.userid} --%>
<%-- 						${qLoginUser.userid != qm.userid } --%>
<%-- 						<c:if test="${qLoginUser.userid != qm.userid }"> --%>
						<c:if test="${qLoginUser.userid != qm.userid }">
							<c:if test="${qm.admin == 0 }">
								<input type="button" value="Change to Admin" onClick="location.href='qmem.do?cmd=qEditAdmin&userid=${qm.userid }'">
							</c:if>
							<c:if test="${qm.admin == 1 }">
								<input type="button" value="Change to User" onClick="location.href='qmem.do?cmd=qEditAdmin&userid=${qm.userid }'">
							</c:if>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	
</body>
</html>