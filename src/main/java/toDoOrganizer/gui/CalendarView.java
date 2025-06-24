package toDoOrganizer.gui;

import toDoOrganizer.data.Data;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.Calendar;

import static javax.swing.BoxLayout.Y_AXIS;

public class CalendarView extends JPanel {
    private Data data = Data.getInstance();
    private DefaultListModel<DayLabel> daysLabelListModel;
    private DefaultListModel<DayLabel> daysWithTodosLabelListModel;
    private JLabel monthLabel;
    private JPanel calendarPanel;
    private JButton monthBackButton;
    private JButton monthForthButton;
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
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        calendarPanel = new JPanel();
        calendarPanel.setLayout(new GridLayout(0, 7)); // 7 Spalten für Wochentage

        inithead();
        getMonthData();
        initCalendarDays();
        add(calendarPanel);
    }

    private void inithead() {
        //month
        JPanel monthPanel = new JPanel();
        monthPanel.setLayout(new FlowLayout());
        monthBackButton = new JButton("<<");
        monthForthButton = new JButton(">>");
        monthLabel = new JLabel();
        setText(monthLabel);
        monthLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        monthPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, monthPanel.getPreferredSize().height));
        monthPanel.add(monthBackButton);
        monthPanel.add(monthLabel);
        monthPanel.add(monthForthButton);
        add(monthPanel);
        //days
        addDays();

    }

    private void addDays() {
        String[] days = {"So", "Mo", "Di", "Mi", "Do", "Fr", "Sa"};
        for (String day : days) {
            JLabel daysLabel = new JLabel(day, SwingConstants.CENTER);
            daysLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            calendarPanel.add(daysLabel);
        }
    }

    private void setText(JLabel monthLabel) {
        monthLabel.setText("<html>" + Month.of(activeMonth).name() + " " + activeYear + "</html>");
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
            DayLabel dayLabel = new DayLabel(day, activeMonth, activeYear);
            setDesign(dayLabel, day);
            checkTodos(dayLabel, day);
            daysLabelListModel.addElement(dayLabel);
            calendarPanel.add(dayLabel);
        }
    }

    private void addEmptyLabels() {
        for (int i = 0; i < startDay; i++) {
            JLabel emptyLabel = new JLabel("");
//            daysLabelListModel.addElement(emptyLabel);
            calendarPanel.add(emptyLabel); //empty Labels before day 1
        }
    }

    private void setDesign(DayLabel dayLabel, int day) {
        dayLabel.setVerticalAlignment(SwingConstants.TOP);
        dayLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dayLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        dayLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        //color for today
        if (LocalDate.now().equals(LocalDate.of(activeYear, activeMonth, day))) {
            dayLabel.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
        }
    }

    private void checkTodos(DayLabel dayLabel, int day) {
        int numberTodos = data.countToDos(day, activeMonth, activeYear);
        dayLabel.setText("<html>" + String.valueOf(day) + "<br><br>(" + numberTodos + " Todos)<html>" );
        //color if day have Todos
        if (numberTodos > 0) {
            dayLabel.setOpaque(true);
            dayLabel.setBackground(new Color(173, 216, 230));
            if (!daysWithTodosLabelListModel.contains(dayLabel)) {
                daysWithTodosLabelListModel.addElement(dayLabel);
            }
        } //if day no todo anymore remove label from list
        else if (numberTodos == 0) {
            dayLabel.setOpaque(false);
            dayLabel.setBackground(new Color(255, 255, 255));
            if (daysWithTodosLabelListModel.contains(dayLabel)) {
                daysWithTodosLabelListModel.removeElement(dayLabel);
            }
        }

    }

    public void refreshView() {
        for (int i = 0; i < daysLabelListModel.getSize(); i++) {
            int day = i + 1;
            DayLabel dayLabel = daysLabelListModel.getElementAt(i);
            checkTodos(dayLabel, day);
        }
    }

    public void renewView() {
        setText(monthLabel);

        calendarPanel.removeAll();
        daysLabelListModel.clear();
        daysWithTodosLabelListModel.clear();
        addDays();
        getMonthData();
        initCalendarDays();

    }


    public DefaultListModel<DayLabel> getDaysLabelListModel() {
        return daysLabelListModel;
    }

    public DefaultListModel<DayLabel> getDaysWithTodosLabelListModel() {
        return daysWithTodosLabelListModel;
    }

    public JButton getMonthBackButton() {
        return monthBackButton;
    }

    public JButton getMonthForthButton() {
        return monthForthButton;
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
