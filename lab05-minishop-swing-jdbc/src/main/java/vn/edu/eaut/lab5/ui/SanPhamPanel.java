package vn.edu.eaut.lab5.ui;

import vn.edu.eaut.lab5.bus.DanhMucBUS;
import vn.edu.eaut.lab5.bus.SanPhamBUS;
import vn.edu.eaut.lab5.model.DanhMuc;
import vn.edu.eaut.lab5.model.SanPham;
import vn.edu.eaut.lab5.util.MessageUtil;
import vn.edu.eaut.lab5.util.UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

public class SanPhamPanel extends JPanel {

    private final JTextField txtName = UI.field();
    private final JTextField txtPrice = UI.field();
    private final JTextField txtQty = UI.field();

    private final JTextField txtSearch = UI.field();
    private final JTextField txtMin = UI.field();
    private final JTextField txtMax = UI.field();
    private final JTextField txtMinQty = UI.field();

    private final JComboBox<DanhMuc> cboCategory =
            new JComboBox<>();

    private final DefaultTableModel model =
            new DefaultTableModel(
                    new String[]{
                            "Mã", "Tên sản phẩm",
                            "Đơn giá", "Tồn kho",
                            "Danh mục"
                    }, 0) {
                @Override
                public boolean isCellEditable(
                        int row, int column) {
                    return false;
                }
            };

    private final JTable table =
            UI.table(new JTable(model));

    private final SanPhamBUS bus =
            new SanPhamBUS();

    private final DanhMucBUS dmBus =
            new DanhMucBUS();

    private int selectedId = 0;

    public SanPhamPanel() {

        setBackground(UI.PINK_PALE);

        setBorder(
                BorderFactory.createEmptyBorder(
                        16, 18, 16, 18));

        setLayout(
                new BorderLayout(12, 12));

        JPanel form = UI.card();

        form.setLayout(
                new GridLayout(2, 4, 10, 8));

        form.add(UI.label("Tên sản phẩm"));
        form.add(UI.label("Đơn giá"));
        form.add(UI.label("Số lượng"));
        form.add(UI.label("Danh mục"));

        form.add(txtName);
        form.add(txtPrice);
        form.add(txtQty);
        form.add(cboCategory);

        add(form, BorderLayout.NORTH);

        table.getSelectionModel()
                .addListSelectionListener(e -> {

                    if (!e.getValueIsAdjusting()
                            && table.getSelectedRow() >= 0) {
                        fillForm();
                    }
                });

        add(new JScrollPane(table),
                BorderLayout.CENTER);

        JPanel bottom =
                new JPanel(new BorderLayout(8, 8));

        bottom.setOpaque(false);

        JPanel searchBox = UI.card();

        searchBox.setLayout(
                new FlowLayout(
                        FlowLayout.LEFT, 8, 8));

        searchBox.add(UI.label("Tên"));
        searchBox.add(txtSearch);

        searchBox.add(UI.label("Giá từ"));
        searchBox.add(txtMin);

        searchBox.add(UI.label("đến"));
        searchBox.add(txtMax);

        searchBox.add(UI.label("Tồn ≥"));
        searchBox.add(txtMinQty);

        JButton search =
                UI.button("🔎 Tìm kiếm");

        search.addActionListener(
                e -> loadData());

        searchBox.add(search);

        JPanel buttons =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT));

        buttons.setOpaque(false);

        JButton add = UI.button("＋ Thêm");
        JButton edit = UI.button("✎ Sửa");
        JButton delete = UI.button("🗑 Xóa");
        JButton clear = UI.button("↻ Làm mới");

        add.addActionListener(e -> save());
        edit.addActionListener(e -> save());
        delete.addActionListener(e -> delete());
        clear.addActionListener(e -> clearForm());

        buttons.add(add);
        buttons.add(edit);
        buttons.add(delete);
        buttons.add(clear);

        bottom.add(searchBox,
                BorderLayout.CENTER);

        bottom.add(buttons,
                BorderLayout.SOUTH);

        add(bottom, BorderLayout.SOUTH);

        loadCategories();
        loadData();
    }

    private void loadCategories() {

        try {

            cboCategory.removeAllItems();

            for (DanhMuc dm : dmBus.findAll()) {
                cboCategory.addItem(dm);
            }

        } catch (Exception e) {
            MessageUtil.error(
                    this, e.getMessage());
        }
    }

    private void loadData() {

        try {

            List<SanPham> list =
                    bus.search(
                            txtSearch.getText(),
                            txtMin.getText(),
                            txtMax.getText(),
                            txtMinQty.getText());

            model.setRowCount(0);

            for (SanPham sp : list) {

                model.addRow(new Object[]{
                        sp.getMaSp(),
                        sp.getTenSp(),
                        String.format(
                                "%,.0f",
                                sp.getDonGia()),
                        sp.getSoLuong(),
                        sp.getTenDm()
                });
            }

        } catch (Exception e) {

            MessageUtil.error(
                    this, e.getMessage());
        }
    }

    private void fillForm() {

        int row = table.getSelectedRow();

        selectedId =
                (int) model.getValueAt(row, 0);

        txtName.setText(
                String.valueOf(
                        model.getValueAt(row, 1)));

        txtPrice.setText(
                String.valueOf(
                                model.getValueAt(row, 2))
                        .replace(",", ""));

        txtQty.setText(
                String.valueOf(
                        model.getValueAt(row, 3)));

        String category =
                String.valueOf(
                        model.getValueAt(row, 4));

        for (int i = 0;
             i < cboCategory.getItemCount();
             i++) {

            if (cboCategory
                    .getItemAt(i)
                    .getTenDm()
                    .equals(category)) {

                cboCategory.setSelectedIndex(i);
                break;
            }
        }
    }

    private void save() {

        try {

            SanPham sp = new SanPham();

            sp.setMaSp(selectedId);
            sp.setTenSp(
                    txtName.getText().trim());

            sp.setDonGia(
                    new BigDecimal(
                            txtPrice.getText().trim()));

            sp.setSoLuong(
                    Integer.parseInt(
                            txtQty.getText().trim()));

            DanhMuc dm =
                    (DanhMuc)
                            cboCategory.getSelectedItem();

            sp.setMaDm(
                    dm == null ? 0 :
                            dm.getMaDm());

            bus.save(sp);

            MessageUtil.info(
                    this,
                    selectedId == 0
                            ? "Thêm sản phẩm thành công."
                            : "Cập nhật sản phẩm thành công.");

            clearForm();
            loadData();

        } catch (Exception e) {

            MessageUtil.error(
                    this, e.getMessage());
        }
    }

    private void delete() {

        try {

            if (selectedId == 0) {
                MessageUtil.error(
                        this,
                        "Hãy chọn sản phẩm cần xóa.");
                return;
            }

            if (MessageUtil.confirm(
                    this,
                    "Bạn có chắc muốn xóa sản phẩm này?")) {

                bus.delete(selectedId);

                clearForm();
                loadData();
            }

        } catch (Exception e) {

            MessageUtil.error(
                    this, e.getMessage());
        }
    }

    private void clearForm() {

        selectedId = 0;

        txtName.setText("");
        txtPrice.setText("");
        txtQty.setText("");

        table.clearSelection();
    }
}
