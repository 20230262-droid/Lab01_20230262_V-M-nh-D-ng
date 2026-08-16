<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="vi">

<head>

    <meta charset="UTF-8">

    <title>403 - Forbidden</title>

    <style>

        body {
            font-family: Arial, sans-serif;
            background: #f1f5f9;
            text-align: center;
        }

        .box {
            width: 500px;
            margin: 120px auto;
            background: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 20px rgba(0,0,0,0.1);
        }

        h1 {
            font-size: 60px;
            color: #dc2626;
        }

        a {
            display: inline-block;
            margin-top: 20px;
            padding: 12px 20px;
            background: #2563eb;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }

    </style>

</head>

<body>

<div class="box">

    <h1>403</h1>

    <h2>Không có quyền truy cập</h2>

    <p>
        Bạn không có quyền thực hiện chức năng này.
    </p>

    <a href="${pageContext.request.contextPath}/welcome.jsp">
        Quay lại
    </a>

</div>

</body>

</html>