package toDoOrganizer.controller;

import toDoOrganizer.data.Data;
import toDoOrganizer.gui.CalendarView;
import toDoOrganizer.gui.DayLabel;
import toDoOrganizer.gui.MainView;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

public class CalendarController {
    Data data = Data.getInstance();
    private CalendarView calendarView;
    private DefaultListModel<DayLabel> oldDaysWithTodosLabelListModel = new DefaultListModel<>();
    private Map<DayLabel, MouseListener> labelMouseListenerMap = new IdentityHashMap<>();

    public CalendarController(CalendarView view) {
        calendarView = view;
        addListeners();
    }

    private void addListeners() {
        DefaultListModel<DayLabel> labelListCopy = calendarView.getDaysWithTodosLabelListModel();
        DefaultListModel<DayLabel> oldAndNewLabels = getOldAndNewLabels(labelListCopy, oldDaysWithTodosLabelListModel);
        DefaultListModel<DayLabel> removedLabels = getReferenceDifference(labelListCopy, oldDaysWithTodosLabelListModel);
        for (int i = 0; i < oldAndNewLabels.getSize(); i++) { //Old and new to compare if something is new or some labelWithTodos is missing
            DayLabel dayLabel = oldAndNewLabels.getElementAt(i);
            if (!oldDaysWithTodosLabelListModel.contains(dayLabel)) {
                MouseListener mouseListener = initListener(dayLabel);
                dayLabel.addMouseListener(mouseListener);
                labelMouseListenerMap.put(dayLabel, mouseListener);
            }
            else if (removedLabels.contains(dayLabel)) {
                MouseListener mouseListener = labelMouseListenerMap.get(dayLabel);
                if (mouseListener != null) {
                    dayLabel.removeMouseListener(mouseListener);
                    labelMouseListenerMap.remove(dayLabel);
                }
            }
        }

        //need a real copy of DaysWithTodosLabelListModel, otherwise I would compare two references of the same object, what doesnt work
        //so labelListCopy is not necessary but shorter, but a new List for oldDays with single addition of every element is necessary
        oldDaysWithTodosLabelListModel = new DefaultListModel<>();
        for (int i = 0; i < labelListCopy.getSize(); i++) {
            oldDaysWithTodosLabelListModel.addElement(labelListCopy.getElementAt(i));
        }
    }

    private MouseListener initListener(DayLabel dayLabel) {
        MouseListener listener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //date attribute from new class DayLabel
                MainView.getTodayView().setActiveDate(dayLabel.getDate());
                MainView.getTodayController().refreshData();
                MainController.switchPanel("Today");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                dayLabel.setBackground(new Color(53, 83, 184));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                dayLabel.setBackground(new Color(173, 216, 230));
            }
        };
        return listener;
    }

    private DefaultListModel<DayLabel> getOldAndNewLabels(DefaultListModel<DayLabel> labelListCopy, DefaultListModel<DayLabel> oldList) {
        DefaultListModel<DayLabel> allLabels = new DefaultListModel<>();

        for (int i = 0; i < labelListCopy.getSize(); i++) {
            DayLabel label = labelListCopy.getElementAt(i);
            if (!allLabels.contains(label)) {
                allLabels.addElement(label);
            }
        }

        for (int i = 0; i < oldList.getSize(); i++) {
            DayLabel label = oldList.getElementAt(i);
            if (!allLabels.contains(label)) {
                allLabels.addElement(label);
            }
        }

        return allLabels;
    }

    public DefaultListModel<DayLabel> getReferenceDifference(DefaultListModel<DayLabel> labelListCopy, DefaultListModel<DayLabel> oldList) {
        //only references that are in the oldLabelList and not present in current DaysWith todos
        DefaultListModel<DayLabel> removedLabels = new DefaultListModel<>();
        for (int i = 0; i < oldList.getSize(); i++) {
            DayLabel label = oldList.getElementAt(i);
            if (!labelListCopy.contains(label)) {
                removedLabels.addElement(label);
            }
        }
        return removedLabels;
    }

    public void refreshData() {
        //leave month and year as it was
        calendarView.refreshView();
        addListeners();
        calendarView.revalidate();
        calendarView.repaint();

    }

}
