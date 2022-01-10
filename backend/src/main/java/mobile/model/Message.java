package mobile.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Message")
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long idMessage ;


    //@OneToMany(cascade = CascadeType.ALL)
    @ManyToMany(cascade = CascadeType.ALL)
   // @JoinColumn(name = "id")
    private List<User> user;

    public Message(Long idMessage) {
        this.idMessage = idMessage;
    }

    @Lob
    @Column(name = "audio",nullable = true)
    //@JsonIgnore
    private byte[] audio;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    //@JsonIgnore
    private Date instantdeMessage;

    public Date getInstantdeMessage() {
        return instantdeMessage;
    }

    public void setInstantdeMessage(Date instantdeMessage) {
        this.instantdeMessage = instantdeMessage;
    }




    public byte[] getAudio() {
        return audio;
    }

    public void setAudio(byte[] audio) {
        this.audio = audio;
    }

    public Long getIdMessage() {
        return idMessage;
    }
    public void setIdMessage(Long idMessage) {
        this.idMessage = idMessage;
    }

    public List<User> getUser() {
        return user;
    }
    public void setUser(List<User> user) {
        this.user = user;
    }


    public Message(Long idMessage, byte[] audio, List<User> user,Date instant) {
        this.idMessage = idMessage;
        this.audio = audio;
        this.user = user;
        this.instantdeMessage=instant;
    }









    public Message() {

    }
}



