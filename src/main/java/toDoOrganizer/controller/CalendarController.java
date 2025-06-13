package toDoOrganizer.controller;

import toDoOrganizer.data.Data;
import toDoOrganizer.gui.CalendarView;

public class CalendarController {
    Data data = Data.getInstance();
    private CalendarView calendarView;

    public CalendarController(CalendarView view) {
        calendarView = view;


    }

}
