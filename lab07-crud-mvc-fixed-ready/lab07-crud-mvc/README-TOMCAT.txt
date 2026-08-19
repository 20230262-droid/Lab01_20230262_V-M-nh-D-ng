LAB 07 - CRUD MVC (Tomcat 10.1)

1. Open this folder as a Maven project in IntelliJ IDEA.
2. Run: mvn clean package
3. Configure Tomcat 10.1.x and deploy artifact: lab07:war exploded.
4. Application context: /lab07
5. Start Tomcat.
6. Open: http://localhost:8080/lab07/
7. Login: admin / 123

Main URLs:
/admin
/sinhvien
/sach
/sanpham
/lophoc
/diem
/giohang

The controllers also accept the older aliases /sinh-vien, /san-pham, /lop-hoc and /gio-hang to avoid 404s from old links.

If Tomcat still shows 404 after replacing the project, stop Tomcat, remove the old deployment, add lab07:war exploded again, set context to /lab07, and redeploy.
