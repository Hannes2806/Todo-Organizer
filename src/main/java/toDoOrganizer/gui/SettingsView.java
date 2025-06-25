package toDoOrganizer.gui;

import toDoOrganizer.data.Data;

import javax.swing.*;
import java.awt.*;

public class SettingsView extends JPanel {
    Data data = Data.getInstance();
    private JButton clearTodosButton;

    public SettingsView() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel heading = new JLabel("Settings");
        heading.setFont(data.getHeadingFont());
        heading.setAlignmentX(Component.CENTER_ALIGNMENT);
        heading.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        add(heading);

        clearTodosButton = new JButton("Delete Todo-List");
        clearTodosButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        clearTodosButton.setFont(data.getTextFont());
        add(clearTodosButton);
        JLabel warningLabel = new JLabel("(Warning: deletes all data irretrievably!)");
        warningLabel.setFont(data.getTextFont());
        warningLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        warningLabel.setForeground(Color.RED);
        add(warningLabel);
    }

    public JButton getClearTodosButton() {
        return clearTodosButton;
    }
}
