package toDoOrganizer.gui;

import toDoOrganizer.controller.DetailController;
import toDoOrganizer.data.Data;
import toDoOrganizer.data.ToDo;

import javax.swing.*;
import java.awt.*;

public class OverviewView extends JPanel {
    private Data data = Data.getInstance();

    private DefaultListModel urgentListModel;
    private DefaultListModel notUrgentListModel;

    private JList<ToDo> urgentList;
    private JList<ToDo> notUrgentList;

    private DetailView detailView;
    private DetailController detailController;
    private JPanel overviewPanel;

    public OverviewView() {
        detailView = new DetailView(this);
        detailController = new DetailController(detailView);

        //Flow-Layout for overview next to details, init layout
        setLayout(new FlowLayout());
        overviewPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        //init Overview-Panel
        initHeadline(gbc);
        initSubheads(gbc); //Urgent and Not Urgent
        initScrollPanes(gbc); //Filtered Todo-Lists with extern option to show details

        add(overviewPanel);
        add(detailView); //detailView next to overview
    }

    private void initHeadline(GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel overviewLabel = new JLabel("<html><u>Overview:</u></html>");
        overviewLabel.setFont(new Font("Arial", Font.BOLD, 18));
        overviewPanel.add(overviewLabel, gbc);
    }

    private void initSubheads(GridBagConstraints gbc) {
        gbc.gridy = 1;
        JLabel urgentLabel = new JLabel("Urgent:");
        urgentLabel.setFont(new Font("Arial", Font.BOLD, 16));
        overviewPanel.add(urgentLabel, gbc);
        gbc.gridy = 3;
        JLabel notUrgentLabel = new JLabel("Not urgent:");
        notUrgentLabel.setFont(new Font("Arial", Font.BOLD, 16));
        overviewPanel.add(notUrgentLabel, gbc);
    }

    private void initScrollPanes(GridBagConstraints gbc) {
        gbc.gridy = 2;
        urgentListModel = data.filterUrgency(data.getToDoList(), true);
        urgentList = new JList<>(urgentListModel);
        urgentList.setCellRenderer(new MainView.BulletPointRenderer()); //integrate Bulletpoints
        JScrollPane urgentScrollPane = new JScrollPane(urgentList);
        urgentScrollPane.setPreferredSize(new Dimension(300, 200)); //minimum width/height
        overviewPanel.add(urgentScrollPane, gbc);
        gbc.gridy = 4;
        notUrgentListModel = data.filterUrgency(data.getToDoList(), false);
        notUrgentList = new JList<>(notUrgentListModel);
        notUrgentList.setCellRenderer(new MainView.BulletPointRenderer()); //integrate Bulletpoints
        JScrollPane notUrgentScrollPane = new JScrollPane(notUrgentList);
        notUrgentScrollPane.setPreferredSize(new Dimension(300, 200)); //minimum width/height
        overviewPanel.add(notUrgentScrollPane, gbc);
    }

    public DefaultListModel getUrgentListModel() {
        return urgentListModel;
    }

    public DefaultListModel getNotUrgentListModel() {
        return notUrgentListModel;
    }

    public JList<ToDo> getUrgentList() {
        return urgentList;
    }

    public JList<ToDo> getNotUrgentList() {
        return notUrgentList;
    }

    public DetailController getDetailController() {
        return detailController;
    }
}
