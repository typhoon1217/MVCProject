package controller;

import java.io.IOException;
import java.net.Socket;
import model.SValidation;
import view.MenuChoice;

public class ClientIDManager {
    // 서버
    // 클라이언트 ID 확인 및 처리
    public static String IDCheck(Socket s) {
        SValidation val = new SValidation();
        String msg = MenuChoice.FAIL;
        SClientHandler sch = null;
        String clientID;

        try {
            sch = new SClientHandler(s);
        } catch (IOException e) {
            System.out.print("sch 에러: ");
            e.printStackTrace();
            return MenuChoice.FAIL;  // 여기선 이걸 보내야 프로그램이 종료됨
        }
//-----------------------------------
        // 클라이언트로부터 ID를 받음
        clientID = sch.receive();//1

        if ("empty".equals(clientID)) {
            // ID가 없을 시 새로 생성
            clientID = ClientIDDAO.generateIDFromDB();
            msg = "new";
            if(MenuChoice.FAIL.equals(clientID)) {
            	return MenuChoice.FAIL;
            }
            sch.send(clientID);//2
            sch.send(msg);//3
            return clientID;
        } else {
            // ID가 있을 시 확인 후 결과 반환
            if (ClientIDDAO.checkClientIDExists(clientID)) {
                // 최근 30분 동안의 로그인 시도 횟수를 확인
                int recentAttempts = ClientIDDAO.getRecentLoginAttempts(clientID);
                if (recentAttempts >= SLoginManager.maxAttempts) {
                	clientID = "30분 내 "+"maxAttempts"+"회 로그인 실패. 로그인 차단.";
                	msg = MenuChoice.FAIL; //종료시킬려면 이걸 보내야됨
              
                    sch.send(clientID);
                    sch.send(msg);
                    return msg;//종료시킬려면 이걸 받아야됨 
                }
                msg = "pass";
                // 클라이언트로 ID와 결과를 보냄
                sch.send(clientID);//2
                sch.send(msg);//3

                return clientID;
            } else {
            	System.out.println("CID Manager:CID 없음 클라이언트 이상");
                // 클라이언트로 ID와 결과를 보냄
                sch.send(MenuChoice.FAIL);//2
                sch.send(MenuChoice.FAIL);//3

                return MenuChoice.FAIL;
            }
        }
    }
}

