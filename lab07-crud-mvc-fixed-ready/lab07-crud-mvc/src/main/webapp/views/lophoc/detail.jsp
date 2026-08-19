<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Chi tiết lớp học</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">

</head>

<body>

<div class="header">

    <div class="logo">
        🏫 LAB 07 <span>CRUD MVC</span>
    </div>

</div>

<div class="navbar">

    <a href="${pageContext.request.contextPath}/admin">
        🏠 Trang chủ
    </a>

    <a href="${pageContext.request.contextPath}/lophoc">
        🏫 Lớp học
    </a>

</div>

<div class="container">

    <h1 class="page-title">
        🔎 Chi tiết lớp học
    </h1>

    <div class="card">

        <div class="detail-grid">

            <div class="detail-label">ID</div>
            <div class="detail-value">${lopHoc.id}</div>

            <div class="detail-label">Mã lớp</div>
            <div class="detail-value">${lopHoc.maLop}</div>

            <div class="detail-label">Tên lớp</div>
            <div class="detail-value">${lopHoc.tenLop}</div>

            <div class="detail-label">Cố vấn</div>
            <div class="detail-value">${lopHoc.coVan}</div>

            <div class="detail-label">
                Số lượng sinh viên
            </div>

            <div class="detail-value">
                ${lopHoc.soLuongSinhVien}
            </div>

        </div>

        <div class="form-actions">

            <a class="btn btn-warning"
               href="${pageContext.request.contextPath}/lophoc?action=form&id=${lopHoc.id}">
                ✏️ Sửa
            </a>

            <a class="btn btn-secondary"
               href="${pageContext.request.contextPath}/lophoc">
                ← Quay lại
            </a>

        </div>

    </div>

</div>

</body>
</html>