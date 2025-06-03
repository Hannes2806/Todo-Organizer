package toDoOrganizer.controller;

import toDoOrganizer.data.Data;
import toDoOrganizer.gui.OverviewView;

import java.util.Collections;

public class OverviewController {
    private Data data = Data.getInstance();
    private OverviewView overviewView;

    public OverviewController(OverviewView view) {
        //add detailView - action
        overviewView = view;

        overviewView.getUrgentList().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) {
                int selectedIndex = data.getToDoList().indexOf(overviewView.getUrgentList().getSelectedValue());
                overviewView.getDetailController().showDetails(selectedIndex);
            }
        });

        overviewView.getNotUrgentList().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) {
                int selectedIndex = data.getToDoList().indexOf(overviewView.getNotUrgentList().getSelectedValue());
                overviewView.getDetailController().showDetails(selectedIndex);
            }
        });
    }

    public void refreshData() {
        overviewView.getUrgentListModel().clear();
        overviewView.getUrgentListModel().addAll(Collections.list(data.filterUrgency(data.getToDoList(), true).elements()));
        overviewView.getNotUrgentListModel().clear();
        overviewView.getNotUrgentListModel().addAll(Collections.list(data.filterUrgency(data.getToDoList(), false).elements()));
    }
}
