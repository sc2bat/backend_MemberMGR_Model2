package controller.qaction;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.QMDao;
import dto.QMDto;

public class QIdCheckAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		QMDao qmdao = QMDao.getInstance();
		QMDto qmdto = qmdao.getMember(id);
		
		if(qmdto == null) {
			request.setAttribute("check", -1);
		}else {
			request.setAttribute("check", 1);
		}
		
		request.setAttribute("id", id);
		
		RequestDispatcher dp = request.getRequestDispatcher("qmem/qIdCheck.jsp");
		dp.forward(request, response);
	}

}
