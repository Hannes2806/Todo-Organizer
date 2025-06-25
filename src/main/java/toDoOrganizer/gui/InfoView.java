package toDoOrganizer.gui;

import javax.swing.*;
import java.awt.*;

public class InfoView extends JPanel {
    private Font headingFont;
    private Font subheadingFont;
    private Font textFont;


    public InfoView() {
        headingFont = new Font("Arial", Font.BOLD, 24);
        subheadingFont = new Font("Arial", Font.BOLD, 16);
        textFont = new Font("Arial", Font.PLAIN, 14);

        setLayout(new BorderLayout());

        add(initHead(), BorderLayout.NORTH);
        add(initMain(), BorderLayout.CENTER);


    }

    private JPanel initHead() {
        JPanel headingPanel = new JPanel();
        headingPanel.setLayout(new BoxLayout(headingPanel, BoxLayout.Y_AXIS));

        JLabel heading = new JLabel("Todo-Organizer Info");
        heading.setFont(headingFont);
        heading.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 0));
        heading.setAlignmentX(Component.LEFT_ALIGNMENT);
        headingPanel.add(heading);

        JTextArea firstDescription = new JTextArea("This todo organiser is for setting up, sorting and editing todos. " +
                "Various categories such as urgency, group affiliation or whether a todo should be " +
                "permanently available help to categorise the todos. So you never lose track.");
        firstDescription.setLineWrap(true);                // activate line wrap
        firstDescription.setWrapStyleWord(true);           // Line wrap at the end of the word
        firstDescription.setEditable(false);               // not editable
        firstDescription.setOpaque(false);                 // no white background
        firstDescription.setBorder(null);                  // no visible Border
        firstDescription.setAlignmentX(Component.LEFT_ALIGNMENT);
        firstDescription.setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 0));
        firstDescription.setFont(textFont);
        firstDescription.setMaximumSize(new Dimension(600, Integer.MAX_VALUE)); // max width
        headingPanel.add(firstDescription);

        return headingPanel;
    }

    private JPanel initMain() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = initGridBagConstraints();

        gbc.gridwidth = 2;
        initHeadline(mainPanel, gbc);
        gbc.gridwidth = 1;
        initOverviewDescription(mainPanel, gbc);
        initTodayDescription(mainPanel, gbc);
        initCalendarDescription(mainPanel, gbc);
        initNewTodoDescription(mainPanel, gbc);
        initDeletionDescription(mainPanel, gbc);

        return mainPanel;
    }

    private GridBagConstraints initGridBagConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        return gbc;
    }

    private void initHeadline(JPanel mainPanel, GridBagConstraints gbc) {
        gbc.gridy = 0;
        gbc.gridx = 0;
        JLabel functionsHeading = new JLabel("Functions");
        functionsHeading.setFont(subheadingFont);
        mainPanel.add(functionsHeading, gbc);
    }

    private void initOverviewDescription(JPanel mainPanel, GridBagConstraints gbc) {
        gbc.gridy = 1;
        gbc.gridx = 0;
        JLabel overviewTitle = new JLabel("Overview: ");
        overviewTitle.setFont(textFont);
        mainPanel.add(overviewTitle, gbc);

        JTextArea overviewDescription = new JTextArea("Here all todos are divided into urgent and not urgent. " +
                "Details can be queried or changed with a simple click on the todo.");
        overviewDescription.setLineWrap(true);                // activate line wrap
        overviewDescription.setWrapStyleWord(true);           // Line wrap at the end of the word
        overviewDescription.setEditable(false);               // not editable
        overviewDescription.setOpaque(false);                 // no white background
        overviewDescription.setBorder(null);                  // no visible Border
        overviewDescription.setAlignmentX(Component.LEFT_ALIGNMENT);
        overviewDescription.setFont(textFont);
        overviewDescription.setPreferredSize(new Dimension(300, 50));
        gbc.gridx = 1;
        mainPanel.add(overviewDescription, gbc);
    }

    private void initTodayDescription(JPanel mainPanel, GridBagConstraints gbc) {
        gbc.gridy = 2;
        gbc.gridx = 0;
        JLabel todayTitle = new JLabel("Today: ");
        todayTitle.setFont(textFont);
        mainPanel.add(todayTitle, gbc);

        JTextArea todayDescription = new JTextArea("Here we filter for the todos that are pending today. " +
                "(which have their expiry date today)");
        todayDescription.setLineWrap(true);                // activate line wrap
        todayDescription.setWrapStyleWord(true);           // Line wrap at the end of the word
        todayDescription.setEditable(false);               // not editable
        todayDescription.setOpaque(false);                 // no white background
        todayDescription.setBorder(null);                  // no visible Border
        todayDescription.setAlignmentX(Component.LEFT_ALIGNMENT);
        todayDescription.setFont(textFont);
        todayDescription.setPreferredSize(new Dimension(300, 50));
        gbc.gridx = 1;
        mainPanel.add(todayDescription, gbc);
    }

    private void initCalendarDescription(JPanel mainPanel, GridBagConstraints gbc) {
        gbc.gridy = 3;
        gbc.gridx = 0;
        JLabel calendarTitle = new JLabel("Calendar: ");
        calendarTitle.setFont(textFont);
        mainPanel.add(calendarTitle, gbc);

        JTextArea calendarDescription = new JTextArea("The calendar sorts the todos by day. Clicking " +
                "on the day displays the todos that have been incurred.");
        calendarDescription.setLineWrap(true);                // activate line wrap
        calendarDescription.setWrapStyleWord(true);           // Line wrap at the end of the word
        calendarDescription.setEditable(false);               // not editable
        calendarDescription.setOpaque(false);                 // no white background
        calendarDescription.setBorder(null);                  // no visible Border
        calendarDescription.setAlignmentX(Component.LEFT_ALIGNMENT);
        calendarDescription.setFont(textFont);
        calendarDescription.setPreferredSize(new Dimension(300, 50));
        gbc.gridx = 1;
        mainPanel.add(calendarDescription, gbc);
    }

    private void initNewTodoDescription(JPanel mainPanel, GridBagConstraints gbc) {
        gbc.gridy = 4;
        gbc.gridx = 0;
        JLabel newTodoTitle = new JLabel("New Todo: ");
        newTodoTitle.setFont(textFont);
        mainPanel.add(newTodoTitle, gbc);

        JTextArea newTodoDescription = new JTextArea("New todos can be created here. Each todo needs " +
                "at least one title. Permanency determines whether a todo should appear every day. " +
                "It can be sorted into urgent and non-urgent tasks. You can determine when a todo is " +
                "due and when it should become urgent. Todos can be sorted as desired by assigning them " +
                "to a category such as education or homework.");
        newTodoDescription.setLineWrap(true);                // activate line wrap
        newTodoDescription.setWrapStyleWord(true);           // Line wrap at the end of the word
        newTodoDescription.setEditable(false);               // not editable
        newTodoDescription.setOpaque(false);                 // no white background
        newTodoDescription.setBorder(null);                  // no visible Border
        newTodoDescription.setAlignmentX(Component.LEFT_ALIGNMENT);
        newTodoDescription.setFont(textFont);
        newTodoDescription.setPreferredSize(new Dimension(300, 100));
        gbc.gridx = 1;
        mainPanel.add(newTodoDescription, gbc);
    }

    private void initDeletionDescription(JPanel mainPanel, GridBagConstraints gbc) {
        gbc.gridy = 5;
        gbc.gridx = 0;
        JLabel deletionTitle = new JLabel("Delete Todos: ");
        deletionTitle.setFont(textFont);
        mainPanel.add(deletionTitle, gbc);

        JTextArea deletionDescription = new JTextArea("In addition to being able to delete the todos in the details, " +
                "the entire todo list can also be deleted. To do this, go to Settings -> " +
                "Delete Todo-List.");
        deletionDescription.setLineWrap(true);                // activate line wrap
        deletionDescription.setWrapStyleWord(true);           // Line wrap at the end of the word
        deletionDescription.setEditable(false);               // not editable
        deletionDescription.setOpaque(false);                 // no white background
        deletionDescription.setBorder(null);                  // no visible Border
        deletionDescription.setAlignmentX(Component.LEFT_ALIGNMENT);
        deletionDescription.setFont(textFont);
        deletionDescription.setPreferredSize(new Dimension(300, 100));
        gbc.gridx = 1;
        mainPanel.add(deletionDescription, gbc);
    }
}
