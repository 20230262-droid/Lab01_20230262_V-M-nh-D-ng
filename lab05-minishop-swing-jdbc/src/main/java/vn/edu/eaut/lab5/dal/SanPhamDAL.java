package vn.edu.eaut.lab5.dal;

import vn.edu.eaut.lab5.config.DBHelper;
import vn.edu.eaut.lab5.model.SanPham;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SanPhamDAL {

    private SanPham map(ResultSet rs) throws SQLException {
        return new SanPham(
                rs.getInt("ma_sp"),
                rs.getString("ten_sp"),
                rs.getBigDecimal("don_gia"),
                rs.getInt("so_luong"),
                rs.getInt("ma_dm"),
                rs.getString("ten_dm")
        );
    }

    public List<SanPham> findAll() throws SQLException {
        return search("", "", "", "");
    }

    public List<SanPham> search(String name, String min,
                                String max, String minQty)
            throws SQLException {

        StringBuilder sql = new StringBuilder("""
            SELECT sp.*,
                   COALESCE(dm.ten_dm, 'Chưa phân loại') AS ten_dm
            FROM san_pham sp
            LEFT JOIN danh_muc dm ON sp.ma_dm = dm.ma_dm
            WHERE 1=1
            """);

        List<Object> params = new ArrayList<>();

        if (!name.isBlank()) {
            sql.append(" AND sp.ten_sp LIKE ?");
            params.add("%" + name + "%");
        }

        if (!min.isBlank()) {
            sql.append(" AND sp.don_gia >= ?");
            params.add(new BigDecimal(min));
        }

        if (!max.isBlank()) {
            sql.append(" AND sp.don_gia <= ?");
            params.add(new BigDecimal(max));
        }

        if (!minQty.isBlank()) {
            sql.append(" AND sp.so_luong >= ?");
            params.add(Integer.parseInt(minQty));
        }

        sql.append(" ORDER BY sp.ma_sp DESC");

        try (Connection c = DBHelper.getConnection();
             PreparedStatement ps = c.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                List<SanPham> list = new ArrayList<>();
                while (rs.next()) list.add(map(rs));
                return list;
            }
        }
    }

    public boolean insert(SanPham sp) throws SQLException {
        String sql = """
            INSERT INTO san_pham(ten_sp, don_gia, so_luong, ma_dm)
            VALUES (?, ?, ?, ?)
            """;

        try (Connection c = DBHelper.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, sp.getTenSp());
            ps.setBigDecimal(2, sp.getDonGia());
            ps.setInt(3, sp.getSoLuong());

            if (sp.getMaDm() > 0)
                ps.setInt(4, sp.getMaDm());
            else
                ps.setNull(4, Types.INTEGER);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean update(SanPham sp) throws SQLException {
        String sql = """
            UPDATE san_pham
            SET ten_sp=?, don_gia=?, so_luong=?, ma_dm=?
            WHERE ma_sp=?
            """;

        try (Connection c = DBHelper.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, sp.getTenSp());
            ps.setBigDecimal(2, sp.getDonGia());
            ps.setInt(3, sp.getSoLuong());

            if (sp.getMaDm() > 0)
                ps.setInt(4, sp.getMaDm());
            else
                ps.setNull(4, Types.INTEGER);

            ps.setInt(5, sp.getMaSp());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        try (Connection c = DBHelper.getConnection();
             PreparedStatement ps =
                     c.prepareStatement("DELETE FROM san_pham WHERE ma_sp=?")) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
