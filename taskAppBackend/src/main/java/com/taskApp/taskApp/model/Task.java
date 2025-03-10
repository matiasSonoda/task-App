package com.taskApp.taskApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name="tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTask;
    @NotBlank(message = "Title is mandatory")
    @Size(min = 1, max = 100, message = "Title must be between 1 and 100 characters")
    @Column
    private String title;
    @Size(min = 1, max = 250, message = "Description must be between 1 and 250 characters")
    @Column
    private String description;
    @NotNull(message="Start is required")
    @FutureOrPresent(message = "Start date must be in the future or present")
    @Column(name="start_date")
    private LocalDateTime start;
    
    @NotNull(message="End is required")
    @FutureOrPresent(message = "End date must be in the future or present")
    @Column(name="end_date")
    private LocalDateTime end;
    @Column
    private boolean completed = false;
    @ManyToOne
    @JoinColumn(name = "idUser")
    @JsonBackReference
    private AppUser user;
    
    public Task(){}
    public Task(String title, String description, LocalDateTime start, LocalDateTime end){
        this.title = title;
        this.description = description;
        this.start = start;
        this.end = end;
    }
}
