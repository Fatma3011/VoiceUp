package mobile.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable=false,updatable=false)
    private Long id ;
    //@JsonIgnore
    private String nom_User;
    //@JsonIgnore
    private String email ;
    //@JsonIgnore
    private String motdepasse;
    @ManyToOne(cascade = CascadeType.ALL)
    //@OneToMany
    @JoinColumn(name="idMessage")
    private Message message;

    public User() {
    }

    public User(Long id, String nom_User, String email, String motdepasse, Message message) {
        this.id = id;
        this.nom_User = nom_User;
        this.email = email;
        this.motdepasse = motdepasse;
        this.message = message;
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








