package vn.edu.eaut.lab7.repository;

import vn.edu.eaut.lab7.model.Diem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiemRepository {

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
    public List<Diem> findAll() {

        List<Diem> list = new ArrayList<>();

        String sql = """
                SELECT id,
                       ma_sinh_vien,
                       ho_ten,
                       chuyen_can,
                       giua_ky,
                       cuoi_ky
                FROM diem
                ORDER BY id DESC
                """;

        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                Diem diem = new Diem();

                diem.setId(rs.getInt("id"));
                diem.setMaSinhVien(rs.getString("ma_sinh_vien"));
                diem.setHoTen(rs.getString("ho_ten"));
                diem.setChuyenCan(rs.getDouble("chuyen_can"));
                diem.setGiuaKy(rs.getDouble("giua_ky"));
                diem.setCuoiKy(rs.getDouble("cuoi_ky"));

                list.add(diem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // =========================
    // SEARCH
    // =========================
    public List<Diem> search(String keyword) {

        List<Diem> list = new ArrayList<>();

        String sql = """
                SELECT id,
                       ma_sinh_vien,
                       ho_ten,
                       chuyen_can,
                       giua_ky,
                       cuoi_ky
                FROM diem
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

                    Diem diem = new Diem();

                    diem.setId(rs.getInt("id"));
                    diem.setMaSinhVien(rs.getString("ma_sinh_vien"));
                    diem.setHoTen(rs.getString("ho_ten"));
                    diem.setChuyenCan(rs.getDouble("chuyen_can"));
                    diem.setGiuaKy(rs.getDouble("giua_ky"));
                    diem.setCuoiKy(rs.getDouble("cuoi_ky"));

                    list.add(diem);
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
    public Diem findById(int id) {

        String sql = """
                SELECT id,
                       ma_sinh_vien,
                       ho_ten,
                       chuyen_can,
                       giua_ky,
                       cuoi_ky
                FROM diem
                WHERE id = ?
                """;

        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Diem diem = new Diem();

                    diem.setId(rs.getInt("id"));
                    diem.setMaSinhVien(rs.getString("ma_sinh_vien"));
                    diem.setHoTen(rs.getString("ho_ten"));
                    diem.setChuyenCan(rs.getDouble("chuyen_can"));
                    diem.setGiuaKy(rs.getDouble("giua_ky"));
                    diem.setCuoiKy(rs.getDouble("cuoi_ky"));

                    return diem;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // =========================
    // ADD
    // Controller của bạn đang gọi add()
    // =========================
    public boolean add(Diem diem) {
        return insert(diem);
    }

    // =========================
    // INSERT
    // =========================
    public boolean insert(Diem diem) {

        String sql = """
                INSERT INTO diem
                (
                    ma_sinh_vien,
                    ho_ten,
                    chuyen_can,
                    giua_ky,
                    cuoi_ky
                )
                VALUES (?, ?, ?, ?, ?)
                """;

        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, diem.getMaSinhVien());
            ps.setString(2, diem.getHoTen());
            ps.setDouble(3, diem.getChuyenCan());
            ps.setDouble(4, diem.getGiuaKy());
            ps.setDouble(5, diem.getCuoiKy());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // =========================
    // UPDATE
    // =========================
    public boolean update(Diem diem) {

        String sql = """
                UPDATE diem
                SET ma_sinh_vien = ?,
                    ho_ten = ?,
                    chuyen_can = ?,
                    giua_ky = ?,
                    cuoi_ky = ?
                WHERE id = ?
                """;

        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, diem.getMaSinhVien());
            ps.setString(2, diem.getHoTen());
            ps.setDouble(3, diem.getChuyenCan());
            ps.setDouble(4, diem.getGiuaKy());
            ps.setDouble(5, diem.getCuoiKy());
            ps.setInt(6, diem.getId());

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

        String sql = "DELETE FROM diem WHERE id = ?";

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