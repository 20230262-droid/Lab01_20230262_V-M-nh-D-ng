<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Chi tiết sản phẩm</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">

</head>

<body>

<div class="header">

    <div class="logo">
        🛍 LAB 07 <span>CRUD MVC</span>
    </div>

</div>

<div class="navbar">

    <a href="${pageContext.request.contextPath}/admin">
        🏠 Trang chủ
    </a>

    <a href="${pageContext.request.contextPath}/sanpham">
        🛍 Sản phẩm
    </a>

</div>

<div class="container">

    <h1 class="page-title">
        🔎 Chi tiết sản phẩm
    </h1>

    <div class="card">

        <div class="detail-grid">

            <div class="detail-label">ID</div>

            <div class="detail-value">
                ${sanPham.id}
            </div>

            <div class="detail-label">Mã sản phẩm</div>

            <div class="detail-value">
                ${sanPham.ma}
            </div>

            <div class="detail-label">Tên sản phẩm</div>

            <div class="detail-value">
                ${sanPham.ten}
            </div>

            <div class="detail-label">Mô tả</div>

            <div class="detail-value">
                ${sanPham.moTa}
            </div>

            <div class="detail-label">Giá</div>

            <div class="detail-value">
                ${sanPham.gia}
            </div>

            <div class="detail-label">Số lượng</div>

            <div class="detail-value">
                ${sanPham.soLuong}
            </div>

        </div>

        <div class="form-actions">

            <a class="btn btn-warning"
               href="${pageContext.request.contextPath}/sanpham?action=form&id=${sanPham.id}">
                ✏️ Sửa
            </a>

            <a class="btn btn-primary"
               href="${pageContext.request.contextPath}/giohang">
                🛒 Xem giỏ hàng
            </a>

            <a class="btn btn-secondary"
               href="${pageContext.request.contextPath}/sanpham">
                ← Quay lại
            </a>

        </div>

    </div>

</div>

</body>
</html>