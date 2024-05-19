package controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.util.Properties;

public class ClientUtill {
	private static final String FILE_PATH = "./ct.properties";
	private static final int MAX_RETRIES = 5;
	private static final int RETRY_INTERVAL = 5000; // milliseconds
	//public static Socket cs = null; //의도대로 동작하지 않기때문에 지역변수로 변경 다시 접속할대 여기 할당된 값때문에 넘어감 
	
	
	public Socket createClientSocket() {
		Socket cs = null;
		int retryCount = 0;

		while (cs == null && retryCount < MAX_RETRIES) {
			try {
				Properties properties = new Properties();
				properties.load(new FileReader(FILE_PATH));
				String serverAddress = properties.getProperty("serverAddress");
				int port = Integer.parseInt(properties.getProperty("port"));

				cs = new Socket(serverAddress, port);
				System.out.println("서버에 연결되었습니다.");

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				retryCount++;
				System.err.println("서버에 연결할 수 없습니다. 재시도합니다. (" + retryCount + "/" + MAX_RETRIES + ")");
				try {
					Thread.sleep(RETRY_INTERVAL); // 재시도 간격 대기
				} catch (InterruptedException ex) {
					System.err.println("재시도 간격 대기 중 오류 발생: " + ex.getMessage());
				}
			}
		}

		if (cs == null) {
			System.err.println("서버에 연결할 수 없습니다. 프로그램을 종료합니다.");
			System.exit(1);
		}

		return cs;
	}


	public static String clientID() {
		// TODO Auto-generated method stub
		return null;
	}
}