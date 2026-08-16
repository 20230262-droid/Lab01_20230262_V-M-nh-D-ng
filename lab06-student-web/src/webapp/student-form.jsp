<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="vn.edu.eaut.lab6.model.Student" %>

<%
    String username =
            (String) session.getAttribute("username");

    String role =
            (String) session.getAttribute("role");

    if (username == null || role == null) {

        response.sendRedirect(
                request.getContextPath() + "/login.jsp"
        );

        return;
    }

    if (!"ADMIN".equals(role)) {

        response.sendRedirect(
                request.getContextPath() + "/students"
        );

        return;
    }


    Student student =
            (Student) request.getAttribute("student");

    if (student == null) {
        student = new Student();
    }


    String error =
            (String) request.getAttribute("error");


    boolean editing =
            student.getId() != null
                    && !student.getId().isBlank();
%>

<!DOCTYPE html>

<html lang="vi">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>
        <%= editing
                ? "Cập nhật sinh viên"
                : "Thêm sinh viên" %>
    </title>


    <style>

        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            min-height: 100vh;
            font-family: "Segoe UI", Arial, sans-serif;
            background:
                    radial-gradient(
                            circle at 90% 10%,
                            #fce7f3,
                            transparent 30%
                    ),
                    #fff7fa;
            color: #443847;
        }

        .topbar {
            height: 72px;
            padding: 0 35px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            background: white;
            border-bottom: 1px solid #f3dce8;
        }

        .brand {
            display: flex;
            align-items: center;
            gap: 10px;
            font-weight: 800;
            font-size: 19px;
        }

        .brand-icon {
            width: 40px;
            height: 40px;
            border-radius: 12px;
            background: linear-gradient(
                    135deg,
                    #f9a8d4,
                    #c4b5fd
            );
            display: flex;
            justify-content: center;
            align-items: center;
            color: white;
        }

        .brand span {
            color: #e56da9;
        }

        .user {
            display: flex;
            align-items: center;
            gap: 9px;
            font-size: 13px;
        }

        .avatar {
            width: 37px;
            height: 37px;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            color: white;
            font-weight: bold;
            background: linear-gradient(
                    135deg,
                    #f9a8d4,
                    #c4b5fd
            );
        }

        .container {
            width: 760px;
            max-width: 94%;
            margin: 45px auto;
        }

        .back {
            display: inline-block;
            margin-bottom: 15px;
            color: #c55e91;
            text-decoration: none;
            font-size: 13px;
        }

        .form-card {
            background: white;
            border: 1px solid #f1dfe9;
            border-radius: 24px;
            box-shadow: 0 18px 45px rgba(190,120,160,.08);
            overflow: hidden;
        }

        .form-header {
            padding: 28px 32px;
            background: linear-gradient(
                    135deg,
                    #fff1f8,
                    #f5f0ff
            );
            border-bottom: 1px solid #f3e4eb;
        }

        .header-icon {
            width: 52px;
            height: 52px;
            border-radius: 16px;
            display: flex;
            align-items: center;
            justify-content: center;
            background: linear-gradient(
                    135deg,
                    #f9a8d4,
                    #c4b5fd
            );
            color: white;
            font-size: 23px;
            margin-bottom: 15px;
        }

        .form-header h1 {
            margin: 0 0 6px;
            font-size: 23px;
        }

        .form-header p {
            margin: 0;
            color: #9c8f9c;
            font-size: 13px;
        }

        .form-body {
            padding: 32px;
        }

        .error {
            padding: 13px 15px;
            margin-bottom: 22px;
            border-radius: 12px;
            background: #fff1f2;
            color: #be123c;
            border: 1px solid #fecdd3;
            font-size: 13px;
        }

        .form-grid {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 20px;
        }

        .form-group {
            margin-bottom: 5px;
        }

        .form-group.full {
            grid-column: 1 / -1;
        }

        label {
            display: block;
            margin-bottom: 8px;
            color: #564a58;
            font-size: 13px;
            font-weight: 700;
        }

        .required {
            color: #e56da9;
        }

        input {
            width: 100%;
            height: 48px;
            border: 1px solid #eadde7;
            border-radius: 11px;
            padding: 0 14px;
            font-size: 13px;
            color: #443847;
            background: #fffafd;
            transition: .2s;
        }

        input:focus {
            outline: none;
            border-color: #f09bc7;
            box-shadow:
                    0 0 0 4px rgba(249,168,212,.15);
        }

        input.readonly {
            background: #f8f4f7;
            color: #9b8e98;
            cursor: not-allowed;
        }

        .hint {
            margin-top: 6px;
            color: #aa9ca7;
            font-size: 11px;
        }

        .buttons {
            display: flex;
            gap: 10px;
            margin-top: 28px;
            padding-top: 22px;
            border-top: 1px solid #f3e7ed;
        }

        .btn {
            min-width: 125px;
            height: 45px;
            border: none;
            border-radius: 11px;
            padding: 0 18px;
            cursor: pointer;
            text-decoration: none;
            display: inline-flex;
            justify-content: center;
            align-items: center;
            font-size: 13px;
            font-weight: 700;
            transition: .2s;
        }

        .btn-primary {
            color: white;
            background: linear-gradient(
                    135deg,
                    #ec6faf,
                    #c4a7f8
            );
            box-shadow: 0 8px 20px rgba(224,115,170,.2);
        }

        .btn-primary:hover {
            transform: translateY(-2px);
        }

        .btn-secondary {
            color: #8b7180;
            background: #fff5f9;
            border: 1px solid #f0dce7;
        }

        .btn-secondary:hover {
            background: #fce7f3;
        }

        @media (max-width: 650px) {

            .form-grid {
                grid-template-columns: 1fr;
            }

            .form-group.full {
                grid-column: auto;
            }

            .topbar {
                padding: 0 20px;
            }

            .form-body {
                padding: 22px;
            }

        }

    </style>

