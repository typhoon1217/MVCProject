package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SLoginManager {
	private static String clientInputString(Socket s) {
		String ci = "fail";
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
			ci = reader.readLine();
		} catch (IOException e) {
			System.out.println(s + "dis 오류");
			e.printStackTrace();
			ci = ("에러" + e);
		}
		return ci;
	}
	
	public static String loginValidation(Socket s) {
	int attempt = 0;
	while (attempt < 5) {
		String id = clientInputString(s);
		System.out.println(s + id + "받음");
		String pw = clientInputString(s);
		System.out.println(s + pw + "받음");
	}
	return "로그인 5회 실패";
	}

}
