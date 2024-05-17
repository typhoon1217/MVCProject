package controller;

import java.net.Socket;

import view.MenuChoice;
import controller.CServerDataSender;

//클라이언트 로그인 매니저
public class CloginManager {
	static CServerDataSender cds = null;
	
	public static String login(Socket cs) {
		String logInAs = null;
		cds=new CServerDataSender(cs);
		while (logInAs == (MenuChoice.ACCOUNT))
		System.out.print("사용자 이름을 입력하세요: ");
		cds.idInput();
		System.out.print("비밀번호를 입력하세요: ");
		cds.pwInput();
		logInAs = cds.reciveFromS();
		System.out.println(logInAs);
		
		return logInAs;
	}

	public static boolean isExit() {
		
		System.out.println("프로그램을 종료할까요?");
		System.out.println("1:예       2:아니요, 다시 로그인 합니다.");
		System.out.print(">>");
		
		return false;
	}
}