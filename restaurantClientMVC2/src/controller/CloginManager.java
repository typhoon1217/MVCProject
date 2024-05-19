package controller;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import model.CValidation;
import view.CrMain;
import view.MenuChoice;
import view.MenuViewer;

//클라이언트 로그인 매니저
public class CloginManager {
	public static String login(Socket cs) {
		String id = MenuChoice.FAIL;
		String pw = MenuChoice.FAIL;
		String logInAs = MenuChoice.FAIL;
		String message = MenuChoice.FAIL;
		CServerDataSender cds = null;

		// TODO: 에러 상황 고민해보기 전송자체가 안되서 알려줄 방법이 없음.
		try {
			cds = new CServerDataSender(cs);
		} catch (IOException e) {
			System.out.print("cds에러: ");
			e.printStackTrace();
			return "cds에러";
		}

		CValidation val = new CValidation();

		while (logInAs.equals(MenuChoice.FAIL)) {
			boolean valid = false;
			while (!valid) {//입력 형식 오류는 서버에서 카운팅 안할의도.
	        	MenuViewer.prBar();
				System.out.print("사용자 이름을 입력하세요: ");
				id = CrMain.sc.nextLine();
				System.out.print("비밀번호를 입력하세요: ");
				pw = CrMain.sc.nextLine();
				if (val.checkID(id) & val.checkPW(pw)) {
					valid = true;
				}
			}//end of whileValid
			cds.send(id);
			cds.send(pw);
        	MenuViewer.prBar();
			logInAs = cds.receive();
			message = cds.receive();
			System.out.println("Message: "+ message);
			
		}//end of WhillMsg
		System.out.println("logInAs: "+ logInAs);
		return logInAs; //FAIL 아닌 값만 반환가능 = 종료됨
	}

}