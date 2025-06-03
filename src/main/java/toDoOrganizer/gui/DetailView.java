package toDoOrganizer.gui;

import toDoOrganizer.data.Data;

import javax.swing.*;
import java.awt.*;

public class DetailView extends JPanel {
    private Data data = Data.getInstance();
    private JTextArea detailsInfoText = new JTextArea("Weitere Informationen");
    private JButton editButton = new JButton("Edit ToDo");
    private JButton deleteButton = new JButton("Delete ToDo");
    private int activeIndex;
    private JPanel activeSite;

    public DetailView(JPanel site) {
        this.activeSite = site;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel detailsLabel = new JLabel("<html><u>Details:</u></html>");
        detailsLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(detailsLabel, gbc);
        gbc.gridy = 1;
        this.detailsInfoText.setEditable(false);
        this.detailsInfoText.setBackground(new Color(224, 239, 252));
        JScrollPane detailsScrollPane = new JScrollPane(this.detailsInfoText);
        detailsScrollPane.setPreferredSize(new Dimension(300, 200));
        add(detailsScrollPane, gbc);
        gbc.gridy = 2;

        this.editButton.setEnabled(false);
        add(this.editButton, gbc);
        gbc.insets = new Insets(10, 10, 137, 10);
        gbc.gridy = 3;
        this.deleteButton.setEnabled(false);
        add(this.deleteButton, gbc);
    }

    public JTextArea getDetailsInfoText() {
        return detailsInfoText;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public int getActiveIndex() {
        return activeIndex;
    }

    public void setActiveIndex(int activeIndex) {
        this.activeIndex = activeIndex;
    }

    public JPanel getActiveSite() {
        return activeSite;
    }
}
