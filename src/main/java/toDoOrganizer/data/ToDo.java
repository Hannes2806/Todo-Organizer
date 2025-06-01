package toDoOrganizer.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class ToDo {
    private String title;
    private String description;

    private boolean permanence;
    private boolean urgent;
    private boolean notUrgent;
    private LocalDate whenUrgent;
    private LocalDate expiryDate;
    private LocalDate creationDate;

    private String category; //shopping, school, work,...

    @JsonCreator
    public ToDo(@JsonProperty("title") String title, @JsonProperty("description") String description, @JsonProperty("permanence") boolean permanence, @JsonProperty("urgent") boolean urgent, @JsonProperty("notUrgent") boolean notUrgent, @JsonProperty("whenUrgent") LocalDate whenUrgent, @JsonProperty("expiryDate") LocalDate expiryDate, @JsonProperty("creationDate") LocalDate creationDate, @JsonProperty("category") String category) {
        this.title = title;
        this.description = description;
        this.permanence = permanence;
        this.urgent = urgent;
        this.notUrgent = notUrgent;
        this.whenUrgent = whenUrgent;
        this.expiryDate = expiryDate;
        this.creationDate = creationDate;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }

    public boolean getPermanence() {
        return permanence;
    }

    public boolean getUrgent() {
        return urgent;
    }

    public boolean getNotUrgent() {
        return notUrgent;
    }

    public LocalDate getWhenUrgent() {
        return whenUrgent;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public String getCategory() {
        return category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPermanence(boolean permanence) {
        this.permanence = permanence;
    }

    public void setUrgent(boolean urgent) {
        this.urgent = urgent;
    }

    public void setNotUrgent(boolean notUrgent) {
        this.notUrgent = notUrgent;
    }

    public void setWhenUrgent(LocalDate whenUrgent) {
        this.whenUrgent = whenUrgent;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
