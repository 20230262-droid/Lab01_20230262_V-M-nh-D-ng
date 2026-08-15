package vn.edu.eaut.lab5.util;

import vn.edu.eaut.lab5.model.ChiTietHoaDon;
import vn.edu.eaut.lab5.model.HoaDon;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

public class InvoiceExporter {

    public static void export(Component parent,
                              HoaDon hoaDon,
                              List<ChiTietHoaDon> details) {

        JFileChooser chooser = new JFileChooser();

        chooser.setSelectedFile(
                new File("HoaDon_" +
                        hoaDon.getMaHd() + ".txt"));

        if (chooser.showSaveDialog(parent)
                != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File file = chooser.getSelectedFile();

        try (PrintWriter out =
                     new PrintWriter(file, "UTF-8")) {

            out.println("========================================");
            out.println("              MINI SHOP");
            out.println("           HÓA ĐƠN BÁN HÀNG");
            out.println("========================================");

            out.println("Mã hóa đơn : " + hoaDon.getMaHd());
            out.println("Ngày lập   : " + hoaDon.getNgayLap());
            out.println("Khách hàng : " + hoaDon.getTenKh());
            out.println("----------------------------------------");

            BigDecimal total = BigDecimal.ZERO;

            for (ChiTietHoaDon ct : details) {

                BigDecimal money = ct.getThanhTien();
                total = total.add(money);

                out.printf(
                        "%-25s %3d x %,.0f = %,.0f%n",
                        ct.getTenSp(),
                        ct.getSoLuong(),
                        ct.getDonGia(),
                        money
                );
            }

            out.println("----------------------------------------");
            out.printf("TỔNG TIỀN: %,.0f VNĐ%n", total);
            out.println("========================================");

            MessageUtil.info(
                    parent,
                    "Đã xuất hóa đơn thành công.");

        } catch (Exception e) {
            MessageUtil.error(
                    parent,
                    "Không thể xuất hóa đơn: "
                            + e.getMessage());
        }
    }
}
