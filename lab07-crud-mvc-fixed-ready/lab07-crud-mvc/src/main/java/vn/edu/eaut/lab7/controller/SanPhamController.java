package vn.edu.eaut.lab7.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.eaut.lab7.model.SanPham;
import vn.edu.eaut.lab7.repository.SanPhamRepository;

import java.io.IOException;

@WebServlet(urlPatterns = {"/sanpham", "/sanpham/*", "/san-pham", "/san-pham/*"})
public class SanPhamController extends HttpServlet {

    private final SanPhamRepository repository =
            new SanPhamRepository();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String action =
                request.getParameter("action");

        if (action == null || action.equals("list")) {
            list(request, response);

        } else if (action.equals("form")) {
            showForm(request, response);

        } else if (action.equals("detail")) {
            detail(request, response);

        } else if (action.equals("delete")) {
            delete(request, response);

        } else {
            list(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        if ("save".equals(
                request.getParameter("action"))) {

            save(request, response);

        } else {

            list(request, response);
        }
    }

    private void list(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        String keyword =
                request.getParameter("keyword");

        if (keyword == null) {

            request.setAttribute(
                    "danhSach",
                    repository.findAll()
            );

        } else {

            request.setAttribute(
                    "danhSach",
                    repository.search(keyword)
            );
        }

        request.setAttribute(
                "keyword",
                keyword
        );

        request.getRequestDispatcher(
                "/views/sanpham/list.jsp"
        ).forward(request, response);
    }

    private void showForm(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String id =
                request.getParameter("id");

        if (id != null && !id.isEmpty()) {

            try {

                SanPham sanPham =
                        repository.findById(
                                Integer.parseInt(id)
                        );

                request.setAttribute(
                        "sanPham",
                        sanPham
                );

            } catch (Exception ignored) {
            }
        }

        request.getRequestDispatcher(
                "/views/sanpham/form.jsp"
        ).forward(request, response);
    }

    private void detail(HttpServletRequest request,
                        HttpServletResponse response)
            throws ServletException, IOException {

        try {

            SanPham sanPham =
                    repository.findById(
                            Integer.parseInt(
                                    request.getParameter("id")
                            )
                    );

            request.setAttribute(
                    "sanPham",
                    sanPham
            );

            request.getRequestDispatcher(
                    "/views/sanpham/detail.jsp"
            ).forward(request, response);

        } catch (Exception e) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/sanpham"
            );
        }
    }

    private void save(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException {

        String id =
                request.getParameter("id");

        String ma =
                request.getParameter("ma");

        String ten =
                request.getParameter("ten");

        String moTa =
                request.getParameter("moTa");

        double gia;

        int soLuong;

        try {

            gia = Double.parseDouble(
                    request.getParameter("gia")
            );

            soLuong = Integer.parseInt(
                    request.getParameter("soLuong")
            );

        } catch (Exception e) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/sanpham?action=form"
            );

            return;
        }

        if (gia <= 0 || soLuong < 0) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/sanpham?action=form"
            );

            return;
        }

        try {

            if (id == null || id.isEmpty()) {

                repository.add(
                        new SanPham(
                                0,
                                ma,
                                ten,
                                moTa,
                                gia,
                                soLuong
                        )
                );

            } else {

                repository.update(
                        new SanPham(
                                Integer.parseInt(id),
                                ma,
                                ten,
                                moTa,
                                gia,
                                soLuong
                        )
                );
            }

        } catch (Exception ignored) {
        }

        response.sendRedirect(
                request.getContextPath()
                        + "/sanpham"
        );
    }

    private void delete(HttpServletRequest request,
                        HttpServletResponse response)
            throws IOException {

        try {

            int id =
                    Integer.parseInt(
                            request.getParameter("id")
                    );

            repository.delete(id);

        } catch (Exception ignored) {
        }

        response.sendRedirect(
                request.getContextPath()
                        + "/sanpham"
        );
    }
}