<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>qJoinForm</title>
<script type="text/javascript">
	function qIdCheck(){
		if(document.frm.id.value.length == 0){
			alert("ID none");
			document.frm.id.focus();
			return;
		}
		
		var inputid = document.frm.id.value;
		var opt = "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=500, height=300";
		window.open("qmem.do?cmd=qIdCheck&id=" + inputid, "idCheck", opt);
	}
	
	function qJoinCheck(){
		if(document.frm.name.value.length ==0){
			alert("Name none");
			document.frm.name.focus();
			return false;
		}
		if(document.frm.id.value.length ==0){
			alert("ID none");
			document.frm.id.focus();
			return false;
		}
		if(document.frm.id.value != document.frm.reid.value){
			alert("Check duplicate");
			document.frm.id.focus();
			return false;
		}
		if(document.frm.pwd.value.length ==0){
			alert("Password none");
			document.frm.pwd.focus();
			return false;
		}
		if(document.frm.pwd.value != document.frm.pwd_chk.value){
			alert("Password not matched");
			document.frm.pwd_chk.focus();
			return false;
		}
		return true;
	}

</script>
</head>
<body>
	<form action="qmem.do" method="post" name="frm">
		<input type="hidden" name="cmd" value="qJoin">
		<table>
			<tr>
				<td>Name</td>
				<td><input type="text" name="name" size="20">&nbsp;*</td>
			</tr>
			<tr>
				<td>ID</td>
				<td>
					<input type="text" name="id" size="20">&nbsp;*
					<input type="button" value="check_dupli" onClick="qIdCheck();">
					<input type="hidden" name="reid" value="">
				</td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="pwd" size="20">&nbsp;*</td>
			</tr>
			<tr>
				<td>Password_Check</td>
				<td><input type="password" name="pwd_chk" size="20">&nbsp;*</td>
			</tr>
			<tr>
				<td>Email</td>
				<td><input type="text" name="email" size="20"></td>
			</tr>
			<tr>
				<td>Phone</td>
				<td><input type="text" name="phone" size="20"></td>
			</tr>
			<tr>
				<td>Permission</td>
				<td>
					<input type="radio" name="admin" value="0" checked="checked">User
					<input type="radio" name="admin" value="1" >Admin
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="sign up" onClick="return qJoinCheck();">
					<input type="reset" value="reset">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>