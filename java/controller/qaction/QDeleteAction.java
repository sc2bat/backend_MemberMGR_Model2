package controller.qaction;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.QMDao;
import dto.QMDto;

public class QDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		QMDto qmdto = (QMDto)session.getAttribute("qLoginUser");
		
		QMDao qmdao = QMDao.getInstance();
		qmdao.qDeleteM(qmdto.getUserid());
		
		session.invalidate();
		
		request.setAttribute("message", qmdto.getUserid() + "WithDrawal success");
		
		RequestDispatcher dp = request.getRequestDispatcher("qmem/qLoginForm.jsp");
		dp.forward(request, response);
	}

}
