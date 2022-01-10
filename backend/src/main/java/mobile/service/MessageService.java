package mobile.service;


import mobile.model.Message;
import mobile.repo.Messagerepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MessageService {
    private final Messagerepo messageRep;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public MessageService(Messagerepo messagerepo, BCryptPasswordEncoder passwordEncoder) {
        this.messageRep = messagerepo;
        this.passwordEncoder = passwordEncoder;
    }

    public Message addMessage(Message message) {
        return messageRep.save(message);
    }

    public List<Message> findAllMessage() {
        return (List<Message>) messageRep.findAll();
    }


    public Message findMessagesByIdMessage(Long idMessage) {
        return messageRep.findMessagesByIdMessage(idMessage);
    }

    public Message saveMessage(Message msg) {
        return   messageRep.save(msg);
    }

}







