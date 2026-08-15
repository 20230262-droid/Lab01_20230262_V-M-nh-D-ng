package vn.edu.eaut.lab5.dal;

import vn.edu.eaut.lab5.config.DBHelper;
import vn.edu.eaut.lab5.model.TaiKhoan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthDAL {

    public TaiKhoan login(String username, String password) {

        String sql = """
                SELECT id, username, password, ho_ten, vai_tro, trang_thai
                FROM tai_khoan
                WHERE username = ?
                  AND password = ?
                  AND trang_thai = 1
                """;

        try (
                Connection conn = DBHelper.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    TaiKhoan tk = new TaiKhoan();

                    tk.setId(rs.getInt("id"));
                    tk.setUsername(rs.getString("username"));
                    tk.setPassword(rs.getString("password"));
                    tk.setHoTen(rs.getString("ho_ten"));
                    tk.setVaiTro(rs.getString("vai_tro"));
                    tk.setTrangThai(rs.getInt("trang_thai"));

                    return tk;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}