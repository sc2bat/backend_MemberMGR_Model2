package controller.qaction;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.QMDao;
import dto.QMDto;

public class QEditAdminAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		QMDao qmdao = QMDao.getInstance();
		QMDto qmdto = qmdao.getMember(userid);
		
		if(qmdto.getAdmin() == 1	) {
			qmdao.qEditAdmin(userid, 0);
		}else {
			qmdao.qEditAdmin(userid, 1);
		}
		
		RequestDispatcher dp = request.getRequestDispatcher("qmem.do?cmd=qMain");
		dp.forward(request, response);
	}

}