</head>

<body>


<!-- TOPBAR -->

<div class="topbar">

    <div class="brand">

        <div class="brand-icon">
            🌸
        </div>

        Student<span>Hub</span>

    </div>


    <div class="user">

        <div class="avatar">
            <%= username.substring(0,1).toUpperCase() %>
        </div>

        <div>
            <strong><%= username %></strong>
            <br>
            <small><%= role %></small>
        </div>

    </div>

</div>


<div class="container">


    <a class="back"
       href="<%= request.getContextPath() %>/students">

        ← Quay lại danh sách

    </a>


    <div class="form-card">


        <!-- HEADER -->

        <div class="form-header">

            <div class="header-icon">

                <%= editing ? "✏️" : "✨" %>

            </div>


            <h1>

                <%= editing
                        ? "Cập nhật sinh viên"
                        : "Thêm sinh viên mới" %>

            </h1>


            <p>

                <%= editing
                        ? "Cập nhật thông tin sinh viên trong hệ thống."
                        : "Điền thông tin để thêm sinh viên mới." %>

            </p>

        </div>


        <!-- BODY -->

        <div class="form-body">


            <% if (error != null && !error.isBlank()) { %>

            <div class="error">
                ⚠ <%= error %>
            </div>

            <% } %>


            <form method="post"
                  action="<%= request.getContextPath() %>/students">


                <input type="hidden"
                       name="action"
                       value="<%= editing ? "update" : "add" %>">


                <div class="form-grid">


                    <!-- ID -->

                    <div class="form-group">

                        <label for="id">

                            Mã sinh viên
                            <span class="required">*</span>

                        </label>


                        <input type="text"
                               id="id"
                               name="id"
                               value="<%= student.getId() != null
                                       ? student.getId()
                                       : "" %>"
                               placeholder="VD: SV006"
                            <%= editing
                                       ? "readonly class=\"readonly\""
                                       : "required" %>>


                        <div class="hint">

                            <%= editing
                                    ? "Mã sinh viên không thể thay đổi."
                                    : "Nhập mã sinh viên duy nhất." %>

                        </div>

                    </div>


                    <!-- NAME -->

                    <div class="form-group">

                        <label for="name">

                            Họ và tên
                            <span class="required">*</span>

                        </label>


                        <input type="text"
                               id="name"
                               name="name"
                               value="<%= student.getName() != null
                                       ? student.getName()
                                       : "" %>"
                               placeholder="Nguyễn Văn An"
                               required>

                    </div>


                    <!-- CLASS -->

                    <div class="form-group">

                        <label for="className">

                            Lớp
                            <span class="required">*</span>

                        </label>


                        <input type="text"
                               id="className"
                               name="className"
                               value="<%= student.getClassName() != null
                                       ? student.getClassName()
                                       : "" %>"
                               placeholder="DCCNTT15"
                               required>

                    </div>


                    <!-- EMAIL -->

                    <div class="form-group">

                        <label for="email">

                            Email
                            <span class="required">*</span>

                        </label>


                        <input type="email"
                               id="email"
                               name="email"
                               value="<%= student.getEmail() != null
                                       ? student.getEmail()
                                       : "" %>"
                               placeholder="student@example.com"
                               required>

                    </div>

                </div>


                <!-- BUTTONS -->

                <div class="buttons">


                    <button type="submit"
                            class="btn btn-primary">

                        <%= editing
                                ? "✓ Lưu thay đổi"
                                : "＋ Thêm sinh viên" %>

                    </button>


                    <a class="btn btn-secondary"
                       href="<%= request.getContextPath() %>/students">

                        Hủy

                    </a>

                </div>


            </form>

        </div>

    </div>

</div>

</body>
</html>