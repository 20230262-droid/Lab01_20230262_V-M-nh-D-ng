package vn.edu.eaut.lab5.util;

import javax.swing.*;
import java.awt.*;

public class MessageUtil {

    public static void info(Component c, String message) {
        JOptionPane.showMessageDialog(
                c, message, "MiniShop",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void error(Component c, String message) {
        JOptionPane.showMessageDialog(
                c, message, "Thông báo",
                JOptionPane.ERROR_MESSAGE);
    }

    public static boolean confirm(Component c, String message) {
        return JOptionPane.showConfirmDialog(
                c, message, "Xác nhận",
                JOptionPane.YES_NO_OPTION)
                == JOptionPane.YES_OPTION;
    }
}
