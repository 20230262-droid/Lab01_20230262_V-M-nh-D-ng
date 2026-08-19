<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="vi">

<head>

    <meta charset="UTF-8">

    <title>Đăng nhập hệ thống</title>

    <style>

        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            min-height: 100vh;

            display: flex;
            justify-content: center;
            align-items: center;

            font-family: Arial, sans-serif;

            background:
                    linear-gradient(
                            135deg,
                            #fff0f6,
                            #ffe4ef,
                            #ffd6e7
                    );
        }

        .login-container {
            width: 420px;

            padding: 40px;

            background: rgba(255, 255, 255, 0.95);

            border-radius: 24px;

            box-shadow:
                    0 20px 50px rgba(190, 80, 120, 0.18);

            border: 1px solid #ffd1e2;
        }

        .logo {
            width: 75px;
            height: 75px;

            margin: 0 auto 20px;

            display: flex;
            justify-content: center;
            align-items: center;

            border-radius: 50%;

            background: linear-gradient(
                    135deg,
                    #ff9fc4,
                    #f47da9
            );

            color: white;

            font-size: 32px;
            font-weight: bold;

            box-shadow:
                    0 10px 25px rgba(244, 125, 169, 0.35);
        }

        h1 {
            text-align: center;

            margin: 0;

            color: #c94f7c;

            font-size: 28px;
        }

        .subtitle {
            text-align: center;

            color: #888;

            margin-top: 8px;
            margin-bottom: 30px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;

            margin-bottom: 8px;

            color: #555;

            font-weight: 600;
        }

        input {
            width: 100%;

            padding: 14px 16px;

            border: 1px solid #f0bfd2;

            border-radius: 12px;

            outline: none;

            font-size: 15px;

            transition: 0.3s;
        }

        input:focus {
            border-color: #ec7eaa;

            box-shadow:
                    0 0 0 4px rgba(236, 126, 170, 0.12);
        }

        .btn-login {
            width: 100%;

            padding: 14px;

            border: none;

            border-radius: 12px;

            background: linear-gradient(
                    135deg,
                    #f58ab1,
                    #e96899
            );

            color: white;

            font-size: 16px;

            font-weight: bold;

            cursor: pointer;

            transition: 0.3s;
        }

        .btn-login:hover {
            transform: translateY(-2px);

            box-shadow:
                    0 10px 25px rgba(233, 104, 153, 0.3);
        }

        .btn-login:active {
            transform: scale(0.98);
        }

        .error {
            padding: 12px;

            margin-bottom: 20px;

            border-radius: 10px;

            background: #fff0f3;

            color: #d95379;

            text-align: center;

            font-size: 14px;
        }

        .demo {
            margin-top: 25px;

            padding: 14px;

            border-radius: 12px;

            background: #fff7fa;

            text-align: center;

            color: #777;

            font-size: 14px;
        }

        .demo strong {
            color: #d85c88;
        }

    </style>

</head>

<body>

<div class="login-container">

    <div class="logo">
        S
    </div>

    <h1>Đăng nhập</h1>

    <div class="subtitle">
        Hệ thống quản lý CRUD MVC
    </div>

    <%
        String error = request.getParameter("error");

        if ("1".equals(error)) {
    %>

    <div class="error">
        ❌ Tài khoản hoặc mật khẩu không đúng!
    </div>

    <%
        }
    %>

    <form
            method="post"
            action="<%= request.getContextPath() %>/login"
    >

        <div class="form-group">

            <label>
                Tên đăng nhập
            </label>

            <input
                    type="text"
                    name="username"
                    placeholder="Nhập tên đăng nhập"
                    required
                    autocomplete="username"
            >

        </div>

        <div class="form-group">

            <label>
                Mật khẩu
            </label>

            <input
                    type="password"
                    name="password"
                    placeholder="Nhập mật khẩu"
                    required
                    autocomplete="current-password"
            >

        </div>

        <button
                type="submit"
                class="btn-login"
        >
            ĐĂNG NHẬP
        </button>

    </form>

    <div class="demo">

        Tài khoản mẫu:
        <strong>admin</strong>
        /
        <strong>123</strong>

    </div>

</div>

</body>

</html>