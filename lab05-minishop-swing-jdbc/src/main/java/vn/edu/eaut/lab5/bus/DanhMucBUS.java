package vn.edu.eaut.lab5.bus;

import vn.edu.eaut.lab5.dal.DanhMucDAL;
import vn.edu.eaut.lab5.model.DanhMuc;

import java.sql.SQLException;
import java.util.List;

public class DanhMucBUS {

    private final DanhMucDAL dal = new DanhMucDAL();

    // =========================
    // LẤY TẤT CẢ
    // =========================
    public List<DanhMuc> findAll() throws SQLException {
        return dal.findAll();
    }

    // =========================
    // THÊM
    // UI đang gọi insert(String)
    // =========================
    public boolean insert(String tenDm) throws SQLException {

        validateTen(tenDm);

        DanhMuc dm = new DanhMuc();
        dm.setTenDm(tenDm.trim());

        return dal.insert(dm);
    }

    // =========================
    // THÊM - nhận object
    // =========================
    public boolean insert(DanhMuc dm) throws SQLException {

        if (dm == null) {
            throw new IllegalArgumentException(
                    "Danh mục không hợp lệ!"
            );
        }

        validateTen(dm.getTenDm());

        dm.setTenDm(dm.getTenDm().trim());

        return dal.insert(dm);
    }

    // =========================
    // CẬP NHẬT
    // =========================
    public boolean update(int maDm, String tenDm) throws SQLException {

        if (maDm <= 0) {
            throw new IllegalArgumentException(
                    "Mã danh mục không hợp lệ!"
            );
        }

        validateTen(tenDm);

        DanhMuc dm = new DanhMuc();
        dm.setMaDm(maDm);
        dm.setTenDm(tenDm.trim());

        return dal.update(dm);
    }

    // =========================
    // CẬP NHẬT - nhận object
    // =========================
    public boolean update(DanhMuc dm) throws SQLException {

        if (dm == null) {
            throw new IllegalArgumentException(
                    "Danh mục không hợp lệ!"
            );
        }

        if (dm.getMaDm() <= 0) {
            throw new IllegalArgumentException(
                    "Mã danh mục không hợp lệ!"
            );
        }

        validateTen(dm.getTenDm());

        dm.setTenDm(dm.getTenDm().trim());

        return dal.update(dm);
    }

    // =========================
    // XÓA
    // =========================
    public boolean delete(int maDm) throws SQLException {

        if (maDm <= 0) {
            throw new IllegalArgumentException(
                    "Mã danh mục không hợp lệ!"
            );
        }

        return dal.delete(maDm);
    }

    // =========================
    // TÌM KIẾM
    // =========================
    public List<DanhMuc> search(String keyword) throws SQLException {

        if (keyword == null) {
            keyword = "";
        }

        return dal.search(keyword.trim());
    }

    // =========================
    // SAVE
    // =========================
    public boolean save(DanhMuc dm) throws SQLException {

        if (dm == null) {
            throw new IllegalArgumentException(
                    "Danh mục không hợp lệ!"
            );
        }

        if (dm.getMaDm() == 0) {
            return insert(dm);
        }

        return update(dm);
    }

    // =========================
    // VALIDATE
    // =========================
    private void validateTen(String tenDm) {

        if (tenDm == null || tenDm.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Tên danh mục không được để trống!"
            );
        }

        if (tenDm.trim().length() > 100) {
            throw new IllegalArgumentException(
                    "Tên danh mục không được vượt quá 100 ký tự!"
            );
        }
    }
}