package toDoOrganizer.gui;

import toDoOrganizer.data.Data;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Calendar;

public class CalendarView extends JPanel {
    private Data data = Data.getInstance();
    private DefaultListModel<JLabel> daysLabelListModel;
    private DefaultListModel<JLabel> daysWithTodosLabelListModel;
    private int startDay;
    private int daysInMonth;
    private int activeMonth;
    private int activeYear;

    public CalendarView(int month, int year) {
        daysLabelListModel = new DefaultListModel<>();
        daysWithTodosLabelListModel = new DefaultListModel<>();
        activeMonth = month;
        activeYear = year;

        //init Layout
        setLayout(new GridLayout(0, 7)); // 7 Spalten für Wochentage

        inithead();
        getMonthData();
        initCalendarDays();
    }

    private void inithead() {
        String[] days = {"So", "Mo", "Di", "Mi", "Do", "Fr", "Sa"};
        for (String day : days) {
            JLabel daysLabel = new JLabel(day, SwingConstants.CENTER);
            daysLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            add(daysLabel);
        }
    }

    private void getMonthData() {
        LocalDate firstDay = LocalDate.of(activeYear, activeMonth, 1);
        startDay = firstDay.getDayOfWeek().getValue() - 1; //0 = So, 1 = Mo, ... (without -1 So would be 1)
        daysInMonth = YearMonth.of(activeYear, activeMonth).lengthOfMonth();
    }

    private void initCalendarDays() {
        addEmptyLabels();
        //init days on calendar
        for (int day = 1; day <= daysInMonth; day++) {
            JLabel dayLabel = new JLabel();
            setDesign(dayLabel, day);
            checkTodos(dayLabel, day);
            daysLabelListModel.addElement(dayLabel);
            add(dayLabel);
        }
    }

    private void addEmptyLabels() {
        for (int i = 0; i < startDay; i++) {
            JLabel emptyLabel = new JLabel("");
//            daysLabelListModel.addElement(emptyLabel);
            add(emptyLabel); //empty Labels before day 1
        }
    }

    private void setDesign(JLabel dayLabel, int day) {
        dayLabel.setVerticalAlignment(SwingConstants.TOP);
        dayLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dayLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        dayLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        //color for today
        if (LocalDate.now().equals(LocalDate.of(activeYear, activeMonth, day))) {
            dayLabel.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
        }
    }

    private void checkTodos(JLabel dayLabel, int day) {
        int numberTodos = data.countToDos(day, activeMonth, activeYear);
        dayLabel.setText("<html>" + String.valueOf(day) + "<br><br>(" + numberTodos + " Todos)<html>" );
        //color if day have Todos
        if (numberTodos > 0) {
            dayLabel.setOpaque(true);
            dayLabel.setBackground(new Color(173, 216, 230));
            if (!daysWithTodosLabelListModel.contains(dayLabel)) {
                daysWithTodosLabelListModel.addElement(dayLabel);
            }
        }

    }

    public void refreshView() {
        for (int i = 0; i < daysLabelListModel.getSize(); i++) {
            int day = i + 1;
            JLabel dayLabel = daysLabelListModel.getElementAt(i);
            checkTodos(dayLabel, day);
        }
    }


    public DefaultListModel<JLabel> getDaysLabelListModel() {
        return daysLabelListModel;
    }

    public DefaultListModel<JLabel> getDaysWithTodosLabelListModel() {
        return daysWithTodosLabelListModel;
    }

    public int getActiveMonth() {
        return activeMonth;
    }

    public int getActiveYear() {
        return activeYear;
    }

    public void setActiveMonth(int month) {
        activeMonth = month;
    }

    public void setActiveYear(int year) {
        activeYear = year;
    }
}
