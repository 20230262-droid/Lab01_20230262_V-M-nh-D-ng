package vn.edu.eaut.lab5.dal;

import vn.edu.eaut.lab5.config.DBHelper;
import vn.edu.eaut.lab5.model.KhachHang;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KhachHangDAL {

    private KhachHang map(ResultSet rs) throws SQLException {
        return new KhachHang(
                rs.getInt("ma_kh"),
                rs.getString("ten_kh"),
                rs.getString("sdt"),
                rs.getString("dia_chi")
        );
    }

    public List<KhachHang> findAll() throws SQLException {
        return search("", "", "");
    }

    public List<KhachHang> search(String name, String phone,
                                  String address) throws SQLException {

        String sql = """
            SELECT * FROM khach_hang
            WHERE ten_kh LIKE ?
              AND sdt LIKE ?
              AND COALESCE(dia_chi,'') LIKE ?
            ORDER BY ma_kh DESC
            """;

        try (Connection c = DBHelper.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, "%" + name + "%");
            ps.setString(2, "%" + phone + "%");
            ps.setString(3, "%" + address + "%");

            try (ResultSet rs = ps.executeQuery()) {
                List<KhachHang> list = new ArrayList<>();
                while (rs.next()) list.add(map(rs));
                return list;
            }
        }
    }

    public boolean insert(KhachHang kh) throws SQLException {
        String sql = "INSERT INTO khach_hang(ten_kh,sdt,dia_chi) VALUES(?,?,?)";

        try (Connection c = DBHelper.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, kh.getTenKh());
            ps.setString(2, kh.getSdt());
            ps.setString(3, kh.getDiaChi());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean update(KhachHang kh) throws SQLException {
        String sql = """
            UPDATE khach_hang
            SET ten_kh=?, sdt=?, dia_chi=?
            WHERE ma_kh=?
            """;

        try (Connection c = DBHelper.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, kh.getTenKh());
            ps.setString(2, kh.getSdt());
            ps.setString(3, kh.getDiaChi());
            ps.setInt(4, kh.getMaKh());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        try (Connection c = DBHelper.getConnection();
             PreparedStatement ps =
                     c.prepareStatement("DELETE FROM khach_hang WHERE ma_kh=?")) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
