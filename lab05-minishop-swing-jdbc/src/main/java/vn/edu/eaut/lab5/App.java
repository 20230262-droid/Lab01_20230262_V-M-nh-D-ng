package vn.edu.eaut.lab5;

import vn.edu.eaut.lab5.config.DBHelper;
import vn.edu.eaut.lab5.ui.MainFrame;
import vn.edu.eaut.lab5.util.UI;

import javax.swing.*;

public class App {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        UI.setupGlobalUI();

        DBHelper.testConnection();

        SwingUtilities.invokeLater(() -> {
            MainFrame frame =
                    new MainFrame();

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}