package board.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dao.BDao;
import board.dto.BDto;
import board.util.Paging;

public class MainAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		BDao bdao = BDao.getInstance();
//		ArrayList<BDto> blist = bdao.selectB();
//		
//		request.setAttribute("blist", blist);
//		RequestDispatcher dp = request.getRequestDispatcher("main.jsp");
//		dp.forward(request, response);
				
		//--------------------------------------------------------------------------------------------------		
		BDao bdao = BDao.getInstance();
		Paging paging = new Paging();
		
		int page = 1;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		paging.setPage(page);
		
		int count = bdao.getAllCount();
		paging.setTotalCount(count);
		
		ArrayList<BDto> blist = bdao.selectB(paging);
		for(BDto bdto : blist) {
			int cnt = bdao.getReplycnt(bdto.getNum());
			bdto.setReplycnt(cnt);
		}
		
		request.setAttribute("blist", blist);
		request.setAttribute("paging", paging);
		
		request.getRequestDispatcher("main.jsp").forward(request, response);
		
	}

}
