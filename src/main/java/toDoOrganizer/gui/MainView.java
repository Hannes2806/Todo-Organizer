package toDoOrganizer.gui;

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
    //private JPanel todayView, details, homeView;

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
        main.setLayout(new CardLayout());

        main.add(new OverviewView(), "Overview");

        main.add(new TodayView(), "Today");
        main.add(new CalendarView(), "Calendar");


        //Action listener header:
        viewsBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedView = (String) viewsBox.getSelectedItem();

                switch(selectedView) {
                    case "Overview":
                        switchPanel(new OverviewView());
                        break;
                    case "Today":
                        switchPanel(new TodayView());
                        break;
                    case "Calendar":
                        switchPanel(new CalendarView());
                        break;
                    case "Home":
                        switchPanel(new JPanel());
                        break;
                    default:
                        System.out.println("Main Panel Unknown");
                }
            }
        });

        newTodoButton.addActionListener(e -> switchPanel(new NewTodoView()));




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

    public static void switchPanel(JPanel panel) {
        main.removeAll();  // Entferne alle Panels
        main.add(panel);    // Füge das neue Panel hinzu
        main.revalidate();  // Layout neu validieren
        main.repaint();     // Layout neu zeichnen
    }
}
