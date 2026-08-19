<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>
        ${empty diem ? 'Thêm điểm' : 'Sửa điểm'}
    </title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">

</head>

<body>

<div class="header">

    <div class="logo">
        📝 LAB 07 <span>CRUD MVC</span>
    </div>

</div>

<div class="navbar">

    <a href="${pageContext.request.contextPath}/admin">
        🏠 Trang chủ
    </a>

    <a href="${pageContext.request.contextPath}/diem">
        📝 Danh sách điểm
    </a>

</div>

<div class="container">

    <h1 class="page-title">

        ${empty diem
        ? '➕ Thêm điểm'
        : '✏️ Cập nhật điểm'}

    </h1>

    <div class="card">

        <form method="post"
              action="${pageContext.request.contextPath}/diem">

            <input type="hidden"
                   name="action"
                   value="save">

            <c:if test="${not empty diem}">

                <input type="hidden"
                       name="id"
                       value="${diem.id}">

            </c:if>

            <div class="form-group">

                <label>Mã sinh viên</label>

                <input type="text"
                       name="maSinhVien"
                       class="form-control"
                       value="${diem.maSinhVien}"
                       required>

            </div>

            <div class="form-group">

                <label>Họ tên</label>

                <input type="text"
                       name="hoTen"
                       class="form-control"
                       value="${diem.hoTen}"
                       required>

            </div>

            <div class="form-group">

                <label>Điểm chuyên cần</label>

                <input type="number"
                       name="chuyenCan"
                       class="form-control"
                       value="${diem.chuyenCan}"
                       min="0"
                       max="10"
                       step="0.1"
                       required>

            </div>

            <div class="form-group">

                <label>Điểm giữa kỳ</label>

                <input type="number"
                       name="giuaKy"
                       class="form-control"
                       value="${diem.giuaKy}"
                       min="0"
                       max="10"
                       step="0.1"
                       required>

            </div>

            <div class="form-group">

                <label>Điểm cuối kỳ</label>

                <input type="number"
                       name="cuoiKy"
                       class="form-control"
                       value="${diem.cuoiKy}"
                       min="0"
                       max="10"
                       step="0.1"
                       required>

            </div>

            <div class="form-actions">

                <button type="submit"
                        class="btn btn-primary">
                    💾 Lưu
                </button>

                <a href="${pageContext.request.contextPath}/diem"
                   class="btn btn-secondary">
                    ← Quay lại
                </a>

            </div>

        </form>

    </div>

</div>

</body>
</html>