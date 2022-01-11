package controller;

import controller.qaction.Action;
import controller.qaction.QDeleteAction;
import controller.qaction.QEditAdminAction;
import controller.qaction.QIdCheckAction;
import controller.qaction.QJoinAction;
import controller.qaction.QJoinFormAction;
import controller.qaction.QLoginAction;
import controller.qaction.QLoginFormAction;
import controller.qaction.QLogoutAction;
import controller.qaction.QMainAction;
import controller.qaction.QUpdateAction;
import controller.qaction.QUpdateFormAction;

public class QActionFactory {
	private QActionFactory() {}
	private static QActionFactory q = new QActionFactory();
	public static QActionFactory getInstance() {return q;}
	
	public Action getAction(String cmd) {
		Action ac = null;
		if(cmd.equals("qLoginForm")) {ac = new QLoginFormAction();
		}else if(cmd.equals("qLogin")) {ac = new QLoginAction();
		}else if(cmd.equals("qMain")) {ac = new QMainAction();
		}else if(cmd.equals("qJoinForm")) {ac = new QJoinFormAction();
		}else if(cmd.equals("qJoin")) {ac = new QJoinAction();
		}else if(cmd.equals("qIdCheck")) {ac = new QIdCheckAction();
		}else if(cmd.equals("qLogout")) {ac = new QLogoutAction();
		}else if(cmd.equals("qUpdateForm")) {ac = new QUpdateFormAction();
		}else if(cmd.equals("qUpdate")) {ac = new QUpdateAction();
		}else if(cmd.equals("qDeleteM")) {ac = new QDeleteAction();
		}else if(cmd.equals("qEditAdmin")) {ac = new QEditAdminAction();
		}
		return ac;
	}
}
