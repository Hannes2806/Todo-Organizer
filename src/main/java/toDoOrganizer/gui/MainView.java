package toDoOrganizer.gui;

import toDoOrganizer.controller.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;


public class MainView extends JFrame {
    //MainView combines every view in one
    private static JPanel main, header;

    private static JComboBox<String> viewsBox;
    private static JButton newTodoButton;
    private static JButton settingsButton;
    private static JButton infoButton;

    private static CardLayout cardLayout;
    private static OverviewView overviewView;
    private static OverviewController overviewController;
    private static TodayView todayView;
    private static TodayController todayController;
    private static CalendarView calendarView;
    private static CalendarController calendarController;
    private HomeView homeView;
    private static NewTodoView newTodoView;
    private static NewTodoController newTodoController;
    private static SettingsView settingsView;
    private static SettingsController settingsController;

    public MainView() {
        initJFrame();
        initHeader();
        initMain(); //Content with CardLayout
    }

    private void initJFrame() {
        setTitle("ToDo-Organizer");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(0, 0));
    }

    private void initHeader() {
        header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //Page selection:
        String[] viewsArray = {"Overview", "Today", "Calendar", "Home"};
        viewsBox = new JComboBox<>(viewsArray);
        header.add(viewsBox);

        newTodoButton = new JButton("New Todo");
        header.add(newTodoButton);
        settingsButton = new JButton("Settings");
        header.add(settingsButton);
        infoButton = new JButton("?");
        header.add(infoButton);
        header.setBackground(new Color(53, 83, 184));
        add(header, BorderLayout.NORTH);
    }

    private void initMain() {
        main = new JPanel();
        cardLayout = new CardLayout();
        main.setLayout(cardLayout);

        initCards(); //Views + Controllers
        addCards();

        JScrollPane mainScrollPane = new JScrollPane(main);
        mainScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(mainScrollPane, BorderLayout.CENTER);
    }

    private void initCards() {
        overviewView = new OverviewView();
        overviewController = new OverviewController(overviewView);
        todayView = new TodayView();
        todayController = new TodayController(todayView);
        calendarView = new CalendarView(LocalDate.now().getMonthValue(), LocalDate.now().getYear());
        calendarController = new CalendarController(calendarView);
        homeView = new HomeView();
        newTodoView = new NewTodoView();
        newTodoController = new NewTodoController(newTodoView);
        settingsView = new SettingsView();
        settingsController = new SettingsController(settingsView);

    }

    private void addCards() {
        main.add(overviewView, "Overview");
        main.add(todayView, "Today");
        main.add(calendarView, "Calendar");
        main.add(homeView, "Home");
        main.add(newTodoView, "NewTodo");
        main.add(settingsView, "Settings");
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

    public static TodayView getTodayView() {
        return todayView;
    }

    public static JComboBox<String> getViewsBox() {
        return viewsBox;
    }

    public static JButton getNewTodoButton() {
        return newTodoButton;
    }

    public static JButton getSettingsButton() {
        return settingsButton;
    }

    public static JButton getInfoButton() {
        return infoButton;
    }

    public static OverviewController getOverviewController() {
        return overviewController;
    }

    public static TodayController getTodayController() {
        return todayController;
    }

    public static CalendarController getCalendarController() {
        return calendarController;
    }
}
