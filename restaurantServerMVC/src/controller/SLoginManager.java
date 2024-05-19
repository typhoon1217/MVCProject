package controller;

import java.io.IOException;
import java.net.Socket;

import model.SValidation;
import view.MenuChoice;

public class SLoginManager {
    public static int maxAttempts = 5; // 최대 시도 횟수

    public String loginValidation(Socket s, String cID) {
        SValidation val = new SValidation();
        String dep = MenuChoice.FAIL;
        String msg = MenuChoice.FAIL;
        SClientHandler sch = null;
        ClientIDDAO ciDAO = new ClientIDDAO();
        SLoginDAO slDAO = new SLoginDAO();

        try {
            sch = new SClientHandler(s);
        } catch (IOException e) {
            System.out.print("sch 에러: ");
            e.printStackTrace();
            return "sch 에러";
        }

        // 최근 30분 동안의 로그인 시도 횟수를 확인
        int recentAttempts = ciDAO.getRecentLoginAttempts(cID);

        while (recentAttempts < maxAttempts) {
            String id = sch.receive();
            String pw = sch.receive();
            System.out.println("ID: " + id + " 받음");
            System.out.println("PW: " + pw + " 받음"); 

            // 로그인 시도 기록 남기기
            ciDAO.logLoginAttempt(cID);
            recentAttempts = ciDAO.getRecentLoginAttempts(cID);

            if (!val.checkID(id) || !val.checkPW(pw)) {
                dep = "Err";
                msg = "클라이언트 변조";
                sch.send(dep);
                sch.send(msg);
                return dep;
            }
            // DB로 확인 후 부서 받아옴
            dep = slDAO.loginAndGetDepartment(id, pw); 
            if (!dep.equals(MenuChoice.FAIL)) {
            	// 로그인 성공 시 로그인 시도 횟수 초기화
            	ciDAO.resetLoginAttempts(cID);
                msg = "PASS";
                sch.send(dep);
                sch.send(msg);
                return dep;
            } else {
                if (recentAttempts < maxAttempts) {
                    msg = "로그인 실패, 남은 횟수: " + (maxAttempts - recentAttempts);
                    System.out.println(msg); // 남은 시도 횟수 출력
                    sch.send(MenuChoice.FAIL);
                    sch.send(msg);
                } else {
                    dep = "문제 발생";
                    msg = "서버: 5회 접속 실패";
                    sch.send(dep);
                    sch.send(msg);
                    return dep;
                }
            }
        }

        // 5회 접속 실패 시 실행되는 코드
        dep = "문제 발생";
        msg = "서버: 5회 접속 실패";
        sch.send(dep);
        sch.send(msg);
        return dep;
    }
}

