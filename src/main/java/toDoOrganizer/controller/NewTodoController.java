package toDoOrganizer.controller;

import toDoOrganizer.data.Data;
import toDoOrganizer.data.ToDo;
import toDoOrganizer.gui.MainView;
import toDoOrganizer.gui.NewTodoView;
import toDoOrganizer.gui.OverviewView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class NewTodoController {
    private Data data = Data.getInstance();

    public NewTodoController(NewTodoView newTodoView) {
        if (newTodoView.getActiveIndex() == -1) {
            newTodoView.getTitleTextField().addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    newTodoView.getToDoSaveButton().setEnabled(!newTodoView.getTitleTextField().getText().trim().isEmpty());
                }
            });
        }

        newTodoView.getPermanentCheckBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newTodoView.getWhenUrgentSpinner().setEnabled(!newTodoView.getPermanentCheckBox().isSelected());
                newTodoView.getExpirySpinner().setEnabled(!newTodoView.getPermanentCheckBox().isSelected());
                //If unselect permantentCheckBox and urgent still selected, do whenUrgent not functional
                if (newTodoView.getUrgentRadioButton().isSelected()) {
                    newTodoView.getWhenUrgentSpinner().setEnabled(false);
                }
            }
        });

        newTodoView.getUrgentRadioButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newTodoView.getWhenUrgentSpinner().setEnabled(!newTodoView.getUrgentRadioButton().isSelected());
            }
        });

        newTodoView.getNotUrgentRadioButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!newTodoView.getPermanentCheckBox().isSelected()) {
                    newTodoView.getWhenUrgentSpinner().setEnabled(newTodoView.getNotUrgentRadioButton().isSelected());
                }
            }
        });

        newTodoView.getToDoSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ToDo toDo = new ToDo(newTodoView.getTitleTextField().getText(), newTodoView.getDescriptionTextArea().getText(),
                        newTodoView.getPermanentCheckBox().isSelected(), newTodoView.getUrgentRadioButton().isSelected(),
                        newTodoView.getNotUrgentRadioButton().isSelected(), dateSpinnerToLocalDate(newTodoView.getWhenUrgentSpinner()),
                        dateSpinnerToLocalDate(newTodoView.getExpirySpinner()), LocalDate.now(),
                        (String) newTodoView.getCategoryBox().getSelectedItem());
                if (newTodoView.getActiveIndex() == -1) {
                    data.addToDo(toDo);
                }
                else {
                    data.updateToDo(toDo, newTodoView.getActiveIndex());
                }
                MainView.newTodoView.clearData();
                MainView.refreshViews();
                MainView.switchPanel("Overview");
            }
        });
    }

    private LocalDate dateSpinnerToLocalDate(JSpinner jSpinner) {
        Date date = (Date) jSpinner.getValue();
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
