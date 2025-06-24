package toDoOrganizer.gui;

import javax.swing.*;
import java.awt.*;

public class SettingsView extends JPanel {
    private JButton clearTodosButton;

    public SettingsView() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel heading = new JLabel("Settings");
        heading.setFont(new Font("Arial", Font.BOLD, 24));
        heading.setAlignmentX(Component.CENTER_ALIGNMENT);
        heading.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        add(heading);

        clearTodosButton = new JButton("Delete Todo-List");
        clearTodosButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(clearTodosButton);
        JLabel warningLabel = new JLabel("(Warning: deletes all data irretrievably!)");
        warningLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        warningLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(warningLabel);
    }

    public JButton getClearTodosButton() {
        return clearTodosButton;
    }
}
