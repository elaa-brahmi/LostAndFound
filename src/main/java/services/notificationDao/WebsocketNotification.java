package services.notificationDao;

import jakarta.websocket.server.ServerEndpoint;
import jakarta.websocket.*;
import org.hibernate.Session;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@ServerEndpoint("notification")
public class WebsocketNotification {
    private static final Map<String, Session> userSessions = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        String userId = getUserIdFromSession(session);
        userSessions.put(userId, session);
        System.out.println("🔗 WebSocket connected: " + userId);
    }

    @OnClose
    public void onClose(Session session) {
        String userId = getUserIdFromSession(session);
        userSessions.remove(userId);
        System.out.println("❌ WebSocket closed: " + userId);
    }

    public static void sendNotificationToUser(Integer userId, String message) {
        Session session = userSessions.get(userId);
        if (session != null && session.isOpen()) {
            try {
                session.getBasicRemote().sendText(message);
                System.out.println("📩 WebSocket notification sent to user: " + userId);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getUserIdFromSession(Session session) {
        // Retrieve user ID from session (Modify this to use your authentication mechanism)
        return session.getUserPrincipal().getName();
    }
}
