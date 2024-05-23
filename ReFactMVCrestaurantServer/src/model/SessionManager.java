package model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


//여러개의 접속세션을 다루기 위한 세션들 정보를 저장하고 여러스레드 동시에 접근해도 안전한
//concurrent hashmap 사용 각 세션에 대한 동기 처리 지원 
public class SessionManager {
    private static final SessionManager instance = new SessionManager();
    private Map<String, Session> sessions = new ConcurrentHashMap<>();

    private SessionManager() {}

    public static SessionManager getInstance() {
        return instance;
    }

    public Session getSession(String sessionId) {
        return sessions.get(sessionId);
    }

    public void createSession(String sessionId, Session session) {
        sessions.put(sessionId, session);
    }

    public void removeSession(String sessionId) {
        sessions.remove(sessionId);
    }
}


//세션 인스턴스 하나를 만들기 위한 싱글톤 패턴 사용
class Session {
    private String sessionId;
    private long lastAccessTime;

    public Session(String sessionId) {
        this.sessionId = sessionId;
        this.lastAccessTime = System.currentTimeMillis();
    }

    public synchronized String getSessionId() {
        return sessionId;
    }

    public synchronized long getLastAccessTime() {
        return lastAccessTime;
    }

    public synchronized void updateLastAccessTime() {
        this.lastAccessTime = System.currentTimeMillis();
    }

    public synchronized boolean isValid(long timeout) {
        return System.currentTimeMillis() - lastAccessTime < timeout;
    }
}
