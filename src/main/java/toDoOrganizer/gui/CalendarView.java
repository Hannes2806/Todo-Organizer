package toDoOrganizer.gui;

import toDoOrganizer.data.Data;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class CalendarView extends JPanel {
    private Data data = Data.getInstance();

    public CalendarView() {
        setLayout(new GridLayout(0, 7)); // 7 Spalten für Wochentage

        String[] days = {"So", "Mo", "Di", "Mi", "Do", "Fr", "Sa"};
        for (String day : days) {
            add(new JLabel(day, SwingConstants.CENTER));
        }

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        int startDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
        int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 0; i < startDay; i++) {
            add(new JLabel("")); // Leere Felder vor dem 1. Tag
        }

        for (int day = 1; day <= daysInMonth; day++) {
            JLabel label = new JLabel(String.valueOf(day), SwingConstants.CENTER);
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            add(label);
        }
    }
}
