package controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import view.SrMain;

public class ServerUtil {

	static String filePath = "./sv.properties";

  

    public static ExecutorService threadPool = Executors.newFixedThreadPool(50); // 쓰레드풀크기

    public static void startServer() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(filePath));
            int port = Integer.parseInt(properties.getProperty("port")); // 파일에서 포트 정보 가져오기
            System.out.println("Server start");

            try (ServerSocket ss = new ServerSocket(port)) { // 서버 소켓 생성
                System.out.println("Login Server: 서버가 클라이언트를 기다리는 중...");

                while (true) {
                    Socket s = ss.accept(); // 클라이언트의 접속 대기
                    System.out.println(s + ": 클라이언트 접속");

                    // 각 클라이언트 요청을 별도의 스레드로 처리
                    threadPool.submit(() -> {
                        System.out.println(s + ": 쓰레드풀 진입");
                        SrMain.loginServer(s);
                        System.out.println(s + ": 접속 종료");
                    });
                }
            } catch (IOException e) {
                System.out.println("Login Server: " + e);
                System.out.println("Login Server IO오류");
            }
        } catch (IOException e) {
            System.out.println("파일을 읽을 수 없습니다: " + e);
        }
    }
}