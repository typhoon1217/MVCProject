package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import view.MenuChoice;

//TODO 쓰레드풀에서 해당 작업 종료클라이언트 측에 에러 리스너 추가하고 에러정보 보내기 
public class SClientHandler {
	//기본 문자열 입력
	public String cGetString(Socket s) {
		try {
			String ci = MenuChoice.FAIL;
			BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
			ci = reader.readLine();
			return ci;
		} catch (IOException e) {
			System.out.println(s + "dis 오류");
			e.printStackTrace();
	        return MenuChoice.FAIL;
		}
	}

    // 클라이언트로 문자열을 보냅니다.
    public void cSendString(Socket s, String m) {
        try {
            PrintWriter writer = new PrintWriter(s.getOutputStream());
            writer.println(m);
        } catch (IOException e) {
			System.out.println(s + "dis 오류");
            System.out.println("클라이언트에게 메시지를 보낼 수 없습니다: " + e.getMessage());
        }
    }
	
}

