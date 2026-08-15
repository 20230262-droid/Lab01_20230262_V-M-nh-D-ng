package vn.edu.eaut.lab5.util;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public final class UI {

    private UI() {
    }

    // =========================================================
    // COLORS - TONE HỒNG
    // =========================================================

    public static final Color PINK =
            new Color(242, 105, 126);

    public static final Color DARK_PINK =
            new Color(226, 82, 106);

    public static final Color PINK_DARK =
            DARK_PINK;

    public static final Color PINK_LIGHT =
            new Color(253, 228, 233);

    public static final Color LIGHT_PINK =
            PINK_LIGHT;

    public static final Color PINK_PALE =
            new Color(255, 239, 243);

    public static final Color PINK_VERY_LIGHT =
            new Color(255, 244, 246);

    public static final Color VERY_LIGHT_PINK =
            PINK_VERY_LIGHT;

    public static final Color PALE_PINK =
            PINK_PALE;

    public static final Color SOFT_PINK =
            PINK_LIGHT;

    public static final Color MAIN_PINK =
            PINK;

    public static final Color PRIMARY_PINK =
            PINK;

    public static final Color PRIMARY_COLOR =
            PINK;

    public static final Color SECONDARY_COLOR =
            PINK_LIGHT;


    // =========================================================
    // MÀU CƠ BẢN
    // =========================================================

    public static final Color WHITE =
            Color.WHITE;

    public static final Color BLACK =
            new Color(30, 30, 35);

    public static final Color GRAY =
            new Color(150, 150, 155);

    public static final Color LIGHT_GRAY =
            new Color(220, 220, 225);

    public static final Color DARK_GRAY =
            new Color(90, 90, 98);

    public static final Color BACKGROUND =
            new Color(247, 247, 249);

    public static final Color BACKGROUND_COLOR =
            BACKGROUND;

    public static final Color BG_COLOR =
            BACKGROUND;

    public static final Color TEXT =
            new Color(45, 45, 52);

    public static final Color TEXT_COLOR =
            TEXT;

    public static final Color TEXT_LIGHT =
            new Color(120, 120, 130);

    public static final Color SECONDARY_TEXT =
            TEXT_LIGHT;

    public static final Color BORDER =
            new Color(232, 232, 236);

    public static final Color BORDER_COLOR =
            BORDER;

    public static final Color BLUE =
            new Color(78, 140, 220);

    public static final Color GREEN =
            new Color(82, 175, 120);

    public static final Color SUCCESS =
            new Color(47, 174, 114);

    public static final Color YELLOW =
            new Color(245, 183, 76);

    public static final Color WARNING =
            new Color(245, 174, 65);

    public static final Color RED =
            new Color(220, 70, 85);

    public static final Color DANGER =
            RED;

    public static final Color PURPLE =
            new Color(155, 105, 190);

    public static final Color PALE =
            BACKGROUND;


    // =========================================================
    // FONT
    // =========================================================

    public static final Font FONT =
            new Font(
                    "Segoe UI",
                    Font.PLAIN,
                    14
            );

    public static final Font FONT_BOLD =
            new Font(
                    "Segoe UI",
                    Font.BOLD,
                    14
            );

    public static final Font FONT_SMALL =
            new Font(
                    "Segoe UI",
                    Font.PLAIN,
                    12
            );

    public static final Font FONT_TITLE =
            new Font(
                    "Segoe UI",
                    Font.BOLD,
                    24
            );

    public static final Font FONT_SUBTITLE =
            new Font(
                    "Segoe UI",
                    Font.BOLD,
                    18
            );

    public static final Font TITLE_FONT =
            FONT_TITLE;

    public static final Font SUBTITLE_FONT =
            FONT_SUBTITLE;

    public static final Font BOLD_FONT =
            FONT_BOLD;


    // =========================================================
    // THEME
    // =========================================================

    public static void theme() {

        try {

            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName()
            );

        } catch (Exception ignored) {
        }

        UIManager.put(
                "Label.font",
                FONT
        );

        UIManager.put(
                "Button.font",
                FONT_BOLD
        );

        UIManager.put(
                "TextField.font",
                FONT
        );

        UIManager.put(
                "PasswordField.font",
                FONT
        );

        UIManager.put(
                "TextArea.font",
                FONT
        );

        UIManager.put(
                "ComboBox.font",
                FONT
        );

        UIManager.put(
                "Table.font",
                FONT
        );

        UIManager.put(
                "TableHeader.font",
                FONT_BOLD
        );

        UIManager.put(
                "CheckBox.font",
                FONT
        );

        UIManager.put(
                "RadioButton.font",
                FONT
        );

        UIManager.put(
                "OptionPane.messageFont",
                FONT
        );

        UIManager.put(
                "OptionPane.buttonFont",
                FONT_BOLD
        );
    }


    public static void setupGlobalUI() {
        theme();
    }


    // =========================================================
    // LABEL
    // =========================================================

    public static JLabel label(String text) {

        JLabel label =
                new JLabel(text);

        label.setFont(FONT);
        label.setForeground(TEXT);

        return label;
    }


    public static JLabel boldLabel(String text) {

        JLabel label =
                new JLabel(text);

        label.setFont(FONT_BOLD);
        label.setForeground(TEXT);

        return label;
    }


    public static JLabel title(String text) {

        JLabel label =
                new JLabel(text);

        label.setFont(FONT_TITLE);
        label.setForeground(TEXT);

        return label;
    }


    public static JLabel subtitle(String text) {

        JLabel label =
                new JLabel(text);

        label.setFont(FONT_SUBTITLE);
        label.setForeground(TEXT);

        return label;
    }


    public static JLabel smallLabel(String text) {

        JLabel label =
                new JLabel(text);

        label.setFont(FONT_SMALL);
        label.setForeground(TEXT_LIGHT);

        return label;
    }


    // =========================================================
    // TEXT FIELD
    // =========================================================

    public static JTextField field() {

        JTextField field =
                new JTextField();

        field.setFont(FONT);
        field.setForeground(TEXT);
        field.setBackground(WHITE);
        field.setCaretColor(DARK_PINK);

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER,
                                1
                        ),
                        new EmptyBorder(
                                8,
                                12,
                                8,
                                12
                        )
                )
        );

        field.setPreferredSize(
                new Dimension(
                        180,
                        40
                )
        );

        return field;
    }


    public static JTextField textField() {
        return field();
    }


    public static JTextField searchField() {

        JTextField field =
                field();

        field.setPreferredSize(
                new Dimension(
                        300,
                        40
                )
        );

        return field;
    }


    // =========================================================
    // PASSWORD
    // =========================================================

    public static JPasswordField passwordField() {

        JPasswordField field =
                new JPasswordField();

        field.setFont(FONT);
        field.setForeground(TEXT);
        field.setBackground(WHITE);
        field.setCaretColor(DARK_PINK);

        field.setEchoChar('•');

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER,
                                1
                        ),
                        new EmptyBorder(
                                8,
                                12,
                                8,
                                12
                        )
                )
        );

        field.setPreferredSize(
                new Dimension(
                        180,
                        40
                )
        );

        return field;
    }


    // =========================================================
    // BUTTON HỒNG
    // =========================================================

    public static JButton pinkButton(
            String text
    ) {

        JButton button =
                new JButton(text);

        button.setFont(FONT_BOLD);
        button.setForeground(WHITE);
        button.setBackground(PINK);

        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.setBorder(
                new EmptyBorder(
                        10,
                        18,
                        10,
                        18
                )
        );

        return button;
    }


    public static JButton button(
            String text
    ) {

        return pinkButton(text);
    }


    // =========================================================
    // BUTTON PHỤ
    // =========================================================

    public static JButton secondaryButton(
            String text
    ) {

        JButton button =
                new JButton(text);

        button.setFont(FONT_BOLD);
        button.setForeground(DARK_PINK);
        button.setBackground(PINK_LIGHT);

        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.setBorder(
                new EmptyBorder(
                        10,
                        18,
                        10,
                        18
                )
        );

        return button;
    }


    public static JButton outlineButton(
            String text
    ) {

        JButton button =
                new JButton(text);

        button.setFont(FONT_BOLD);
        button.setForeground(DARK_PINK);
        button.setBackground(WHITE);

        button.setFocusPainted(false);
        button.setOpaque(true);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                PINK,
                                1
                        ),
                        new EmptyBorder(
                                9,
                                17,
                                9,
                                17
                        )
                )
        );

        return button;
    }


    public static JButton dangerButton(
            String text
    ) {

        JButton button =
                new JButton(text);

        button.setFont(FONT_BOLD);
        button.setForeground(WHITE);
        button.setBackground(DANGER);

        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.setBorder(
                new EmptyBorder(
                        10,
                        18,
                        10,
                        18
                )
        );

        return button;
    }


    // =========================================================
    // COMBO BOX
    // =========================================================

    public static <T> JComboBox<T> comboBox(
            T[] items
    ) {

        JComboBox<T> combo =
                new JComboBox<>(items);

        combo.setFont(FONT);
        combo.setForeground(TEXT);
        combo.setBackground(WHITE);

        combo.setPreferredSize(
                new Dimension(
                        180,
                        40
                )
        );

        return combo;
    }


    // =========================================================
    // CARD
    // =========================================================

    public static JPanel card() {

        RoundedPanel panel =
                new RoundedPanel(
                        WHITE,
                        18
                );

        panel.setBorder(
                new EmptyBorder(
                        18,
                        18,
                        18,
                        18
                )
        );

        return panel;
    }


    public static JPanel pinkCard() {

        RoundedPanel panel =
                new RoundedPanel(
                        PINK_LIGHT,
                        18
                );

        panel.setBorder(
                new EmptyBorder(
                        18,
                        18,
                        18,
                        18
                )
        );

        return panel;
    }


    public static JPanel primaryCard() {

        RoundedPanel panel =
                new RoundedPanel(
                        PINK,
                        20
                );

        panel.setBorder(
                new EmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        return panel;
    }


    // =========================================================
    // ROUNDED PANEL
    // =========================================================

    public static class RoundedPanel
            extends JPanel {

        private final Color color;
        private final int radius;

        public RoundedPanel(
                Color color,
                int radius
        ) {

            this.color = color;
            this.radius = radius;

            setOpaque(false);
        }


        @Override
        protected void paintComponent(
                Graphics g
        ) {

            Graphics2D g2 =
                    (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(color);

            g2.fill(
                    new RoundRectangle2D.Double(
                            0,
                            0,
                            getWidth(),
                            getHeight(),
                            radius,
                            radius
                    )
            );

            g2.dispose();

            super.paintComponent(g);
        }
    }


    // =========================================================
    // TABLE
    // =========================================================

    public static JTable table(
            JTable table
    ) {

        table.setFont(FONT);
        table.setForeground(TEXT);
        table.setBackground(WHITE);

        table.setRowHeight(42);

        table.setShowVerticalLines(false);
        table.setShowHorizontalLines(true);

        table.setGridColor(
                new Color(
                        242,
                        242,
                        244
                )
        );

        table.setSelectionBackground(
                PINK_LIGHT
        );

        table.setSelectionForeground(
                TEXT
        );

        JTableHeader header =
                table.getTableHeader();

        header.setFont(FONT_BOLD);
        header.setForeground(TEXT);
        header.setBackground(
                PINK_VERY_LIGHT
        );

        header.setPreferredSize(
                new Dimension(
                        0,
                        44
                )
        );

        header.setReorderingAllowed(false);

        return table;
    }


    // =========================================================
    // SCROLL
    // =========================================================

    public static JScrollPane scroll(
            Component component
    ) {

        JScrollPane scroll =
                new JScrollPane(component);

        styleScrollPane(scroll);

        return scroll;
    }


    public static void styleScrollPane(
            JScrollPane scroll
    ) {

        scroll.setBorder(
                BorderFactory.createEmptyBorder()
        );

        scroll.setBackground(WHITE);

        scroll.getViewport()
                .setBackground(WHITE);

        scroll.getVerticalScrollBar()
                .setUnitIncrement(16);
    }


    // =========================================================
    // TEXT AREA
    // =========================================================

    public static JTextArea textArea() {

        JTextArea area =
                new JTextArea();

        area.setFont(FONT);
        area.setForeground(TEXT);
        area.setBackground(WHITE);

        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        area.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        new EmptyBorder(
                                8,
                                10,
                                8,
                                10
                        )
                )
        );

        return area;
    }


    // =========================================================
    // CHECKBOX
    // =========================================================

    public static JCheckBox checkBox(
            String text
    ) {

        JCheckBox check =
                new JCheckBox(text);

        check.setFont(FONT);
        check.setForeground(TEXT);
        check.setBackground(WHITE);
        check.setFocusPainted(false);

        return check;
    }


    // =========================================================
    // RADIO
    // =========================================================

    public static JRadioButton radioButton(
            String text
    ) {

        JRadioButton radio =
                new JRadioButton(text);

        radio.setFont(FONT);
        radio.setForeground(TEXT);
        radio.setBackground(WHITE);
        radio.setFocusPainted(false);

        return radio;
    }


    // =========================================================
    // SPACE
    // =========================================================

    public static Component space(
            int width,
            int height
    ) {

        return Box.createRigidArea(
                new Dimension(
                        Math.max(0, width),
                        Math.max(0, height)
                )
        );
    }


    public static Component horizontalSpace(
            int width
    ) {

        return Box.createRigidArea(
                new Dimension(
                        Math.max(0, width),
                        0
                )
        );
    }


    public static Component verticalSpace(
            int height
    ) {

        return Box.createRigidArea(
                new Dimension(
                        0,
                        Math.max(0, height)
                )
        );
    }


    // =========================================================
    // SEPARATOR
    // =========================================================

    public static JSeparator separator() {

        JSeparator separator =
                new JSeparator();

        separator.setForeground(BORDER);

        return separator;
    }


    // =========================================================
    // PANEL
    // =========================================================

    public static JPanel backgroundPanel() {

        JPanel panel =
                new JPanel();

        panel.setBackground(BACKGROUND);

        return panel;
    }


    public static JPanel whitePanel() {

        JPanel panel =
                new JPanel();

        panel.setBackground(WHITE);

        return panel;
    }


    // =========================================================
    // APPLY FONT
    // =========================================================

    public static void applyFont(
            Component component
    ) {

        if (component == null) {
            return;
        }

        component.setFont(FONT);

        if (component instanceof Container) {

            Component[] children =
                    ((Container) component)
                            .getComponents();

            for (Component child : children) {
                applyFont(child);
            }
        }
    }
}