package vn.edu.eaut.lab5.ui;

import vn.edu.eaut.lab5.bus.AuthBUS;
import vn.edu.eaut.lab5.model.TaiKhoan;
import vn.edu.eaut.lab5.util.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginFrame extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;

    private JButton btnLogin;
    private JButton btnExit;
    private JCheckBox chkShowPassword;

    private AuthBUS authBUS;

    public LoginFrame() {

        authBUS = new AuthBUS();

        initFrame();
        initComponents();
        setupEvents();
    }

    // =========================================================
    // CẤU HÌNH FRAME
    // =========================================================

    private void initFrame() {

        setTitle("MiniShop - Đăng nhập");

        setSize(
                900,
                560
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setResizable(false);

        setLayout(
                new BorderLayout()
        );

        getContentPane().setBackground(
                UI.VERY_LIGHT_PINK
        );
    }

    // =========================================================
    // TẠO GIAO DIỆN
    // =========================================================

    private void initComponents() {

        JPanel mainPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                2
                        )
                );

        mainPanel.setBackground(
                Color.WHITE
        );

        // =====================================================
        // PANEL BÊN TRÁI
        // =====================================================

        JPanel leftPanel =
                createLeftPanel();

        // =====================================================
        // PANEL BÊN PHẢI
        // =====================================================

        JPanel rightPanel =
                createLoginPanel();

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        add(
                mainPanel,
                BorderLayout.CENTER
        );
    }

    // =========================================================
    // PANEL TRÁI
    // =========================================================

    private JPanel createLeftPanel() {

        JPanel panel =
                new JPanel();

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        panel.setBackground(
                UI.DARK_PINK
        );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        70,
                        50,
                        70,
                        50
                )
        );

        // -----------------------------------------------------
        // ICON
        // -----------------------------------------------------

        JLabel icon =
                new JLabel("♥");

        icon.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        70
                )
        );

        icon.setForeground(
                Color.WHITE
        );

        icon.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // -----------------------------------------------------
        // TÊN SHOP
        // -----------------------------------------------------

        JLabel lblShop =
                new JLabel("MINISHOP");

        lblShop.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        32
                )
        );

        lblShop.setForeground(
                Color.WHITE
        );

        lblShop.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // -----------------------------------------------------
        // MÔ TẢ
        // -----------------------------------------------------

        JLabel lblWelcome =
                new JLabel(
                        "<html><div style='text-align:center;'>"
                                + "Hệ thống quản lý cửa hàng"
                                + "<br>"
                                + "MiniShop"
                                + "</div></html>"
                );

        lblWelcome.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        16
                )
        );

        lblWelcome.setForeground(
                new Color(
                        255,
                        240,
                        246
                )
        );

        lblWelcome.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // -----------------------------------------------------
        // ĐƯỜNG KẺ
        // -----------------------------------------------------

        JSeparator separator =
                new JSeparator();

        separator.setForeground(
                new Color(
                        255,
                        220,
                        232
                )
        );

        separator.setMaximumSize(
                new Dimension(
                        220,
                        1
                )
        );

        // -----------------------------------------------------
        // THÔNG TIN
        // -----------------------------------------------------

        JLabel lblInfo =
                new JLabel(
                        "<html><div style='text-align:center;'>"
                                + "Quản lý sản phẩm<br>"
                                + "Quản lý khách hàng<br>"
                                + "Quản lý hóa đơn<br>"
                                + "Thống kê doanh thu"
                                + "</div></html>"
                );

        lblInfo.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        lblInfo.setForeground(
                new Color(
                        255,
                        235,
                        242
                )
        );

        lblInfo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        panel.add(icon);

        panel.add(
                UI.space(
                        10,
                        10
                )
        );

        panel.add(lblShop);

        panel.add(
                UI.space(
                        10,
                        10
                )
        );

        panel.add(lblWelcome);

        panel.add(
                UI.space(
                        25,
                        25
                )
        );

        panel.add(separator);

        panel.add(
                UI.space(
                        25,
                        25
                )
        );

        panel.add(lblInfo);

        return panel;
    }

    // =========================================================
    // PANEL ĐĂNG NHẬP
    // =========================================================

    private JPanel createLoginPanel() {

        JPanel outerPanel =
                new JPanel(
                        new GridBagLayout()
                );

        outerPanel.setBackground(
                UI.VERY_LIGHT_PINK
        );

        JPanel panel =
                new JPanel();

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        panel.setBackground(
                Color.WHITE
        );

        panel.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                UI.PINK_PALE,
                                1
                        ),

                        BorderFactory.createEmptyBorder(
                                35,
                                45,
                                35,
                                45
                        )
                )
        );

        panel.setPreferredSize(
                new Dimension(
                        370,
                        410
                )
        );

        // -----------------------------------------------------
        // TIÊU ĐỀ
        // -----------------------------------------------------

        JLabel lblTitle =
                new JLabel(
                        "Đăng nhập"
                );

        lblTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        lblTitle.setForeground(
                UI.DARK_PINK
        );

        lblTitle.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // -----------------------------------------------------
        // SUBTITLE
        // -----------------------------------------------------

        JLabel lblSubtitle =
                new JLabel(
                        "Chào mừng bạn quay trở lại!"
                );

        lblSubtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        lblSubtitle.setForeground(
                UI.GRAY
        );

        lblSubtitle.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // -----------------------------------------------------
        // USERNAME
        // -----------------------------------------------------

        JLabel lblUsername =
                UI.boldLabel(
                        "Tên đăng nhập"
                );

        lblUsername.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        txtUsername =
                UI.field();

        txtUsername.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        42
                )
        );

        // -----------------------------------------------------
        // PASSWORD
        // -----------------------------------------------------

        JLabel lblPassword =
                UI.boldLabel(
                        "Mật khẩu"
                );

        lblPassword.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        txtPassword =
                UI.passwordField();

        txtPassword.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        42
                )
        );

        // -----------------------------------------------------
        // SHOW PASSWORD
        // -----------------------------------------------------

        chkShowPassword =
                new JCheckBox(
                        "Hiển thị mật khẩu"
                );

        chkShowPassword.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        chkShowPassword.setForeground(
                UI.GRAY
        );

        chkShowPassword.setBackground(
                Color.WHITE
        );

        chkShowPassword.setFocusPainted(
                false
        );

        chkShowPassword.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        // -----------------------------------------------------
        // BUTTON LOGIN
        // -----------------------------------------------------

        btnLogin =
                UI.pinkButton(
                        "ĐĂNG NHẬP"
                );

        btnLogin.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        btnLogin.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        45
                )
        );

        // -----------------------------------------------------
        // BUTTON EXIT
        // -----------------------------------------------------

        btnExit =
                UI.secondaryButton(
                        "THOÁT"
                );

        btnExit.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        btnExit.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        42
                )
        );

        // -----------------------------------------------------
        // FOOTER
        // -----------------------------------------------------

        JLabel lblFooter =
                new JLabel(
                        "MiniShop Management System"
                );

        lblFooter.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        11
                )
        );

        lblFooter.setForeground(
                new Color(
                        170,
                        160,
                        165
                )
        );

        lblFooter.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =====================================================
        // ADD COMPONENT
        // =====================================================

        panel.add(lblTitle);

        panel.add(
                UI.space(
                        5,
                        5
                )
        );

        panel.add(lblSubtitle);

        panel.add(
                UI.space(
                        25,
                        25
                )
        );

        panel.add(lblUsername);

        panel.add(
                UI.space(
                        5,
                        5
                )
        );

        panel.add(txtUsername);

        panel.add(
                UI.space(
                        15,
                        15
                )
        );

        panel.add(lblPassword);

        panel.add(
                UI.space(
                        5,
                        5
                )
        );

        panel.add(txtPassword);

        panel.add(
                UI.space(
                        5,
                        5
                )
        );

        panel.add(chkShowPassword);

        panel.add(
                UI.space(
                        20,
                        20
                )
        );

        panel.add(btnLogin);

        panel.add(
                UI.space(
                        10,
                        10
                )
        );

        panel.add(btnExit);

        panel.add(
                UI.space(
                        15,
                        15
                )
        );

        panel.add(lblFooter);

        outerPanel.add(panel);

        return outerPanel;
    }

    // =========================================================
    // EVENTS
    // =========================================================

    private void setupEvents() {

        // -----------------------------------------------------
        // ĐĂNG NHẬP
        // -----------------------------------------------------

        btnLogin.addActionListener(
                e -> login()
        );

        // -----------------------------------------------------
        // ENTER ĐỂ ĐĂNG NHẬP
        // -----------------------------------------------------

        txtUsername.addActionListener(
                e -> login()
        );

        txtPassword.addActionListener(
                e -> login()
        );

        // -----------------------------------------------------
        // THOÁT
        // -----------------------------------------------------

        btnExit.addActionListener(
                e -> {

                    int result =
                            JOptionPane.showConfirmDialog(
                                    this,
                                    "Bạn có chắc muốn thoát?",
                                    "Xác nhận",
                                    JOptionPane.YES_NO_OPTION
                            );

                    if (result ==
                            JOptionPane.YES_OPTION) {

                        System.exit(0);
                    }
                }
        );

        // -----------------------------------------------------
        // HIỆN / ẨN PASSWORD
        // -----------------------------------------------------

        chkShowPassword.addActionListener(
                e -> {

                    if (chkShowPassword.isSelected()) {

                        txtPassword.setEchoChar(
                                (char) 0
                        );

                    } else {

                        txtPassword.setEchoChar(
                                '•'
                        );
                    }
                }
        );
    }

    // =========================================================
    // XỬ LÝ LOGIN
    // =========================================================

    private void login() {

        String username =
                txtUsername.getText()
                        .trim();

        String password =
                new String(
                        txtPassword.getPassword()
                );

        // -----------------------------------------------------
        // KIỂM TRA RỖNG
        // -----------------------------------------------------

        if (username.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Vui lòng nhập tên đăng nhập!",
                    "Thiếu thông tin",
                    JOptionPane.WARNING_MESSAGE
            );

            txtUsername.requestFocus();

            return;
        }

        if (password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Vui lòng nhập mật khẩu!",
                    "Thiếu thông tin",
                    JOptionPane.WARNING_MESSAGE
            );

            txtPassword.requestFocus();

            return;
        }

        // -----------------------------------------------------
        // DISABLE BUTTON
        // -----------------------------------------------------

        btnLogin.setEnabled(false);

        btnLogin.setText(
                "ĐANG ĐĂNG NHẬP..."
        );

        // -----------------------------------------------------
        // GỌI BUS
        // -----------------------------------------------------

        SwingWorker<TaiKhoan, Void> worker =
                new SwingWorker<>() {

                    @Override
                    protected TaiKhoan doInBackground() {

                        try {

                            return authBUS.login(
                                    username,
                                    password
                            );

                        } catch (Exception e) {

                            e.printStackTrace();

                            return null;
                        }
                    }

                    @Override
                    protected void done() {

                        btnLogin.setEnabled(true);

                        btnLogin.setText(
                                "ĐĂNG NHẬP"
                        );

                        try {

                            TaiKhoan taiKhoan =
                                    get();

                            if (taiKhoan != null) {

                                JOptionPane.showMessageDialog(
                                        LoginFrame.this,

                                        "Đăng nhập thành công!\n"
                                                + "Xin chào "
                                                + taiKhoan.getHoTen()
                                                + "!",

                                        "Đăng nhập thành công",

                                        JOptionPane.INFORMATION_MESSAGE
                                );

                                // =================================
                                // MỞ MAIN FRAME
                                // =================================

                                dispose();

                                new MainFrame(
                                        taiKhoan
                                ).setVisible(true);

                            } else {

                                JOptionPane.showMessageDialog(
                                        LoginFrame.this,

                                        "Tên đăng nhập hoặc mật khẩu không đúng!",

                                        "Đăng nhập thất bại",

                                        JOptionPane.ERROR_MESSAGE
                                );

                                txtPassword.setText("");

                                txtPassword.requestFocus();
                            }

                        } catch (Exception e) {

                            JOptionPane.showMessageDialog(
                                    LoginFrame.this,

                                    "Không thể đăng nhập!\n\n"
                                            + e.getMessage(),

                                    "Lỗi",

                                    JOptionPane.ERROR_MESSAGE
                            );

                            e.printStackTrace();
                        }
                    }
                };

        worker.execute();
    }

    // =========================================================
    // MAIN TEST
    // =========================================================

    public static void main(String[] args) {

        UI.theme();

        SwingUtilities.invokeLater(
                () -> {

                    LoginFrame frame =
                            new LoginFrame();

                    frame.setVisible(true);
                }
        );
    }
}