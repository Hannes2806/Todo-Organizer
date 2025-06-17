package toDoOrganizer.controller;

import toDoOrganizer.data.Data;
import toDoOrganizer.gui.CalendarView;
import toDoOrganizer.gui.TodayView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Collections;

public class CalendarController {
    Data data = Data.getInstance();
    private CalendarView calendarView;
    private DefaultListModel<JLabel> oldDaysWithTodosLabelListModel = new DefaultListModel<>();

    public CalendarController(CalendarView view) {
        calendarView = view;
        addListeners();
    }

    private void addListeners() {
        for (int i = 0; i < calendarView.getDaysWithTodosLabelListModel().getSize(); i++) {
            JLabel dayLabel = calendarView.getDaysWithTodosLabelListModel().getElementAt(i);
            if (!oldDaysWithTodosLabelListModel.contains(dayLabel)) {
                addListener(dayLabel);
            } //only if it is a new day with Todos
        }
        oldDaysWithTodosLabelListModel = calendarView.getDaysWithTodosLabelListModel();
    }

    private void addListener(JLabel dayLabel) {
        dayLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

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

    public void refreshData() {
        //leave month and year as it was
        calendarView.refreshView();
        addListeners();
        calendarView.revalidate();
        calendarView.repaint();
    }

}
