package toDoOrganizer.controller;

import toDoOrganizer.data.Data;
import toDoOrganizer.gui.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController {
    Data data = Data.getInstance();

    public MainController(MainView mainView) {
        //Action listener header:
        MainView.getViewsBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedView = (String) MainView.getViewsBox().getSelectedItem();

                switch(selectedView) {
                    case "Overview":
                        switchPanel("Overview");
                        break;
                    case "Today":
                        switchPanel("Today");
                        break;
                    case "Calendar":
                        switchPanel("Calendar");
                        break;
                    case "Home":
                        switchPanel("Home");
                        break;
                    default:
                        System.out.println("Main Panel Unknown");
                }
            }
        });

        MainView.getNewTodoButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainView.getNewTodoView().setActiveIndex(-1);
                switchPanel("NewTodo");
            }
        });
    }

    public static void switchPanel(String panel) {
        MainView.getCardLayout().show(MainView.getMain(), panel);
//        main.removeAll();  // Entferne alle Panels
//        main.add(panel);    // Füge das neue Panel hinzu
//        main.revalidate();  // Layout neu validieren
//        main.repaint();     // Layout neu zeichnen
    }

    public static void refreshViews() {
        MainView.getOverviewController().refreshData();
        MainView.getTodayController().refreshData();
        MainView.getCalendarController().refreshData();
    }

}
