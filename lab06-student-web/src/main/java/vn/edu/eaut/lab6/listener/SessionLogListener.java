package vn.edu.eaut.lab6.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class SessionLogListener
        implements HttpSessionListener {

    @Override
    public void sessionCreated(
            HttpSessionEvent event) {

        System.out.println(
                "[SESSION] Session mới được tạo: "
                        + event.getSession().getId()
        );
    }

    @Override
    public void sessionDestroyed(
            HttpSessionEvent event) {

        System.out.println(
                "[SESSION] Session đã bị hủy: "
                        + event.getSession().getId()
        );
    }
}