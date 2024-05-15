package view;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import controller.DBUtil;
import controller.SLoginDAO;
import controller.SLoginManager;
import controller.ServerUtil;

public class SrestMain {
	public static void main(String[] args) {
		ServerUtil.startServer(); // each client connection goes to new login
		System.out.println("The end");
	}

	public static void loginServer(Socket s) {
		String loginAs = "fail";
		System.out.println("직원 로그인 시도");
		SLoginManager.loginValidation(s);	
	}
}
