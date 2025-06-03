package toDoOrganizer.controller;

import toDoOrganizer.gui.MainView;

public class AppController {
    private MainView view;
    //private Data data = Data.getInstance();

    public AppController() {
        view = new MainView();
        new MainController(view);
    }

    public void startApp() {
        view.setVisible(true);
    }
}
