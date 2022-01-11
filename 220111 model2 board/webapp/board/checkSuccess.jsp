<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>checkSuccess</title>
</head>
<body>
<script type="text/javascript">
	if(window.name == 'update'){
		window.opener.location.href = "board.do?command=boardUpdateForm&num=${param.num}";
	}else if(window.name == 'delete'){
		var bool = confirm("정말로 삭제할까요?");
		if(bool){
			window.opener.location.href = "board.do?command=boardDelete&num=${param.num}";
		}else{
			// 되돌아가고자하는 창이 열려있기때문에 else 부분은 굳이 없어도 됨
// 			window.opener.location.href = "board.do?command=boardViewWithoutCount&num=${param.num}";
		}
			
	}
	self.close();
</script>
</body>
</html>