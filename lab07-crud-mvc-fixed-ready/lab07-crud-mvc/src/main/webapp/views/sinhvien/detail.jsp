<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Chi tiết sinh viên</title>

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
        🎓 Sinh viên
    </a>

</div>

<div class="container">

    <h1 class="page-title">
        👤 Chi tiết sinh viên
    </h1>

    <div class="card">

        <div class="detail-grid">

            <div class="detail-label">
                ID
            </div>

            <div class="detail-value">
                ${sinhVien.id}
            </div>

            <div class="detail-label">
                Mã sinh viên
            </div>

            <div class="detail-value">
                ${sinhVien.maSinhVien}
            </div>

            <div class="detail-label">
                Họ và tên
            </div>

            <div class="detail-value">
                ${sinhVien.hoTen}
            </div>

            <div class="detail-label">
                Email
            </div>

            <div class="detail-value">
                ${sinhVien.email}
            </div>

            <div class="detail-label">
                Lớp
            </div>

            <div class="detail-value">
                ${sinhVien.lop}
            </div>

        </div>

        <div class="form-actions">

            <a class="btn btn-warning"
               href="${pageContext.request.contextPath}/sinhvien?action=form&id=${sinhVien.id}">
                ✏️ Sửa
            </a>

            <a class="btn btn-secondary"
               href="${pageContext.request.contextPath}/sinhvien">
                ← Quay lại
            </a>

        </div>

    </div>

</div>

</body>
</html>