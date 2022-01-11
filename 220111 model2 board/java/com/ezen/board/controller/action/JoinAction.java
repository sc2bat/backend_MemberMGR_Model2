package com.ezen.board.controller.action;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.board.dao.MemberDao;
import com.ezen.board.dto.MemberDto;

public class JoinAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 전달인수들을 모두 Dto 에 넣고 insertMember 에 보내주기
		MemberDto mdto = new MemberDto();
		mdto.setUserid(request.getParameter("userid"));
		mdto.setName(request.getParameter("name"));
		mdto.setPwd(request.getParameter("pwd"));
		mdto.setEmail(request.getParameter("email"));
		mdto.setPhone(request.getParameter("phone"));
		mdto.setAdmin(Integer.parseInt(request.getParameter("admin")));
		
		MemberDao mdao = MemberDao.getInstance();
		int result = mdao.insertMember(mdto);
		
//		if(result == 1) {
//			request.setAttribute("message", "회원가입완료");
//		}else {
//			request.setAttribute("message", "회원가입실패");
//		}
//		
////		RequestDispatcher dp = request.getRequestDispatcher("board.do?command=index");
//		RequestDispatcher dp = request.getRequestDispatcher("member/loginForm.jsp");
//		dp.forward(request, response);
		
		// 새로고침하면 가지고 있는 값으로 다시 회원가입하려고 하게되어 오류 발생
		// insert 경우에만
		String message = "";
		if(result == 1) {
			message = "sign up success 가나";
		}else {
			message = "sign up fail";
		}
		
		// forward 메서드로 이동한 최종 도착페이지에서 새로고침을 하면 데이터도 한번 더 추가되려고 시도합니다.
		// 새로고침에 의해 포워딩 이전 코드가 다시 실행되지 않으려면 sendRedirect 를 이용합니다
//		response.setCharacterEncoding("UTF-8");
//		response.sendRedirect("board.do?command=index&message=" + message);
		response.sendRedirect("board.do?command=index&message=" + URLEncoder.encode(message, "UTF-8"));
	}

}
