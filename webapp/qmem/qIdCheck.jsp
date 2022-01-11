<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>qIdCheck</title>
<script type="text/javascript">
	function qIdok(id){
		opener.frm.id.value= id;
		opener.frm.reid.value= id;
		self.close();
	}
</script>
</head>
<body>
	<form action="qmem.do" name="frm">
		<input type="hidden" name="cmd" value="qIdCheck">
		ID <input type="text" name="id" value="${id }">
		<input type="submit" value="Check_Dupli" >
	</form> <br><br><br>
	<c:if test="${check==1 }">
		<script type="text/javascript">
			opener.document.frm.id.value="";
		</script>
		${id } was used ID
	</c:if>
	<c:if test="${check==-1 }">
		${id } Can use
		<input type="button" value="confirm" class="cancel" onClick="qIdok('${id}');">
	</c:if>
</body>
</html>