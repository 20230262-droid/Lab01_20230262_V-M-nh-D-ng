<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Student Hub - Đăng nhập</title>

    <style>
        * {
            box-sizing: border-box;
        }

        html, body {
            margin: 0;
            min-height: 100%;
            font-family: "Segoe UI", Arial, sans-serif;
        }

        body {
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            background:
                    radial-gradient(circle at 10% 20%, rgba(249,168,212,.55), transparent 30%),
                    radial-gradient(circle at 90% 80%, rgba(196,181,253,.55), transparent 30%),
                    linear-gradient(135deg, #fff7fa, #fdf4ff);
            overflow: hidden;
        }

        .blob {
            position: fixed;
            border-radius: 50%;
            filter: blur(2px);
            opacity: .45;
            z-index: 0;
        }

        .blob.one {
            width: 260px;
            height: 260px;
            background: #f9a8d4;
            top: -100px;
            left: -80px;
        }

        .blob.two {
            width: 300px;
            height: 300px;
            background: #c4b5fd;
            bottom: -130px;
            right: -100px;
        }

        .login-wrapper {
            width: 950px;
            max-width: 94%;
            min-height: 580px;
            display: grid;
            grid-template-columns: 1fr 1fr;
            background: rgba(255,255,255,.78);
            border: 1px solid rgba(255,255,255,.9);
            border-radius: 28px;
            box-shadow: 0 25px 70px rgba(190, 120, 160, .18);
            backdrop-filter: blur(18px);
            overflow: hidden;
            position: relative;
            z-index: 1;
        }

        .brand-panel {
            padding: 55px;
            background:
                    linear-gradient(145deg, rgba(249,168,212,.9), rgba(196,181,253,.85));
            color: #fff;
            display: flex;
            flex-direction: column;
            justify-content: center;
            position: relative;
            overflow: hidden;
        }

        .brand-panel::before,
        .brand-panel::after {
            content: "";
            position: absolute;
            border-radius: 50%;
            background: rgba(255,255,255,.22);
        }

        .brand-panel::before {
            width: 250px;
            height: 250px;
            right: -90px;
            top: -80px;
        }

        .brand-panel::after {
            width: 190px;
            height: 190px;
            left: -80px;
            bottom: -70px;
        }

        .logo {
            width: 70px;
            height: 70px;
            display: flex;
            align-items: center;
            justify-content: center;
            border-radius: 20px;
            background: rgba(255,255,255,.28);
            font-size: 34px;
            margin-bottom: 25px;
            box-shadow: 0 10px 25px rgba(150,80,130,.15);
        }

        .brand-panel h1 {
            margin: 0 0 12px;
            font-size: 38px;
            letter-spacing: -1px;
        }

        .brand-panel p {
            margin: 0;
            line-height: 1.7;
            font-size: 16px;
            max-width: 390px;
            color: rgba(255,255,255,.9);
        }

        .features {
            margin-top: 35px;
        }

        .feature {
            display: flex;
            align-items: center;
            gap: 12px;
            margin: 15px 0;
            font-size: 14px;
        }

        .feature-icon {
            width: 32px;
            height: 32px;
            border-radius: 10px;
            background: rgba(255,255,255,.25);
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .login-panel {
            padding: 55px;
            display: flex;
            flex-direction: column;
            justify-content: center;
            background: rgba(255,255,255,.88);
        }

        .login-panel h2 {
            margin: 0;
            color: #3f3747;
            font-size: 28px;
        }

        .subtitle {
            margin: 8px 0 30px;
            color: #8b8292;
            font-size: 14px;
        }

        .error {
            padding: 13px 15px;
            margin-bottom: 20px;
            border-radius: 12px;
            background: #fff1f2;
            color: #be123c;
            border: 1px solid #fecdd3;
            font-size: 14px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            margin-bottom: 8px;
            font-size: 14px;
            font-weight: 600;
            color: #4b4352;
        }

        .input-wrap {
            position: relative;
        }

        .input-icon {
            position: absolute;
            left: 15px;
            top: 50%;
            transform: translateY(-50%);
            color: #d17aa7;
        }

        .input-wrap input {
            width: 100%;
            height: 50px;
            border: 1px solid #eadde7;
            border-radius: 13px;
            padding: 0 15px 0 45px;
            font-size: 14px;
            color: #3f3747;
            background: #fffafd;
            transition: .2s;
        }

        .input-wrap input:focus {
            outline: none;
            border-color: #f09bc7;
            box-shadow: 0 0 0 4px rgba(249,168,212,.18);
        }

        .login-btn {
            width: 100%;
            height: 52px;
            border: none;
            border-radius: 14px;
            cursor: pointer;
            color: white;
            font-size: 15px;
            font-weight: 700;
            background: linear-gradient(135deg, #ec6faf, #c4a7f8);
            box-shadow: 0 10px 25px rgba(224,115,170,.25);
            transition: .2s;
        }

        .login-btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 14px 30px rgba(224,115,170,.32);
        }

        .test-account {
            margin-top: 25px;
            padding: 14px;
            border-radius: 13px;
            background: #fff6fb;
            border: 1px solid #f6d9e9;
            color: #7d6677;
            font-size: 12px;
            line-height: 1.7;
        }

        .test-account strong {
            color: #c45c91;
        }

        @media (max-width: 750px) {
            .login-wrapper {
                grid-template-columns: 1fr;
            }

            .brand-panel {
                display: none;
            }

            .login-panel {
                padding: 35px 25px;
            }
        }
    </style>
</head>

<body>

<div class="blob one"></div>
<div class="blob two"></div>

<div class="login-wrapper">

    <div class="brand-panel">

        <div class="logo">🌸</div>

        <h1>Student Hub</h1>

        <p>
            Hệ thống quản lý sinh viên hiện đại,
            đơn giản và trực quan dành cho nhà trường.
        </p>

        <div class="features">

            <div class="feature">
                <div class="feature-icon">👨‍🎓</div>
                <span>Quản lý thông tin sinh viên</span>
            </div>

            <div class="feature">
                <div class="feature-icon">🔎</div>
                <span>Tìm kiếm nhanh chóng</span>
            </div>

            <div class="feature">
                <div class="feature-icon">🔐</div>
                <span>Phân quyền người dùng</span>
            </div>

        </div>

    </div>


    <div class="login-panel">

        <h2>Chào mừng trở lại 👋</h2>

        <div class="subtitle">
            Đăng nhập để tiếp tục quản lý hệ thống
        </div>


        <%
            String error = (String) request.getAttribute("error");

            if (error != null && !error.isBlank()) {
        %>

        <div class="error">
            ⚠ <%= error %>
        </div>

        <%
            }
        %>


        <form method="post"
              action="<%= request.getContextPath() %>/login">


            <div class="form-group">

                <label for="username">
                    Tên đăng nhập
                </label>

                <div class="input-wrap">

                    <span class="input-icon">👤</span>

                    <input type="text"
                           id="username"
                           name="username"
                           placeholder="Nhập tên đăng nhập"
                           required>

                </div>

            </div>


            <div class="form-group">

                <label for="password">
                    Mật khẩu
                </label>

                <div class="input-wrap">

                    <span class="input-icon">🔒</span>

                    <input type="password"
                           id="password"
                           name="password"
                           placeholder="Nhập mật khẩu"
                           required>

                </div>

            </div>


            <button type="submit"
                    class="login-btn">

                Đăng nhập →

            </button>

        </form>


        <div class="test-account">

            <strong>Tài khoản demo</strong><br>

            👑 Admin:
            <strong>admin / 123456</strong>

            <br>

            👤 User:
            <strong>user / 123456</strong>

        </div>

    </div>

</div>

</body>
</html>