package view;

import java.net.Socket;

import controller.ClientIDManager;
import controller.SLoginManager;
import controller.ServerUtil;

//TODO db초기값 id 부분 랜덤 대신 문자+시퀸스로 변경
//Q: SrMain 외부에서 쓰레드를 나누어서 다시 돌아오기 때문에 전역변수로 CID를 안줌 줘도 상관없나?

public class SrMain {
	
	public static boolean end = false;

	public static void main(String[] args) {
		// FixedFee.setup();
		ServerUtil.startServer(); 
		System.out.println("server end");
	}

	public static void loginServer(Socket s) {
		String loginas = null;
		
		//클라이언트 아이디 확인
		System.out.println("클라이언트 아이디 확인"+s);
		String cID = ClientIDManager.IDCheck(s); 
		if (MenuChoice.FAIL.equals(cID)) {
			System.out.println("CID FAIL");
			return;
		}
		
		//로그인 
		System.out.println(cID+": 직원 로그인 시도"+s);
		loginas = SLoginManager.loginValidation(s,cID);	
		
		
		// 반환값 확인
		System.out.println(cID+": log in as:" + loginas);
		
		//TODO:분할 가능
		
		// login as:
		switch (loginas) {
		case MenuChoice.ADMIN:
			System.out.println(cID+": log in as:" + loginas);
			adminMenu(cID,s); 
			break;
		case MenuChoice.SERVE:
			System.out.println(cID+": log in as:" + loginas);
			serverMenu(cID,s);
			break;
		case MenuChoice.ACCOUNT:
			accountMenu(cID,s);
			System.out.println(cID+": log in as:" + loginas);
			break;
		case MenuChoice.CHEF:
			System.out.println(cID+": log in as:" + loginas);
			chefMenu(cID,s);
			break;
		default:
            System.out.println(cID+": 오류가 발생했습니다.");
			System.out.println(loginas);
			break;
		}
	}
	
	//서버 관리자 메뉴
    private static void adminMenu(String cID, Socket s) {
        System.out.println(cID+": 관리자 메뉴로 이동합니다.");
        
    }
	//서버 서버 메뉴
    private static void serverMenu(String cID,Socket s) {
        System.out.println(cID+": 서버 메뉴로 이동합니다.");
        
    }
	//서버 회계 메뉴
    private static void accountMenu(String cID,Socket s) {
        System.out.println(cID+": 계정 메뉴로 이동합니다.");
        
    }
	//서버 쉐프 메뉴
    private static void chefMenu(String cID,Socket cs) {
        System.out.println(cID+": 셰프 메뉴로 이동합니다.");
        
    }

}
