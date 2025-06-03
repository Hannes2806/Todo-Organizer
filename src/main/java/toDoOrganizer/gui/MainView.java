package toDoOrganizer.gui;

import toDoOrganizer.controller.NewTodoController;
import toDoOrganizer.controller.OverviewController;
import toDoOrganizer.data.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainView extends JFrame {
    //MainView = Overview
    //private AppController controller;
    private Data data = Data.getInstance();
    private static JPanel main, header;

    private static CardLayout cardLayout;
    public static OverviewView overviewView;
    public static TodayView todayView;
    public static CalendarView calendarView;
    private HomeView homeView;
    public static NewTodoView newTodoView;

    public MainView() {
        //this.controller = controller;

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
        JComboBox<String> viewsBox = new JComboBox<>(viewsArray);
        header.add(viewsBox);

        JButton newTodoButton = new JButton("New Todo");
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
//        new OverviewController(overviewView);
        todayView = new TodayView();
        calendarView = new CalendarView();
        homeView = new HomeView();
        newTodoView = new NewTodoView();
        new NewTodoController(newTodoView);


        main.add(overviewView, "Overview");
        main.add(todayView, "Today");
        main.add(calendarView, "Calendar");
        main.add(homeView, "Home");
        main.add(newTodoView, "NewTodo");


        //Action listener header:
        viewsBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedView = (String) viewsBox.getSelectedItem();

                switch(selectedView) {
                    case "Overview":
                        switchPanel("Overview");
                        break;
                    case "Today":
                        switchPanel("Today");
                        break;
                    case "Calendar":
                        switchPanel("Calender");
                        break;
                    case "Home":
                        switchPanel("Home");
                        break;
                    default:
                        System.out.println("Main Panel Unknown");
                }
            }
        });

        newTodoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newTodoView.setActiveIndex(-1);
                switchPanel("NewTodo");
            }
        });




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

    public static void switchPanel(String panel) {
        cardLayout.show(main, panel);
//        main.removeAll();  // Entferne alle Panels
//        main.add(panel);    // Füge das neue Panel hinzu
//        main.revalidate();  // Layout neu validieren
//        main.repaint();     // Layout neu zeichnen
    }

    public static void refreshViews() {
        overviewView.refreshData();
        todayView.refreshData();
//        calendarView.refreshData();
    }
}
