<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>
        ${empty sinhVien ? 'Thêm sinh viên' : 'Sửa sinh viên'}
    </title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">

</head>

<body>

<div class="header">

    <div class="logo">
        🎓 LAB 07 <span>CRUD MVC</span>
    </div>

</div>

<div class="navbar">

    <a href="${pageContext.request.contextPath}/admin">
        🏠 Trang chủ
    </a>

    <a href="${pageContext.request.contextPath}/sinhvien">
        🎓 Danh sách sinh viên
    </a>

</div>

<div class="container">

    <h1 class="page-title">

        ${empty sinhVien
        ? '➕ Thêm sinh viên mới'
        : '✏️ Cập nhật sinh viên'}

    </h1>

    <div class="card">

        <form method="post"
              action="${pageContext.request.contextPath}/sinhvien">

            <input type="hidden"
                   name="action"
                   value="save">

            <c:if test="${not empty sinhVien}">

                <input type="hidden"
                       name="id"
                       value="${sinhVien.id}">

            </c:if>

            <div class="form-group">

                <label>Mã sinh viên</label>

                <input type="text"
                       name="maSinhVien"
                       class="form-control"
                       value="${sinhVien.maSinhVien}"
                       placeholder="Ví dụ: SV001"
                       required>

            </div>

            <div class="form-group">

                <label>Họ và tên</label>

                <input type="text"
                       name="hoTen"
                       class="form-control"
                       value="${sinhVien.hoTen}"
                       placeholder="Nhập họ và tên"
                       required>

            </div>

            <div class="form-group">

                <label>Email</label>

                <input type="email"
                       name="email"
                       class="form-control"
                       value="${sinhVien.email}"
                       placeholder="example@gmail.com">

            </div>

            <div class="form-group">

                <label>Lớp</label>

                <input type="text"
                       name="lop"
                       class="form-control"
                       value="${sinhVien.lop}"
                       placeholder="Ví dụ: CNTT01">

            </div>

            <div class="form-actions">

                <button type="submit"
                        class="btn btn-primary">
                    💾 Lưu
                </button>

                <a href="${pageContext.request.contextPath}/sinhvien"
                   class="btn btn-secondary">
                    ← Quay lại
                </a>

            </div>

        </form>

    </div>

</div>

</body>
</html>