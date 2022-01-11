package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.qaction.Action;

@WebServlet("/qmem.do")
public class qMServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public qMServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String cmd = request.getParameter("cmd");
		
		Action ac = null;
		
		QActionFactory af = QActionFactory.getInstance();
		
		ac = af.getAction(cmd);
		
		if(ac == null) {
			System.out.println("ac 값 null cmd 확인");
			System.out.printf("cmd : " + cmd);
		}
		
		ac.execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
