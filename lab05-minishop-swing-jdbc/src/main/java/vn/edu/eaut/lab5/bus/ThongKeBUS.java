package vn.edu.eaut.lab5.bus;

import vn.edu.eaut.lab5.dal.ThongKeDAL;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;

public class ThongKeBUS {

    private final ThongKeDAL dal = new ThongKeDAL();

    public BigDecimal getRevenue(LocalDate from,
                                 LocalDate to)
            throws SQLException {
        return dal.getRevenue(from, to);
    }

    public int getInvoiceCount(LocalDate from,
                               LocalDate to)
            throws SQLException {
        return dal.getInvoiceCount(from, to);
    }

    public String getBestProduct() throws SQLException {
        return dal.getBestProduct();
    }

    public BigDecimal getHighestInvoice() throws SQLException {
        return dal.getHighestInvoice();
    }
}
