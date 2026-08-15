DROP DATABASE IF EXISTS minishop_db;
CREATE DATABASE minishop_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE minishop_db;

CREATE TABLE tai_khoan (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           username VARCHAR(50) NOT NULL UNIQUE,
                           password VARCHAR(100) NOT NULL,
                           ho_ten VARCHAR(100) NOT NULL,
                           vai_tro VARCHAR(20) NOT NULL,
                           trang_thai TINYINT NOT NULL DEFAULT 1
);

CREATE TABLE danh_muc (
                          ma_dm INT AUTO_INCREMENT PRIMARY KEY,
                          ten_dm VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE san_pham (
                          ma_sp INT AUTO_INCREMENT PRIMARY KEY,
                          ten_sp VARCHAR(100) NOT NULL,
                          don_gia DECIMAL(12,2) NOT NULL,
                          so_luong INT NOT NULL DEFAULT 0,
                          ma_dm INT NULL,
                          FOREIGN KEY (ma_dm) REFERENCES danh_muc(ma_dm) ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE khach_hang (
                            ma_kh INT AUTO_INCREMENT PRIMARY KEY,
                            ten_kh VARCHAR(100) NOT NULL,
                            sdt VARCHAR(10) NOT NULL,
                            dia_chi VARCHAR(255)
);

CREATE TABLE hoa_don (
                         ma_hd INT AUTO_INCREMENT PRIMARY KEY,
                         ngay_lap DATE NOT NULL,
                         ma_kh INT NOT NULL,
                         tong_tien DECIMAL(12,2) NOT NULL DEFAULT 0,
                         FOREIGN KEY (ma_kh) REFERENCES khach_hang(ma_kh)
);

CREATE TABLE chi_tiet_hoa_don (
                                  ma_hd INT NOT NULL,
                                  ma_sp INT NOT NULL,
                                  so_luong INT NOT NULL,
                                  don_gia DECIMAL(12,2) NOT NULL,
                                  thanh_tien DECIMAL(12,2) NOT NULL,
                                  PRIMARY KEY (ma_hd, ma_sp),
                                  FOREIGN KEY (ma_hd) REFERENCES hoa_don(ma_hd) ON DELETE CASCADE,
                                  FOREIGN KEY (ma_sp) REFERENCES san_pham(ma_sp)
);

INSERT INTO tai_khoan(username,password,ho_ten,vai_tro) VALUES
                                                            ('admin','123456','Quản trị viên','ADMIN'),
                                                            ('nhanvien','123456','Nhân viên bán hàng','NHANVIEN'),
                                                            ('ketoan','123456','Nhân viên kế toán','KETOAN');

INSERT INTO danh_muc(ten_dm) VALUES ('Điện thoại'),('Laptop'),('Máy tính bảng'),('Phụ kiện'),('Tai nghe');
INSERT INTO san_pham(ten_sp,don_gia,so_luong,ma_dm) VALUES
                                                        ('iPhone 15',18990000,20,1),('Samsung Galaxy S24',16990000,15,1),('Xiaomi Redmi Note 13',5990000,30,1),
                                                        ('MacBook Air M3',28990000,10,2),('Dell Inspiron 15',15990000,12,2),('ASUS Vivobook 15',13990000,18,2),
                                                        ('iPad Air',15990000,14,3),('Samsung Galaxy Tab S9',17990000,8,3),('AirPods Pro 2',5990000,25,4),
                                                        ('Chuột Logitech M331',490000,50,4),('Bàn phím Logitech K380',790000,35,4),('Sony WH-1000XM5',8490000,10,5);
INSERT INTO khach_hang(ten_kh,sdt,dia_chi) VALUES
                                               ('Nguyễn Văn An','0901234567','Hà Nội'),('Trần Thị Bình','0912345678','Bắc Ninh'),('Lê Văn Cường','0923456789','Hải Dương');
