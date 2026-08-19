<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Quản lý sản phẩm</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">

</head>

<body>

<div class="header">

    <div class="logo">
        🛍 LAB 07 <span>CRUD MVC</span>
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
        🛍 Quản lý sản phẩm
    </h1>

    <p class="page-description">
        Quản lý sản phẩm và giá bán.
    </p>

    <div class="card">

        <form method="get"
              action="${pageContext.request.contextPath}/sanpham"
              class="search-box">

            <input type="text"
                   name="keyword"
                   value="${keyword}"
                   placeholder="Nhập mã hoặc tên sản phẩm...">

            <button type="submit"
                    class="btn btn-primary">
                🔍 Tìm kiếm
            </button>

            <a href="${pageContext.request.contextPath}/sanpham?action=form"
               class="btn btn-success">
                + Thêm sản phẩm
            </a>

        </form>

        <div class="table-wrapper">

            <table class="table">

                <thead>

                <tr>

                    <th>ID</th>
                    <th>Mã</th>
                    <th>Tên sản phẩm</th>
                    <th>Mô tả</th>
                    <th>Giá</th>
                    <th>Số lượng</th>
                    <th>Thao tác</th>

                </tr>

                </thead>

                <tbody>

                <c:choose>

                    <c:when test="${empty danhSach}">

                        <tr>

                            <td colspan="7"
                                style="text-align:center">

                                Không có sản phẩm.

                            </td>

                        </tr>

                    </c:when>

                    <c:otherwise>

                        <c:forEach var="sanPham"
                                   items="${danhSach}">

                            <tr>

                                <td>
                                        ${sanPham.id}
                                </td>

                                <td>
                                        ${sanPham.ma}
                                </td>

                                <td>
                                    <strong>
                                            ${sanPham.ten}
                                    </strong>
                                </td>

                                <td>
                                        ${sanPham.moTa}
                                </td>

                                <td>
                                        ${sanPham.gia}
                                </td>

                                <td>
                                        ${sanPham.soLuong}
                                </td>

                                <td>

                                    <div class="actions">

                                        <a class="btn btn-primary"
                                           href="${pageContext.request.contextPath}/sanpham?action=detail&id=${sanPham.id}">
                                            Xem
                                        </a>

                                        <a class="btn btn-warning"
                                           href="${pageContext.request.contextPath}/sanpham?action=form&id=${sanPham.id}">
                                            Sửa
                                        </a>

                                        <a class="btn btn-danger"
                                           href="${pageContext.request.contextPath}/sanpham?action=delete&id=${sanPham.id}"
                                           onclick="return confirm('Bạn có chắc muốn xóa sản phẩm này không?')">
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