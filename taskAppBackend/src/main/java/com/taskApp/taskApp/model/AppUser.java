
package com.taskApp.taskApp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "users")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    @NotBlank(message="Username is required")
    @Column(unique = true)
    private String username;
    @NotBlank(message="Mail is required")
    @Column(name = "mail")
    private String mail;
    @NotBlank(message="Password is required")
    @Column
    private String password;
    @Column
    private String firstname;
    @Column
    private String lastname;
    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name="user_authorities",
                     joinColumns = @JoinColumn(name="user_id"))
    private Set<String> authorities;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true )
    @JsonManagedReference
    private List<Task> tasks;
    
    public AppUser(){
        super();
    }
    public AppUser(String username, String mail, String password,String firstname, String lastname,Set<String> authorities, List<Task> tasks){
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.authorities = authorities;
        this.tasks = tasks;
    }

}
