package controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import view.SrestMain;

public class ServerUtil {
    private static String filePath = "./sv.properties";
    private static Properties properties = new Properties();
    private static int port;

    static {
        try {
            properties.load(new FileReader(filePath));
            port = Integer.parseInt(properties.getProperty("port"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ExecutorService threadPool = Executors.newFixedThreadPool(50); // 쓰레드풀크기

    public static void startServer() {
        System.out.println("Server start");
        try (ServerSocket ss = new ServerSocket(port)) { // 서버 소켓 생성
            System.out.println("Login Server: 서버가 클라이언트를 기다리는 중...");

            while (true) {
                Socket s = ss.accept(); // 클라이언트의 접속 대기
                System.out.println(s+"클라이언트 접속");

                // 각 클라이언트 요청을 별도의 스레드로 처리
                threadPool.submit(() -> {
                    System.out.println(s+"쓰레드풀 진입");
                    SrestMain.loginServer(s);
                });
            }
        } catch (IOException e) {
            System.out.println("Login Server: " + e);
            System.out.println("Login Server IO오류");
        } 
    }


}
