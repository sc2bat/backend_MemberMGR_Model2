<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardUpdate</title>
<link rel="stylesheet" type="text/css" href="css/board.css">
<script type="text/javascript" src="script/script.js"></script>
</head>
<body>
<div id="wrap" align="center">
	<h1>게시글 수정</h1>
	<form action="board.do?command=boardUpdate" method="post" name="frm">
	<input type="hidden" name="num" value="${board.num }">
		<table>
			<tr>
				<th>작성자</th>
				<td>${board.userid }<input type="hidden" name="userid" value="${loginUser.userid }"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="pass" size="12">* (게시물 수정 삭제시 필요합니다)</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" value="${board.email }" size="12" name="email"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" value="${board.title}" size="20" name="title">*</td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea cols="70" rows="15" name="content">${board.content }</textarea></td>
			</tr>
		</table> <br>
		<input type="submit" value="수정" onClick="return boardCheck();">
		<input type="reset" value="다시 작성">
		<input type="button" value="돌아가기" onClick="location.href='board.do?command=boardViewWithoutCount&num=${board.num}'">
	</form>
</div>
</body>
</html>