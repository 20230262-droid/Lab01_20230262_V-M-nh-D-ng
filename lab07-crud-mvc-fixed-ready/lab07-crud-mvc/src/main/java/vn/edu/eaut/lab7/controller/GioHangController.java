package vn.edu.eaut.lab7.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.eaut.lab7.model.CartItem;
import vn.edu.eaut.lab7.model.SanPham;
import vn.edu.eaut.lab7.repository.SanPhamRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/giohang", "/giohang/*", "/gio-hang", "/gio-hang/*"})
public class GioHangController extends HttpServlet {

    private final SanPhamRepository repository =
            new SanPhamRepository();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession();

        List<CartItem> cart =
                getCart(session);

        request.setAttribute(
                "cart",
                cart
        );

        request.getRequestDispatcher(
                "/views/giohang/cart.jsp"
        ).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {

        request.setCharacterEncoding("UTF-8");

        String action =
                request.getParameter("action");

        HttpSession session =
                request.getSession();

        List<CartItem> cart =
                getCart(session);

        if ("add".equals(action)) {

            addToCart(request, cart);

        } else if ("remove".equals(action)) {

            removeFromCart(request, cart);

        } else if ("update".equals(action)) {

            updateCart(request, cart);

        } else if ("clear".equals(action)) {

            cart.clear();
        }

        session.setAttribute(
                "cart",
                cart
        );

        response.sendRedirect(
                request.getContextPath()
                        + "/giohang"
        );
    }

    @SuppressWarnings("unchecked")
    private List<CartItem> getCart(HttpSession session) {

        Object object =
                session.getAttribute("cart");

        if (object instanceof List<?>) {

            return (List<CartItem>) object;
        }

        List<CartItem> cart =
                new ArrayList<>();

        session.setAttribute(
                "cart",
                cart
        );

        return cart;
    }

    private void addToCart(
            HttpServletRequest request,
            List<CartItem> cart) {

        try {

            int productId =
                    Integer.parseInt(
                            request.getParameter(
                                    "id"
                            )
                    );

            int quantity =
                    Integer.parseInt(
                            request.getParameter(
                                    "soLuong"
                            )
                    );

            if (quantity <= 0) {
                return;
            }

            SanPham sanPham =
                    repository.findById(productId);

            if (sanPham == null) {
                return;
            }

            for (CartItem item : cart) {

                if (item.getSanPham()
                        .getId() == productId) {

                    item.setSoLuong(
                            item.getSoLuong()
                                    + quantity
                    );

                    return;
                }
            }

            cart.add(
                    new CartItem(
                            sanPham,
                            quantity
                    )
            );

        } catch (Exception ignored) {
        }
    }

    private void removeFromCart(
            HttpServletRequest request,
            List<CartItem> cart) {

        try {

            int productId =
                    Integer.parseInt(
                            request.getParameter("id")
                    );

            cart.removeIf(
                    item ->
                            item.getSanPham()
                                    .getId()
                                    == productId
            );

        } catch (Exception ignored) {
        }
    }

    private void updateCart(
            HttpServletRequest request,
            List<CartItem> cart) {

        try {

            int productId =
                    Integer.parseInt(
                            request.getParameter("id")
                    );

            int quantity =
                    Integer.parseInt(
                            request.getParameter(
                                    "soLuong"
                            )
                    );

            if (quantity <= 0) {

                removeFromCart(
                        request,
                        cart
                );

                return;
            }

            for (CartItem item : cart) {

                if (item.getSanPham()
                        .getId() == productId) {

                    item.setSoLuong(quantity);

                    return;
                }
            }

        } catch (Exception ignored) {
        }
    }
}