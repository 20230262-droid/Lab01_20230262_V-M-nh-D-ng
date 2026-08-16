package vn.edu.eaut.lab6.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Date;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {

        response.sendRedirect(
                request.getContextPath() + "/login.jsp"
        );
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null) {
            username = "";
        }

        username = username.trim();

        if (password == null) {
            password = "";
        }

        String role = null;

        /*
         * Tài khoản ADMIN
         */
        if ("admin".equals(username)
                && "123456".equals(password)) {

            role = "ADMIN";
        }

        /*
         * Tài khoản USER
         */
        else if ("user".equals(username)
                && "123456".equals(password)) {

            role = "USER";
        }

        /*
         * Đăng nhập sai
         */
        if (role == null) {

            request.setAttribute(
                    "error",
                    "Sai tên đăng nhập hoặc mật khẩu!"
            );

            request.getRequestDispatcher(
                    "/login.jsp"
            ).forward(request, response);

            return;
        }

        /*
         * Xóa session cũ nếu có
         */
        HttpSession oldSession =
                request.getSession(false);

        if (oldSession != null) {
            oldSession.invalidate();
        }

        /*
         * Tạo session mới
         */
        HttpSession session =
                request.getSession(true);

        session.setAttribute(
                "username",
                username
        );

        session.setAttribute(
                "role",
                role
        );

        session.setAttribute(
                "loginTime",
                new Date()
        );

        /*
         * Sau khi đăng nhập thành công
         * chuyển sang trang welcome
         */
        response.sendRedirect(
                request.getContextPath()
                        + "/welcome.jsp"
        );
    }
}