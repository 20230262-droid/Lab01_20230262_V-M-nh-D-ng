package vn.edu.eaut.lab6.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {
        "/students",
        "/student-form.jsp",
        "/welcome.jsp"
})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);

        boolean loggedIn = false;

        if (session != null) {
            String username = (String) session.getAttribute("username");
            String role = (String) session.getAttribute("role");

            loggedIn = username != null
                    && !username.isBlank()
                    && role != null
                    && !role.isBlank();
        }

        if (loggedIn) {
            chain.doFilter(request, response);
            return;
        }

        // Chưa đăng nhập
        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }
}