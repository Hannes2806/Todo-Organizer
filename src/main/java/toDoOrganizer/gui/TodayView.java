package toDoOrganizer.gui;

import toDoOrganizer.controller.DetailController;
import toDoOrganizer.data.Data;
import toDoOrganizer.data.ToDo;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class TodayView extends JPanel {
    private Data data = Data.getInstance();
    private static DefaultListModel todayListModel;
    private static JList<ToDo> todayList;
    private static DetailView detailView;
    private static DetailController detailController;
    private JPanel todayPanel;

    public TodayView() {
        detailView = new DetailView(this);
        detailController = new DetailController(detailView);

        //init Layout, Todos today next to details
        setLayout(new FlowLayout());
        todayPanel = new JPanel();
        todayPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); //distances

        //init todayPanel
        initHeadline(gbc);
        initScrollPane(gbc); //Todays Todos

        add(todayPanel);
        add(detailView);
    }

    private void initHeadline(GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel todayLabel = new JLabel("<html><u>Today:</u></html>");
        todayLabel.setFont(new Font("Arial", Font.BOLD, 18));
        todayPanel.add(todayLabel, gbc);
    }

    private void initScrollPane(GridBagConstraints gbc) {
        gbc.insets = new Insets(10, 10, 230, 10); //distances
        gbc.gridy = 1;
        todayListModel = data.filterDate(data.getToDoList(), LocalDate.now());
        todayList = new JList<>(todayListModel);
        todayList.setCellRenderer(new MainView.BulletPointRenderer()); //integrate Bulletpoints
        JScrollPane todayScrollPane = new JScrollPane(todayList);
        todayScrollPane.setPreferredSize(new Dimension(300, 200)); //minimum width/height
        todayPanel.add(todayScrollPane, gbc);
    }

    public static DefaultListModel getTodayListModel() {
        return todayListModel;
    }

    public static JList<ToDo> getTodayList() {
        return todayList;
    }

    public static DetailController getDetailController() {
        return detailController;
    }
}
