package controller;

import java.net.Socket;

import view.MenuChoice;
import controller.CServerDataSender;

public class CloginManager {
	static CServerDataSender cds = null;
	
	public static String login(Socket cs) {
		String logInAs = null;
		cds=new CServerDataSender(cs);
		while (logInAs == (MenuChoice.ACCOUNT))
		System.out.print("사용자 이름을 입력하세요: ");
		cds.sendToS();
		System.out.print("비밀번호를 입력하세요: ");
		cds.sendToS();
		logInAs = cds.reciveFromS();
		System.out.println(logInAs);
		
		return logInAs;
	}
}