package vn.edu.eaut.lab7.filter;

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
        "/admin", "/admin/*",
        "/sinhvien", "/sinhvien/*",
        "/sinh-vien", "/sinh-vien/*",
        "/sach", "/sach/*",
        "/sanpham", "/sanpham/*",
        "/san-pham", "/san-pham/*",
        "/lophoc", "/lophoc/*",
        "/lop-hoc", "/lop-hoc/*",
        "/diem", "/diem/*",
        "/giohang", "/giohang/*",
        "/gio-hang", "/gio-hang/*"
})
public class LoginFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);

        boolean loggedIn = session != null
                && Boolean.TRUE.equals(session.getAttribute("loggedIn"));

        if (loggedIn) {
            chain.doFilter(request, response);
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
