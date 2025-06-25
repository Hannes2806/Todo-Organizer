package toDoOrganizer.controller;

import toDoOrganizer.data.Data;
import toDoOrganizer.data.ToDo;
import toDoOrganizer.gui.DetailView;
import toDoOrganizer.gui.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DetailController {
    private Data data = Data.getInstance();
    private DetailView detailView;

    public DetailController(DetailView view) {
        detailView = view;

        detailView.getEditButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainView.getNewTodoView().setActiveIndex(detailView.getActiveIndex());
                MainView.getNewTodoView().prefillData(detailView.getActiveIndex());
                MainController.switchPanel("NewTodo");
            }
        });

        detailView.getDeleteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                        detailView.getActiveSite(),
                        "Are you sure you want to delete: " + data.getToDoList().get(detailView.getActiveIndex()).toString() + "?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    data.deleteToDo(detailView.getActiveIndex());
                    MainController.refreshViews();
                }
            }
        });
    }

    public void showDetails(int index) {
        detailView.setActiveIndex(index);
        ToDo selected = data.getToDo(index);
        detailView.getDetailsInfoText().setText(
                "Title: " + selected.getTitle() + "\n" +
                        "Description: " + selected.getDescription() + "\n" +
                        "Expiry date: " + selected.getExpiryDate() + "\n" +
                        "Is urgent: " + (selected.getUrgent() ? "Yes" : "No") + "\n" +
                        "Is permanent: " + (selected.getPermanence() ? "Yes" : "No") + "\n" +
                        "Category: " + selected.getCategory()
        );
        detailView.getDeleteButton().setEnabled(true);
        detailView.getEditButton().setEnabled(true);
    }

}
