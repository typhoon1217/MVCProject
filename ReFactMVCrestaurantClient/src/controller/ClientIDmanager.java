package controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClientIDmanager {
    private static final String FILE_PATH = "./properties/CID.txt";

	public boolean checkClientID() {
		boolean pass = false;
		ClientUtill cUtill= new ClientUtill();
		
        // 파일이 있는지 조회하고, 있으면 파일 내용을 읽음
        Path path = Paths.get(FILE_PATH);
        if (Files.exists(path)) {
            try {// 성공 실패 여부 전달 ( // cid 서버로 요청해서 확인)
            	return (cUtill.checkClientIDwAttempt(Files.readString(path).trim())); //5회학인*
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("파일읽기 에러");
                return false;
            } //try end
        } else {
        	return(cUtill.requestNewClientID());	
        }
	}
}
