package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CServerDataSender {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public CServerDataSender(Socket cs) {
        this.socket = cs;
            reader = new BufferedReader(new InputStreamReader(System.in)); // 표준 입력을 통해 사용자로부터 입력 받음
            try {
				writer = new PrintWriter(cs.getOutputStream(), true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }

    // 서버에 메시지 전송
    public void sendToS() {
        try {
            String message = reader.readLine(); // 사용자로부터 입력 받음
            writer.println(message); // 입력 받은 내용을 서버로 전송
        } catch (IOException e) {
            System.out.println("메시지 전송 오류: " + e.getMessage());
        }
    }

    // 서버로부터 응답 받기
    public String reciveFromS() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            System.out.println("서버 응답 수신 오류: " + e.getMessage());
        }
        return "Fail";
    }

    // 서버와의 연결 종료
    public void closeConnection() {
        try {
            if (reader != null) reader.close();
            if (writer != null) writer.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            System.out.println("클라이언트 핸들러 연결 종료 오류: " + e.getMessage());
        }
    }
}
