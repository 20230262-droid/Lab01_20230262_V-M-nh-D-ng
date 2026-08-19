<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Chi tiết sách</title>

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
        📖 Chi tiết sách
    </h1>

    <div class="card">

        <div class="detail-grid">

            <div class="detail-label">
                ID
            </div>

            <div class="detail-value">
                ${sach.id}
            </div>

            <div class="detail-label">
                Mã sách
            </div>

            <div class="detail-value">
                ${sach.maSach}
            </div>

            <div class="detail-label">
                Tên sách
            </div>

            <div class="detail-value">
                ${sach.tenSach}
            </div>

            <div class="detail-label">
                Tác giả
            </div>

            <div class="detail-value">
                ${sach.tacGia}
            </div>

            <div class="detail-label">
                Nhà xuất bản
            </div>

            <div class="detail-value">
                ${sach.nhaXuatBan}
            </div>

            <div class="detail-label">
                Năm xuất bản
            </div>

            <div class="detail-value">
                ${sach.namXuatBan}
            </div>

        </div>

        <div class="form-actions">

            <a
                    href="${pageContext.request.contextPath}/sach?action=form&id=${sach.id}"
                    class="btn btn-warning">
                ✏️ Sửa
            </a>

            <a
                    href="${pageContext.request.contextPath}/sach"
                    class="btn btn-secondary">
                ← Quay lại
            </a>

        </div>

    </div>

</div>

</body>
</html>