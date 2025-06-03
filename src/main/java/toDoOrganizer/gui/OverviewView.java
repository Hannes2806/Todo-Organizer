package toDoOrganizer.gui;

import toDoOrganizer.data.Data;
import toDoOrganizer.data.ToDo;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;

public class OverviewView extends JPanel {
    private Data data = Data.getInstance();

    private DefaultListModel urgentListModel;
    private DefaultListModel notUrgentListModel;

    public OverviewView() {
        DetailView details = new DetailView(this);
        setLayout(new FlowLayout());
        JPanel overviewPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();


        //Overview categories

        //Label Overview categories
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel overviewLabel = new JLabel("<html><u>Overview:</u></html>");
        overviewLabel.setFont(new Font("Arial", Font.BOLD, 18));
        overviewPanel.add(overviewLabel, gbc);
        //Sub-Labels:
        gbc.gridy = 1;
        JLabel urgentLabel = new JLabel("Urgent:");
        urgentLabel.setFont(new Font("Arial", Font.BOLD, 16));
        overviewPanel.add(urgentLabel, gbc);
        gbc.gridy = 3;
        JLabel notUrgentLabel = new JLabel("Not urgent:");
        notUrgentLabel.setFont(new Font("Arial", Font.BOLD, 16));
        overviewPanel.add(notUrgentLabel, gbc);
        //Lists:
        gbc.gridy = 2;
        urgentListModel = data.filterUrgency(data.getToDoList(), true);
        JList<ToDo> urgentList = new JList<>(urgentListModel);
        urgentList.setCellRenderer(new MainView.BulletPointRenderer()); //integrate Bulletpoints
        JScrollPane urgentScrollPane = new JScrollPane(urgentList);
        urgentScrollPane.setPreferredSize(new Dimension(300, 200)); //minimum width/height
        overviewPanel.add(urgentScrollPane, gbc);
        gbc.gridy = 4;
        notUrgentListModel = data.filterUrgency(data.getToDoList(), false);
        JList<ToDo> notUrgentList = new JList<>(notUrgentListModel);
        notUrgentList.setCellRenderer(new MainView.BulletPointRenderer()); //integrate Bulletpoints
        JScrollPane notUrgentScrollPane = new JScrollPane(notUrgentList);
        notUrgentScrollPane.setPreferredSize(new Dimension(300, 200)); //minimum width/height
        overviewPanel.add(notUrgentScrollPane, gbc);


        add(overviewPanel);
        add(details);

        //add details view - action

        urgentList.addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) {
                int selectedIndex = data.getToDoList().indexOf(urgentList.getSelectedValue());
                details.showDetails(selectedIndex);
            }
        });

        notUrgentList.addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) {
                int selectedIndex = data.getToDoList().indexOf(notUrgentList.getSelectedValue());
                details.showDetails(selectedIndex);
            }
        });

    }

    public void refreshData() {
        urgentListModel.clear();
        urgentListModel.addAll(Collections.list(data.filterUrgency(data.getToDoList(), true).elements()));
        notUrgentListModel.clear();
        notUrgentListModel.addAll(Collections.list(data.filterUrgency(data.getToDoList(), false).elements()));
    }
}
