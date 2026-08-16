package vn.edu.eaut.lab6.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import vn.edu.eaut.lab6.store.StudentStore;

@WebListener
public class AppContextListener
        implements ServletContextListener {

    @Override
    public void contextInitialized(
            ServletContextEvent event) {

        ServletContext context =
                event.getServletContext();

        context.setAttribute(
                "appName",
                "Lab 6 - Quản lý sinh viên"
        );

        System.out.println(
                "================================="
        );

        System.out.println(
                "Ứng dụng Lab 6 đã khởi động."
        );

        System.out.println(
                "Số sinh viên mẫu: "
                        + StudentStore.count()
        );

        System.out.println(
                "================================="
        );
    }

    @Override
    public void contextDestroyed(
            ServletContextEvent event) {

        System.out.println(
                "================================="
        );

        System.out.println(
                "Ứng dụng Lab 6 đã dừng."
        );

        System.out.println(
                "Tổng số sinh viên: "
                        + StudentStore.count()
        );

        System.out.println(
                "================================="
        );
    }
}