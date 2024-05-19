package controller;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import model.CValidation;
import model.LoginVO;
import view.CrMain;
import view.MenuChoice;
import view.MenuViewer;

//클라이언트 로그인 매니저
public class CloginController {

    private CValidation validation;
    private CServerDataSender cds;


	public String login(Socket cs) {
    	validation = new CValidation();
		try {
			cds = new CServerDataSender(cs);
		} catch (IOException e) {
			System.out.print("cds에러: ");
			e.printStackTrace();
			return "cds에러";
		}
        String logInAs = MenuChoice.FAIL;
        String message = MenuChoice.FAIL;

        while (logInAs.equals(MenuChoice.FAIL)) {
            boolean valid = false;
            LoginVO loginVO = new LoginVO();
            while (!valid) {
                MenuViewer.prBar();
                System.out.print("사용자 이름을 입력하세요: ");
                loginVO.setId(CrMain.sc.nextLine());
                System.out.print("비밀번호를 입력하세요: ");
                loginVO.setPassword(CrMain.sc.nextLine());

                if (validation.checkID(loginVO.getId())) {
                	if (validation.checkPW(loginVO.getPassword())) {
                		valid = true;
                	}
                }
            }
            cds.send(loginVO.getId());
            cds.send(loginVO.getPassword());
            MenuViewer.prBar();
            logInAs = cds.receive();
            message = cds.receive();
            System.out.println("Message: " + message);
        }
        System.out.println("logInAs: " + logInAs);
        return logInAs;
    }
}