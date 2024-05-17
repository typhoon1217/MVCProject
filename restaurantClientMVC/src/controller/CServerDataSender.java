package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CServerDataSender {
	private Socket socket;
	private BufferedReader sIn;
	private PrintWriter sOut;
	private BufferedReader input;
	private CValidation val = new CValidation();
	private Socket cs;

	public CServerDataSender(Socket cs) {
		this.socket = cs;

		try {
			input = new BufferedReader(new InputStreamReader(System.in)); // 사용자로부터 입력 받음
			 //서버에서 받음 
			sOut = new PrintWriter(cs.getOutputStream(), true); //서버로 보냄
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 서버로부터 응답 받기
	public String reciveFromS() {
		sIn = new BufferedReader(new InputStreamReader(cs .getInputStream()));
		String ans = sIn.readLine();
		return ans;
	}
	
	//i 서버로 전송
	public void stToS(String i){
		sOut.println(i); // 입력 받은 내용을 서버로 전송
	}
	
	public void inputB() {
		
	}
	
	// 서버에 입력 전송
	public void stInput() {
		try {
			stToS(input.readLine()); // 입력 받은 내용을 서버로 전송
		} catch (IOException e) {
			System.out.println("메시지 전송 오류: " + e.getMessage());
		}
	}



	// 아이디 입력
	public void idInput() {
		String id;
		id = input.readLine();
		if (!val.checkID(id)) {
			
			}
	}

	// 비번 입력
	public String pwInput() {

		stToS()

	}

	// 서버와의 연결 종료
	public void closeConnection() {
		try {
			if (sOut != null)
				sOut.close();
			if (sIn != null)
				sIn.close();
			if (socket != null)
				socket.close();
		} catch (IOException e) {
			System.out.println("클라이언트 핸들러 연결 종료 오류: " + e.getMessage());
		}
	}
	//검사용 나중에 지움
	public static void test(String t) {
		System.out.println("-검사:"+t+"------------------------------------------------------");
	}
}
