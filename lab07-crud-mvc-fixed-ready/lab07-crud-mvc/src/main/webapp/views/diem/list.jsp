<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Quản lý điểm</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">

</head>

<body>

<div class="header">

    <div class="logo">
        📝 LAB 07 <span>CRUD MVC</span>
    </div>

    <div>
        Xin chào,
        <strong>${sessionScope.username}</strong>
    </div>

</div>

<div class="navbar">

    <a href="${pageContext.request.contextPath}/admin">
        🏠 Trang chủ
    </a>

    <a href="${pageContext.request.contextPath}/sinhvien">
        🎓 Sinh viên
    </a>

    <a href="${pageContext.request.contextPath}/sach">
        📚 Sách
    </a>

    <a href="${pageContext.request.contextPath}/sanpham">
        🛍 Sản phẩm
    </a>

    <a href="${pageContext.request.contextPath}/lophoc">
        🏫 Lớp học
    </a>

    <a href="${pageContext.request.contextPath}/diem">
        📝 Điểm
    </a>

    <a href="${pageContext.request.contextPath}/giohang">
        🛒 Giỏ hàng
    </a>

    <a href="${pageContext.request.contextPath}/logout">
        🚪 Đăng xuất
    </a>

</div>

<div class="container">

    <h1 class="page-title">
        📝 Quản lý điểm
    </h1>

    <p class="page-description">
        Quản lý điểm học tập của sinh viên.
    </p>

    <div class="card">

        <div style="margin-bottom:20px">

            <a href="${pageContext.request.contextPath}/diem?action=form"
               class="btn btn-success">
                + Thêm điểm
            </a>

        </div>

        <div class="table-wrapper">

            <table class="table">

                <thead>

                <tr>

                    <th>ID</th>
                    <th>Mã SV</th>
                    <th>Họ tên</th>
                    <th>Chuyên cần</th>
                    <th>Giữa kỳ</th>
                    <th>Cuối kỳ</th>
                    <th>Điểm TB</th>
                    <th>Thao tác</th>

                </tr>

                </thead>

                <tbody>

                <c:choose>

                    <c:when test="${empty danhSach}">

                        <tr>

                            <td colspan="8"
                                style="text-align:center">

                                Chưa có dữ liệu điểm.

                            </td>

                        </tr>

                    </c:when>

                    <c:otherwise>

                        <c:forEach var="diem"
                                   items="${danhSach}">

                            <tr>

                                <td>
                                        ${diem.id}
                                </td>

                                <td>
                                        ${diem.maSinhVien}
                                </td>

                                <td>
                                    <strong>
                                            ${diem.hoTen}
                                    </strong>
                                </td>

                                <td>
                                        ${diem.chuyenCan}
                                </td>

                                <td>
                                        ${diem.giuaKy}
                                </td>

                                <td>
                                        ${diem.cuoiKy}
                                </td>

                                <td>
                                        ${diem.diemTrungBinh}
                                </td>

                                <td>

                                    <div class="actions">

                                        <a class="btn btn-primary"
                                           href="${pageContext.request.contextPath}/diem?action=detail&id=${diem.id}">
                                            Xem
                                        </a>

                                        <a class="btn btn-warning"
                                           href="${pageContext.request.contextPath}/diem?action=form&id=${diem.id}">
                                            Sửa
                                        </a>

                                        <a class="btn btn-danger"
                                           href="${pageContext.request.contextPath}/diem?action=delete&id=${diem.id}"
                                           onclick="return confirm('Bạn có chắc muốn xóa điểm này không?')">
                                            Xóa
                                        </a>

                                    </div>

                                </td>

                            </tr>

                        </c:forEach>

                    </c:otherwise>

                </c:choose>

                </tbody>

            </table>

        </div>

    </div>

</div>

</body>
</html>