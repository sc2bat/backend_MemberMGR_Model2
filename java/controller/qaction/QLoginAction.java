package controller.qaction;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.QMDao;
import dto.QMDto;

public class QLoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		QMDao qmdao = QMDao.getInstance();
		QMDto qmdto = qmdao.getMember(userid);
		
		String url = "qmem/qLoginForm.jsp";
		
		if(qmdto == null) {
			request.setAttribute("message", "ID none");
		}else if(qmdto.getPwd() == null) {
			request.setAttribute("message", "password error");
		}else if(!qmdto.getPwd().equals(pwd)) {
			request.setAttribute("message", "password not matched");
		}else if(qmdto.getPwd().equals(pwd)) {
//			url = "qmain.jsp";
			url = "qmem.do?cmd=qMain";
			HttpSession session = request.getSession();
			session.setAttribute("qLoginUser", qmdto);
		}else {
			request.setAttribute("message", "Login Failed");
		}
		
		RequestDispatcher dp = request.getRequestDispatcher(url);
		dp.forward(request, response);
	}

}
