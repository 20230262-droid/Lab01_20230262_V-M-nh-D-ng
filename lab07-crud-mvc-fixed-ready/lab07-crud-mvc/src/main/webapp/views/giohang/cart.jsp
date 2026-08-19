<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Giỏ hàng</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">

</head>

<body>

<div class="header">

    <div class="logo">
        🛒 LAB 07 <span>CRUD MVC</span>
    </div>

</div>

<div class="navbar">

    <a href="${pageContext.request.contextPath}/admin">
        🏠 Trang chủ
    </a>

    <a href="${pageContext.request.contextPath}/sanpham">
        🛍 Sản phẩm
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
        🛒 Giỏ hàng
    </h1>

    <div class="card">

        <c:choose>

            <c:when test="${empty cart}">

                <div style="text-align:center; padding:40px">

                    <div style="font-size:50px">
                        🛒
                    </div>

                    <h2>
                        Giỏ hàng đang trống
                    </h2>

                    <p>
                        Hãy thêm sản phẩm vào giỏ hàng.
                    </p>

                    <a href="${pageContext.request.contextPath}/sanpham"
                       class="btn btn-primary">
                        🛍 Xem sản phẩm
                    </a>

                </div>

            </c:when>

            <c:otherwise>

                <div class="table-wrapper">

                    <table class="table">

                        <thead>

                        <tr>

                            <th>Sản phẩm</th>
                            <th>Đơn giá</th>
                            <th>Số lượng</th>
                            <th>Thành tiền</th>
                            <th>Thao tác</th>

                        </tr>

                        </thead>

                        <tbody>

                        <c:set var="total" value="0"/>

                        <c:forEach var="item"
                                   items="${cart}">

                            <c:set var="subtotal"
                                   value="${item.sanPham.gia * item.soLuong}"/>

                            <c:set var="total"
                                   value="${total + subtotal}"/>

                            <tr>

                                <td>
                                    <strong>
                                            ${item.sanPham.ten}
                                    </strong>
                                </td>

                                <td>
                                        ${item.sanPham.gia}
                                </td>

                                <td>

                                    <form method="post"
                                          action="${pageContext.request.contextPath}/giohang">

                                        <input type="hidden"
                                               name="action"
                                               value="update">

                                        <input type="hidden"
                                               name="id"
                                               value="${item.sanPham.id}">

                                        <input type="number"
                                               name="soLuong"
                                               value="${item.soLuong}"
                                               min="1"
                                               style="width:70px; padding:8px">

                                        <button class="btn btn-primary"
                                                type="submit">
                                            Cập nhật
                                        </button>

                                    </form>

                                </td>

                                <td>
                                        ${subtotal}
                                </td>

                                <td>

                                    <form method="post"
                                          action="${pageContext.request.contextPath}/giohang">

                                        <input type="hidden"
                                               name="action"
                                               value="remove">

                                        <input type="hidden"
                                               name="id"
                                               value="${item.sanPham.id}">

                                        <button class="btn btn-danger"
                                                type="submit">
                                            Xóa
                                        </button>

                                    </form>

                                </td>

                            </tr>

                        </c:forEach>

                        </tbody>

                    </table>

                </div>

                <div class="cart-total">

                    Tổng tiền:
                        ${total}

                </div>

                <div class="form-actions">

                    <form method="post"
                          action="${pageContext.request.contextPath}/giohang">

                        <input type="hidden"
                               name="action"
                               value="clear">

                        <button class="btn btn-danger"
                                type="submit"
                                onclick="return confirm('Xóa toàn bộ giỏ hàng?')">
                            🗑 Xóa giỏ hàng
                        </button>

                    </form>

                    <a href="${pageContext.request.contextPath}/sanpham"
                       class="btn btn-primary">
                        ← Tiếp tục mua hàng
                    </a>

                </div>

            </c:otherwise>

        </c:choose>

    </div>

</div>

</body>
</html>