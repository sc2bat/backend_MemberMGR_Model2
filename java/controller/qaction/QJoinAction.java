package controller.qaction;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.QMDao;
import dto.QMDto;

public class QJoinAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QMDto qmdto = new QMDto();
		qmdto.setUserid(request.getParameter("id"));
		qmdto.setName(request.getParameter("name"));
		qmdto.setPwd(request.getParameter("pwd"));
		qmdto.setEmail(request.getParameter("email"));
		qmdto.setPhone(request.getParameter("phone"));
		qmdto.setAdmin(Integer.parseInt( request.getParameter("admin")));
		QMDao qmdao = QMDao.getInstance();
		
		int result = qmdao.qInsertM(qmdto);
		
		if(result == 1) {
			request.setAttribute("message", "Insert success");
		}else {
			request.setAttribute("message", "Insert fail");
		}
		
		RequestDispatcher dp = request.getRequestDispatcher("qmem/qLoginForm.jsp");
		dp.forward(request, response);
	}

}
