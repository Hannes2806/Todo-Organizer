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

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        initHead();

        JPanel mainPanel = new JPanel();
        add(mainPanel);
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridy = 0;
        gbc.gridx = 0;

        JLabel functionsHeading = new JLabel("Functions");
        functionsHeading.setFont(subheadingFont);
        mainPanel.add(functionsHeading, gbc);

        JLabel overviewTitle = new JLabel("Overview: ");
        overviewTitle.setFont(textFont);
        gbc.gridy = 1;
        mainPanel.add(overviewTitle);

        JLabel overviewDescription = new JLabel("Here all todos are divided into urgent and not urgent. " +
                "Details can be queried or changed with a simple click on the todo.");
        overviewDescription.setFont(textFont);
        gbc.gridx = 1;
        mainPanel.add(overviewDescription);
    }

    private void initHead() {
        JPanel headingPanel = new JPanel();
        headingPanel.setLayout(new BoxLayout(headingPanel, BoxLayout.Y_AXIS));
        add(headingPanel);

        JLabel heading = new JLabel("Todo-Organizer Info");
        heading.setFont(headingFont);
        headingPanel.add(heading);

        JLabel firstDescription = new JLabel("This todo organiser is for setting up, sorting and editing todos. " +
                "Various categories such as urgency, group affiliation or whether a todo should be " +
                "permanently available help to categorise the todos. So you never lose track.");
        firstDescription.setFont(textFont);
        headingPanel.add(firstDescription);
    }
}
