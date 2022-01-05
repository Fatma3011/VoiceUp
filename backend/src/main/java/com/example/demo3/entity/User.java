package com.example.demo3.entity;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="User")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable=false,updatable=false)
    private Long id ;

    @Column(name="nom_user")
    private String nom_User;
    @Column(name="email")
    private String email ;
    @Column(name="motdepasse")
    private String motdepasse;

    @ManyToOne
    @JoinColumn(name="idMessage")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User() {
    }

    public User(Long id, String nom_User, String email, String motdepasse) {
        this.id = id;
        this.nom_User = nom_User;
        this.email = email;
        this.motdepasse = motdepasse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;

    }

    public String getNom_User() {
        return nom_User;
    }

    public void setNom_User(String nom_User) {
        this.nom_User = nom_User;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nom_User='" + nom_User + '\'' +
                ", email='" + email + '\'' +
                ", motdepasse='" + motdepasse + '\'' +
                '}';
    }
}


