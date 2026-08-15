package vn.edu.eaut.lab5.ui;

import vn.edu.eaut.lab5.model.TaiKhoan;
import vn.edu.eaut.lab5.util.UI;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    // =========================================================
    // TÀI KHOẢN ĐĂNG NHẬP
    // =========================================================

    private TaiKhoan taiKhoan;


    // =========================================================
    // CONTENT
    // =========================================================

    private final JPanel contentPanel;

    private final CardLayout cardLayout;


    // =========================================================
    // MÀU SIDEBAR
    // =========================================================

    private final Color SIDEBAR =
            new Color(
                    242,
                    105,
                    126
            );


    // =========================================================
    // CONSTRUCTOR KHÔNG THAM SỐ
    // =========================================================

    public MainFrame() {

        this(null);
    }


    // =========================================================
    // CONSTRUCTOR CÓ TÀI KHOẢN
    // =========================================================

    public MainFrame(
            TaiKhoan taiKhoan
    ) {

        this.taiKhoan = taiKhoan;


        // =====================================================
        // FRAME
        // =====================================================

        setTitle(
                "MiniShop - Quản lý cửa hàng"
        );


        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );


        setSize(
                1400,
                850
        );


        setMinimumSize(
                new Dimension(
                        1100,
                        700
                )
        );


        setLocationRelativeTo(
                null
        );


        setLayout(
                new BorderLayout()
        );


        // =====================================================
        // SIDEBAR
        // =====================================================

        add(
                createSidebar(),
                BorderLayout.WEST
        );


        // =====================================================
        // RIGHT AREA
        // =====================================================

        JPanel right =
                new JPanel(
                        new BorderLayout()
                );

        right.setBackground(
                UI.BACKGROUND
        );


        // =====================================================
        // HEADER
        // =====================================================

        right.add(
                createHeader(),
                BorderLayout.NORTH
        );


        // =====================================================
        // CARD LAYOUT
        // =====================================================

        cardLayout =
                new CardLayout();


        contentPanel =
                new JPanel(
                        cardLayout
                );


        contentPanel.setBackground(
                UI.BACKGROUND
        );


        // =====================================================
        // DASHBOARD
        // =====================================================

        contentPanel.add(
                new DashboardPanel(),
                "dashboard"
        );


        // =====================================================
        // SẢN PHẨM
        // =====================================================

        contentPanel.add(
                new SanPhamPanel(),
                "sanpham"
        );


        // =====================================================
        // DANH MỤC
        // =====================================================

        try {

            contentPanel.add(
                    new DanhMucPanel(),
                    "danhmuc"
            );

        } catch (Exception e) {

            // Nếu project chưa có DanhMucPanel
            // thì tạo màn hình thông báo

            JPanel panel =
                    createEmptyPanel(
                            "Danh mục"
                    );

            contentPanel.add(
                    panel,
                    "danhmuc"
            );
        }


        // =====================================================
        // KHÁCH HÀNG
        // =====================================================

        contentPanel.add(
                new KhachHangPanel(),
                "khachhang"
        );


        // =====================================================
        // HÓA ĐƠN
        // =====================================================

        contentPanel.add(
                new HoaDonPanel(),
                "hoadon"
        );


        // =====================================================
        // THỐNG KÊ
        // =====================================================

        contentPanel.add(
                new ThongKePanel(),
                "thongke"
        );


        // =====================================================
        // ADD CONTENT
        // =====================================================

        right.add(
                contentPanel,
                BorderLayout.CENTER
        );


        add(
                right,
                BorderLayout.CENTER
        );


        // =====================================================
        // HIỆN DASHBOARD MẶC ĐỊNH
        // =====================================================

        cardLayout.show(
                contentPanel,
                "dashboard"
        );
    }


    // =========================================================
    // SIDEBAR
    // =========================================================

    private JPanel createSidebar() {

        JPanel sidebar =
                new JPanel(
                        new BorderLayout()
                );


        sidebar.setBackground(
                SIDEBAR
        );


        sidebar.setPreferredSize(
                new Dimension(
                        225,
                        0
                )
        );


        // =====================================================
        // LOGO
        // =====================================================

        JPanel logo =
                new JPanel();


        logo.setOpaque(false);


        logo.setLayout(
                new BoxLayout(
                        logo,
                        BoxLayout.Y_AXIS
                )
        );


        logo.setBorder(
                BorderFactory.createEmptyBorder(
                        28,
                        25,
                        25,
                        20
                )
        );


        JLabel logoTitle =
                new JLabel(
                        "MINISHOP"
                );


        logoTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        25
                )
        );


        logoTitle.setForeground(
                Color.WHITE
        );


        JLabel logoSub =
                new JLabel(
                        "QUẢN LÝ CỬA HÀNG"
                );


        logoSub.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        10
                )
        );


        logoSub.setForeground(
                new Color(
                        255,
                        230,
                        235
                )
        );


        logo.add(
                logoTitle
        );


        logo.add(
                Box.createVerticalStrut(
                        3
                )
        );


        logo.add(
                logoSub
        );


        sidebar.add(
                logo,
                BorderLayout.NORTH
        );


        // =====================================================
        // MENU
        // =====================================================

        JPanel menu =
                new JPanel();


        menu.setOpaque(false);


        menu.setLayout(
                new BoxLayout(
                        menu,
                        BoxLayout.Y_AXIS
                )
        );


        menu.setBorder(
                BorderFactory.createEmptyBorder(
                        5,
                        12,
                        10,
                        12
                )
        );


        // Tổng quan

        addMenuButton(
                menu,
                "⌂",
                "Tổng quan",
                "dashboard"
        );


        // Sản phẩm

        addMenuButton(
                menu,
                "▣",
                "Sản phẩm",
                "sanpham"
        );


        // Danh mục

        addMenuButton(
                menu,
                "▤",
                "Danh mục",
                "danhmuc"
        );


        // Khách hàng

        addMenuButton(
                menu,
                "♙",
                "Khách hàng",
                "khachhang"
        );


        // Hóa đơn

        addMenuButton(
                menu,
                "▧",
                "Hóa đơn",
                "hoadon"
        );


        // Thống kê

        addMenuButton(
                menu,
                "▥",
                "Thống kê",
                "thongke"
        );


        sidebar.add(
                menu,
                BorderLayout.CENTER
        );


        // =====================================================
        // LOGOUT
        // =====================================================

        JPanel bottom =
                new JPanel();


        bottom.setOpaque(false);


        bottom.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        12,
                        20,
                        12
                )
        );


        JButton logout =
                createMenuButton(
                        "⇥",
                        "Đăng xuất"
                );


        logout.addActionListener(
                e -> logout()
        );


        bottom.add(
                logout
        );


        sidebar.add(
                bottom,
                BorderLayout.SOUTH
        );


        return sidebar;
    }


    // =========================================================
    // ADD MENU BUTTON
    // =========================================================

    private void addMenuButton(
            JPanel menu,
            String icon,
            String text,
            String card
    ) {

        JButton button =
                createMenuButton(
                        icon,
                        text
                );


        button.addActionListener(
                e -> showCard(card)
        );


        menu.add(
                button
        );


        menu.add(
                Box.createVerticalStrut(
                        5
                )
        );
    }


    // =========================================================
    // HIỆN CARD
    // =========================================================

    private void showCard(
            String card
    ) {

        try {

            cardLayout.show(
                    contentPanel,
                    card
            );

        } catch (Exception e) {

            showMessage(
                    "Lỗi",
                    "Không thể mở màn hình: "
                            + card
                            + "\n"
                            + e.getMessage()
            );
        }
    }


    // =========================================================
    // MENU BUTTON
    // =========================================================

    private JButton createMenuButton(
            String icon,
            String text
    ) {

        JButton button =
                new JButton();


        button.setLayout(
                new BorderLayout(
                        12,
                        0
                )
        );


        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        52
                )
        );


        button.setPreferredSize(
                new Dimension(
                        195,
                        52
                )
        );


        button.setBackground(
                SIDEBAR
        );


        button.setForeground(
                Color.WHITE
        );


        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );


        button.setFocusPainted(
                false
        );


        button.setBorderPainted(
                false
        );


        button.setContentAreaFilled(
                true
        );


        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


        JLabel iconLabel =
                new JLabel(
                        icon
                );


        iconLabel.setPreferredSize(
                new Dimension(
                        35,
                        35
                )
        );


        iconLabel.setHorizontalAlignment(
                SwingConstants.CENTER
        );


        iconLabel.setFont(
                new Font(
                        "Segoe UI Symbol",
                        Font.PLAIN,
                        19
                )
        );


        iconLabel.setForeground(
                Color.WHITE
        );


        JLabel textLabel =
                new JLabel(
                        text
                );


        textLabel.setForeground(
                Color.WHITE
        );


        textLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );


        button.add(
                iconLabel,
                BorderLayout.WEST
        );


        button.add(
                textLabel,
                BorderLayout.CENTER
        );


        return button;
    }


    // =========================================================
    // HEADER
    // =========================================================

    private JPanel createHeader() {

        JPanel header =
                new JPanel(
                        new BorderLayout(
                                20,
                                0
                        )
                );


        header.setBackground(
                Color.WHITE
        );


        header.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        25,
                        15,
                        25
                )
        );


        // =====================================================
        // SEARCH
        // =====================================================

        JPanel searchPanel =
                new JPanel(
                        new BorderLayout()
                );


        searchPanel.setBackground(
                UI.PINK_LIGHT
        );


        searchPanel.setPreferredSize(
                new Dimension(
                        390,
                        42
                )
        );


        JLabel searchIcon =
                new JLabel(
                        "⌕"
                );


        searchIcon.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20
                )
        );


        searchIcon.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        12,
                        0,
                        8
                )
        );


        JTextField search =
                new JTextField();


        search.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        5,
                        0,
                        10
                )
        );


        search.setOpaque(false);


        search.setFont(
                UI.FONT
        );


        search.setToolTipText(
                "Tìm kiếm"
        );


        searchPanel.add(
                searchIcon,
                BorderLayout.WEST
        );


        searchPanel.add(
                search,
                BorderLayout.CENTER
        );


        // =====================================================
        // RIGHT HEADER
        // =====================================================

        JPanel right =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                15,
                                0
                        )
                );


        right.setOpaque(false);


        JButton help =
                headerButton(
                        "?"
                );


        JButton setting =
                headerButton(
                        "⚙"
                );


        JButton notification =
                headerButton(
                        "♟"
                );


        JLabel avatar =
                new JLabel(
                        "👤"
                );


        avatar.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.PLAIN,
                        22
                )
        );


        // =====================================================
        // TÊN NGƯỜI ĐĂNG NHẬP
        // =====================================================

        String userName =
                "Quản trị viên";


        if (taiKhoan != null) {

            try {

                String hoTen =
                        taiKhoan.getHoTen();

                if (
                        hoTen != null
                                &&
                                !hoTen.trim().isEmpty()
                ) {

                    userName =
                            hoTen;
                }

            } catch (Exception ignored) {
            }
        }


        JLabel user =
                new JLabel(
                        userName + "  ▼"
                );


        user.setFont(
                UI.FONT_BOLD
        );


        user.setForeground(
                UI.TEXT
        );


        right.add(
                help
        );


        right.add(
                setting
        );


        right.add(
                notification
        );


        right.add(
                avatar
        );


        right.add(
                user
        );


        header.add(
                searchPanel,
                BorderLayout.WEST
        );


        header.add(
                right,
                BorderLayout.EAST
        );


        return header;
    }


    // =========================================================
    // HEADER BUTTON
    // =========================================================

    private JButton headerButton(
            String text
    ) {

        JButton button =
                new JButton(
                        text
                );


        button.setFont(
                new Font(
                        "Segoe UI Symbol",
                        Font.PLAIN,
                        18
                )
        );


        button.setForeground(
                UI.TEXT
        );


        button.setBackground(
                Color.WHITE
        );


        button.setBorderPainted(
                false
        );


        button.setFocusPainted(
                false
        );


        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


        return button;
    }


    // =========================================================
    // LOGOUT
    // =========================================================

    private void logout() {

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Bạn có chắc chắn muốn đăng xuất?",
                        "Đăng xuất",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );


        if (
                result ==
                        JOptionPane.YES_OPTION
        ) {

            dispose();


            // Quay lại màn hình đăng nhập

            SwingUtilities.invokeLater(
                    () -> {

                        LoginFrame loginFrame =
                                new LoginFrame();

                        loginFrame.setVisible(
                                true
                        );
                    }
            );
        }
    }


    // =========================================================
    // MESSAGE
    // =========================================================

    private void showMessage(
            String title,
            String message
    ) {

        JOptionPane.showMessageDialog(
                this,
                message,
                title,
                JOptionPane.INFORMATION_MESSAGE
        );
    }


    // =========================================================
    // PANEL TRỐNG
    // =========================================================

    private JPanel createEmptyPanel(
            String title
    ) {

        JPanel panel =
                new JPanel(
                        new GridBagLayout()
                );


        panel.setBackground(
                UI.BACKGROUND
        );


        JLabel label =
                new JLabel(
                        title
                                + " chưa được cấu hình"
                );


        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20
                )
        );


        label.setForeground(
                UI.DARK_PINK
        );


        panel.add(
                label
        );


        return panel;
    }


    // =========================================================
    // GET TÀI KHOẢN
    // =========================================================

    public TaiKhoan getTaiKhoan() {

        return taiKhoan;
    }


    // =========================================================
    // MAIN TEST
    // =========================================================

    public static void main(
            String[] args
    ) {

        UI.theme();


        SwingUtilities.invokeLater(
                () -> {

                    MainFrame frame =
                            new MainFrame();


                    frame.setVisible(
                            true
                    );
                }
        );
    }
}