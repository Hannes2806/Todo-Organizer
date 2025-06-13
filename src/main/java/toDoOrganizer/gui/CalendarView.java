package toDoOrganizer.gui;

import toDoOrganizer.data.Data;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Calendar;

public class CalendarView extends JPanel {
    private Data data = Data.getInstance();

    public CalendarView(int month, int year) {
        //init Layout
        setLayout(new GridLayout(0, 7)); // 7 Spalten für Wochentage

        //init head
        String[] days = {"So", "Mo", "Di", "Mi", "Do", "Fr", "Sa"};
        for (String day : days) {
            JLabel daysLabel = new JLabel(day, SwingConstants.CENTER);
            daysLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            add(daysLabel);
        }

        //get data
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        //0 = So, 1 = Mo, ... (without -1 So would be 1)
        int startDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
        int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 0; i < startDay; i++) {
            add(new JLabel("")); //empty Labels before day 1
        }

        //init days on calendar
        for (int day = 1; day <= daysInMonth; day++) {
            int numberTodos = data.countToDos(day, month, year);
            JLabel dayLabel = new JLabel("<html>" + String.valueOf(day) + "<br><br>(" + numberTodos + " Todos)<html>" , SwingConstants.CENTER);
            dayLabel.setVerticalAlignment(SwingConstants.TOP);
            dayLabel.setHorizontalAlignment(SwingConstants.CENTER);
            dayLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            dayLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            //color for today
            if (LocalDate.now().equals(LocalDate.of(year, month, day))) {
                dayLabel.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            }
            //color if day have Todos
            if (numberTodos > 0) {
                dayLabel.setOpaque(true);
                dayLabel.setBackground(new Color(173, 216, 230));
            }
            add(dayLabel);
        }
    }
}
