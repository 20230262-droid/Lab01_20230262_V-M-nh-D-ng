package vn.edu.eaut.lab5.dal;

import vn.edu.eaut.lab5.config.DBHelper;
import vn.edu.eaut.lab5.model.*;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDAL {

    public int createInvoice(int maKh,
                             List<ChiTietHoaDon> details)
            throws SQLException {

        if (details == null || details.isEmpty()) {
            throw new SQLException("Hóa đơn chưa có sản phẩm.");
        }

        try (Connection c = DBHelper.getConnection()) {
            c.setAutoCommit(false);

            try {
                BigDecimal total = BigDecimal.ZERO;

                for (ChiTietHoaDon ct : details) {
                    total = total.add(ct.getThanhTien());
                }

                int maHd;

                String insertHd = """
                    INSERT INTO hoa_don(ngay_lap,ma_kh,tong_tien)
                    VALUES(?,?,?)
                    """;

                try (PreparedStatement ps =
                             c.prepareStatement(
                                     insertHd,
                                     Statement.RETURN_GENERATED_KEYS)) {

                    ps.setDate(1, Date.valueOf(LocalDate.now()));
                    ps.setInt(2, maKh);
                    ps.setBigDecimal(3, total);
                    ps.executeUpdate();

                    try (ResultSet rs = ps.getGeneratedKeys()) {
                        if (!rs.next())
                            throw new SQLException(
                                    "Không lấy được mã hóa đơn.");

                        maHd = rs.getInt(1);
                    }
                }

                String stockSql = """
                    UPDATE san_pham
                    SET so_luong = so_luong - ?
                    WHERE ma_sp = ?
                      AND so_luong >= ?
                    """;

                String detailSql = """
                    INSERT INTO chi_tiet_hoa_don
                    (ma_hd,ma_sp,so_luong,don_gia,thanh_tien)
                    VALUES(?,?,?,?,?)
                    """;

                try (PreparedStatement stock =
                             c.prepareStatement(stockSql);
                     PreparedStatement detail =
                             c.prepareStatement(detailSql)) {

                    for (ChiTietHoaDon ct : details) {

                        stock.setInt(1, ct.getSoLuong());
                        stock.setInt(2, ct.getMaSp());
                        stock.setInt(3, ct.getSoLuong());

                        if (stock.executeUpdate() == 0) {
                            throw new SQLException(
                                    "Sản phẩm " + ct.getTenSp()
                                            + " không đủ tồn kho.");
                        }

                        detail.setInt(1, maHd);
                        detail.setInt(2, ct.getMaSp());
                        detail.setInt(3, ct.getSoLuong());
                        detail.setBigDecimal(4, ct.getDonGia());
                        detail.setBigDecimal(5, ct.getThanhTien());
                        detail.addBatch();
                    }

                    detail.executeBatch();
                }

                c.commit();
                return maHd;

            } catch (SQLException e) {
                c.rollback();
                throw e;
            } finally {
                c.setAutoCommit(true);
            }
        }
    }

    public List<HoaDon> search(LocalDate from,
                               LocalDate to,
                               String customer,
                               String min,
                               String max)
            throws SQLException {

        String sql = """
            SELECT hd.*, kh.ten_kh
            FROM hoa_don hd
            JOIN khach_hang kh ON hd.ma_kh = kh.ma_kh
            WHERE hd.ngay_lap BETWEEN ? AND ?
              AND kh.ten_kh LIKE ?
              AND hd.tong_tien BETWEEN ? AND ?
            ORDER BY hd.ma_hd DESC
            """;

        BigDecimal minValue =
                min.isBlank() ? BigDecimal.ZERO :
                        new BigDecimal(min);

        BigDecimal maxValue =
                max.isBlank()
                        ? new BigDecimal("999999999999")
                        : new BigDecimal(max);

        try (Connection c = DBHelper.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(from));
            ps.setDate(2, Date.valueOf(to));
            ps.setString(3, "%" + customer + "%");
            ps.setBigDecimal(4, minValue);
            ps.setBigDecimal(5, maxValue);

            try (ResultSet rs = ps.executeQuery()) {
                List<HoaDon> list = new ArrayList<>();

                while (rs.next()) {
                    HoaDon hd = new HoaDon();

                    hd.setMaHd(rs.getInt("ma_hd"));
                    hd.setNgayLap(
                            rs.getDate("ngay_lap").toLocalDate());
                    hd.setMaKh(rs.getInt("ma_kh"));
                    hd.setTongTien(rs.getBigDecimal("tong_tien"));
                    hd.setTenKh(rs.getString("ten_kh"));

                    list.add(hd);
                }

                return list;
            }
        }
    }

    public HoaDon findById(int id) throws SQLException {

        String sql = """
            SELECT hd.*, kh.ten_kh
            FROM hoa_don hd
            JOIN khach_hang kh ON hd.ma_kh=kh.ma_kh
            WHERE hd.ma_hd=?
            """;

        try (Connection c = DBHelper.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;

                HoaDon hd = new HoaDon();

                hd.setMaHd(rs.getInt("ma_hd"));
                hd.setNgayLap(
                        rs.getDate("ngay_lap").toLocalDate());
                hd.setMaKh(rs.getInt("ma_kh"));
                hd.setTongTien(rs.getBigDecimal("tong_tien"));
                hd.setTenKh(rs.getString("ten_kh"));

                return hd;
            }
        }
    }
}
