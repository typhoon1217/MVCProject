package view;

import java.net.Socket;

import controller.FixedFee;
import controller.SLoginDAO;
import controller.SLoginManager;
import controller.ServerUtil;

//TODO db초기값 id 부분 랜덤 대신 문자+시퀸스로 변경
public class SrMain {
	public static void main(String[] args) {
		FixedFee.setup();
		ServerUtil.startServer(); // each client connection goes to new login
		System.out.println("The end");
	}

	
	
	
	
	
	
	
	public static void loginServer(Socket s) {
		String loginas = null;
		System.out.println("직원 로그인 시도");
		loginas=SLoginManager.loginValidation(s);
		//반환값 확인
		System.out.println("log in as:" + loginas);
		switch (loginas) {
        case MenuChoice.ADMIN:
        	System.out.println("실행"+loginas);
            break;
        case MenuChoice.SERVE:

        	System.out.println("실행"+loginas);
            break;
        case MenuChoice.ACCOUNT:

        	System.out.println("실행"+loginas);
            break;
        case MenuChoice.CHEF:
        	System.out.println("실행"+loginas);
        	
            break;
        case MenuChoice.FAIL:
        	System.out.println("실행"+loginas);
        	return; //종료됨 
		}
	}
	//검사용 나중에 지움
	public static void test(String t) {
		System.out.println("검사"+t+"------------------------------------------------------");
	}
}
 