package toDoOrganizer.gui;

import toDoOrganizer.controller.MainController;
import toDoOrganizer.data.Data;
import toDoOrganizer.data.ToDo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        //Add action listeners

        this.editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainView.getNewTodoView().setActiveIndex(activeIndex);
                MainView.getNewTodoView().prefillData(activeIndex);
                MainController.switchPanel("NewTodo");
            }
        });

        this.deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                        activeSite,
                        "Are you sure you want to delete: " + data.getToDoList().get(activeIndex).toString() + "?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    data.deleteToDo(activeIndex);
                    MainController.refreshViews();
                }
            }
        }
        );
    }

    public void showDetails(int index) {
        this.activeIndex = index;
        ToDo selected = data.getToDo(index);
        this.detailsInfoText.setText(
                "Title: " + selected.getTitle() + "\n" +
                        "Description: " + selected.getDescription() + "\n" +
                        "Expiry date: " + selected.getExpiryDate() + "\n" +
                        "Is urgent: " + (selected.getUrgent() ? "Yes" : "No") + "\n" +
                        "Is permanent: " + (selected.getPermanence() ? "Yes" : "No") + "\n" +
                        "Category: " + selected.getCategory()
        );
        this.deleteButton.setEnabled(true);
        this.editButton.setEnabled(true);
    }
}
