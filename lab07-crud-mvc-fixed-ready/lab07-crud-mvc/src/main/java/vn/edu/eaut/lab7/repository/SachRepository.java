package vn.edu.eaut.lab7.repository;

import vn.edu.eaut.lab7.model.Sach;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SachRepository {

    // =====================================================
    // DATABASE
    // =====================================================

    private static final String URL =
            "jdbc:mysql://localhost:3306/lab07_crud"
                    + "?useSSL=false"
                    + "&serverTimezone=Asia/Ho_Chi_Minh"
                    + "&characterEncoding=UTF-8";

    private static final String USER = "root";

    // Nếu MySQL của bạn có mật khẩu thì sửa dòng này
    private static final String PASSWORD = "";


    // =====================================================
    // LOAD MYSQL DRIVER
    // =====================================================

    static {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {

            throw new RuntimeException(
                    "Không tìm thấy MySQL JDBC Driver!",
                    e
            );
        }
    }


    // =====================================================
    // CONNECTION
    // =====================================================

    private Connection getConnection()
            throws SQLException {

        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }


    // =====================================================
    // FIND ALL
    // =====================================================

    public List<Sach> findAll() {

        List<Sach> list =
                new ArrayList<>();

        String sql = """
                SELECT
                    id,
                    ma_sach,
                    ten_sach,
                    tac_gia,
                    nha_xuat_ban,
                    nam_xuat_ban
                FROM sach
                ORDER BY id DESC
                """;

        try (
                Connection conn =
                        getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            while (rs.next()) {

                Sach sach =
                        new Sach();

                sach.setId(
                        rs.getInt("id")
                );

                sach.setMaSach(
                        rs.getString("ma_sach")
                );

                sach.setTenSach(
                        rs.getString("ten_sach")
                );

                sach.setTacGia(
                        rs.getString("tac_gia")
                );

                sach.setNhaXuatBan(
                        rs.getString("nha_xuat_ban")
                );

                sach.setNamXuatBan(
                        rs.getInt("nam_xuat_ban")
                );

                list.add(sach);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return list;
    }


    // =====================================================
    // SEARCH
    // =====================================================

    public List<Sach> search(
            String keyword
    ) {

        List<Sach> list =
                new ArrayList<>();

        String sql = """
                SELECT
                    id,
                    ma_sach,
                    ten_sach,
                    tac_gia,
                    nha_xuat_ban,
                    nam_xuat_ban
                FROM sach
                WHERE ma_sach LIKE ?
                   OR ten_sach LIKE ?
                   OR tac_gia LIKE ?
                   OR nha_xuat_ban LIKE ?
                ORDER BY id DESC
                """;

        try (
                Connection conn =
                        getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            String value =
                    "%" + keyword + "%";

            ps.setString(1, value);
            ps.setString(2, value);
            ps.setString(3, value);
            ps.setString(4, value);

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                while (rs.next()) {

                    Sach sach =
                            new Sach();

                    sach.setId(
                            rs.getInt("id")
                    );

                    sach.setMaSach(
                            rs.getString("ma_sach")
                    );

                    sach.setTenSach(
                            rs.getString("ten_sach")
                    );

                    sach.setTacGia(
                            rs.getString("tac_gia")
                    );

                    sach.setNhaXuatBan(
                            rs.getString("nha_xuat_ban")
                    );

                    sach.setNamXuatBan(
                            rs.getInt("nam_xuat_ban")
                    );

                    list.add(sach);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return list;
    }


    // =====================================================
    // FIND BY ID
    // =====================================================

    public Sach findById(
            int id
    ) {

        String sql = """
                SELECT
                    id,
                    ma_sach,
                    ten_sach,
                    tac_gia,
                    nha_xuat_ban,
                    nam_xuat_ban
                FROM sach
                WHERE id = ?
                """;

        try (
                Connection conn =
                        getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                if (rs.next()) {

                    Sach sach =
                            new Sach();

                    sach.setId(
                            rs.getInt("id")
                    );

                    sach.setMaSach(
                            rs.getString("ma_sach")
                    );

                    sach.setTenSach(
                            rs.getString("ten_sach")
                    );

                    sach.setTacGia(
                            rs.getString("tac_gia")
                    );

                    sach.setNhaXuatBan(
                            rs.getString("nha_xuat_ban")
                    );

                    sach.setNamXuatBan(
                            rs.getInt("nam_xuat_ban")
                    );

                    return sach;
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }


    // =====================================================
    // ADD
    // =====================================================

    public boolean add(
            Sach sach
    ) {

        return insert(sach);
    }


    // =====================================================
    // INSERT
    // =====================================================

    public boolean insert(
            Sach sach
    ) {

        String sql = """
                INSERT INTO sach
                (
                    ma_sach,
                    ten_sach,
                    tac_gia,
                    nha_xuat_ban,
                    nam_xuat_ban
                )
                VALUES (?, ?, ?, ?, ?)
                """;

        try (
                Connection conn =
                        getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    sach.getMaSach()
            );

            ps.setString(
                    2,
                    sach.getTenSach()
            );

            ps.setString(
                    3,
                    sach.getTacGia()
            );

            ps.setString(
                    4,
                    sach.getNhaXuatBan()
            );

            ps.setInt(
                    5,
                    sach.getNamXuatBan()
            );

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }


    // =====================================================
    // UPDATE
    // =====================================================

    public boolean update(
            Sach sach
    ) {

        String sql = """
                UPDATE sach
                SET
                    ma_sach = ?,
                    ten_sach = ?,
                    tac_gia = ?,
                    nha_xuat_ban = ?,
                    nam_xuat_ban = ?
                WHERE id = ?
                """;

        try (
                Connection conn =
                        getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    sach.getMaSach()
            );

            ps.setString(
                    2,
                    sach.getTenSach()
            );

            ps.setString(
                    3,
                    sach.getTacGia()
            );

            ps.setString(
                    4,
                    sach.getNhaXuatBan()
            );

            ps.setInt(
                    5,
                    sach.getNamXuatBan()
            );

            ps.setInt(
                    6,
                    sach.getId()
            );

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }


    // =====================================================
    // DELETE
    // =====================================================

    public boolean delete(
            int id
    ) {

        String sql =
                "DELETE FROM sach WHERE id = ?";

        try (
                Connection conn =
                        getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setInt(
                    1,
                    id
            );

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }
}