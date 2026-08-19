package vn.edu.eaut.lab7.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppListener
        implements ServletContextListener {

    @Override
    public void contextInitialized(
            ServletContextEvent event) {

        System.out.println(
                "===================================="
        );

        System.out.println(
                " LAB 07 CRUD MVC"
        );

        System.out.println(
                " Ứng dụng đã được khởi động."
        );

        System.out.println(
                "===================================="
        );

        event.getServletContext()
                .setAttribute(
                        "appName",
                        "LAB 07 - CRUD MVC"
                );
    }

    @Override
    public void contextDestroyed(
            ServletContextEvent event) {

        System.out.println(
                "LAB 07 CRUD MVC đã dừng."
        );
    }
}