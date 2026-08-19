<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">

    <title>
        ${empty lopHoc ? 'Thêm lớp học' : 'Sửa lớp học'}
    </title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<div class="header">
    <div class="logo">🏫 LAB 07 <span>CRUD MVC</span></div>
</div>

<div class="navbar">

    <a href="${pageContext.request.contextPath}/admin">
        🏠 Trang chủ
    </a>

    <a href="${pageContext.request.contextPath}/lophoc">
        🏫 Danh sách lớp
    </a>

</div>

<div class="container">

    <h1 class="page-title">

        ${empty lopHoc
        ? '➕ Thêm lớp học'
        : '✏️ Cập nhật lớp học'}

    </h1>

    <div class="card">

        <form method="post"
              action="${pageContext.request.contextPath}/lophoc">

            <input type="hidden"
                   name="action"
                   value="save">

            <c:if test="${not empty lopHoc}">

                <input type="hidden"
                       name="id"
                       value="${lopHoc.id}">

            </c:if>

            <div class="form-group">

                <label>Mã lớp</label>

                <input type="text"
                       name="maLop"
                       class="form-control"
                       value="${lopHoc.maLop}"
                       placeholder="Ví dụ: CNTT01"
                       required>

            </div>

            <div class="form-group">

                <label>Tên lớp</label>

                <input type="text"
                       name="tenLop"
                       class="form-control"
                       value="${lopHoc.tenLop}"
                       placeholder="Nhập tên lớp"
                       required>

            </div>

            <div class="form-group">

                <label>Cố vấn</label>

                <input type="text"
                       name="coVan"
                       class="form-control"
                       value="${lopHoc.coVan}"
                       placeholder="Nhập tên giảng viên">

            </div>

            <div class="form-group">

                <label>Số lượng sinh viên</label>

                <input type="number"
                       name="soLuongSinhVien"
                       class="form-control"
                       value="${lopHoc.soLuongSinhVien}"
                       min="0"
                       required>

            </div>

            <div class="form-actions">

                <button type="submit"
                        class="btn btn-primary">
                    💾 Lưu
                </button>

                <a href="${pageContext.request.contextPath}/lophoc"
                   class="btn btn-secondary">
                    ← Quay lại
                </a>

            </div>

        </form>

    </div>

</div>

</body>
</html>