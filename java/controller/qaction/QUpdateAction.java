package controller.qaction;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.QMDao;
import dto.QMDto;

public class QUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QMDto qmdto = new QMDto();
		qmdto.setName(request.getParameter("name"));
		qmdto.setUserid(request.getParameter("id"));
		qmdto.setPwd(request.getParameter("pwd"));
		qmdto.setEmail(request.getParameter("email"));
		qmdto.setPhone(request.getParameter("phone"));
		qmdto.setAdmin(Integer.parseInt(request.getParameter("admin")));
		
		QMDao qmdao = QMDao.getInstance();
		int result = qmdao.qUpdateM(qmdto);
		HttpSession session = request.getSession();
		
		if(result == 1) {
			request.setAttribute("message", "Update member success");
			session.setAttribute("qLoginUser", qmdto);
		}else {
			request.setAttribute("message", "Update member fail");
		}
		
//		RequestDispatcher dp = request.getRequestDispatcher("qmain.jsp");
		RequestDispatcher dp = request.getRequestDispatcher("qmem.do?cmd=qMain");
		dp.forward(request, response);
	}

}
