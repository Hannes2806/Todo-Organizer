package toDoOrganizer.gui;

import toDoOrganizer.data.Data;
import toDoOrganizer.data.ToDo;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Collections;

public class TodayView extends JPanel {
    private Data data = Data.getInstance();
    private static DefaultListModel todayListModel;
    private static JList<ToDo> todayList;
    private static DetailView details;

    public TodayView() {
        details = new DetailView(this);
        setLayout(new FlowLayout());
        JPanel todayPanel = new JPanel();
        todayPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //Today Overview
        gbc.insets = new Insets(10, 10, 10, 10); //distances
        //Label Today:
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel todayLabel = new JLabel("<html><u>Today:</u></html>");
        todayLabel.setFont(new Font("Arial", Font.BOLD, 18));
        todayPanel.add(todayLabel, gbc);
        //Lists today and details
        gbc.insets = new Insets(10, 10, 230, 10); //distances
        gbc.gridy = 1;
        todayListModel = data.filterDate(data.getToDoList(), LocalDate.now());
        todayList = new JList<>(todayListModel);
        todayList.setCellRenderer(new MainView.BulletPointRenderer()); //integrate Bulletpoints
        JScrollPane todayScrollPane = new JScrollPane(todayList);
        todayScrollPane.setPreferredSize(new Dimension(300, 200)); //minimum width/height
        todayPanel.add(todayScrollPane, gbc);

        add(todayPanel);
        add(details);

        //add detailView - action
//        todayList.addListSelectionListener(e -> {
//            if (e.getValueIsAdjusting()) {
//                int selectedIndex = data.getToDoList().indexOf(todayList.getSelectedValue());
//                details.showDetails(selectedIndex);
//            }
//        });
    }

    public static DefaultListModel getTodayListModel() {
        return todayListModel;
    }

    public static JList<ToDo> getTodayList() {
        return todayList;
    }

    public static DetailView getDetails() {
        return details;
    }
}
