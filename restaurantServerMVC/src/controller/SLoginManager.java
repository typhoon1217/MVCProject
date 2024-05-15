package controller;

import java.net.Socket;

import view.MenuChoice;

public class SLoginManager {

	public static String loginValidation(Socket s) {
		SClientHandler sch = new SClientHandler();
		String dep = MenuChoice.FAIL;
		boolean flag = false;
		int maxTry = 5;
		int attempt = 0;

		while (attempt < maxTry) {
			String id;
			String pw;

			SValidation val = new SValidation();

			id = sch.cGetString(s);
			flag = val.checkID(id);// 정규식 검사
			System.out.println(s + ":" + id + "받음");

			pw = sch.cGetString(s);
			flag = val.checkPW(pw);// 정규식 검사
			System.out.println(s + ":" + "pw" + "받음"); // 비밀번호 숨기기 "pw" 보여주기 pw

			if (flag) {
				dep = SLoginDAO.loginAndGetDepartment(id, pw); // DB로 확인후 부서 받아옴
				sch.cSendString(s, dep);
				return dep;
			} else {
				System.out.println("로그인 실패");
				System.out.print("남은횟수:");
				System.out.println(maxTry - (attempt + 1)); // 남은 시도 횟수 출력
				attempt++; // 시도 횟수 증가
			}
		}

		if (attempt >= maxTry) {
			dep = "failed5time";
		}

		sch.cSendString(s, dep); // 로그인 실패 메시지 전송
		return dep;
	}
}