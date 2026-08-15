package vn.edu.eaut.lab5.ui;

import vn.edu.eaut.lab5.bus.DanhMucBUS;
import vn.edu.eaut.lab5.model.DanhMuc;
import vn.edu.eaut.lab5.util.MessageUtil;
import vn.edu.eaut.lab5.util.UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DanhMucPanel extends JPanel {

    private final JTextField txtName = UI.field();

    private final DefaultTableModel model =
            new DefaultTableModel(
                    new String[]{"Mã", "Tên danh mục"}, 0);

    private final JTable table =
            UI.table(new JTable(model));

    private final DanhMucBUS bus =
            new DanhMucBUS();

    private int selectedId = 0;

    public DanhMucPanel() {

        setBackground(UI.PINK_PALE);

        setBorder(
                BorderFactory.createEmptyBorder(
                        16, 18, 16, 18));

        setLayout(
                new BorderLayout(12, 12));

        JPanel form = UI.card();

        form.setLayout(
                new FlowLayout(
                        FlowLayout.LEFT, 10, 8));

        form.add(UI.label("Tên danh mục"));
        form.add(txtName);

        JButton add =
                UI.button("＋ Thêm");

        JButton edit =
                UI.button("✎ Sửa");

        JButton delete =
                UI.button("🗑 Xóa");

        JButton clear =
                UI.button("↻ Làm mới");

        add.addActionListener(
                e -> insert());

        edit.addActionListener(
                e -> update());

        delete.addActionListener(
                e -> delete());

        clear.addActionListener(
                e -> clearForm());

        form.add(add);
        form.add(edit);
        form.add(delete);
        form.add(clear);

        add(form, BorderLayout.NORTH);

        table.getSelectionModel()
                .addListSelectionListener(e -> {

                    if (!e.getValueIsAdjusting()
                            && table.getSelectedRow() >= 0) {

                        int row =
                                table.getSelectedRow();

                        selectedId =
                                (int)
                                        model.getValueAt(
                                                row, 0);

                        txtName.setText(
                                String.valueOf(
                                        model.getValueAt(
                                                row, 1)));
                    }
                });

        add(new JScrollPane(table),
                BorderLayout.CENTER);

        loadData();
    }

    private void loadData() {

        try {

            model.setRowCount(0);

            for (DanhMuc dm :
                    bus.findAll()) {

                model.addRow(new Object[]{
                        dm.getMaDm(),
                        dm.getTenDm()
                });
            }

        } catch (Exception e) {

            MessageUtil.error(
                    this, e.getMessage());
        }
    }

    private void insert() {

        try {

            bus.insert(
                    txtName.getText().trim());

            MessageUtil.info(
                    this,
                    "Thêm danh mục thành công.");

            clearForm();
            loadData();

        } catch (Exception e) {

            MessageUtil.error(
                    this, e.getMessage());
        }
    }

    private void update() {

        try {

            if (selectedId == 0) {
                MessageUtil.error(
                        this,
                        "Hãy chọn danh mục.");
                return;
            }

            bus.update(
                    new DanhMuc(
                            selectedId,
                            txtName.getText().trim()));

            MessageUtil.info(
                    this,
                    "Cập nhật danh mục thành công.");

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
                        "Hãy chọn danh mục.");
                return;
            }

            if (MessageUtil.confirm(
                    this,
                    "Bạn có chắc muốn xóa danh mục?")) {

                bus.delete(selectedId);

                clearForm();
                loadData();
            }

        } catch (Exception e) {

            MessageUtil.error(
                    this,
                    "Không thể xóa danh mục. "
                            + "Có thể danh mục đang được sản phẩm sử dụng.");
        }
    }

    private void clearForm() {

        selectedId = 0;

        txtName.setText("");

        table.clearSelection();
    }
}
