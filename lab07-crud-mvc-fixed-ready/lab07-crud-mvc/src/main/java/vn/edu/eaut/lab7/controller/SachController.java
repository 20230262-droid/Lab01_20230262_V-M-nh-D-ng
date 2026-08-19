package vn.edu.eaut.lab7.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.eaut.lab7.model.Sach;
import vn.edu.eaut.lab7.repository.SachRepository;

import java.io.IOException;

@WebServlet(urlPatterns = {"/sach", "/sach/*"})
public class SachController extends HttpServlet {

    private final SachRepository repository =
            new SachRepository();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        if (action == null || action.isEmpty()
                || "list".equals(action)) {

            list(request, response);

        } else if ("form".equals(action)) {

            showForm(request, response);

        } else if ("detail".equals(action)) {

            detail(request, response);

        } else if ("delete".equals(action)) {

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

        String action = request.getParameter("action");

        if ("save".equals(action)) {

            save(request, response);

        } else {

            list(request, response);
        }
    }

    // =====================================================
    // DANH SÁCH
    // =====================================================

    private void list(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        String keyword =
                request.getParameter("keyword");

        if (keyword == null) {
            keyword = "";
        }

        if (keyword.trim().isEmpty()) {

            request.setAttribute(
                    "danhSach",
                    repository.findAll()
            );

        } else {

            request.setAttribute(
                    "danhSach",
                    repository.search(keyword.trim())
            );
        }

        request.setAttribute(
                "keyword",
                keyword
        );

        request.getRequestDispatcher(
                "/views/sach/list.jsp"
        ).forward(request, response);
    }

    // =====================================================
    // HIỂN THỊ FORM THÊM / SỬA
    // =====================================================

    private void showForm(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String id =
                request.getParameter("id");

        if (id != null && !id.trim().isEmpty()) {

            try {

                int sachId =
                        Integer.parseInt(id);

                Sach sach =
                        repository.findById(sachId);

                if (sach != null) {

                    request.setAttribute(
                            "sach",
                            sach
                    );
                }

            } catch (NumberFormatException e) {

                request.setAttribute(
                        "error",
                        "ID sách không hợp lệ!"
                );
            }
        }

        request.getRequestDispatcher(
                "/views/sach/form.jsp"
        ).forward(request, response);
    }

    // =====================================================
    // CHI TIẾT
    // =====================================================

    private void detail(HttpServletRequest request,
                        HttpServletResponse response)
            throws ServletException, IOException {

        String id =
                request.getParameter("id");

        if (id == null || id.trim().isEmpty()) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/sach"
            );

            return;
        }

        try {

            int sachId =
                    Integer.parseInt(id);

            Sach sach =
                    repository.findById(sachId);

            if (sach == null) {

                response.sendRedirect(
                        request.getContextPath()
                                + "/sach"
                );

                return;
            }

            request.setAttribute(
                    "sach",
                    sach
            );

            request.getRequestDispatcher(
                    "/views/sach/detail.jsp"
            ).forward(request, response);

        } catch (NumberFormatException e) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/sach"
            );
        }
    }

    // =====================================================
    // THÊM / CẬP NHẬT
    // =====================================================

    private void save(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException {

        String id =
                request.getParameter("id");

        String maSach =
                request.getParameter("maSach");

        String tenSach =
                request.getParameter("tenSach");

        String tacGia =
                request.getParameter("tacGia");

        String nhaXuatBan =
                request.getParameter("nhaXuatBan");

        String namXuatBanText =
                request.getParameter("namXuatBan");

        // -------------------------------------------------
        // Chuẩn hóa dữ liệu
        // -------------------------------------------------

        if (maSach != null) {
            maSach = maSach.trim();
        }

        if (tenSach != null) {
            tenSach = tenSach.trim();
        }

        if (tacGia != null) {
            tacGia = tacGia.trim();
        }

        if (nhaXuatBan != null) {
            nhaXuatBan = nhaXuatBan.trim();
        }

        // -------------------------------------------------
        // Kiểm tra bắt buộc
        // -------------------------------------------------

        if (maSach == null || maSach.isEmpty()
                || tenSach == null || tenSach.isEmpty()) {

            request.getSession().setAttribute(
                    "error",
                    "Mã sách và tên sách không được để trống!"
            );

            response.sendRedirect(
                    request.getContextPath()
                            + "/sach?action=form"
            );

            return;
        }

        // -------------------------------------------------
        // Năm xuất bản
        // -------------------------------------------------

        int namXuatBan = 0;

        if (namXuatBanText != null
                && !namXuatBanText.trim().isEmpty()) {

            try {

                namXuatBan =
                        Integer.parseInt(
                                namXuatBanText.trim()
                        );

            } catch (NumberFormatException e) {

                request.getSession().setAttribute(
                        "error",
                        "Năm xuất bản phải là số!"
                );

                response.sendRedirect(
                        request.getContextPath()
                                + "/sach?action=form"
                );

                return;
            }
        }

        // =================================================
        // THÊM MỚI
        // =================================================

        if (id == null || id.trim().isEmpty()) {

            Sach sach =
                    new Sach(
                            0,
                            maSach,
                            tenSach,
                            tacGia,
                            nhaXuatBan,
                            namXuatBan
                    );

            boolean success =
                    repository.add(sach);

            if (success) {

                request.getSession().setAttribute(
                        "success",
                        "Thêm sách thành công!"
                );

            } else {

                request.getSession().setAttribute(
                        "error",
                        "Không thể thêm sách!"
                );
            }

        }

        // =================================================
        // CẬP NHẬT
        // =================================================

        else {

            try {

                int sachId =
                        Integer.parseInt(
                                id.trim()
                        );

                Sach sach =
                        new Sach(
                                sachId,
                                maSach,
                                tenSach,
                                tacGia,
                                nhaXuatBan,
                                namXuatBan
                        );

                boolean success =
                        repository.update(sach);

                if (success) {

                    request.getSession().setAttribute(
                            "success",
                            "Cập nhật sách thành công!"
                    );

                } else {

                    request.getSession().setAttribute(
                            "error",
                            "Không thể cập nhật sách!"
                    );
                }

            } catch (NumberFormatException e) {

                request.getSession().setAttribute(
                        "error",
                        "ID sách không hợp lệ!"
                );
            }
        }

        response.sendRedirect(
                request.getContextPath()
                        + "/sach"
        );
    }

    // =====================================================
    // XÓA
    // =====================================================

    private void delete(HttpServletRequest request,
                        HttpServletResponse response)
            throws IOException {

        String id =
                request.getParameter("id");

        if (id == null || id.trim().isEmpty()) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/sach"
            );

            return;
        }

        try {

            int sachId =
                    Integer.parseInt(id.trim());

            boolean success =
                    repository.delete(sachId);

            if (success) {

                request.getSession().setAttribute(
                        "success",
                        "Xóa sách thành công!"
                );

            } else {

                request.getSession().setAttribute(
                        "error",
                        "Không thể xóa sách!"
                );
            }

        } catch (NumberFormatException e) {

            request.getSession().setAttribute(
                    "error",
                    "ID sách không hợp lệ!"
            );
        }

        response.sendRedirect(
                request.getContextPath()
                        + "/sach"
        );
    }
}