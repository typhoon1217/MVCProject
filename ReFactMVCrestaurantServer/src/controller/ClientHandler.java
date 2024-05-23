package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private Socket clientSocket;
    private DataInputStream in;
    private DataOutputStream out;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
        try {
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            closeConnection();
        }
    }

    //한번에 받게 설계하기 권한만 나누고 그이후로는 알아서 
    
    public void handleClient() {  
        try {
            String request;
            while ((request = in.readUTF()) != null) {
                System.out.println("Received: " + request);
                // Handle different types of requests
                switch (request) { //1차 분할 권한별 나누기  
                    case "LOGIN":
                        handleLogin();
                        break;
                        
                    case "ADMIN":
                    	handleAdminMenu();
                    	break;
                        
                    case "FETCH_DATA":
                        handleFetchData();
                        break;
                        
                    default:
                        out.writeUTF("Invalid command");
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    private void handleAdminMenu() {
		// TODO Auto-generated method stub
		
	}


	private void handleLogin() {
        String username;
        String password;
		try {
			username = in.readUTF();
			password = in.readUTF();
			//검증로직 추가해야됨
			if ("user".equals(username) && "pass".equals(password)) {
			//검증로직 추가 
	            out.writeUTF("PASS");
	            out.writeUTF("유저토큰임/"); 
	            out.writeUTF("부서임/");  
			}	else {
	            out.writeUTF("FAIL");
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private void handleFetchData() {
        String userToken;

        String requestData;
		try {
			userToken = in.readUTF();
			requestData = in.readUTF();
			if ("userToken123".equals(userToken)) {
	            out.writeUTF("Data for request: " + requestData);
	        } else {
	            out.writeUTF("Unauthorized");
	        }
			
		} catch (IOException e) {
			e.printStackTrace();
		}
        
    }

    private void closeConnection() {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (clientSocket != null) clientSocket.close();
            System.out.println("Connection closed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

