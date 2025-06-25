package toDoOrganizer.gui;

import toDoOrganizer.controller.DetailController;
import toDoOrganizer.data.Data;
import toDoOrganizer.data.ToDo;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class TodayView extends JPanel {
    private Data data = Data.getInstance();
    private JLabel todayLabel;
    private JButton calendarButton;
    private DefaultListModel todayListModel;
    private JList<ToDo> todayList;
    private DetailView detailView;
    private DetailController detailController;
    private LocalDate activeDate;
    private JPanel todayPanel;

    public TodayView() {
        detailView = new DetailView(this);
        detailController = new DetailController(detailView);
        activeDate = LocalDate.now();

        //init Layout, Todos today next to details
        setLayout(new FlowLayout());
        todayPanel = new JPanel();
        todayPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); //distances
        gbc.gridx = 0;

        //init todayPanel
        initHeadline(gbc);
        initScrollPane(gbc); //Todays Todos
        initCalendarButton(gbc);

        add(todayPanel);
        add(detailView);
    }

    private void initHeadline(GridBagConstraints gbc) {
        gbc.gridy = 0;
        todayLabel = new JLabel("Todos on: " + activeDate);
        todayLabel.setFont(data.getHeadingFont());
        todayPanel.add(todayLabel, gbc);
    }

    private void initScrollPane(GridBagConstraints gbc) {
        gbc.gridy = 1;
        todayListModel = data.filterDate(data.getToDoList(), activeDate);
        todayList = new JList<>(todayListModel);
        todayList.setCellRenderer(new MainView.BulletPointRenderer()); //integrate Bulletpoints
        todayList.setFont(data.getTextFont());
        JScrollPane todayScrollPane = new JScrollPane(todayList);
        todayScrollPane.setPreferredSize(new Dimension(300, 200)); //minimum width/height
        todayPanel.add(todayScrollPane, gbc);
    }

    private void initCalendarButton(GridBagConstraints gbc) {
        gbc.insets = new Insets(10, 10, 185, 10); //distances
        gbc.gridy = 2;
        calendarButton = new JButton("<- To calendar");
        calendarButton.setFont(data.getTextFont());
        todayPanel.add(calendarButton, gbc);
    }

    public JLabel getTodayLabel() {
        return todayLabel;
    }

    public JButton getCalendarButton() {
        return calendarButton;
    }

    public DefaultListModel getTodayListModel() {
        return todayListModel;
    }

    public JList<ToDo> getTodayList() {
        return todayList;
    }

    public DetailController getDetailController() {
        return detailController;
    }

    public LocalDate getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(LocalDate date) {
        activeDate = date;
    }
}
