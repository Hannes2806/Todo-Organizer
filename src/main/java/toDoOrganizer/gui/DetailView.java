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

        initHeadline(gbc);
        initInfoText(gbc); //To show Todo-details
        initButtons(gbc); //Edit and Delete
    }

    private void initHeadline(GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel detailsLabel = new JLabel("<html>Details:</html>");
        detailsLabel.setFont(data.getHeadingFont());
        add(detailsLabel, gbc);
    }

    private void initInfoText(GridBagConstraints gbc) {
        gbc.gridy = 1;
        this.detailsInfoText.setEditable(false);
        this.detailsInfoText.setBackground(new Color(224, 239, 252));
        this.detailsInfoText.setFont(data.getTextFont());
        JScrollPane detailsScrollPane = new JScrollPane(this.detailsInfoText);
        detailsScrollPane.setPreferredSize(new Dimension(300, 200));
        add(detailsScrollPane, gbc);
    }

    private void initButtons(GridBagConstraints gbc) {
        gbc.gridy = 2;
        this.editButton.setEnabled(false);
        this.editButton.setFont(data.getTextFont());
        add(this.editButton, gbc);
        gbc.insets = new Insets(10, 10, 137, 10);
        gbc.gridy = 3;
        this.deleteButton.setEnabled(false);
        this.deleteButton.setFont(data.getTextFont());
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
