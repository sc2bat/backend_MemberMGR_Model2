package controller.qaction;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class QLoginFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "qmem/qLoginForm.jsp";
		HttpSession session = request.getSession();
		if(session.getAttribute("qLoginUser") != null) {
//			url = "qmain.jsp";
			url = "qmem.do?cmd=qMain";
		}
		
//		RequestDispatcher dp = request.getRequestDispatcher("qmem/qLoginForm.jsp");
		RequestDispatcher dp = request.getRequestDispatcher(url);
		dp.forward(request, response);
		
		
	}

}
