package view;

import java.net.Socket;

import controller.SLoginManager;
import controller.ServerUtil;

//TODO db초기값 id 부분 랜덤 대신 문자+시퀸스로 변경
public class SrMain {
	public static boolean end = false;
	
	public static void main(String[] args) {
		//FixedFee.setup();
		ServerUtil.startServer(); // each client connection goes to new login
		System.out.println("server end");
	}

	public static void loginServer(Socket s) {
		String loginas = null;
		System.out.println("직원 로그인 시도");
		while(loginas!=MenuChoice.END) { //계속 로그인 다시받기
			loginas=SLoginManager.loginValidation(s);
			//반환값 확인
			System.out.println("log in as:" + loginas);
			switch (loginas) {
	        case MenuChoice.ADMIN:
	    		System.out.println("log in as:" + loginas);
	    		adminMenu(s); //종료하기 위해 Srmain.end=0 
	            break;
	        case MenuChoice.SERVE:
	    		System.out.println("log in as:" + loginas);
	    		serveMenu(s);
	            break;
	        case MenuChoice.ACCOUNT:
	    		AcountMenu(s);
	    		System.out.println("log in as:" + loginas);
	            break;
	        case MenuChoice.CHEF:
	    		System.out.println("log in as:" + loginas);
	    		chefMenu(s);
	            break;
	        case MenuChoice.FAIL:
	        	System.out.println(loginas);
	            break;
	        case MenuChoice.END:
	        	loginas=MenuChoice.END;
	            break;
	        default: 
	        	System.out.println(loginas);
	            break;
			}
		
		}
	}

	private static void adminMenu(Socket s) {
		// TODO Auto-generated method stub
		
	}

	private static void serveMenu(Socket s) {
		// TODO Auto-generated method stub
		
	}

	private static void AcountMenu(Socket s) {
		// TODO Auto-generated method stub
		
	}

	private static void chefMenu(Socket s) {
		// TODO Auto-generated method stub
		
	}

	//검사용 나중에 지움
	public static void test(String t) {
		System.out.println("-검사:"+t+"------------------------------------------------------");
	}
}
 