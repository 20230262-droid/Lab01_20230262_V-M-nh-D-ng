package vn.edu.eaut.lab5.ui;

import vn.edu.eaut.lab5.bus.KhachHangBUS;
import vn.edu.eaut.lab5.model.KhachHang;
import vn.edu.eaut.lab5.util.MessageUtil;
import vn.edu.eaut.lab5.util.UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class KhachHangPanel extends JPanel {

    private final JTextField txtName = UI.field();
    private final JTextField txtPhone = UI.field();
    private final JTextField txtAddress = UI.field();

    private final JTextField txtSearchName = UI.field();
    private final JTextField txtSearchPhone = UI.field();
    private final JTextField txtSearchAddress = UI.field();

    private final DefaultTableModel model =
            new DefaultTableModel(
                    new String[]{
                            "Mã", "Tên khách hàng",
                            "Số điện thoại", "Địa chỉ"
                    }, 0);

    private final JTable table =
            UI.table(new JTable(model));

    private final KhachHangBUS bus =
            new KhachHangBUS();

    private int selectedId = 0;

    public KhachHangPanel() {

        setBackground(UI.PINK_PALE);

        setBorder(
                BorderFactory.createEmptyBorder(
                        16, 18, 16, 18));

        setLayout(
                new BorderLayout(12, 12));

        JPanel form = UI.card();

        form.setLayout(
                new GridLayout(2, 3, 10, 8));

        form.add(UI.label("Tên khách hàng"));
        form.add(UI.label("Số điện thoại"));
        form.add(UI.label("Địa chỉ"));

        form.add(txtName);
        form.add(txtPhone);
        form.add(txtAddress);

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
                        FlowLayout.LEFT));

        searchBox.add(UI.label("Tên"));
        searchBox.add(txtSearchName);

        searchBox.add(UI.label("SĐT"));
        searchBox.add(txtSearchPhone);

        searchBox.add(UI.label("Địa chỉ"));
        searchBox.add(txtSearchAddress);

        JButton search =
                UI.button("🔎 Tìm");

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

        loadData();
    }

    private void loadData() {

        try {

            model.setRowCount(0);

            for (KhachHang kh :
                    bus.search(
                            txtSearchName.getText(),
                            txtSearchPhone.getText(),
                            txtSearchAddress.getText())) {

                model.addRow(new Object[]{
                        kh.getMaKh(),
                        kh.getTenKh(),
                        kh.getSdt(),
                        kh.getDiaChi()
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

        txtPhone.setText(
                String.valueOf(
                        model.getValueAt(row, 2)));

        txtAddress.setText(
                String.valueOf(
                        model.getValueAt(row, 3)));
    }

    private void save() {

        try {

            KhachHang kh =
                    new KhachHang(
                            selectedId,
                            txtName.getText().trim(),
                            txtPhone.getText().trim(),
                            txtAddress.getText().trim());

            bus.save(kh);

            MessageUtil.info(
                    this,
                    selectedId == 0
                            ? "Thêm khách hàng thành công."
                            : "Cập nhật khách hàng thành công.");

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
                        "Hãy chọn khách hàng.");
                return;
            }

            if (MessageUtil.confirm(
                    this,
                    "Bạn có chắc muốn xóa khách hàng?")) {

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
        txtPhone.setText("");
        txtAddress.setText("");

        table.clearSelection();
    }
}
