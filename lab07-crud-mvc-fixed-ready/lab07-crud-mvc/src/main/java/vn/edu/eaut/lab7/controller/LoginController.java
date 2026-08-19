package vn.edu.eaut.lab7.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "123";


    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect(
                request.getContextPath()
                        + "/login.jsp"
        );
    }


    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String username =
                request.getParameter("username");

        String password =
                request.getParameter("password");


        // Kiểm tra null
        if (username == null || password == null) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/login.jsp?error=1"
            );

            return;
        }


        username = username.trim();


        // Kiểm tra tài khoản
        if (USERNAME.equals(username)
                && PASSWORD.equals(password)) {

            // Tạo session
            HttpSession session =
                    request.getSession();

            // Lưu username
            session.setAttribute(
                    "username",
                    username
            );

            // Lưu trạng thái đăng nhập
            session.setAttribute(
                    "loggedIn",
                    true
            );


            // Đăng nhập thành công
            response.sendRedirect(
                    request.getContextPath()
                            + "/admin"
            );

        } else {

            // Đăng nhập thất bại
            response.sendRedirect(
                    request.getContextPath()
                            + "/login.jsp?error=1"
            );
        }
    }
}