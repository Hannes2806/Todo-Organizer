package toDoOrganizer.gui;

import toDoOrganizer.data.Data;
import toDoOrganizer.data.ToDo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class NewTodoView extends JPanel {
    private Data data = Data.getInstance();

    public NewTodoView() {
        init(-1);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 30, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel headingLabel = new JLabel("<html><u>New ToDo</u></html>");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(headingLabel, gbc);

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel titleLabel = new JLabel("Title:");
        add(titleLabel, gbc);
        gbc.gridx = 1;
        JTextField titleTextField = new JTextField(30);
        add(titleTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel descriptionLabel = new JLabel("Description:");
        add(descriptionLabel, gbc);
        gbc.gridx = 1;
        JTextArea descriptionTextArea = new JTextArea(10, 30);
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setWrapStyleWord(true);
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionTextArea);
        add(descriptionScrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel permanentLabel = new JLabel("Permanent:");
        add(permanentLabel, gbc);
        gbc.gridx = 1;
        JCheckBox permanentCheckBox = new JCheckBox();
        add(permanentCheckBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel urgentLabel = new JLabel("Urgent:");
        add(urgentLabel, gbc);
        gbc.gridx = 1;
        JRadioButton urgentRadioButton = new JRadioButton();
        add(urgentRadioButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        JLabel notUrgentLabel = new JLabel("Not urgent:");
        add(notUrgentLabel, gbc);
        gbc.gridx = 1;
        JRadioButton notUrgentRadioButton = new JRadioButton();
        notUrgentRadioButton.setSelected(true);
        add(notUrgentRadioButton, gbc);

        ButtonGroup classGroup = new ButtonGroup();
        classGroup.add(urgentRadioButton);
        classGroup.add(notUrgentRadioButton);

        gbc.gridx = 0;
        gbc.gridy = 6;
        JLabel whenUrgentLabel = new JLabel("When urgent:");
        add(whenUrgentLabel, gbc);
        gbc.gridx = 1;
        SpinnerDateModel whenUrgentModel = new SpinnerDateModel();
        JSpinner whenUrgentSpinner = new JSpinner(whenUrgentModel);
        JSpinner.DateEditor whenUrgentEditor = new JSpinner.DateEditor(whenUrgentSpinner, "dd.MM.yyyy");
        whenUrgentSpinner.setEditor(whenUrgentEditor);
        add(whenUrgentSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        JLabel expiryLabel = new JLabel("Expiry date:");
        add(expiryLabel, gbc);
        gbc.gridx = 1;
        SpinnerDateModel expiryModel = new SpinnerDateModel();
        JSpinner expirySpinner = new JSpinner(expiryModel);
        JSpinner.DateEditor expiryEditor = new JSpinner.DateEditor(expirySpinner, "dd.MM.yyyy");
        expirySpinner.setEditor(expiryEditor);
        add(expirySpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        JLabel categoryLabel = new JLabel("Category:");
        add(categoryLabel, gbc);
        gbc.gridx = 1;
        String[] cathegoryArray = data.getCategoryArray();
        JComboBox<String> cathegoryBox = new JComboBox<>(cathegoryArray);
        add(cathegoryBox, gbc);

        gbc.insets = new Insets(20, 10, 10, 10);
        gbc.gridy = 9;
        JButton toDoSaveButton = new JButton("Save");
        toDoSaveButton.setEnabled(false);
        add(toDoSaveButton, gbc);


        //Add Action Listeners
        titleTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                toDoSaveButton.setEnabled(!titleTextField.getText().trim().isEmpty());
            }
        });

        permanentCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                whenUrgentSpinner.setEnabled(!permanentCheckBox.isSelected());
                expirySpinner.setEnabled(!permanentCheckBox.isSelected());
                //If unselect permantentCheckBox and urgent still selected, do whenUrgent not functional
                if (urgentRadioButton.isSelected()) {
                    whenUrgentSpinner.setEnabled(false);
                }
            }
        });

        urgentRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                whenUrgentSpinner.setEnabled(!urgentRadioButton.isSelected());
            }
        });

        notUrgentRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!permanentCheckBox.isSelected()) {
                    whenUrgentSpinner.setEnabled(notUrgentRadioButton.isSelected());
                }
            }
        });

        toDoSaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ToDo toDo = new ToDo(titleTextField.getText(), descriptionTextArea.getText(),
                        permanentCheckBox.isSelected(), urgentRadioButton.isSelected(),
                        notUrgentRadioButton.isSelected(), dateSpinnerToLocalDate(whenUrgentSpinner),
                        dateSpinnerToLocalDate(expirySpinner), LocalDate.now(),
                        (String) cathegoryBox.getSelectedItem());
                data.addToDo(toDo);
                System.out.println("ToDo saved.");
                MainView.switchPanel(new OverviewView());
            }
        });
    }

    public NewTodoView(int activeIndex) {
        //Hier die seite mit daten vom aktuellen Index füllen




        //Anschließend actionListeners in controller auslagern

        //Das ist ein Test 2
        //Ist es schon drin, das Todo später automatisch urgent wird?


        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 30, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel headingLabel = new JLabel("<html><u>New ToDo</u></html>");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(headingLabel, gbc);

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel titleLabel = new JLabel("Title:");
        add(titleLabel, gbc);
        gbc.gridx = 1;
        JTextField titleTextField = new JTextField(data.getToDo(activeIndex).getTitle(), 30);
        add(titleTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel descriptionLabel = new JLabel("Description:");
        add(descriptionLabel, gbc);
        gbc.gridx = 1;
        JTextArea descriptionTextArea = new JTextArea(data.getToDo(activeIndex).getDescription(), 10, 30);
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setWrapStyleWord(true);
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionTextArea);
        add(descriptionScrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel permanentLabel = new JLabel("Permanent:");
        add(permanentLabel, gbc);
        gbc.gridx = 1;
        JCheckBox permanentCheckBox = new JCheckBox();
        add(permanentCheckBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel urgentLabel = new JLabel("Urgent:");
        add(urgentLabel, gbc);
        gbc.gridx = 1;
        JRadioButton urgentRadioButton = new JRadioButton();
        add(urgentRadioButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        JLabel notUrgentLabel = new JLabel("Not urgent:");
        add(notUrgentLabel, gbc);
        gbc.gridx = 1;
        JRadioButton notUrgentRadioButton = new JRadioButton();
        add(notUrgentRadioButton, gbc);

        ButtonGroup classGroup = new ButtonGroup();
        classGroup.add(urgentRadioButton);
        classGroup.add(notUrgentRadioButton);

        gbc.gridx = 0;
        gbc.gridy = 6;
        JLabel whenUrgentLabel = new JLabel("When urgent:");
        add(whenUrgentLabel, gbc);
        gbc.gridx = 1;
        Date whenUrgentDate = Date.from(data.getToDo(activeIndex).getWhenUrgent().atStartOfDay(ZoneId.systemDefault()).toInstant()); //convert to date
        SpinnerDateModel whenUrgentModel = new SpinnerDateModel(whenUrgentDate, null, null, Calendar.DAY_OF_MONTH);
        JSpinner whenUrgentSpinner = new JSpinner(whenUrgentModel);
        JSpinner.DateEditor whenUrgentEditor = new JSpinner.DateEditor(whenUrgentSpinner, "dd.MM.yyyy");
        whenUrgentSpinner.setEditor(whenUrgentEditor);
        add(whenUrgentSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        JLabel expiryLabel = new JLabel("Expiry date:");
        add(expiryLabel, gbc);
        gbc.gridx = 1;
        Date expiryDate = Date.from(data.getToDo(activeIndex).getExpiryDate().atStartOfDay(ZoneId.systemDefault()).toInstant()); //convert to date
        SpinnerDateModel expiryModel = new SpinnerDateModel(expiryDate, null, null, Calendar.DAY_OF_MONTH);
        JSpinner expirySpinner = new JSpinner(expiryModel);
        JSpinner.DateEditor expiryEditor = new JSpinner.DateEditor(expirySpinner, "dd.MM.yyyy");
        expirySpinner.setEditor(expiryEditor);
        add(expirySpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        JLabel categoryLabel = new JLabel("Category:");
        add(categoryLabel, gbc);
        gbc.gridx = 1;
        String[] cathegoryArray = data.getCategoryArray();
        JComboBox<String> cathegoryBox = new JComboBox<>(cathegoryArray);
        cathegoryBox.setSelectedItem(data.getToDo(activeIndex).getCategory());
        add(cathegoryBox, gbc);
        gbc.insets = new Insets(20, 10, 10, 10);
        gbc.gridy = 9;
        JButton toDoSaveButton = new JButton("Save");
        add(toDoSaveButton, gbc);



        //permanent = without date
        if (data.getToDo(activeIndex).getPermanence()) {
            permanentCheckBox.setSelected(true);
            whenUrgentSpinner.setEnabled(false);
            expirySpinner.setEnabled(false);
        }

        //Urgent or not urgent
        if (data.getToDo(activeIndex).getUrgent()) {
            urgentRadioButton.setSelected(true);
        }
        else {
            notUrgentRadioButton.setSelected(true);
        }


        //Add Action Listeners
//        titleTextField.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyReleased(KeyEvent e) {
//                toDoSaveButton.setEnabled(!titleTextField.getText().trim().isEmpty());
//            }
//        });

        permanentCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                whenUrgentSpinner.setEnabled(!permanentCheckBox.isSelected());
                expirySpinner.setEnabled(!permanentCheckBox.isSelected());
                //If unselect permantentCheckBox and urgent still selected, do whenUrgent not functional
                if (urgentRadioButton.isSelected()) {
                    whenUrgentSpinner.setEnabled(false);
                }
            }
        });

        urgentRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                whenUrgentSpinner.setEnabled(!urgentRadioButton.isSelected());
            }
        });

        notUrgentRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!permanentCheckBox.isSelected()) {
                    whenUrgentSpinner.setEnabled(notUrgentRadioButton.isSelected());
                }
            }
        });

        toDoSaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ToDo toDo = new ToDo(titleTextField.getText(), descriptionTextArea.getText(),
                        permanentCheckBox.isSelected(), urgentRadioButton.isSelected(),
                        notUrgentRadioButton.isSelected(), dateSpinnerToLocalDate(whenUrgentSpinner),
                        dateSpinnerToLocalDate(expirySpinner), LocalDate.now(),
                        (String) cathegoryBox.getSelectedItem());
                data.updateToDo(toDo, activeIndex);
                System.out.println("ToDo saved.");
                MainView.switchPanel(new OverviewView());
            }
        });
    }

    private void init(int activeIndex) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 30, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel headingLabel = new JLabel("<html><u>New ToDo</u></html>");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(headingLabel, gbc);

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel titleLabel = new JLabel("Title:");
        add(titleLabel, gbc);
        gbc.gridx = 1;
        JTextField titleTextField = new JTextField(30);
        add(titleTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel descriptionLabel = new JLabel("Description:");
        add(descriptionLabel, gbc);
        gbc.gridx = 1;
        JTextArea descriptionTextArea = new JTextArea(10, 30);
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setWrapStyleWord(true);
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionTextArea);
        add(descriptionScrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel permanentLabel = new JLabel("Permanent:");
        add(permanentLabel, gbc);
        gbc.gridx = 1;
        JCheckBox permanentCheckBox = new JCheckBox();
        add(permanentCheckBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel urgentLabel = new JLabel("Urgent:");
        add(urgentLabel, gbc);
        gbc.gridx = 1;
        JRadioButton urgentRadioButton = new JRadioButton();
        add(urgentRadioButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        JLabel notUrgentLabel = new JLabel("Not urgent:");
        add(notUrgentLabel, gbc);
        gbc.gridx = 1;
        JRadioButton notUrgentRadioButton = new JRadioButton();
        notUrgentRadioButton.setSelected(true);
        add(notUrgentRadioButton, gbc);

        ButtonGroup classGroup = new ButtonGroup();
        classGroup.add(urgentRadioButton);
        classGroup.add(notUrgentRadioButton);

        gbc.gridx = 0;
        gbc.gridy = 6;
        JLabel whenUrgentLabel = new JLabel("When urgent:");
        add(whenUrgentLabel, gbc);
        gbc.gridx = 1;
        SpinnerDateModel whenUrgentModel = new SpinnerDateModel();
        JSpinner whenUrgentSpinner = new JSpinner(whenUrgentModel);
        JSpinner.DateEditor whenUrgentEditor = new JSpinner.DateEditor(whenUrgentSpinner, "dd.MM.yyyy");
        whenUrgentSpinner.setEditor(whenUrgentEditor);
        add(whenUrgentSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        JLabel expiryLabel = new JLabel("Expiry date:");
        add(expiryLabel, gbc);
        gbc.gridx = 1;
        SpinnerDateModel expiryModel = new SpinnerDateModel();
        JSpinner expirySpinner = new JSpinner(expiryModel);
        JSpinner.DateEditor expiryEditor = new JSpinner.DateEditor(expirySpinner, "dd.MM.yyyy");
        expirySpinner.setEditor(expiryEditor);
        add(expirySpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        JLabel categoryLabel = new JLabel("Category:");
        add(categoryLabel, gbc);
        gbc.gridx = 1;
        String[] cathegoryArray = data.getCategoryArray();
        JComboBox<String> cathegoryBox = new JComboBox<>(cathegoryArray);
        add(cathegoryBox, gbc);

        gbc.insets = new Insets(20, 10, 10, 10);
        gbc.gridy = 9;
        JButton toDoSaveButton = new JButton("Save");
        toDoSaveButton.setEnabled(false);
        add(toDoSaveButton, gbc);


        //Add Action Listeners
        titleTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                toDoSaveButton.setEnabled(!titleTextField.getText().trim().isEmpty());
            }
        });

        permanentCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                whenUrgentSpinner.setEnabled(!permanentCheckBox.isSelected());
                expirySpinner.setEnabled(!permanentCheckBox.isSelected());
                //If unselect permantentCheckBox and urgent still selected, do whenUrgent not functional
                if (urgentRadioButton.isSelected()) {
                    whenUrgentSpinner.setEnabled(false);
                }
            }
        });

        urgentRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                whenUrgentSpinner.setEnabled(!urgentRadioButton.isSelected());
            }
        });

        notUrgentRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!permanentCheckBox.isSelected()) {
                    whenUrgentSpinner.setEnabled(notUrgentRadioButton.isSelected());
                }
            }
        });

        toDoSaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ToDo toDo = new ToDo(titleTextField.getText(), descriptionTextArea.getText(),
                        permanentCheckBox.isSelected(), urgentRadioButton.isSelected(),
                        notUrgentRadioButton.isSelected(), dateSpinnerToLocalDate(whenUrgentSpinner),
                        dateSpinnerToLocalDate(expirySpinner), LocalDate.now(),
                        (String) cathegoryBox.getSelectedItem());
                data.addToDo(toDo);
                System.out.println("ToDo saved.");
                MainView.switchPanel(new OverviewView());
            }
        });
    }

    private LocalDate dateSpinnerToLocalDate(JSpinner jSpinner) {
        Date date = (Date) jSpinner.getValue();
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
