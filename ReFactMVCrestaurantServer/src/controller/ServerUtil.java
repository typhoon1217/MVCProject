package controller;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class ServerUtil {
    private static final int PORT = 1217; 
    private static final int MAX_THREADS = 50; 
    private ServerSocket serverSocket;
    private ExecutorService threadPool;

    public ServerUtil() {
        try {
            serverSocket = new ServerSocket(PORT);
            threadPool = Executors.newFixedThreadPool(MAX_THREADS);
            System.out.println("Server started on port " + PORT);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("serverUtill생성자단 오류");
        }
    }

    public void start() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());
                //ip주소 가져옴 사용은 안함

                ClientHandler clientHandler = new ClientHandler(clientSocket);
                threadPool.execute(() -> clientHandler.handleClient());
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("start단에서 오류 발생");
                //나가고 새로 접속받음 지속 에러 발생시 주의 필요 
            }
        }
    }
}
