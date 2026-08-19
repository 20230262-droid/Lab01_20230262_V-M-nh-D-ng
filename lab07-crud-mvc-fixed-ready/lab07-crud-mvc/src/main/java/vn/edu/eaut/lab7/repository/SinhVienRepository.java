package vn.edu.eaut.lab7.repository;

import vn.edu.eaut.lab7.model.SinhVien;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SinhVienRepository {

    private static final String URL =
            "jdbc:mysql://localhost:3306/lab07_crud"
                    + "?useSSL=false"
                    + "&serverTimezone=Asia/Ho_Chi_Minh"
                    + "&characterEncoding=UTF-8";

    private static final String USER = "root";
    private static final String PASSWORD = "";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Không tìm thấy MySQL JDBC Driver!", e);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // =========================
    // FIND ALL
    // =========================
    public List<SinhVien> findAll() {

        List<SinhVien> list = new ArrayList<>();

        String sql = """
                SELECT id,
                       ma_sinh_vien,
                       ho_ten,
                       email,
                       lop
                FROM sinh_vien
                ORDER BY id DESC
                """;

        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                SinhVien sv = new SinhVien();

                sv.setId(rs.getInt("id"));
                sv.setMaSinhVien(
                        rs.getString("ma_sinh_vien")
                );
                sv.setHoTen(
                        rs.getString("ho_ten")
                );
                sv.setEmail(
                        rs.getString("email")
                );
                sv.setLop(
                        rs.getString("lop")
                );

                list.add(sv);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // =========================
    // SEARCH
    // =========================
    public List<SinhVien> search(String keyword) {

        List<SinhVien> list = new ArrayList<>();

        String sql = """
                SELECT id,
                       ma_sinh_vien,
                       ho_ten,
                       email,
                       lop
                FROM sinh_vien
                WHERE ma_sinh_vien LIKE ?
                   OR ho_ten LIKE ?
                ORDER BY id DESC
                """;

        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            String value = "%" + keyword + "%";

            ps.setString(1, value);
            ps.setString(2, value);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    SinhVien sv = new SinhVien();

                    sv.setId(rs.getInt("id"));
                    sv.setMaSinhVien(
                            rs.getString("ma_sinh_vien")
                    );
                    sv.setHoTen(
                            rs.getString("ho_ten")
                    );
                    sv.setEmail(
                            rs.getString("email")
                    );
                    sv.setLop(
                            rs.getString("lop")
                    );

                    list.add(sv);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // =========================
    // FIND BY ID
    // =========================
    public SinhVien findById(int id) {

        String sql = """
                SELECT id,
                       ma_sinh_vien,
                       ho_ten,
                       email,
                       lop
                FROM sinh_vien
                WHERE id = ?
                """;

        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    SinhVien sv = new SinhVien();

                    sv.setId(rs.getInt("id"));
                    sv.setMaSinhVien(
                            rs.getString("ma_sinh_vien")
                    );
                    sv.setHoTen(
                            rs.getString("ho_ten")
                    );
                    sv.setEmail(
                            rs.getString("email")
                    );
                    sv.setLop(
                            rs.getString("lop")
                    );

                    return sv;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // =========================
    // ADD
    // =========================
    public boolean add(SinhVien sv) {
        return insert(sv);
    }

    // =========================
    // INSERT
    // =========================
    public boolean insert(SinhVien sv) {

        String sql = """
                INSERT INTO sinh_vien
                (
                    ma_sinh_vien,
                    ho_ten,
                    email,
                    lop
                )
                VALUES (?, ?, ?, ?)
                """;

        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    sv.getMaSinhVien()
            );

            ps.setString(
                    2,
                    sv.getHoTen()
            );

            ps.setString(
                    3,
                    sv.getEmail()
            );

            ps.setString(
                    4,
                    sv.getLop()
            );

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // =========================
    // UPDATE
    // =========================
    public boolean update(SinhVien sv) {

        String sql = """
                UPDATE sinh_vien
                SET ma_sinh_vien = ?,
                    ho_ten = ?,
                    email = ?,
                    lop = ?
                WHERE id = ?
                """;

        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    sv.getMaSinhVien()
            );

            ps.setString(
                    2,
                    sv.getHoTen()
            );

            ps.setString(
                    3,
                    sv.getEmail()
            );

            ps.setString(
                    4,
                    sv.getLop()
            );

            ps.setInt(
                    5,
                    sv.getId()
            );

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // =========================
    // DELETE
    // =========================
    public boolean delete(int id) {

        String sql =
                "DELETE FROM sinh_vien WHERE id = ?";

        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}