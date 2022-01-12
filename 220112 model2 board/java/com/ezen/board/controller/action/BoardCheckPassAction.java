package com.ezen.board.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.board.dao.BoardDao;
import com.ezen.board.dto.BoardDto;

public class BoardCheckPassAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파라미터들을 변수에 저장합니다
		int num = Integer.parseInt(request.getParameter("num"));
		String pass = request.getParameter("pass");
		
		// 파라미터로 전달된 게시물 번호로 게시물을 조회하고, dto 에 저장합니다
		BoardDao bdao = BoardDao.getInstance();
		BoardDto bdto = bdao.getBoard(num);
		
		// 파라미터로 전달된 입력패스워드랑 조회된 게시물의 패스워드랑 비교합니다
		String url = null;
		if(bdto.getPass() == null	) {
			// 비밀번호가 다르거나 오류이면, 비밀번호 입력페이지로 다시 복귀
			request.setAttribute("message", "비밀번호 오류 관리자에게 문의");
			url = "board/boardCheckPass.jsp";
		}else if(bdto.getPass().equals(pass)) {
			// 비밀번호가 같다면 checkSuccess.jsp 로 이동
			url = "board/checkSuccess.jsp";
		}else {
			request.setAttribute("message", "비밀번호가 다릅니다");
			url = "board/boardCheckPass.jsp";
		}
		
		RequestDispatcher dp = request.getRequestDispatcher(url);
		dp.forward(request, response);
		
	}

}
