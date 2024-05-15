package controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.util.Properties;

public class ClientUtill {
    public static Socket createClientSocket() {
        String filePath = "./ct.properties";
        Socket clientSocket = null;
        try {
            Properties properties = new Properties();
            properties.load(new FileReader(filePath));
            String serverAddress = properties.getProperty("serverAddress");
            int port = Integer.parseInt(properties.getProperty("port"));

            clientSocket = new Socket(serverAddress, port);
            System.out.println("서버에 연결되었습니다.");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clientSocket;
    }
}
