package vn.edu.eaut.lab5.ui;

import vn.edu.eaut.lab5.bus.ThongKeBUS;
import vn.edu.eaut.lab5.util.MessageUtil;
import vn.edu.eaut.lab5.util.UI;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ThongKePanel extends JPanel {

    private final JTextField txtFrom = UI.field();
    private final JTextField txtTo = UI.field();

    private final JLabel lblRevenue =
            new JLabel("0 VNĐ");

    private final JLabel lblCount =
            new JLabel("0");

    private final JLabel lblBest =
            new JLabel("Chưa có dữ liệu");

    private final JLabel lblHighest =
            new JLabel("0 VNĐ");

    private final ThongKeBUS bus =
            new ThongKeBUS();

    public ThongKePanel() {

        setBackground(UI.PINK_PALE);

        setBorder(
                BorderFactory.createEmptyBorder(
                        16, 18, 16, 18));

        setLayout(
                new BorderLayout(12, 12));

        JPanel filter = UI.card();

        filter.setLayout(
                new FlowLayout(
                        FlowLayout.LEFT));

        filter.add(
                UI.label("Từ (yyyy-MM-dd)"));

        filter.add(txtFrom);

        filter.add(
                UI.label("Đến"));

        filter.add(txtTo);

        JButton btn =
                UI.button("📊 Thống kê");

        btn.addActionListener(
                e -> loadStatistics());

        filter.add(btn);

        add(filter, BorderLayout.NORTH);

        JPanel cards =
                new JPanel(
                        new GridLayout(
                                2, 2, 12, 12));

        cards.setOpaque(false);

        cards.add(
                createCard(
                        "💰 DOANH THU",
                        lblRevenue));

        cards.add(
                createCard(
                        "🧾 SỐ HÓA ĐƠN",
                        lblCount));

        cards.add(
                createCard(
                        "🔥 SẢN PHẨM BÁN CHẠY",
                        lblBest));

        cards.add(
                createCard(
                        "🏆 HÓA ĐƠN CAO NHẤT",
                        lblHighest));

        add(cards, BorderLayout.CENTER);

        LocalDate now =
                LocalDate.now();

        txtFrom.setText(
                now.withDayOfMonth(1)
                        .toString());

        txtTo.setText(
                now.toString());
    }

    private JPanel createCard(
            String title,
            JLabel value) {

        JPanel card = UI.card();

        card.setLayout(
                new BorderLayout(
                        5, 5));

        JLabel titleLabel =
                UI.label(title);

        titleLabel.setForeground(
                UI.PINK);

        value.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD, 22));

        value.setForeground(
                UI.TEXT);

        card.add(
                titleLabel,
                BorderLayout.NORTH);

        card.add(
                value,
                BorderLayout.CENTER);

        return card;
    }

    private void loadStatistics() {

        SwingWorker<Object[], Void> worker =
                new SwingWorker<>() {

                    @Override
                    protected Object[] doInBackground()
                            throws Exception {

                        LocalDate from =
                                LocalDate.parse(
                                        txtFrom.getText()
                                                .trim());

                        LocalDate to =
                                LocalDate.parse(
                                        txtTo.getText()
                                                .trim());

                        BigDecimal revenue =
                                bus.getRevenue(
                                        from, to);

                        int count =
                                bus.getInvoiceCount(
                                        from, to);

                        String best =
                                bus.getBestProduct();

                        BigDecimal highest =
                                bus.getHighestInvoice();

                        return new Object[]{
                                revenue,
                                count,
                                best,
                                highest
                        };
                    }

                    @Override
                    protected void done() {

                        try {

                            Object[] data =
                                    get();

                            lblRevenue.setText(
                                    String.format(
                                            "%,.0f VNĐ",
                                            (BigDecimal)
                                                    data[0]));

                            lblCount.setText(
                                    String.valueOf(
                                            data[1]));

                            lblBest.setText(
                                    String.valueOf(
                                            data[2]));

                            lblHighest.setText(
                                    String.format(
                                            "%,.0f VNĐ",
                                            (BigDecimal)
                                                    data[3]));

                        } catch (Exception e) {

                            MessageUtil.error(
                                    ThongKePanel.this,
                                    e.getMessage());
                        }
                    }
                };

        worker.execute();
    }
}
