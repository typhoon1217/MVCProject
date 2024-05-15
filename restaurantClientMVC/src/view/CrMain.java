package view;

import java.net.Socket;
import java.util.Scanner;

import controller.CServerDataSender;
import controller.ClientUtill;
import controller.CloginManager;

public class CrMain {
	static String logInAs;
	
	public static void main(String[] args) {
		Socket cs = ClientUtill.createClientSocket();
		logInAs = CloginManager.login(cs);
		switch (logInAs) {
		case MenuChoice.ADMIN:
			MenuViewer.loginas(logInAs);
        	adminMenu();
            break;
        case MenuChoice.SERVE:
			MenuViewer.loginas(logInAs);
        	serverMenu();
            break;
        case MenuChoice.ACCOUNT:
			MenuViewer.loginas(logInAs);
			AccountMenu();
            break;
        case MenuChoice.CHEF:
			MenuViewer.loginas(logInAs);
			ChefMenu();
            break;
        case MenuChoice.FAIL:
        	System.out.println("FAIL");
        	return;
        default: 
        	System.out.println("FAIL");
        	return;
		}
	}
	private static void ChefMenu() {
		// TODO Auto-generated method stub
		
	}
	private static void AccountMenu() {
		// TODO Auto-generated method stub
		
	}
	private static void serverMenu() {
		// TODO Auto-generated method stub
		
	}
	private static void adminMenu() {
		// TODO Auto-generated method stub
		
	}

}