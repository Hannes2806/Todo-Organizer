package toDoOrganizer.gui;

import toDoOrganizer.controller.NewTodoController;
import toDoOrganizer.controller.OverviewController;
import toDoOrganizer.controller.TodayController;

import javax.swing.*;
import java.awt.*;


public class MainView extends JFrame {
    //MainView combines every view in one
    private static JPanel main, header;

    private static JComboBox<String> viewsBox;
    private static JButton newTodoButton;

    private static CardLayout cardLayout;
    private static OverviewView overviewView;
    private static OverviewController overviewController;
    private static TodayView todayView;
    private static TodayController todayController;
    private static CalendarView calendarView;
    private HomeView homeView;
    private static NewTodoView newTodoView;
    private static NewTodoController newTodoController;

    public MainView() {
        //JFrame:
        setTitle("ToDo-Organizer");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(0, 0));

        //Header:
        header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //Page selection:
        String[] viewsArray = {"Overview", "Today", "Calendar", "Home"};
        viewsBox = new JComboBox<>(viewsArray);
        header.add(viewsBox);

        newTodoButton = new JButton("New Todo");
        header.add(newTodoButton);
        header.add(new JButton("Settings"));
        header.add(new JButton("?"));
        header.setBackground(new Color(53, 83, 184));
        add(header, BorderLayout.NORTH);

        //Main:
        main = new JPanel();
        cardLayout = new CardLayout();
        main.setLayout(cardLayout);


        overviewView = new OverviewView();
        overviewController = new OverviewController(overviewView);
        todayView = new TodayView();
        todayController = new TodayController(todayView);
        calendarView = new CalendarView();
        homeView = new HomeView();
        newTodoView = new NewTodoView();
        newTodoController = new NewTodoController(newTodoView);


        main.add(overviewView, "Overview");
        main.add(todayView, "Today");
        main.add(calendarView, "Calendar");
        main.add(homeView, "Home");
        main.add(newTodoView, "NewTodo");


        JScrollPane mainScrollPane = new JScrollPane(main);
        mainScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(mainScrollPane, BorderLayout.CENTER);
    }

    static class BulletPointRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            label.setText("• " + value);  // Fügt automatisch ein "•" vor jedem Eintrag hinzu
            return label;
        }
    }

    public static JPanel getMain() {
        return main;
    }

    public static CardLayout getCardLayout() {
        return cardLayout;
    }

    public static NewTodoView getNewTodoView() {
        return newTodoView;
    }

    public static JComboBox<String> getViewsBox() {
        return viewsBox;
    }

    public static JButton getNewTodoButton() {
        return newTodoButton;
    }

    public static OverviewController getOverviewController() {
        return overviewController;
    }

    public static TodayController getTodayController() {
        return todayController;
    }
}
