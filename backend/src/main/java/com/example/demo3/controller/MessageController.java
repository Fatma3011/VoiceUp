package com.example.demo3.controller;

import com.example.demo3.entity.Message;
import com.example.demo3.entity.User;
import com.example.demo3.repisotory.MessageRepo;
import com.example.demo3.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.element.Element;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@RestController
@RequestMapping("/Message")
public class MessageController {
    public final MessageService messageService;
    @Autowired
    private MessageRepo messageRepo;

    public MessageController(MessageService messageservice) {
        this.messageService = messageservice;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Message>> getAllMessages(){
        List<Message> message = messageService.findAllMessage();
        return new ResponseEntity(message, HttpStatus.OK);

    }

    @GetMapping("/ListUsers")
    public ResponseEntity<List<User>> getAllFriends(){
        List<Message> message = messageService.findAllMessage();
        ListIterator<Message> msgIterator = message.listIterator();
        List<List<User>> couple_friends=null;
       while (msgIterator.hasNext()) {
        List<User> friends=message.get(msgIterator.nextIndex()).getUser();
        couple_friends.add(friends);
        msgIterator.next();

        }
        return new ResponseEntity(couple_friends, HttpStatus.OK);

    }

    @GetMapping("/{idMessage}")
    public Message getMessage(@RequestParam Long idMessage){
        Message message = messageService.findMessagesByIdMessage(idMessage);
        return message;

    }

     @PostMapping("/saveMessage")
     public Message addMessage(@RequestParam("AudioFile") byte [] file,@RequestBody Message message ) throws IOException, SQLException {
       System.out.println("Original Audio Byte Size - " + file.length );

     if(file!=null) {
        message.setAudio(compressBytes(file));
        message.setInstantdeMessage(new Date());
     }
     return messageRepo.save(message);
    }

    //get the Audio by id Message
    @GetMapping(path = { "/getAudioByEmail/{idMessage}" })
    public Message getAudio(@PathVariable Long idMessage) throws IOException {
        Message message =  messageRepo.findMessagesByIdMessage(idMessage);
        if(message.getAudio()!=null){
            message.setAudio(decompressBytes(message.getAudio()));
        }
        return message;
    }
    //get the Audio By Ids Users
    @GetMapping(path = { "/getAudioByEmail/{id1}/{id2}" })
    public List<byte []> getAudioByIdUsers(@PathVariable Long id1,@PathVariable Long id2) throws IOException {
        List<byte []> resultat = null;
        List<Message> message = messageService.findAllMessage();
        ListIterator<Message> msgIterator = message.listIterator();
        while (msgIterator.hasNext()) {
            List<User> friends = message.get(msgIterator.nextIndex()).getUser();
            if (id1 == friends.get(0).getId() && id2 == friends.get(1).getId()) {

                if (message.get(msgIterator.nextIndex()).getAudio() != null) {
                    message.get(msgIterator.nextIndex()).setAudio(decompressBytes(message.get(msgIterator.nextIndex()).getAudio()));
                    resultat.add(message.get(msgIterator.nextIndex()).getAudio());
                }
            }
            msgIterator.next();
        }

        return resultat;
    }

    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();

        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];

        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Audio Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }

        return outputStream.toByteArray();
    }



}








