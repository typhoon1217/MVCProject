package model;


//로그인 정보 저장  싱글톤 세션과 
//cid는 나누는게 역할분담이 확실하지만 편의를 위해 같이 작성 문제를 발생시키지 않을것으로 보임
//동기화 싱글톤 

public class LoginSession {
    private static LoginSession instance;
    private String userToken;
    private String username;
    private String userdepartment;
    private String clientID;
    private int attemptRemain;

    private LoginSession() { 
    	
    }
    
    //클라이언트에서 필요하진 않음 멀티스레드시 필요함 서버와 맞추기위해 해둠
    public static synchronized LoginSession getInstance() { 
        if (instance == null) {
            instance = new LoginSession();
        }
        return instance;
    }

    public void setSession(String userToken, String username, String userdep) {
        this.userToken = userToken;
        this.username = username;
        this.userdepartment = userdep;
    }
    public void setClientID(String cid, int attemptRemain) {
    	this.clientID = cid;
    	this.attemptRemain = attemptRemain;
    }
    
    public String getClientID() {
        return clientID;
    }  
    
    public int getAttemptRemain() {
        return attemptRemain;
    }
    
    public String getUserToken() {
        return userToken;
    }

    public String getUsername() {
        return username;
    }
    
    public String getUserdep() {
        return userdepartment;
    }

    public void clearSession() {  // 로그인정보 초기화 로그아웃or 종료시? 호출 종료시는 필요없을 수도 있음 프로그램이 완전이 종료된다면. 
        this.userToken = null;
        this.username = null;
        this.userdepartment = null;
        this.clientID = null;
        this.attemptRemain = 5;
    }
}
