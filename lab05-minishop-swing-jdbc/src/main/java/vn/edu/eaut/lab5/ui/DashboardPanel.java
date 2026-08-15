package vn.edu.eaut.lab5.ui;

import vn.edu.eaut.lab5.config.DBHelper;
import vn.edu.eaut.lab5.util.UI;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DashboardPanel extends JPanel {

    // =========================================================
    // LABEL THỐNG KÊ
    // =========================================================

    private JLabel productValue;
    private JLabel categoryValue;
    private JLabel customerValue;
    private JLabel invoiceValue;
    private JLabel revenueValue;

    // =========================================================
    // DANH SÁCH
    // =========================================================

    private JPanel productList;
    private JPanel orderList;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public DashboardPanel() {

        setLayout(new BorderLayout(18, 18));

        setBackground(UI.BACKGROUND);

        setBorder(
                BorderFactory.createEmptyBorder(
                        25, 25, 25, 25
                )
        );

        // =====================================================
        // HEADER
        // =====================================================

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);

        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel title = UI.title("Tổng quan");

        JLabel subtitle = UI.smallLabel(
                "Chào mừng bạn quay trở lại! " +
                        "Quản lý MiniShop dễ dàng và hiệu quả."
        );

        titlePanel.add(title);

        titlePanel.add(
                Box.createVerticalStrut(5)
        );

        titlePanel.add(subtitle);

        header.add(
                titlePanel,
                BorderLayout.WEST
        );

        JButton refresh = UI.secondaryButton(
                "↻  Làm mới"
        );

        refresh.addActionListener(
                e -> loadData()
        );

        header.add(
                refresh,
                BorderLayout.EAST
        );

        // =====================================================
        // WELCOME
        // =====================================================

        JPanel welcome = new UI.RoundedPanel(
                UI.PINK,
                22
        );

        welcome.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 30, 25, 30
                )
        );

        welcome.setLayout(
                new BorderLayout()
        );

        JPanel welcomeText = new JPanel();

        welcomeText.setOpaque(false);

        welcomeText.setLayout(
                new BoxLayout(
                        welcomeText,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel welcomeTitle = new JLabel(
                "Chào mừng bạn trở lại! 👋"
        );

        welcomeTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        25
                )
        );

        welcomeTitle.setForeground(
                Color.WHITE
        );

        JLabel welcomeSub = new JLabel(
                "Theo dõi sản phẩm, khách hàng " +
                        "và hóa đơn của cửa hàng."
        );

        welcomeSub.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        welcomeSub.setForeground(
                new Color(255, 245, 247)
        );

        welcomeText.add(welcomeTitle);

        welcomeText.add(
                Box.createVerticalStrut(8)
        );

        welcomeText.add(welcomeSub);

        welcome.add(
                welcomeText,
                BorderLayout.WEST
        );

        // =====================================================
        // STAT CARDS
        // =====================================================

        JPanel stats = new JPanel(
                new GridLayout(
                        1,
                        5,
                        15,
                        0
                )
        );

        stats.setOpaque(false);

        // SẢN PHẨM
        JPanel productCard = createStatCard(
                "Sản phẩm",
                "📦",
                UI.PINK
        );

        productValue = getValueLabel(
                productCard
        );

        // DANH MỤC
        JPanel categoryCard = createStatCard(
                "Danh mục",
                "🗂",
                new Color(174, 112, 207)
        );

        categoryValue = getValueLabel(
                categoryCard
        );

        // KHÁCH HÀNG
        JPanel customerCard = createStatCard(
                "Khách hàng",
                "👥",
                new Color(79, 166, 180)
        );

        customerValue = getValueLabel(
                customerCard
        );

        // HÓA ĐƠN
        JPanel invoiceCard = createStatCard(
                "Hóa đơn",
                "🧾",
                new Color(245, 174, 65)
        );

        invoiceValue = getValueLabel(
                invoiceCard
        );

        // DOANH THU
        JPanel revenueCard = createStatCard(
                "Doanh thu",
                "₫",
                new Color(102, 165, 105)
        );

        revenueValue = getValueLabel(
                revenueCard
        );

        stats.add(productCard);
        stats.add(categoryCard);
        stats.add(customerCard);
        stats.add(invoiceCard);
        stats.add(revenueCard);

        // =====================================================
        // LOWER
        // =====================================================

        JPanel lower = new JPanel(
                new GridLayout(
                        1,
                        2,
                        18,
                        0
                )
        );

        lower.setOpaque(false);

        JPanel products = createProductsCard();

        JPanel orders = createOrdersCard();

        lower.add(products);
        lower.add(orders);

        // =====================================================
        // CONTENT
        // =====================================================

        JPanel content = new JPanel();

        content.setOpaque(false);

        content.setLayout(
                new BoxLayout(
                        content,
                        BoxLayout.Y_AXIS
                )
        );

        content.add(welcome);

        content.add(
                Box.createVerticalStrut(18)
        );

        content.add(stats);

        content.add(
                Box.createVerticalStrut(18)
        );

        content.add(lower);

        add(
                header,
                BorderLayout.NORTH
        );

        add(
                content,
                BorderLayout.CENTER
        );

        // =====================================================
        // LOAD
        // =====================================================

        SwingUtilities.invokeLater(
                this::loadData
        );
    }

    // =========================================================
    // STAT CARD
    // =========================================================

    private JPanel createStatCard(
            String title,
            String icon,
            Color accent
    ) {

        JPanel card = UI.card();

        card.setLayout(
                new BorderLayout(
                        10,
                        5
                )
        );

        JPanel iconPanel = new UI.RoundedPanel(
                new Color(253, 235, 239),
                14
        );

        iconPanel.setPreferredSize(
                new Dimension(55, 55)
        );

        iconPanel.setLayout(
                new BorderLayout()
        );

        JLabel iconLabel = new JLabel(icon);

        iconLabel.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        iconLabel.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.PLAIN,
                        22
                )
        );

        iconPanel.add(
                iconLabel,
                BorderLayout.CENTER
        );

        JPanel text = new JPanel();

        text.setOpaque(false);

        text.setLayout(
                new BoxLayout(
                        text,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel titleLabel = UI.smallLabel(title);

        JLabel value = new JLabel("0");

        value.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        22
                )
        );

        value.setForeground(accent);

        text.add(titleLabel);

        text.add(
                Box.createVerticalStrut(3)
        );

        text.add(value);

        card.add(
                iconPanel,
                BorderLayout.WEST
        );

        card.add(
                text,
                BorderLayout.CENTER
        );

        return card;
    }

    // =========================================================
    // LẤY LABEL
    // =========================================================

    private JLabel getValueLabel(
            JPanel card
    ) {

        for (Component component : card.getComponents()) {

            if (component instanceof JPanel panel) {

                for (Component child : panel.getComponents()) {

                    if (child instanceof JLabel label) {

                        String text = label.getText();

                        if ("0".equals(text)) {

                            return label;
                        }
                    }
                }
            }
        }

        return new JLabel("0");
    }

    // =========================================================
    // PRODUCTS CARD
    // =========================================================

    private JPanel createProductsCard() {

        JPanel card = UI.card();

        card.setLayout(
                new BorderLayout(
                        10,
                        15
                )
        );

        JLabel title = UI.title(
                "Sản phẩm nổi bật"
        );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        productList = new JPanel();

        productList.setOpaque(false);

        productList.setLayout(
                new BoxLayout(
                        productList,
                        BoxLayout.Y_AXIS
                )
        );

        JScrollPane scroll = new JScrollPane(
                productList
        );

        scroll.setBorder(
                BorderFactory.createEmptyBorder()
        );

        scroll.setOpaque(false);

        scroll.getViewport().setOpaque(false);

        card.add(
                title,
                BorderLayout.NORTH
        );

        card.add(
                scroll,
                BorderLayout.CENTER
        );

        return card;
    }

    // =========================================================
    // PRODUCT ROW
    // =========================================================

    private void addProductRow(
            String name,
            BigDecimal price,
            int stock
    ) {

        JPanel row = new JPanel(
                new BorderLayout(
                        12,
                        0
                )
        );

        row.setBackground(
                new Color(
                        255,
                        247,
                        249
                )
        );

        row.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                UI.PINK_LIGHT
                        ),
                        BorderFactory.createEmptyBorder(
                                10,
                                12,
                                10,
                                12
                        )
                )
        );

        JLabel icon = new JLabel("●");

        icon.setForeground(UI.PINK);

        JPanel center = new JPanel();

        center.setOpaque(false);

        center.setLayout(
                new BoxLayout(
                        center,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel product = UI.label(name);

        product.setFont(UI.FONT_BOLD);

        JLabel stockLabel = UI.smallLabel(
                "Tồn kho: " +
                        stock +
                        " sản phẩm"
        );

        center.add(product);

        center.add(
                Box.createVerticalStrut(3)
        );

        center.add(stockLabel);

        JLabel priceLabel = new JLabel(
                formatMoney(price)
        );

        priceLabel.setFont(UI.FONT_BOLD);

        priceLabel.setForeground(
                UI.PINK_DARK
        );

        row.add(
                icon,
                BorderLayout.WEST
        );

        row.add(
                center,
                BorderLayout.CENTER
        );

        row.add(
                priceLabel,
                BorderLayout.EAST
        );

        productList.add(row);

        productList.add(
                Box.createVerticalStrut(8)
        );
    }

    // =========================================================
    // ORDER CARD
    // =========================================================

    private JPanel createOrdersCard() {

        JPanel card = UI.card();

        card.setLayout(
                new BorderLayout(
                        10,
                        15
                )
        );

        JLabel title = UI.title(
                "Hóa đơn gần đây"
        );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        orderList = new JPanel();

        orderList.setOpaque(false);

        orderList.setLayout(
                new BoxLayout(
                        orderList,
                        BoxLayout.Y_AXIS
                )
        );

        JScrollPane scroll = new JScrollPane(
                orderList
        );

        scroll.setBorder(
                BorderFactory.createEmptyBorder()
        );

        scroll.setOpaque(false);

        scroll.getViewport().setOpaque(false);

        card.add(
                title,
                BorderLayout.NORTH
        );

        card.add(
                scroll,
                BorderLayout.CENTER
        );

        return card;
    }

    // =========================================================
    // ORDER ROW
    // =========================================================

    private void addOrderRow(
            int maHd,
            String customer,
            Date date,
            BigDecimal total
    ) {

        JPanel row = new JPanel(
                new BorderLayout(
                        10,
                        0
                )
        );

        row.setBackground(Color.WHITE);

        row.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(
                                0,
                                4,
                                0,
                                0,
                                UI.PINK
                        ),
                        BorderFactory.createEmptyBorder(
                                12,
                                12,
                                12,
                                12
                        )
                )
        );

        JPanel info = new JPanel();

        info.setOpaque(false);

        info.setLayout(
                new BoxLayout(
                        info,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel codeLabel = UI.label(
                "Hóa đơn #" + maHd
        );

        codeLabel.setFont(UI.FONT_BOLD);

        JLabel customerLabel = UI.smallLabel(
                customer
        );

        JLabel dateLabel = UI.smallLabel(
                "Ngày lập: " + date
        );

        info.add(codeLabel);

        info.add(
                Box.createVerticalStrut(3)
        );

        info.add(customerLabel);

        info.add(
                Box.createVerticalStrut(3)
        );

        info.add(dateLabel);

        JLabel totalLabel = new JLabel(
                formatMoney(total)
        );

        totalLabel.setFont(UI.FONT_BOLD);

        totalLabel.setForeground(
                UI.PINK_DARK
        );

        row.add(
                info,
                BorderLayout.CENTER
        );

        row.add(
                totalLabel,
                BorderLayout.EAST
        );

        orderList.add(row);

        orderList.add(
                Box.createVerticalStrut(8)
        );
    }

    // =========================================================
    // LOAD DATA
    // =========================================================

    private void loadData() {

        productValue.setText("...");
        categoryValue.setText("...");
        customerValue.setText("...");
        invoiceValue.setText("...");
        revenueValue.setText("...");

        productList.removeAll();
        orderList.removeAll();

        SwingWorker<DashboardData, Void> worker =
                new SwingWorker<>() {

                    @Override
                    protected DashboardData doInBackground() {

                        return readDatabase();
                    }

                    @Override
                    protected void done() {

                        try {

                            DashboardData data = get();

                            // =================================
                            // HIỂN THỊ THỐNG KÊ
                            // =================================

                            productValue.setText(
                                    String.valueOf(
                                            data.productCount
                                    )
                            );

                            categoryValue.setText(
                                    String.valueOf(
                                            data.categoryCount
                                    )
                            );

                            customerValue.setText(
                                    String.valueOf(
                                            data.customerCount
                                    )
                            );

                            invoiceValue.setText(
                                    String.valueOf(
                                            data.invoiceCount
                                    )
                            );

                            revenueValue.setText(
                                    formatMoney(
                                            data.revenue
                                    )
                            );

                            // =================================
                            // SẢN PHẨM
                            // =================================

                            productList.removeAll();

                            if (data.products.isEmpty()) {

                                addEmptyLabel(
                                        productList,
                                        "Chưa có sản phẩm."
                                );

                            } else {

                                for (
                                        ProductData p :
                                        data.products
                                ) {

                                    addProductRow(
                                            p.name,
                                            p.price,
                                            p.stock
                                    );
                                }
                            }

                            // =================================
                            // HÓA ĐƠN
                            // =================================

                            orderList.removeAll();

                            if (data.orders.isEmpty()) {

                                addEmptyLabel(
                                        orderList,
                                        "Chưa có hóa đơn."
                                );

                            } else {

                                for (
                                        OrderData o :
                                        data.orders
                                ) {

                                    addOrderRow(
                                            o.id,
                                            o.customer,
                                            o.date,
                                            o.total
                                    );
                                }
                            }

                            productList.revalidate();
                            productList.repaint();

                            orderList.revalidate();
                            orderList.repaint();

                        } catch (Exception e) {

                            e.printStackTrace();

                            JOptionPane.showMessageDialog(
                                    DashboardPanel.this,
                                    "Lỗi tải Dashboard:\n\n"
                                            + getErrorMessage(e),
                                    "Lỗi",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    }
                };

        worker.execute();
    }

    // =========================================================
    // ĐỌC DATABASE
    // =========================================================

    private DashboardData readDatabase() {

        DashboardData data =
                new DashboardData();

        Connection conn = null;

        try {

            conn = DBHelper.getConnection();

            if (conn == null) {

                throw new SQLException(
                        "DBHelper.getConnection() trả về NULL."
                );
            }

            System.out.println(
                    "======================================"
            );

            System.out.println(
                    "DASHBOARD: ĐÃ KẾT NỐI DATABASE"
            );

            System.out.println(
                    "Database: "
                            + conn.getCatalog()
            );

            System.out.println(
                    "======================================"
            );

            // =================================================
            // SẢN PHẨM
            // =================================================

            try {

                data.productCount =
                        count(
                                conn,
                                "san_pham"
                        );

            } catch (SQLException e) {

                System.err.println(
                        "Lỗi bảng san_pham: "
                                + e.getMessage()
                );

                data.productCount = 0;
            }

            // =================================================
            // DANH MỤC
            // =================================================

            try {

                data.categoryCount =
                        count(
                                conn,
                                "danh_muc"
                        );

            } catch (SQLException e) {

                System.err.println(
                        "Lỗi bảng danh_muc: "
                                + e.getMessage()
                );

                data.categoryCount = 0;
            }

            // =================================================
            // KHÁCH HÀNG
            // =================================================

            try {

                data.customerCount =
                        count(
                                conn,
                                "khach_hang"
                        );

            } catch (SQLException e) {

                System.err.println(
                        "Lỗi bảng khach_hang: "
                                + e.getMessage()
                );

                data.customerCount = 0;
            }

            // =================================================
            // HÓA ĐƠN
            // =================================================

            try {

                data.invoiceCount =
                        count(
                                conn,
                                "hoa_don"
                        );

            } catch (SQLException e) {

                System.err.println(
                        "Lỗi bảng hoa_don: "
                                + e.getMessage()
                );

                data.invoiceCount = 0;
            }

            // =================================================
            // DOANH THU
            // =================================================

            try {

                String sql =
                        "SELECT COALESCE("
                                + "SUM(tong_tien),0"
                                + ") "
                                + "FROM hoa_don";

                try (
                        PreparedStatement ps =
                                conn.prepareStatement(sql);

                        ResultSet rs =
                                ps.executeQuery()
                ) {

                    if (rs.next()) {

                        data.revenue =
                                rs.getBigDecimal(1);
                    }
                }

            } catch (SQLException e) {

                System.err.println(
                        "Lỗi doanh thu: "
                                + e.getMessage()
                );

                data.revenue =
                        BigDecimal.ZERO;
            }

            if (data.revenue == null) {

                data.revenue =
                        BigDecimal.ZERO;
            }

            // =================================================
            // SẢN PHẨM NỔI BẬT
            // =================================================

            try {

                String sql =
                        "SELECT ten_sp, don_gia, so_luong "
                                + "FROM san_pham "
                                + "ORDER BY so_luong DESC, ma_sp DESC "
                                + "LIMIT 5";

                try (
                        PreparedStatement ps =
                                conn.prepareStatement(sql);

                        ResultSet rs =
                                ps.executeQuery()
                ) {

                    while (rs.next()) {

                        ProductData p =
                                new ProductData();

                        p.name =
                                rs.getString(
                                        "ten_sp"
                                );

                        p.price =
                                rs.getBigDecimal(
                                        "don_gia"
                                );

                        p.stock =
                                rs.getInt(
                                        "so_luong"
                                );

                        data.products.add(p);
                    }
                }

            } catch (SQLException e) {

                System.err.println(
                        "Không tải được sản phẩm nổi bật: "
                                + e.getMessage()
                );
            }

            // =================================================
            // HÓA ĐƠN GẦN ĐÂY
            // =================================================

            try {

                String sql =
                        "SELECT hd.ma_hd, "
                                + "hd.ngay_lap, "
                                + "hd.tong_tien, "
                                + "kh.ten_kh "
                                + "FROM hoa_don hd "
                                + "LEFT JOIN khach_hang kh "
                                + "ON hd.ma_kh = kh.ma_kh "
                                + "ORDER BY hd.ma_hd DESC "
                                + "LIMIT 5";

                try (
                        PreparedStatement ps =
                                conn.prepareStatement(sql);

                        ResultSet rs =
                                ps.executeQuery()
                ) {

                    while (rs.next()) {

                        OrderData o =
                                new OrderData();

                        o.id =
                                rs.getInt(
                                        "ma_hd"
                                );

                        o.date =
                                rs.getDate(
                                        "ngay_lap"
                                );

                        o.total =
                                rs.getBigDecimal(
                                        "tong_tien"
                                );

                        o.customer =
                                rs.getString(
                                        "ten_kh"
                                );

                        if (
                                o.customer == null
                                        ||
                                        o.customer.trim().isEmpty()
                        ) {

                            o.customer =
                                    "Khách lẻ";
                        }

                        if (o.total == null) {

                            o.total =
                                    BigDecimal.ZERO;
                        }

                        data.orders.add(o);
                    }
                }

            } catch (SQLException e) {

                System.err.println(
                        "Không tải được hóa đơn gần đây: "
                                + e.getMessage()
                );
            }

        } catch (SQLException e) {

            System.err.println(
                    "======================================"
            );

            System.err.println(
                    "KHÔNG THỂ KẾT NỐI DATABASE"
            );

            System.err.println(
                    e.getMessage()
            );

            System.err.println(
                    "======================================"
            );

            e.printStackTrace();

        } finally {

            if (conn != null) {

                try {

                    conn.close();

                } catch (SQLException ignored) {
                }
            }
        }

        return data;
    }

    // =========================================================
    // COUNT
    // =========================================================

    private int count(
            Connection conn,
            String table
    ) throws SQLException {

        String[] allowed = {
                "san_pham",
                "danh_muc",
                "khach_hang",
                "hoa_don"
        };

        boolean valid = false;

        for (String item : allowed) {

            if (item.equals(table)) {

                valid = true;
                break;
            }
        }

        if (!valid) {

            throw new SQLException(
                    "Tên bảng không được phép: "
                            + table
            );
        }

        String sql =
                "SELECT COUNT(*) FROM `"
                        + table
                        + "`";

        try (
                PreparedStatement ps =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            if (rs.next()) {

                return rs.getInt(1);
            }
        }

        return 0;
    }

    // =========================================================
    // EMPTY
    // =========================================================

    private void addEmptyLabel(
            JPanel panel,
            String text
    ) {

        JLabel label =
                UI.smallLabel(text);

        label.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        label.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        10,
                        20,
                        10
                )
        );

        panel.add(label);
    }

    // =========================================================
    // FORMAT MONEY
    // =========================================================

    private String formatMoney(
            BigDecimal value
    ) {

        if (value == null) {

            return "0 ₫";
        }

        NumberFormat format =
                NumberFormat.getNumberInstance(
                        new Locale(
                                "vi",
                                "VN"
                        )
                );

        format.setMaximumFractionDigits(0);

        format.setMinimumFractionDigits(0);

        return format.format(value)
                + " ₫";
    }

    // =========================================================
    // ERROR
    // =========================================================

    private String getErrorMessage(
            Exception e
    ) {

        Throwable cause = e;

        while (
                cause.getCause() != null
        ) {

            cause = cause.getCause();
        }

        if (
                cause.getMessage() != null
        ) {

            return cause.getMessage();
        }

        return e.toString();
    }

    // =========================================================
    // DATA
    // =========================================================

    private static class DashboardData {

        int productCount;

        int categoryCount;

        int customerCount;

        int invoiceCount;

        BigDecimal revenue =
                BigDecimal.ZERO;

        List<ProductData> products =
                new ArrayList<>();

        List<OrderData> orders =
                new ArrayList<>();
    }

    private static class ProductData {

        String name;

        BigDecimal price;

        int stock;
    }

    private static class OrderData {

        int id;

        String customer;

        Date date;

        BigDecimal total;
    }
}