package vn.edu.eaut.lab5.bus;

import vn.edu.eaut.lab5.dal.HoaDonDAL;
import vn.edu.eaut.lab5.model.ChiTietHoaDon;
import vn.edu.eaut.lab5.model.HoaDon;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class HoaDonBUS {

    private final HoaDonDAL dal = new HoaDonDAL();

    public int createInvoice(int maKh,
                             List<ChiTietHoaDon> details)
            throws SQLException {
        return dal.createInvoice(maKh, details);
    }

    public List<HoaDon> search(LocalDate from,
                               LocalDate to,
                               String customer,
                               String min,
                               String max)
            throws SQLException {
        return dal.search(from, to, customer, min, max);
    }

    public HoaDon findById(int id) throws SQLException {
        return dal.findById(id);
    }
}
