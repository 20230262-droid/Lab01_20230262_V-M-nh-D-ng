<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>
        ${empty sanPham ? 'Thêm sản phẩm' : 'Sửa sản phẩm'}
    </title>

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
        🛍 Danh sách sản phẩm
    </a>

</div>

<div class="container">

    <h1 class="page-title">

        ${empty sanPham
        ? '➕ Thêm sản phẩm'
        : '✏️ Cập nhật sản phẩm'}

    </h1>

    <div class="card">

        <form method="post"
              action="${pageContext.request.contextPath}/sanpham">

            <input type="hidden"
                   name="action"
                   value="save">

            <c:if test="${not empty sanPham}">

                <input type="hidden"
                       name="id"
                       value="${sanPham.id}">

            </c:if>

            <div class="form-group">

                <label>Mã sản phẩm</label>

                <input type="text"
                       name="ma"
                       class="form-control"
                       value="${sanPham.ma}"
                       required>

            </div>

            <div class="form-group">

                <label>Tên sản phẩm</label>

                <input type="text"
                       name="ten"
                       class="form-control"
                       value="${sanPham.ten}"
                       required>

            </div>

            <div class="form-group">

                <label>Mô tả</label>

                <textarea name="moTa"
                          class="form-control">${sanPham.moTa}</textarea>

            </div>

            <div class="form-group">

                <label>Giá</label>

                <input type="number"
                       name="gia"
                       class="form-control"
                       value="${sanPham.gia}"
                       min="0"
                       step="0.01"
                       required>

            </div>

            <div class="form-group">

                <label>Số lượng</label>

                <input type="number"
                       name="soLuong"
                       class="form-control"
                       value="${sanPham.soLuong}"
                       min="0"
                       required>

            </div>

            <div class="form-actions">

                <button type="submit"
                        class="btn btn-primary">
                    💾 Lưu
                </button>

                <a href="${pageContext.request.contextPath}/sanpham"
                   class="btn btn-secondary">
                    ← Quay lại
                </a>

            </div>

        </form>

    </div>

</div>

</body>
</html>