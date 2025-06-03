package toDoOrganizer.controller;

import toDoOrganizer.data.Data;
import toDoOrganizer.gui.TodayView;

import java.time.LocalDate;
import java.util.Collections;

public class TodayController {
    Data data = Data.getInstance();

    public TodayController(TodayView todayView) {
        //add detailView - action
        TodayView.getTodayList().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) {
                int selectedIndex = data.getToDoList().indexOf(TodayView.getTodayList().getSelectedValue());
                TodayView.getDetailController().showDetails(selectedIndex);
            }
        });
    }

    public void refreshData() {
        TodayView.getTodayListModel().clear();
        TodayView.getTodayListModel().addAll(Collections.list(data.filterDate(data.getToDoList(), LocalDate.now()).elements()));
    }

}
