package com.ezen.board.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.board.dao.BoardDao;
import com.ezen.board.dto.BoardDto;
import com.ezen.board.dto.ReplyDto;

public class BoardViewWithoutCountAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		BoardDao bdao = BoardDao.getInstance();
		
		// BoardViewAction.java 와 거의 동일한데 조회수 카운트만 제거
//		bdao.plusReadCount(num);
		
		BoardDto bdto = bdao.getBoard(num);
		request.setAttribute("board", bdto);
		
		//
		ArrayList<ReplyDto> list = bdao.selectReply(num);
		request.setAttribute("replyList", list);
		
		RequestDispatcher dp = request.getRequestDispatcher("board/boardView.jsp");
		dp.forward(request, response);
		
	}

}
