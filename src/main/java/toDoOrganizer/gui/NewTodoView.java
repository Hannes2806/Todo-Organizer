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
import java.util.Date;

public class NewTodoView extends JPanel {
    private Data data = Data.getInstance();

    private int activeIndex = -1;

    private JTextField titleTextField;
    private JTextArea descriptionTextArea;
    private JCheckBox permanentCheckBox;
    private JRadioButton urgentRadioButton;
    private JRadioButton notUrgentRadioButton;
    private SpinnerDateModel whenUrgentModel;
    private JSpinner whenUrgentSpinner;
    private SpinnerDateModel expiryModel;
    private JSpinner expirySpinner;
    private JComboBox<String> categoryBox;
    private JButton toDoSaveButton;

    public NewTodoView() {
        init();

        //Anschließend actionListeners in controller auslagern
        // - dazu Seitenaufruf im Card layout main optimieren

        //Ist es schon drin, das Todo später automatisch urgent wird?
    }

    private void init() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        initHeadingSection(gbc);
        initTitleSection(gbc);
        initDescriptionSection(gbc);
        initPermanentSection(gbc);
        initUrgencySection(gbc);
        initWhenUrgentSection(gbc);
        initExpirySection(gbc);
        initCategorySection(gbc);
        initSaveSection(gbc);
    }

    private void initHeadingSection(GridBagConstraints gbc) {
        gbc.insets = new Insets(10, 10, 30, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel headingLabel = new JLabel("<html><u>New ToDo</u></html>");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(headingLabel, gbc);
    }

    private void initTitleSection(GridBagConstraints gbc) {
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel titleLabel = new JLabel("Title:");
        add(titleLabel, gbc);
        gbc.gridx = 1;
        titleTextField = new JTextField(30);
        add(titleTextField, gbc);
    }

    private void initDescriptionSection(GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel descriptionLabel = new JLabel("Description:");
        add(descriptionLabel, gbc);
        gbc.gridx = 1;
        descriptionTextArea = new JTextArea(10, 30);
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setWrapStyleWord(true);
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionTextArea);
        add(descriptionScrollPane, gbc);
    }

    private void initPermanentSection(GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel permanentLabel = new JLabel("Permanent:");
        add(permanentLabel, gbc);
        gbc.gridx = 1;
        permanentCheckBox = new JCheckBox();
        add(permanentCheckBox, gbc);
    }

    private void initUrgencySection(GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel urgentLabel = new JLabel("Urgent:");
        add(urgentLabel, gbc);
        gbc.gridx = 1;
        urgentRadioButton = new JRadioButton();
        add(urgentRadioButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        JLabel notUrgentLabel = new JLabel("Not urgent:");
        add(notUrgentLabel, gbc);
        gbc.gridx = 1;
        notUrgentRadioButton = new JRadioButton();
        notUrgentRadioButton.setSelected(true);
        add(notUrgentRadioButton, gbc);

        ButtonGroup classGroup = new ButtonGroup();
        classGroup.add(urgentRadioButton);
        classGroup.add(notUrgentRadioButton);
    }

    private void initWhenUrgentSection(GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = 6;
        JLabel whenUrgentLabel = new JLabel("When urgent:");
        add(whenUrgentLabel, gbc);
        gbc.gridx = 1;
        whenUrgentModel = new SpinnerDateModel();
        whenUrgentSpinner = new JSpinner(whenUrgentModel);
        JSpinner.DateEditor whenUrgentEditor = new JSpinner.DateEditor(whenUrgentSpinner, "dd.MM.yyyy");
        whenUrgentSpinner.setEditor(whenUrgentEditor);
        add(whenUrgentSpinner, gbc);
    }

    private void initExpirySection(GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = 7;
        JLabel expiryLabel = new JLabel("Expiry date:");
        add(expiryLabel, gbc);
        gbc.gridx = 1;
        expiryModel = new SpinnerDateModel();
        expirySpinner = new JSpinner(expiryModel);
        JSpinner.DateEditor expiryEditor = new JSpinner.DateEditor(expirySpinner, "dd.MM.yyyy");
        expirySpinner.setEditor(expiryEditor);
        add(expirySpinner, gbc);
    }

    private void initCategorySection(GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = 8;
        JLabel categoryLabel = new JLabel("Category:");
        add(categoryLabel, gbc);
        gbc.gridx = 1;
        String[] categoryArray = data.getCategoryArray();
        categoryBox = new JComboBox<>(categoryArray);
        add(categoryBox, gbc);
    }

    private void initSaveSection(GridBagConstraints gbc) {
        gbc.insets = new Insets(20, 10, 10, 10);
        gbc.gridy = 9;
        toDoSaveButton = new JButton("Save");
        toDoSaveButton.setEnabled(false);
        add(toDoSaveButton, gbc);
    }

    public void prefillData(int activeIndex) {
        titleTextField.setText(data.getToDo(activeIndex).getTitle());
        descriptionTextArea.setText(data.getToDo(activeIndex).getDescription());
        Date whenUrgentDate = Date.from(data.getToDo(activeIndex).getWhenUrgent().atStartOfDay(ZoneId.systemDefault()).toInstant()); //convert to date
        whenUrgentModel.setValue(whenUrgentDate);
        Date expiryDate = Date.from(data.getToDo(activeIndex).getExpiryDate().atStartOfDay(ZoneId.systemDefault()).toInstant()); //convert to date
        expiryModel.setValue(expiryDate);
        categoryBox.setSelectedItem(data.getToDo(activeIndex).getCategory());
        toDoSaveButton.setEnabled(true);

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
    }

    public void clearData() {
        titleTextField.setText("");
        descriptionTextArea.setText("");
        whenUrgentModel.setValue(new Date());
        expiryModel.setValue(new Date());
        categoryBox.setSelectedItem("Education");
        toDoSaveButton.setEnabled(false);
        permanentCheckBox.setSelected(false);
        whenUrgentSpinner.setEnabled(true);
        expirySpinner.setEnabled(true);
        notUrgentRadioButton.setSelected(true);
    }

    public int getActiveIndex() {
        return activeIndex;
    }

    public void setActiveIndex(int index) {
        activeIndex = index;
    }

    public JTextField getTitleTextField() {
        return titleTextField;
    }

    public JTextArea getDescriptionTextArea() {
        return descriptionTextArea;
    }

    public JCheckBox getPermanentCheckBox() {
        return permanentCheckBox;
    }

    public JRadioButton getUrgentRadioButton() {
        return urgentRadioButton;
    }

    public JRadioButton getNotUrgentRadioButton() {
        return notUrgentRadioButton;
    }

    public JSpinner getWhenUrgentSpinner() {
        return whenUrgentSpinner;
    }

    public JSpinner getExpirySpinner() {
        return expirySpinner;
    }

    public JComboBox<String> getCategoryBox() {
        return categoryBox;
    }

    public JButton getToDoSaveButton() {
        return toDoSaveButton;
    }
}
