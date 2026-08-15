package vn.edu.eaut.lab5.bus;

import vn.edu.eaut.lab5.dal.SanPhamDAL;
import vn.edu.eaut.lab5.model.SanPham;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class SanPhamBUS {

    private final SanPhamDAL dal = new SanPhamDAL();

    public List<SanPham> findAll() throws SQLException {
        return dal.findAll();
    }

    public List<SanPham> search(String name,
                                String min,
                                String max,
                                String minQty)
            throws SQLException {
        return dal.search(name, min, max, minQty);
    }

    public boolean save(SanPham sp) throws SQLException {

        if (sp.getTenSp() == null ||
                sp.getTenSp().isBlank()) {
            throw new IllegalArgumentException(
                    "Tên sản phẩm không được để trống.");
        }

        if (sp.getDonGia() == null ||
                sp.getDonGia().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Đơn giá phải lớn hơn 0.");
        }

        if (sp.getSoLuong() < 0) {
            throw new IllegalArgumentException(
                    "Số lượng không được âm.");
        }

        return sp.getMaSp() == 0
                ? dal.insert(sp)
                : dal.update(sp);
    }

    public boolean delete(int id) throws SQLException {
        return dal.delete(id);
    }
}
