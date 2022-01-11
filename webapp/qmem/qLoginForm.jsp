<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>qLoginForm</title>
<script type="text/javascript">
	function qLoginCheck(){
		if(document.frm.id.value==""){
			alert("ID none");
			document.frm.id.focus();
			return false;
		}
		if(document.frm.pwd.value==""){
			alert("Password none");
			document.frm.pwd.focus();
			return false;
		}
		return true;
	}
</script>
</head>
<body>
<form action="qmem.do" method="post" name="frm">
	<input type="hidden" name="cmd" value="qLogin">
	<table>
		<tr>
			<td>ID</td>
			<td><input type="text" name="id"></td>
		</tr>
		<tr>
			<td>PASSWORD</td>
			<td><input type="password" name="pwd"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="Login" onClick="return qLoginCheck();">
				<input type="reset" value="Reset">
				<input type="button" value="Sign up" onClick="location.href='qmem.do?cmd=qJoinForm'">
			</td>
		</tr>
		<tr>
			<td colspan="2">
				${message }
			</td>
		</tr>
	</table>
</form>
</body>
</html>