LAB 8 – JAKARTA FACES / JSF

Đã kiểm tra và bổ sung:
- Sửa lỗi cú pháp ProductRepository và SachRepository.
- Form Sinh viên: thêm, sửa, xóa, tìm kiếm, validation, h:selectOneMenu.
- Form Sách: validation tên, tác giả, năm xuất bản.
- Form Sản phẩm: validation tên, giá > 0, số lượng >= 0.
- LoginBean/login.xhtml: FacesMessage khi đăng nhập sai, điều hướng khi đúng.
- Header/menu/footer dùng chung.
- Đăng xuất đã gọi phương thức logout thật và hủy session.
- Giữ FacesMessage sau redirect để thông báo thành công hiển thị đúng.
- Bổ sung Hibernate Validator và PROJECT_STAGE=Development theo cấu hình gợi ý của đề.

Yêu cầu môi trường:
- JDK 17 hoặc JDK 21.
- Apache Maven.
- Apache Tomcat 10.x.

Build:
    mvn clean package

Deploy file:
    target/lab08-jsf-validation.war

Truy cập:
    http://localhost:8080/lab08-jsf-validation/login.xhtml

Tài khoản demo:
    admin

Mật khẩu demo:
    123456
