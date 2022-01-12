package com.ezen.board.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.board.dao.BoardDao;
import com.ezen.board.dto.BoardDto;
import com.ezen.board.util.Paging;

public class MainAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDao bdao = BoardDao.getInstance();
//		ArrayList<BoardDto> list = bdao.selectBoard();
		
		// 페이징
		Paging paging = new Paging();
		int page = 1;
		// 2페이지를 클릭했을 때 -> board.do?command=main&page=2
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		paging.setPage(page);
		
		int count = bdao.getAllCount();
		// setTotalCount 메서드 안에는 paging() 메서드 호출명령이 같이 들어있습니다.
		paging.setTotalCount(count); // 레코드 총 갯수 세팅 + 각 멤버변수 값 계산
		
		// 화면에 10개만 표시하게끔 조회하기에 이동
		ArrayList<BoardDto> list = bdao.selectBoard(paging);
		
		// 댓글 갯수
		for(BoardDto bdto : list) {
			int cnt = bdao.getReplycnt(bdto.getNum());
			bdto.setReplycnt(cnt);
		}
		
		request.setAttribute("boardList", list);
		// 페이징 전송
		request.setAttribute("paging", paging);
		
		RequestDispatcher dp = request.getRequestDispatcher("main.jsp");
		dp.forward(request, response);
	}

}
