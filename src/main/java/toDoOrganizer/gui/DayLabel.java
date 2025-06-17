package toDoOrganizer.gui;

import javax.swing.*;
import java.time.LocalDate;

public class DayLabel extends JLabel {
    private LocalDate date;

    public DayLabel(int day, int month, int year) {
        date = LocalDate.of(year, month, day);
    }

    public LocalDate getDate() {
        return date;
    }
}
