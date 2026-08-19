package vn.edu.eaut.lab7.repository;

import vn.edu.eaut.lab7.model.SanPham;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SanPhamRepository {

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
    public List<SanPham> findAll() {

        List<SanPham> list = new ArrayList<>();

        String sql = """
                SELECT id,
                       ma,
                       ten,
                       mo_ta,
                       gia,
                       so_luong
                FROM san_pham
                ORDER BY id DESC
                """;

        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                SanPham sp = new SanPham();

                sp.setId(rs.getInt("id"));
                sp.setMa(rs.getString("ma"));
                sp.setTen(rs.getString("ten"));
                sp.setMoTa(rs.getString("mo_ta"));
                sp.setGia(rs.getDouble("gia"));
                sp.setSoLuong(rs.getInt("so_luong"));

                list.add(sp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // =========================
    // SEARCH
    // =========================
    public List<SanPham> search(String keyword) {

        List<SanPham> list = new ArrayList<>();

        String sql = """
                SELECT id,
                       ma,
                       ten,
                       mo_ta,
                       gia,
                       so_luong
                FROM san_pham
                WHERE ma LIKE ?
                   OR ten LIKE ?
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

                    SanPham sp = new SanPham();

                    sp.setId(rs.getInt("id"));
                    sp.setMa(rs.getString("ma"));
                    sp.setTen(rs.getString("ten"));
                    sp.setMoTa(rs.getString("mo_ta"));
                    sp.setGia(rs.getDouble("gia"));
                    sp.setSoLuong(rs.getInt("so_luong"));

                    list.add(sp);
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
    public SanPham findById(int id) {

        String sql = """
                SELECT id,
                       ma,
                       ten,
                       mo_ta,
                       gia,
                       so_luong
                FROM san_pham
                WHERE id = ?
                """;

        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    SanPham sp = new SanPham();

                    sp.setId(rs.getInt("id"));
                    sp.setMa(rs.getString("ma"));
                    sp.setTen(rs.getString("ten"));
                    sp.setMoTa(rs.getString("mo_ta"));
                    sp.setGia(rs.getDouble("gia"));
                    sp.setSoLuong(rs.getInt("so_luong"));

                    return sp;
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
    public boolean add(SanPham sp) {
        return insert(sp);
    }

    // =========================
    // INSERT
    // =========================
    public boolean insert(SanPham sp) {

        String sql = """
                INSERT INTO san_pham
                (
                    ma,
                    ten,
                    mo_ta,
                    gia,
                    so_luong
                )
                VALUES (?, ?, ?, ?, ?)
                """;

        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, sp.getMa());
            ps.setString(2, sp.getTen());
            ps.setString(3, sp.getMoTa());
            ps.setDouble(4, sp.getGia());
            ps.setInt(5, sp.getSoLuong());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // =========================
    // UPDATE
    // =========================
    public boolean update(SanPham sp) {

        String sql = """
                UPDATE san_pham
                SET ma = ?,
                    ten = ?,
                    mo_ta = ?,
                    gia = ?,
                    so_luong = ?
                WHERE id = ?
                """;

        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, sp.getMa());
            ps.setString(2, sp.getTen());
            ps.setString(3, sp.getMoTa());
            ps.setDouble(4, sp.getGia());
            ps.setInt(5, sp.getSoLuong());
            ps.setInt(6, sp.getId());

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
                "DELETE FROM san_pham WHERE id = ?";

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