<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>
        ${empty sach ? 'Thêm sách' : 'Sửa sách'}
    </title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">

</head>

<body>

<div class="header">

    <div class="logo">
        📚 LAB 07 <span>CRUD MVC</span>
    </div>

</div>

<div class="navbar">

    <a href="${pageContext.request.contextPath}/admin">
        🏠 Trang chủ
    </a>

    <a href="${pageContext.request.contextPath}/sach">
        📚 Danh sách sách
    </a>

</div>

<div class="container">

    <h1 class="page-title">

        ${empty sach ? '➕ Thêm sách mới' : '✏️ Cập nhật sách'}

    </h1>

    <div class="card">

        <form method="post"
              action="${pageContext.request.contextPath}/sach">

            <input type="hidden"
                   name="action"
                   value="save">

            <c:if test="${not empty sach}">

                <input type="hidden"
                       name="id"
                       value="${sach.id}">

            </c:if>

            <div class="form-group">

                <label>Mã sách</label>

                <input
                        type="text"
                        name="maSach"
                        class="form-control"
                        value="${sach.maSach}"
                        required>

            </div>

            <div class="form-group">

                <label>Tên sách</label>

                <input
                        type="text"
                        name="tenSach"
                        class="form-control"
                        value="${sach.tenSach}"
                        required>

            </div>

            <div class="form-group">

                <label>Tác giả</label>

                <input
                        type="text"
                        name="tacGia"
                        class="form-control"
                        value="${sach.tacGia}">

            </div>

            <div class="form-group">

                <label>Nhà xuất bản</label>

                <input
                        type="text"
                        name="nhaXuatBan"
                        class="form-control"
                        value="${sach.nhaXuatBan}">

            </div>

            <div class="form-group">

                <label>Năm xuất bản</label>

                <input
                        type="number"
                        name="namXuatBan"
                        class="form-control"
                        value="${sach.namXuatBan}"
                        min="0">

            </div>

            <div class="form-actions">

                <button
                        type="submit"
                        class="btn btn-primary">
                    💾 Lưu
                </button>

                <a
                        href="${pageContext.request.contextPath}/sach"
                        class="btn btn-secondary">
                    ← Quay lại
                </a>

            </div>

        </form>

    </div>

</div>

</body>
</html>