<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <title>Lab 6 - Quản lý sinh viên</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f4f6f9;
            margin: 0;
        }

        .container {
            width: 700px;
            margin: 100px auto;
            background: white;
            padding: 40px;
            border-radius: 12px;
            text-align: center;
            box-shadow: 0 4px 20px rgba(0,0,0,0.1);
        }

        h1 {
            color: #1e3a8a;
        }

        a {
            display: inline-block;
            margin: 10px;
            padding: 12px 20px;
            text-decoration: none;
            background: #2563eb;
            color: white;
            border-radius: 6px;
        }

        a:hover {
            background: #1d4ed8;
        }
    </style>
</head>

<body>

<div class="container">

    <h1>LAB 6 - CÔNG NGHỆ JAVA</h1>

    <h2>Ứng dụng quản lý sinh viên</h2>

    <p>
        Servlet + JSP + JSTL + Filter + Listener + MVC
    </p>

    <a href="${pageContext.request.contextPath}/hello">
        Hello Servlet
    </a>

    <a href="${pageContext.request.contextPath}/login.jsp">
        Đăng nhập
    </a>

</div>

</body>

</html>