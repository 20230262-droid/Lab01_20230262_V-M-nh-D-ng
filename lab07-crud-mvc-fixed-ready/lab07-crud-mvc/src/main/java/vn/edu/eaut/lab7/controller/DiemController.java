package vn.edu.eaut.lab7.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.eaut.lab7.model.Diem;
import vn.edu.eaut.lab7.repository.DiemRepository;

import java.io.IOException;

@WebServlet(urlPatterns = {"/diem", "/diem/*"})
public class DiemController extends HttpServlet {

    private final DiemRepository repository =
            new DiemRepository();

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

        request.setAttribute(
                "danhSach",
                repository.findAll()
        );

        request.getRequestDispatcher(
                "/views/diem/list.jsp"
        ).forward(request, response);
    }

    private void showForm(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String id =
                request.getParameter("id");

        if (id != null && !id.isEmpty()) {

            try {

                Diem diem =
                        repository.findById(
                                Integer.parseInt(id)
                        );

                request.setAttribute(
                        "diem",
                        diem
                );

            } catch (Exception ignored) {
            }
        }

        request.getRequestDispatcher(
                "/views/diem/form.jsp"
        ).forward(request, response);
    }

    private void detail(HttpServletRequest request,
                        HttpServletResponse response)
            throws ServletException, IOException {

        try {

            Diem diem =
                    repository.findById(
                            Integer.parseInt(
                                    request.getParameter("id")
                            )
                    );

            request.setAttribute(
                    "diem",
                    diem
            );

            request.getRequestDispatcher(
                    "/views/diem/detail.jsp"
            ).forward(request, response);

        } catch (Exception e) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/diem"
            );
        }
    }

    private void save(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException {

        String id =
                request.getParameter("id");

        String maSinhVien =
                request.getParameter("maSinhVien");

        String hoTen =
                request.getParameter("hoTen");

        try {

            double chuyenCan =
                    Double.parseDouble(
                            request.getParameter(
                                    "chuyenCan"
                            )
                    );

            double giuaKy =
                    Double.parseDouble(
                            request.getParameter(
                                    "giuaKy"
                            )
                    );

            double cuoiKy =
                    Double.parseDouble(
                            request.getParameter(
                                    "cuoiKy"
                            )
                    );

            if (chuyenCan < 0 || chuyenCan > 10
                    || giuaKy < 0 || giuaKy > 10
                    || cuoiKy < 0 || cuoiKy > 10) {

                response.sendRedirect(
                        request.getContextPath()
                                + "/diem?action=form"
                );

                return;
            }

            if (id == null || id.isEmpty()) {

                repository.add(
                        new Diem(
                                0,
                                maSinhVien,
                                hoTen,
                                chuyenCan,
                                giuaKy,
                                cuoiKy
                        )
                );

            } else {

                repository.update(
                        new Diem(
                                Integer.parseInt(id),
                                maSinhVien,
                                hoTen,
                                chuyenCan,
                                giuaKy,
                                cuoiKy
                        )
                );
            }

        } catch (Exception ignored) {
        }

        response.sendRedirect(
                request.getContextPath()
                        + "/diem"
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
                        + "/diem"
        );
    }
}