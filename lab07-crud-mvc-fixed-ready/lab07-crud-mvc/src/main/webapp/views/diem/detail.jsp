<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Chi tiết điểm</title>

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
        📝 Điểm
    </a>

</div>

<div class="container">

    <h1 class="page-title">
        📊 Chi tiết điểm
    </h1>

    <div class="card">

        <div class="detail-grid">

            <div class="detail-label">ID</div>
            <div class="detail-value">${diem.id}</div>

            <div class="detail-label">Mã sinh viên</div>
            <div class="detail-value">${diem.maSinhVien}</div>

            <div class="detail-label">Họ tên</div>
            <div class="detail-value">${diem.hoTen}</div>

            <div class="detail-label">Chuyên cần</div>
            <div class="detail-value">${diem.chuyenCan}</div>

            <div class="detail-label">Giữa kỳ</div>
            <div class="detail-value">${diem.giuaKy}</div>

            <div class="detail-label">Cuối kỳ</div>
            <div class="detail-value">${diem.cuoiKy}</div>

            <div class="detail-label">
                Điểm trung bình
            </div>

            <div class="detail-value">
                <strong>${diem.diemTrungBinh}</strong>
            </div>

        </div>

        <div class="form-actions">

            <a class="btn btn-warning"
               href="${pageContext.request.contextPath}/diem?action=form&id=${diem.id}">
                ✏️ Sửa
            </a>

            <a class="btn btn-secondary"
               href="${pageContext.request.contextPath}/diem">
                ← Quay lại
            </a>

        </div>

    </div>

</div>

</body>
</html>