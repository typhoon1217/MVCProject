package view;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import controller.CLientID;
import controller.ClientUtill;
import controller.CloginManager;

public class CrMain {
    static String logInAs;
    public static Scanner sc = new Scanner(System.in);
    public static String clientID = "empty";
    
    

    public static void main(String[] args) {
        CMainMenu();
    }
    private static void CMainMenu() {
    	boolean isLogin = true;
        isLogin = exit();
    	while (isLogin) {
    		ClientUtill cu = new ClientUtill();
            Socket cs = cu.createClientSocket();  
            //클라이언트 아이디 없을시 생성 있을시 확인. 
            CLientID cID = new CLientID();
            clientID=cID.handleClientID(cs, clientID); 				
            if (MenuChoice.FAIL.equals(cID)) {
    			System.out.println("CID FAIL");
    			return;
    		}
            //로그인
            CloginManager cLM = new CloginManager();	
            String logInAs = cLM.login(cs); 							
            
            //반환값 확인 
            System.out.println(logInAs);
            
            //TODO:분할 가능
            
            // loginas:
            switch (logInAs) {
                case MenuChoice.ADMIN:
                    adminMenu(cs);
                    break;
                case MenuChoice.SERVE:
                    serverMenu(cs);
                    break;
                case MenuChoice.ACCOUNT:
                    accountMenu(cs);
                    break;
                case MenuChoice.CHEF:
                    chefMenu(cs);
                    break;
                case MenuChoice.FAIL: // 아직 반복-------------------------------중요------------
                    System.out.println("실패했습니다.");
                    break;
                default: //에러등 프로그램을 종료시키고 싶을때 
                	isLogin=false;
                    System.out.println("오류가 발생했습니다.");
                    System.out.println("프로그램을 종료합니다.");
                    return;
            }
            if (isLogin) {
                isLogin = exit();
            }
        }//end of while
    	//리턴 종료
    }
    //클라이언트 관리자 메뉴

    private static void adminMenu(Socket s) {
        System.out.println("관리자 메뉴로 이동합니다.");
        
        
    }
    //클라이언트 서버 메뉴

    private static void serverMenu(Socket s) {
        System.out.println("서버 메뉴로 이동합니다.");
    
        
    }
    //클라이언트 계정 메뉴

    private static void accountMenu(Socket s) {
        System.out.println("계정 메뉴로 이동합니다.");
    
        
    }
    //클라이언트 셰프 메뉴
    private static void chefMenu(Socket cs) {
        System.out.println("셰프 메뉴로 이동합니다.");
    
        
    }

    
    //종료?
    private static boolean exit() {
        while (true) {
        	MenuViewer.prBar();
            System.out.println("로그인 하시겠습니까?");
            System.out.println("1:로그인    2:종료");
            System.out.print(">>>");
            int i = sc.nextInt();
        	MenuViewer.prBar();
            sc.nextLine();
            if (i == 1) {
                return true;
            } else if (i == 2) {
            	System.out.println("종료합니다.");
                return false;
            } else {
                System.out.println("잘못된 입력값입니다. 다시 입력해주세요.");
            }
        }
    }
}
