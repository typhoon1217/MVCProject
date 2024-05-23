package controller;

import java.io.*;
import java.net.Socket;
import java.util.Properties;

import main.MainClient;
import model.LoginSession;
import view.LoginChoice;

public class ClientUtill {
	private static final String FILE_PATH = "./properties/ct.properties";
	private static final int MAX_RETRIES = 5;
	private static final int RETRY_INTERVAL = 5000; // 5초

	// 소통키 다른쪽에도 똑같이 사용할것

	private static final String PASS = "PASS"; // 성공
	private static final String FAIL = "FAIL"; // 실패
	
	private static final String LOGIN_CMD = "LOGIN"; // 로그인 요청
	private static final String NEW_CID_CMD = "NEW_CID_CMD"; // CID 생성 요청
	private static final String CHECK_CID_CMD = "CHECK_CID_CMG"; // CID 확인 요청
	private static final String FETCH_DATA_CMD = "FETCH_DATA"; // 정보 요청

	private Socket cs;
	private DataOutputStream out;
	private DataInputStream in;
	private String serverAddress;
	private int port;

	public ClientUtill() {
		this.cs = connect();
		if (this.cs != null) {
			try {
				this.out = new DataOutputStream(cs.getOutputStream());
				this.in = new DataInputStream(cs.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
				closeConnection();
			}
		}
	}

	private Socket connect() { // 내부 생성자 전용
		int retryCount = 0;

		while (cs == null && retryCount < MAX_RETRIES) {
			try {
				Properties properties = new Properties();
				properties.load(new FileReader(FILE_PATH));
				serverAddress = properties.getProperty("serverAddress");
				port = Integer.parseInt(properties.getProperty("port"));

				cs = new Socket(serverAddress, port);
				System.out.println("서버에 연결되었습니다.");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				break;
			} catch (IOException e) {
				retryCount++;
				System.err.println("서버에 연결할 수 없습니다. 재시도합니다. (" + retryCount + "/" + MAX_RETRIES + ")");
				try {
					Thread.sleep(RETRY_INTERVAL);
				} catch (InterruptedException ex) {
					System.err.println("재시도 간격 대기 중 오류 발생: ");
					return null; // 와일문 밖에서 null pointer 로 에러를 받는게 나음 반복문안에서 에러 발생시 무한 반복 문제발생
				}
			}
		}
		if (cs == null) {
			System.err.println("서버에 연결할 수 없습니다. 프로그램을 종료합니다.");
			System.exit(1);
		}

		return cs;
	}

	public boolean checkClientIDwAttempt(String clientID) {// 5회학인*
		boolean pass = false;
		try {
			// 서버에 로그인 요청
			out.writeUTF(CHECK_CID_CMD);
			out.writeUTF(clientID);
			// 서버로부터 토큰 수신
			String response = in.readUTF();
			if (PASS.equals(response)) {
				pass = true;// pass 값은 여기서만 변환됨 
				//
				// 설정 같다는 검증에 통과한것이기 때문에 clientID 유지 모르는거 1개만 가져옴
				int remainLoginAttempt = in.readInt(); // 숫자 
				// 싱글톤 인스턴스를 가져와 세션 정보 
				LoginSession.getInstance().setClientID(clientID, remainLoginAttempt);
			} else if ("FAIL".equals(response)) {// 로그인 실패 PASS가 아님
				System.err.println("클라이언트 아이디 검증 실패");
			}
		} catch (IOException ex) {
			System.err.println("클라이언트ID생성오류: ");
		}
		return pass;
	}
	
	public boolean requestNewClientID() {
		try {
			out.writeUTF(NEW_CID_CMD);
			
			String clientID = in.readUTF();
			if (FAIL.equals(clientID)) {
				return false;
			}else {
				String loginAttempt = in.readUTF();
				LoginSession.getInstance().setClientID(clientID, 0);
				return true;	
			}
		} catch (IOException ex) {
			System.err.println("클라이언트ID생성오류: ");
			return false;
		}
	}

	public boolean login(String username, String password) {
		boolean pass = false;
		try {
			// 서버에 로그인 요청
			out.writeUTF(LOGIN_CMD);
			out.writeUTF(username);
			out.writeUTF(password);

			// 서버로부터 토큰 수신
			String response = in.readUTF();
			if (PASS.equals(response)) {
				pass = true;
				String userToken = in.readUTF();
				String dep = in.readUTF();
				// 싱글톤 인스턴스를 가져와 세션 정보 설정
				LoginSession.getInstance().setSession(userToken, username, dep);
				return pass;
			} else if (FAIL.equals(response)) {// 로그인 실패 PASS가 아님
				String reason = in.readUTF();
				System.err.println("로그인에 실패했어요 아이디와 비밀번호가 일치하지 않아요");
				System.out.println("사유:");
				System.err.println(reason);
			} 
			//test 넣는곳
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("로그인 중 에러 발생");
		}
		return false;
	}

	public String fetchData(String requestData) {
		try {
			// 싱글톤 인스턴스로부터 사용자 식별자 가져오기
			String userToken = LoginSession.getInstance().getUserToken();
			// 서버에 데이터 요청
			out.writeUTF(FETCH_DATA_CMD);
			out.writeUTF(userToken); // 사용자 식별자 포함
			out.writeUTF(requestData);// 요청 정보 종류/요청 정보 (예:학생 삭제/ xxxx)

			// 서버로부터 응답 수신
			return in.readUTF(); // 정보종류/ 성공여부or 정보내용 (정보 종류가 리스트인 경우 메서드내부에서 requestData를 , 기준으로쪼갬)/ 스트링으로 반환하기
									// 위해서 한줄로 받음 처리는 외부에서
		} catch (IOException e) {
			System.err.println("데이터 가져오는 중 오류 발생");
			e.printStackTrace();
			return FAIL;
		}
	}

	// 연결 종료 //컨텐츠 유틸리티 호출후 닫아주기 추후 새로 연결
	public void closeConnection() {
		try {
			if (in != null)
				in.close();
			if (out != null)
				out.close();
			if (cs != null)
				cs.close();
			System.err.println("연결 종료.");
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}

}
