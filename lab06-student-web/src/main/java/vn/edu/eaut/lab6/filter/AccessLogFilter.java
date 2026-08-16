package vn.edu.eaut.lab6.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class AccessLogFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req =
                (HttpServletRequest) request;

        long start = System.currentTimeMillis();

        String method = req.getMethod();
        String uri = req.getRequestURI();

        HttpSession session =
                req.getSession(false);

        String username = "Guest";

        if (session != null
                && session.getAttribute("username") != null) {

            username =
                    String.valueOf(
                            session.getAttribute("username")
                    );
        }

        try {

            chain.doFilter(request, response);

        } finally {

            long time =
                    System.currentTimeMillis() - start;

            System.out.println(
                    "[ACCESS] "
                            + method
                            + " "
                            + uri
                            + " | User: "
                            + username
                            + " | "
                            + time
                            + " ms"
            );
        }
    }
}