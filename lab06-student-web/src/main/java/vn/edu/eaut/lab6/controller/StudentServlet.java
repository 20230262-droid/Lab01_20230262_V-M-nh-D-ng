package vn.edu.eaut.lab6.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import vn.edu.eaut.lab6.model.Student;
import vn.edu.eaut.lab6.store.StudentStore;

import java.io.IOException;
import java.util.List;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {

    /*
     * =========================
     * GET /students
     * =========================
     */
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action = getParameter(request, "action");

        /*
         * =========================
         * MỞ FORM THÊM
         * /students?action=add
         * =========================
         */
        if ("add".equalsIgnoreCase(action)) {

            if (!isAdmin(request)) {

                setError(
                        request,
                        "Bạn không có quyền thêm sinh viên."
                );

                redirectToStudents(
                        request,
                        response
                );

                return;
            }

            Student student = new Student();

            request.setAttribute(
                    "student",
                    student
            );

            request.getRequestDispatcher(
                    "/student-form.jsp"
            ).forward(request, response);

            return;
        }

        /*
         * =========================
         * MỞ FORM SỬA
         * /students?action=edit&id=SV001
         * =========================
         */
        if ("edit".equalsIgnoreCase(action)) {

            if (!isAdmin(request)) {

                setError(
                        request,
                        "Bạn không có quyền sửa sinh viên."
                );

                redirectToStudents(
                        request,
                        response
                );

                return;
            }

            String id =
                    getParameter(request, "id");

            Student student =
                    StudentStore.findById(id);

            if (student == null) {

                setError(
                        request,
                        "Không tìm thấy sinh viên."
                );

                redirectToStudents(
                        request,
                        response
                );

                return;
            }

            request.setAttribute(
                    "student",
                    student
            );

            request.getRequestDispatcher(
                    "/student-form.jsp"
            ).forward(request, response);

            return;
        }

        /*
         * =========================
         * MẶC ĐỊNH:
         * /students
         * /students?keyword=...
         * =========================
         */

        String keyword =
                getParameter(request, "keyword");

        List<Student> students;

        if (keyword.isEmpty()) {

            students =
                    StudentStore.findAll();

        } else {

            students =
                    StudentStore.search(keyword);
        }

        request.setAttribute(
                "students",
                students
        );

        request.setAttribute(
                "keyword",
                keyword
        );

        /*
         * Xóa thông báo sau khi hiển thị
         */
        HttpSession session =
                request.getSession(false);

        if (session != null) {

            Object success =
                    session.getAttribute("success");

            Object error =
                    session.getAttribute("error");

            if (success != null) {

                request.setAttribute(
                        "success",
                        success
                );

                session.removeAttribute("success");
            }

            if (error != null) {

                request.setAttribute(
                        "error",
                        error
                );

                session.removeAttribute("error");
            }
        }

        /*
         * Hiển thị danh sách
         */
        request.getRequestDispatcher(
                "/student-list.jsp"
        ).forward(request, response);
    }


    /*
     * =========================
     * POST /students
     * =========================
     */
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        /*
         * Chỉ ADMIN được thay đổi dữ liệu
         */
        if (!isAdmin(request)) {

            setError(
                    request,
                    "Bạn không có quyền thực hiện thao tác này."
            );

            redirectToStudents(
                    request,
                    response
            );

            return;
        }

        String action =
                getParameter(request, "action");

        /*
         * Thêm
         */
        if ("add".equalsIgnoreCase(action)) {

            addStudent(
                    request,
                    response
            );

            return;
        }

        /*
         * Cập nhật
         */
        if ("update".equalsIgnoreCase(action)) {

            updateStudent(
                    request,
                    response
            );

            return;
        }

        /*
         * Xóa
         */
        if ("delete".equalsIgnoreCase(action)) {

            deleteStudent(
                    request,
                    response
            );

            return;
        }

        /*
         * Action không hợp lệ
         */
        setError(
                request,
                "Thao tác không hợp lệ."
        );

        redirectToStudents(
                request,
                response
        );
    }


    /*
     * =========================
     * ADD
     * =========================
     */
    private void addStudent(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String id =
                getParameter(request, "id");

        String name =
                getParameter(request, "name");

        String className =
                getParameter(request, "className");

        String email =
                getParameter(request, "email");

        Student student =
                new Student(
                        id,
                        name,
                        className,
                        email
                );

        /*
         * Kiểm tra dữ liệu
         */
        if (id.isEmpty()
                || name.isEmpty()
                || className.isEmpty()
                || email.isEmpty()) {

            request.setAttribute(
                    "error",
                    "Vui lòng nhập đầy đủ thông tin."
            );

            request.setAttribute(
                    "student",
                    student
            );

            request.getRequestDispatcher(
                    "/student-form.jsp"
            ).forward(request, response);

            return;
        }

        /*
         * Kiểm tra trùng mã
         */
        if (StudentStore.exists(id)) {

            request.setAttribute(
                    "error",
                    "Mã sinh viên đã tồn tại."
            );

            request.setAttribute(
                    "student",
                    student
            );

            request.getRequestDispatcher(
                    "/student-form.jsp"
            ).forward(request, response);

            return;
        }

        /*
         * Thêm
         */
        StudentStore.add(student);

        setSuccess(
                request,
                "Thêm sinh viên thành công."
        );

        redirectToStudents(
                request,
                response
        );
    }


    /*
     * =========================
     * UPDATE
     * =========================
     */
    private void updateStudent(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String id =
                getParameter(request, "id");

        String name =
                getParameter(request, "name");

        String className =
                getParameter(request, "className");

        String email =
                getParameter(request, "email");

        /*
         * Kiểm tra dữ liệu
         */
        if (id.isEmpty()
                || name.isEmpty()
                || className.isEmpty()
                || email.isEmpty()) {

            Student student =
                    new Student(
                            id,
                            name,
                            className,
                            email
                    );

            request.setAttribute(
                    "error",
                    "Vui lòng nhập đầy đủ thông tin."
            );

            request.setAttribute(
                    "student",
                    student
            );

            request.getRequestDispatcher(
                    "/student-form.jsp"
            ).forward(request, response);

            return;
        }

        /*
         * Cập nhật
         */
        boolean updated =
                StudentStore.update(
                        id,
                        name,
                        className,
                        email
                );

        if (updated) {

            setSuccess(
                    request,
                    "Cập nhật sinh viên thành công."
            );

        } else {

            setError(
                    request,
                    "Không tìm thấy sinh viên."
            );
        }

        redirectToStudents(
                request,
                response
        );
    }


    /*
     * =========================
     * DELETE
     * =========================
     */
    private void deleteStudent(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        String id =
                getParameter(request, "id");

        boolean deleted =
                StudentStore.delete(id);

        if (deleted) {

            setSuccess(
                    request,
                    "Xóa sinh viên thành công."
            );

        } else {

            setError(
                    request,
                    "Không tìm thấy sinh viên cần xóa."
            );
        }

        redirectToStudents(
                request,
                response
        );
    }


    /*
     * =========================
     * KIỂM TRA ADMIN
     * =========================
     */
    private boolean isAdmin(
            HttpServletRequest request) {

        HttpSession session =
                request.getSession(false);

        if (session == null) {
            return false;
        }

        String role =
                (String) session.getAttribute("role");

        return "ADMIN".equals(role);
    }


    /*
     * =========================
     * SUCCESS
     * =========================
     */
    private void setSuccess(
            HttpServletRequest request,
            String message) {

        request.getSession(true)
                .setAttribute(
                        "success",
                        message
                );
    }


    /*
     * =========================
     * ERROR
     * =========================
     */
    private void setError(
            HttpServletRequest request,
            String message) {

        request.getSession(true)
                .setAttribute(
                        "error",
                        message
                );
    }


    /*
     * =========================
     * REDIRECT
     * =========================
     */
    private void redirectToStudents(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        response.sendRedirect(
                request.getContextPath()
                        + "/students"
        );
    }


    /*
     * =========================
     * LẤY PARAMETER AN TOÀN
     * =========================
     */
    private String getParameter(
            HttpServletRequest request,
            String name) {

        String value =
                request.getParameter(name);

        if (value == null) {
            return "";
        }

        return value.trim();
    }
}