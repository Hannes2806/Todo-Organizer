package toDoOrganizer.controller;

import toDoOrganizer.data.Data;
import toDoOrganizer.gui.TodayView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

public class TodayController {
    private Data data = Data.getInstance();
    private TodayView todayView;

    public TodayController(TodayView view) {
        todayView = view;
        //add detailView - action
        todayView.getTodayList().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) {
                int selectedIndex = data.getToDoList().indexOf(todayView.getTodayList().getSelectedValue());
                todayView.getDetailController().showDetails(selectedIndex);
            }
        });

        todayView.getCalendarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainController.switchPanel("Calendar");
            }
        });
    }

    public void refreshData() {
        todayView.getTodayLabel().setText("Todos on: " + todayView.getActiveDate());
        todayView.getTodayListModel().clear();
        todayView.getTodayListModel().addAll(Collections.list(data.filterDate(data.getToDoList(), todayView.getActiveDate()).elements()));
    }

}
