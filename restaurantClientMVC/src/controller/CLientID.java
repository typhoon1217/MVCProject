package controller;

import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import view.MenuChoice;

public class CLientID {
    private static final String FILE_PATH = "./CID.txt";
    private CServerDataSender cds;
    private String result;

    // 클라이언트 ID 처리
    public String handleClientID(Socket cs, String clientID) {
        try {
            cds = new CServerDataSender(cs);
        } catch (IOException e) {
            System.out.print("cds 에러: ");
            e.printStackTrace();
            return "cds 에러";
        }

        // 파일이 있는지 조회하고, 있으면 파일 내용을 읽음
        Path path = Paths.get(FILE_PATH);
        if (Files.exists(path)) {
            try {
                clientID = Files.readString(path).trim();
            } catch (IOException e) {
                e.printStackTrace();
                return "파일 읽기 오류";
            }
        } else {
            clientID = "empty";  // 파일이 없을 경우 "empty"로 설정
        }

        // 서버로 클라이언트 ID를 전송
        cds.send(clientID);
        System.out.println(clientID + " 전송 요청 대기중");

        // 서버로부터 클라이언트 ID와 결과를 받음
        clientID = cds.receive();
        result = cds.receive();

        System.out.println("clientID: " + clientID);
        System.out.println("result: " + result);

        // 파일에 새 클라이언트 ID 저장 (필요시)
        switch (result) {
            case "new":
                try {
                    Files.writeString(path, clientID);
                    System.out.println("clientID 생성완료");
                    return clientID;
                } catch (IOException e) {
                    e.printStackTrace();
                    return "clientID 생성오류";
                }

            case MenuChoice.FAIL:
                return MenuChoice.FAIL;

            case "pass":
            	
                return clientID;

            default:
                return MenuChoice.FAIL;
        }
    }
}
