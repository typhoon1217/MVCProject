package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import view.MenuChoice;

// 서버
public class SClientHandler {
	private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    // 생성자
    public SClientHandler(Socket s) throws IOException {
        this.socket = s;
        this.out = new PrintWriter(s.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        System.out.println("Connected to the server.");
    }


    // 클라이언트로 문자열 전송
    public void send(String i) {
        if (out != null) {
            out.println(i);
        }
    }

    // 클라이언트로부터 응답
    public String receive(){
        if (in != null) {
            try {
				return in.readLine();
			} catch (IOException e) {
				System.out.println("서버 응답오류");
				e.printStackTrace();
			}
        }
        return MenuChoice.FAIL;
    }

    // 연결 종료
    public void closeConnection() {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null) socket.close();
            System.out.println("Connection closed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}

