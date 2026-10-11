package com.taskmanagerapi.entity;

//""""""" Import classes used to represent dates and times. """"""""""""""""""""""""
import java.time.LocalDateTime;
import java.time.LocalDate;

//""""""" Import JPA annotations to map this Java class to a database table. """""""
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

//""""""" Marks this class as a JPA entity so Hibernate can persist tasks in the database.
@Entity 
public class Task {
//""""""" Unique identifier for each task, generated automatically by the database.
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//""""""" Title summarising what the task is about.
    private String tittle;
//""""""" Optional details explaining what needs to be done.
    private String description;
//""""""" Indicates whether the task has been completed.
    private boolean completed;
//""""""" Records the date and time when the task was created.
    private LocalDateTime createdAt;
//""""""" Stores the deadline for completing the task.
    private LocalDate dueDate;
//""""""" Associates this task with a user & Many tasks can belong to the same user.
    @ManyToOne 
    private User user;
   
//""""""" Default constructor required by JPA when creating entity instances.
    public Task() {
    }

//""""""" getters
    public Long getId() {
        return id;
    }

    public String getTittle() {
        return tittle;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public User getUser() {
        return user;
    }

//""""""setters
    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
