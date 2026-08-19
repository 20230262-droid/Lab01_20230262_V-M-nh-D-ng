package vn.edu.eaut.lab7.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.eaut.lab7.model.LopHoc;
import vn.edu.eaut.lab7.repository.LopHocRepository;

import java.io.IOException;

@WebServlet(urlPatterns = {"/lophoc", "/lophoc/*", "/lop-hoc", "/lop-hoc/*"})
public class LopHocController extends HttpServlet {

    private final LopHocRepository repository =
            new LopHocRepository();

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

        request.setAttribute(
                "danhSach",
                keyword == null
                        ? repository.findAll()
                        : repository.search(keyword)
        );

        request.setAttribute(
                "keyword",
                keyword
        );

        request.getRequestDispatcher(
                "/views/lophoc/list.jsp"
        ).forward(request, response);
    }

    private void showForm(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String id =
                request.getParameter("id");

        if (id != null && !id.isEmpty()) {

            try {

                LopHoc lopHoc =
                        repository.findById(
                                Integer.parseInt(id)
                        );

                request.setAttribute(
                        "lopHoc",
                        lopHoc
                );

            } catch (Exception ignored) {
            }
        }

        request.getRequestDispatcher(
                "/views/lophoc/form.jsp"
        ).forward(request, response);
    }

    private void detail(HttpServletRequest request,
                        HttpServletResponse response)
            throws ServletException, IOException {

        try {

            LopHoc lopHoc =
                    repository.findById(
                            Integer.parseInt(
                                    request.getParameter("id")
                            )
                    );

            request.setAttribute(
                    "lopHoc",
                    lopHoc
            );

            request.getRequestDispatcher(
                    "/views/lophoc/detail.jsp"
            ).forward(request, response);

        } catch (Exception e) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/lophoc"
            );
        }
    }

    private void save(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException {

        String id =
                request.getParameter("id");

        String maLop =
                request.getParameter("maLop");

        String tenLop =
                request.getParameter("tenLop");

        String coVan =
                request.getParameter("coVan");

        int soLuongSinhVien;

        try {

            soLuongSinhVien =
                    Integer.parseInt(
                            request.getParameter(
                                    "soLuongSinhVien"
                            )
                    );

        } catch (Exception e) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/lophoc?action=form"
            );

            return;
        }

        if (id == null || id.isEmpty()) {

            repository.add(
                    new LopHoc(
                            0,
                            maLop,
                            tenLop,
                            coVan,
                            soLuongSinhVien
                    )
            );

        } else {

            try {

                repository.update(
                        new LopHoc(
                                Integer.parseInt(id),
                                maLop,
                                tenLop,
                                coVan,
                                soLuongSinhVien
                        )
                );

            } catch (Exception ignored) {
            }
        }

        response.sendRedirect(
                request.getContextPath()
                        + "/lophoc"
        );
    }

    private void delete(HttpServletRequest request,
                        HttpServletResponse response)
            throws IOException {

        try {

            repository.delete(
                    Integer.parseInt(
                            request.getParameter("id")
                    )
            );

        } catch (Exception ignored) {
        }

        response.sendRedirect(
                request.getContextPath()
                        + "/lophoc"
        );
    }
}