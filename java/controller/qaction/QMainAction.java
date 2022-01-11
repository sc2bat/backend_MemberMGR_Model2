package controller.qaction;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.QMDao;
import dto.QMDto;

public class QMainAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QMDao qmdao = QMDao.getInstance();
		
		ArrayList<QMDto> list = qmdao.qSelectM();
		request.setAttribute("QML", list);
		
		RequestDispatcher dp = request.getRequestDispatcher("qmain.jsp");
		dp.forward(request, response);
	}

}
