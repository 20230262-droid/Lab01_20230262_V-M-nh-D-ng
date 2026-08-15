package vn.edu.eaut.lab5.bus;

import vn.edu.eaut.lab5.dal.KhachHangDAL;
import vn.edu.eaut.lab5.model.KhachHang;

import java.sql.SQLException;
import java.util.List;

public class KhachHangBUS {

    private final KhachHangDAL dal = new KhachHangDAL();

    public List<KhachHang> findAll() throws SQLException {
        return dal.findAll();
    }

    public List<KhachHang> search(String name,
                                  String phone,
                                  String address)
            throws SQLException {
        return dal.search(name, phone, address);
    }

    public boolean save(KhachHang kh) throws SQLException {

        if (kh.getTenKh() == null ||
                kh.getTenKh().isBlank()) {
            throw new IllegalArgumentException(
                    "Tên khách hàng không được để trống.");
        }

        if (kh.getSdt() == null ||
                !kh.getSdt().matches("\\d{10}")) {
            throw new IllegalArgumentException(
                    "Số điện thoại phải gồm đúng 10 chữ số.");
        }

        return kh.getMaKh() == 0
                ? dal.insert(kh)
                : dal.update(kh);
    }

    public boolean delete(int id) throws SQLException {
        return dal.delete(id);
    }
}
