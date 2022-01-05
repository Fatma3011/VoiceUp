package com.example.demo3.service;

import com.example.demo3.entity.Message;
import com.example.demo3.entity.User;
import com.example.demo3.repisotory.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MessageService {
    private final MessageRepo messageRep;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public MessageService(MessageRepo messagerepo, BCryptPasswordEncoder passwordEncoder) {
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









