package vn.edu.eaut.lab5.dal;

import vn.edu.eaut.lab5.config.DBHelper;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;

public class ThongKeDAL {

    public BigDecimal getRevenue(LocalDate from,
                                 LocalDate to) throws SQLException {

        String sql = """
            SELECT COALESCE(SUM(tong_tien),0)
            FROM hoa_don
            WHERE ngay_lap BETWEEN ? AND ?
            """;

        try (Connection c = DBHelper.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(from));
            ps.setDate(2, Date.valueOf(to));

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getBigDecimal(1);
            }
        }
    }

    public int getInvoiceCount(LocalDate from,
                               LocalDate to) throws SQLException {

        String sql = """
            SELECT COUNT(*)
            FROM hoa_don
            WHERE ngay_lap BETWEEN ? AND ?
            """;

        try (Connection c = DBHelper.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(from));
            ps.setDate(2, Date.valueOf(to));

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        }
    }

    public String getBestProduct() throws SQLException {

        String sql = """
            SELECT sp.ten_sp, SUM(ct.so_luong) AS sl
            FROM chi_tiet_hoa_don ct
            JOIN san_pham sp ON ct.ma_sp=sp.ma_sp
            GROUP BY sp.ma_sp, sp.ten_sp
            ORDER BY sl DESC
            LIMIT 1
            """;

        try (Connection c = DBHelper.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (!rs.next()) return "Chưa có dữ liệu";

            return rs.getString("ten_sp")
                    + " (" + rs.getInt("sl") + " sản phẩm)";
        }
    }

    public BigDecimal getHighestInvoice() throws SQLException {

        String sql = """
            SELECT COALESCE(MAX(tong_tien),0)
            FROM hoa_don
            """;

        try (Connection c = DBHelper.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            rs.next();
            return rs.getBigDecimal(1);
        }
    }
}
