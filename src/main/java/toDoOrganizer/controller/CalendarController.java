package toDoOrganizer.controller;

import toDoOrganizer.data.Data;
import toDoOrganizer.gui.CalendarView;
import toDoOrganizer.gui.DayLabel;
import toDoOrganizer.gui.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CalendarController {
    Data data = Data.getInstance();
    private CalendarView calendarView;
    private DefaultListModel<DayLabel> oldDaysWithTodosLabelListModel = new DefaultListModel<>();

    public CalendarController(CalendarView view) {
        calendarView = view;
        addListeners();
    }

    private void addListeners() {
        for (int i = 0; i < calendarView.getDaysWithTodosLabelListModel().getSize(); i++) {
            DayLabel dayLabel = calendarView.getDaysWithTodosLabelListModel().getElementAt(i);
            if (!containsDayLabel(oldDaysWithTodosLabelListModel, dayLabel)) {
                addListener(dayLabel);
            } //only if it is a new day with Todos
        }
        oldDaysWithTodosLabelListModel = calendarView.getDaysWithTodosLabelListModel();
    }

    private void addListener(DayLabel dayLabel) {
        dayLabel.addMouseListener(new MouseAdapter() {
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
        });
    }

    private boolean containsDayLabel(DefaultListModel<DayLabel> oldDaysWithTodosLabelListModel, DayLabel dayLabel) {
        //
    }

    public void refreshData() {
        //leave month and year as it was
        calendarView.refreshView();
        addListeners();
        calendarView.revalidate();
        calendarView.repaint();
    }

}
