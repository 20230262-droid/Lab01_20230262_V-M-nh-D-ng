package vn.edu.eaut.lab5.dal;

import vn.edu.eaut.lab5.config.DBHelper;
import vn.edu.eaut.lab5.model.DanhMuc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DanhMucDAL {

    // =========================
    // LẤY TẤT CẢ DANH MỤC
    // =========================
    public List<DanhMuc> findAll() throws SQLException {

        List<DanhMuc> list = new ArrayList<>();

        String sql = """
                SELECT ma_dm, ten_dm
                FROM danh_muc
                ORDER BY ma_dm
                """;

        try (
                Connection conn = DBHelper.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                DanhMuc dm = new DanhMuc();

                dm.setMaDm(rs.getInt("ma_dm"));
                dm.setTenDm(rs.getString("ten_dm"));

                list.add(dm);
            }
        }

        return list;
    }


    // =========================
    // THÊM DANH MỤC
    // =========================
    public boolean insert(DanhMuc dm) throws SQLException {

        String sql = """
                INSERT INTO danh_muc(ten_dm)
                VALUES (?)
                """;

        try (
                Connection conn = DBHelper.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, dm.getTenDm());

            return ps.executeUpdate() > 0;
        }
    }


    // =========================
    // CẬP NHẬT DANH MỤC
    // =========================
    public boolean update(DanhMuc dm) throws SQLException {

        String sql = """
                UPDATE danh_muc
                SET ten_dm = ?
                WHERE ma_dm = ?
                """;

        try (
                Connection conn = DBHelper.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, dm.getTenDm());
            ps.setInt(2, dm.getMaDm());

            return ps.executeUpdate() > 0;
        }
    }


    // =========================
    // XÓA DANH MỤC
    // =========================
    public boolean delete(int maDm) throws SQLException {

        // Kiểm tra danh mục có đang được sản phẩm sử dụng không
        String checkSql = """
                SELECT COUNT(*)
                FROM san_pham
                WHERE ma_dm = ?
                """;

        try (
                Connection conn = DBHelper.getConnection();
                PreparedStatement ps = conn.prepareStatement(checkSql)
        ) {

            ps.setInt(1, maDm);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    int count = rs.getInt(1);

                    if (count > 0) {
                        throw new SQLException(
                                "Không thể xóa danh mục vì đang có sản phẩm sử dụng!"
                        );
                    }
                }
            }
        }

        String sql = """
                DELETE FROM danh_muc
                WHERE ma_dm = ?
                """;

        try (
                Connection conn = DBHelper.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, maDm);

            return ps.executeUpdate() > 0;
        }
    }


    // =========================
    // TÌM KIẾM DANH MỤC
    // =========================
    public List<DanhMuc> search(String keyword) throws SQLException {

        List<DanhMuc> list = new ArrayList<>();

        String sql = """
                SELECT ma_dm, ten_dm
                FROM danh_muc
                WHERE ten_dm LIKE ?
                ORDER BY ma_dm
                """;

        try (
                Connection conn = DBHelper.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, "%" + keyword + "%");

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    DanhMuc dm = new DanhMuc();

                    dm.setMaDm(rs.getInt("ma_dm"));
                    dm.setTenDm(rs.getString("ten_dm"));

                    list.add(dm);
                }
            }
        }

        return list;
    }
}