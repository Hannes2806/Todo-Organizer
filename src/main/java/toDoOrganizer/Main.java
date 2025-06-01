package toDoOrganizer;

import toDoOrganizer.controller.AppController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppController controller = new AppController();
            controller.startApp();
        });
    }
}
