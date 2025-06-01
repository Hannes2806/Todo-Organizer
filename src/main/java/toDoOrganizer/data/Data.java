package toDoOrganizer.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.swing.*;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Data {
    //Singleton
    private static Data instance;
    private ArrayList<ToDo> toDoList;
    private DefaultListModel<ToDo> toDoListModel;
    private static final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private static final File FILE = new File("toDos.json");
    private static final String[] categoryArray = new String[5];

    public static Data getInstance() {
        if (instance == null) {
            instance = new Data();
        }
        return instance;
    }

    private Data() {
        setCathegoryArray();

        mapper.registerModule(new JavaTimeModule());
        this.toDoList = load();
    }

    private void setCathegoryArray() {
        categoryArray[0] = "Education";
        categoryArray[1] = "Work";
        categoryArray[2] = "Housework";
        categoryArray[3] = "Leisure";
        categoryArray[4] = "Other";
    }

    public String[] getCategoryArray() {
        return categoryArray;
    }

    private static ArrayList<ToDo> load() {
        try {
            if (!FILE.exists()) {
                return new ArrayList<>();
            }
            ToDo[] toDoArray = mapper.readValue(FILE, ToDo[].class);
            System.out.println("data loaded.");
            return new ArrayList<>(Arrays.asList(toDoArray));
        } catch (Exception e) {
            System.err.println("load falure: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void save(List<ToDo> toDoList) {
        try {
            mapper.writeValue(FILE, toDoList);
        } catch (Exception e) {
            System.err.println("save falure: " + e.getMessage());
        }
    }

    public void addToDo(ToDo toDo) {
        this.toDoList.add(toDo);
        save(this.toDoList);
        System.out.println("ToDo saved.");
    }

    public void deleteToDo(int activeIndex) {
        toDoList.remove(activeIndex);
        save(this.toDoList);
        System.out.println("ToDo deleted.");
    }

    public void updateToDo(ToDo toDo, int index) {
        getToDo(index).setTitle(toDo.getTitle());
        getToDo(index).setDescription(toDo.getDescription());
        getToDo(index).setPermanence(toDo.getPermanence());
        getToDo(index).setUrgent(toDo.getUrgent());
        getToDo(index).setNotUrgent(toDo.getNotUrgent());
        getToDo(index).setWhenUrgent(toDo.getWhenUrgent());
        getToDo(index).setExpiryDate(toDo.getExpiryDate());
        getToDo(index).setCategory(toDo.getCategory());
        save(this.toDoList);
        System.out.println("ToDo updated.");
    }

    public ArrayList<ToDo> getToDoList() {
        return this.toDoList;
    }

    public DefaultListModel<ToDo> getToDoListModel() {
        return toDoListModel;
    }

    public DefaultListModel<String> getTitles(ArrayList<ToDo> filteredList) {
        DefaultListModel<String> titlesModel = new DefaultListModel<>();
        for (ToDo toDo : filteredList) {
            titlesModel.addElement(toDo.getTitle());
        }
        return titlesModel;
    }

    public DefaultListModel<ToDo> filterUrgency(ArrayList<ToDo> list, Boolean urgent) {
        DefaultListModel<ToDo> filteredList = new DefaultListModel<>();
        list.stream()
                .filter(toDo -> toDo.getUrgent() == urgent)
                .forEach(filteredList::addElement);
        return filteredList;
    }

    public DefaultListModel<ToDo> filterDate(ArrayList<ToDo> list, LocalDate specificDate) {
        DefaultListModel<ToDo> filteredList = new DefaultListModel<>();
        list.stream()
                .filter(toDo -> toDo.getExpiryDate().equals(specificDate))
                .forEach(filteredList::addElement);
        return filteredList;
    }

    public ToDo getToDo(int index) {
        return toDoList.get(index);
    }
}
