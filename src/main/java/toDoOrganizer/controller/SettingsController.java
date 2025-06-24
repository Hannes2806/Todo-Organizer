package toDoOrganizer.controller;

import toDoOrganizer.data.Data;
import toDoOrganizer.gui.SettingsView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsController {
    private Data data = Data.getInstance();
    private SettingsView settingsView;

    public SettingsController(SettingsView view) {
        settingsView = view;

        settingsView.getClearTodosButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                        settingsView,
                        "Are you sure you want to clear all data? This cannot be undone!",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    data.deleteToDoList();
                    MainController.refreshViews();
                }
            }
        });


    }
}
