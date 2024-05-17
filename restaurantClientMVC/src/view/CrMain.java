package view;

import java.net.Socket;
import controller.ClientUtill;
import controller.CloginManager;

public class CrMain {
	static String logInAs;
	
	public static void main(String[] args) {
		CMainMenu();
	}

	private static void CMainMenu() {
		boolean isExit = false;
		while(!isExit) {
			isExit=exit();
			Socket cs = ClientUtill.createClientSocket();
			logInAs = CloginManager.login(cs);

        	System.out.println(logInAs);
			switch (logInAs) {
			case MenuChoice.ADMIN:
	        	adminMenu(cs);
	            break;
	        case MenuChoice.SERVE:
	        	serverMenu(cs);
	            break;
	        case MenuChoice.ACCOUNT:
				AccountMenu(cs);
	            break;
	        case MenuChoice.CHEF:
				ChefMenu(cs);
	            break;
	        case MenuChoice.FAIL:
	            break;
	        case MenuChoice.END:
	        	System.out.println("종료합니다");
	            break;
	        default: 
	            break;
			}			
			isExit=CloginManager.isExit();
		}//end of while
	}


	private static void adminMenu(Socket cs) {
		// TODO Auto-generated method stub
		
	}

	private static void serverMenu(Socket cs) {
		// TODO Auto-generated method stub
		
	}

	private static void AccountMenu(Socket cs) {
		// TODO Auto-generated method stub
		
	}

	private static void ChefMenu(Socket cs) {
		// TODO Auto-generated method stub
		
	}
			

	private static boolean exit() {
		System.out.println("로그인 하시겠습니까?");
		System.out.println("1:로그인    2.종료");
		return false;
	}
}
