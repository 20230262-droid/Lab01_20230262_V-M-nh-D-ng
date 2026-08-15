package vn.edu.eaut.lab5.ui;

import vn.edu.eaut.lab5.bus.HoaDonBUS;
import vn.edu.eaut.lab5.bus.KhachHangBUS;
import vn.edu.eaut.lab5.bus.SanPhamBUS;
import vn.edu.eaut.lab5.model.*;
import vn.edu.eaut.lab5.util.InvoiceExporter;
import vn.edu.eaut.lab5.util.MessageUtil;
import vn.edu.eaut.lab5.util.UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class HoaDonPanel extends JPanel {

    private final JComboBox<KhachHang> cboCustomer =
            new JComboBox<>();

    private final JComboBox<SanPham> cboProduct =
            new JComboBox<>();

    private final JTextField txtQty =
            UI.field();

    private final JLabel lblTotal =
            new JLabel("0 VNĐ");

    private final DefaultTableModel model =
            new DefaultTableModel(
                    new String[]{
                            "Mã SP", "Sản phẩm",
                            "SL", "Đơn giá",
                            "Thành tiền"
                    }, 0);

    private final JTable table =
            UI.table(new JTable(model));

    private final List<ChiTietHoaDon> details =
            new ArrayList<>();

    private final SanPhamBUS spBus =
            new SanPhamBUS();

    private final KhachHangBUS khBus =
            new KhachHangBUS();

    private final HoaDonBUS hdBus =
            new HoaDonBUS();

    private int lastInvoiceId = 0;
    private HoaDon lastInvoice;
    private final List<ChiTietHoaDon> lastDetails = new ArrayList<>();

    public HoaDonPanel() {

        setBackground(UI.PINK_PALE);

        setBorder(
                BorderFactory.createEmptyBorder(
                        16, 18, 16, 18));

        setLayout(
                new BorderLayout(12, 12));

        JPanel top = UI.card();

        top.setLayout(
                new GridLayout(2, 3, 10, 8));

        top.add(UI.label("Khách hàng"));
        top.add(UI.label("Sản phẩm"));
        top.add(UI.label("Số lượng"));

        top.add(cboCustomer);
        top.add(cboProduct);
        top.add(txtQty);

        add(top, BorderLayout.NORTH);

        add(new JScrollPane(table),
                BorderLayout.CENTER);

        JPanel bottom =
                new JPanel(new BorderLayout());

        bottom.setOpaque(false);

        JPanel buttons =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT));

        buttons.setOpaque(false);

        JButton add =
                UI.button("＋ Thêm sản phẩm");

        JButton remove =
                UI.button("− Xóa dòng");

        JButton save =
                UI.button("💾 Lưu hóa đơn");

        JButton export =
                UI.button("📄 Xuất hóa đơn");

        add.addActionListener(
                e -> addProduct());

        remove.addActionListener(
                e -> removeProduct());

        save.addActionListener(
                e -> saveInvoice());

        export.addActionListener(
                e -> exportInvoice());

        buttons.add(add);
        buttons.add(remove);
        buttons.add(save);
        buttons.add(export);

        JPanel totalPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT));

        totalPanel.setOpaque(false);

        JLabel text =
                UI.label("TỔNG TIỀN:");

        text.setFont(
                new Font("Segoe UI",
                        Font.BOLD, 15));

        lblTotal.setFont(
                new Font("Segoe UI",
                        Font.BOLD, 24));

        lblTotal.setForeground(UI.PINK);

        totalPanel.add(text);
        totalPanel.add(lblTotal);

        bottom.add(buttons,
                BorderLayout.WEST);

        bottom.add(totalPanel,
                BorderLayout.EAST);

        add(bottom, BorderLayout.SOUTH);

        loadData();
    }

    private void loadData() {

        try {

            cboCustomer.removeAllItems();

            for (KhachHang kh :
                    khBus.findAll()) {
                cboCustomer.addItem(kh);
            }

            cboProduct.removeAllItems();

            for (SanPham sp :
                    spBus.findAll()) {
                cboProduct.addItem(sp);
            }

        } catch (Exception e) {

            MessageUtil.error(
                    this, e.getMessage());
        }
    }

    private void addProduct() {

        try {

            SanPham sp =
                    (SanPham)
                            cboProduct.getSelectedItem();

            if (sp == null) {
                throw new IllegalArgumentException(
                        "Chưa chọn sản phẩm.");
            }

            int qty =
                    Integer.parseInt(
                            txtQty.getText().trim());

            if (qty <= 0) {
                throw new IllegalArgumentException(
                        "Số lượng phải lớn hơn 0.");
            }

            int oldQty = 0;

            for (ChiTietHoaDon ct :
                    details) {

                if (ct.getMaSp()
                        == sp.getMaSp()) {

                    oldQty =
                            ct.getSoLuong();
                }
            }

            if (oldQty + qty >
                    sp.getSoLuong()) {

                throw new IllegalArgumentException(
                        "Không đủ tồn kho. "
                                + "Tồn hiện tại: "
                                + sp.getSoLuong());
            }

            boolean merged = false;

            for (ChiTietHoaDon ct :
                    details) {

                if (ct.getMaSp()
                        == sp.getMaSp()) {

                    ct.setSoLuong(
                            ct.getSoLuong()
                                    + qty);

                    merged = true;
                    break;
                }
            }

            if (!merged) {

                details.add(
                        new ChiTietHoaDon(
                                sp.getMaSp(),
                                sp.getTenSp(),
                                qty,
                                sp.getDonGia()));
            }

            refreshTable();

            txtQty.setText("");

        } catch (Exception e) {

            MessageUtil.error(
                    this, e.getMessage());
        }
    }

    private void removeProduct() {

        int row =
                table.getSelectedRow();

        if (row < 0) {
            MessageUtil.error(
                    this,
                    "Hãy chọn dòng cần xóa.");
            return;
        }

        details.remove(row);

        refreshTable();
    }

    private void refreshTable() {

        model.setRowCount(0);

        BigDecimal total =
                BigDecimal.ZERO;

        for (ChiTietHoaDon ct :
                details) {

            model.addRow(new Object[]{
                    ct.getMaSp(),
                    ct.getTenSp(),
                    ct.getSoLuong(),
                    String.format(
                            "%,.0f",
                            ct.getDonGia()),
                    String.format(
                            "%,.0f",
                            ct.getThanhTien())
            });

            total =
                    total.add(
                            ct.getThanhTien());
        }

        lblTotal.setText(
                String.format(
                        "%,.0f VNĐ", total));
    }

    private void saveInvoice() {

        try {

            KhachHang kh =
                    (KhachHang)
                            cboCustomer.getSelectedItem();

            if (kh == null) {
                throw new IllegalArgumentException(
                        "Chưa chọn khách hàng.");
            }

            if (details.isEmpty()) {
                throw new IllegalArgumentException(
                        "Hóa đơn chưa có sản phẩm.");
            }

            lastInvoiceId =
                    hdBus.createInvoice(
                            kh.getMaKh(),
                            details);

            lastInvoice =
                    hdBus.findById(
                            lastInvoiceId);

            lastDetails.clear();
            for (ChiTietHoaDon ct : details) {
                lastDetails.add(new ChiTietHoaDon(
                        ct.getMaSp(), ct.getTenSp(),
                        ct.getSoLuong(), ct.getDonGia()));
            }

            MessageUtil.info(
                    this,
                    "Lưu hóa đơn thành công!\n"
                            + "Mã hóa đơn: #"
                            + lastInvoiceId
                            + "\nTồn kho đã được cập nhật.");

            details.clear();
            refreshTable();
            loadData();

        } catch (Exception e) {

            MessageUtil.error(
                    this, e.getMessage());
        }
    }

    private void exportInvoice() {

        if (lastInvoice == null) {

            MessageUtil.error(
                    this,
                    "Hãy lưu hóa đơn trước.");
            return;
        }

        InvoiceExporter.export(
                this,
                lastInvoice,
                lastDetails);
    }
}
