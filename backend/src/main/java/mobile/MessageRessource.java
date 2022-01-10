package mobile;

import mobile.model.Message;
import mobile.model.User;
import mobile.service.MessageService;
import mobile.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@RestController
@RequestMapping("/Message")
public class MessageRessource {
    public final MessageService messageService;
    public final UserService userService;

    public MessageRessource(MessageService messageservice, UserService userService) {
        this.messageService = messageservice;
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Message>> getAllMessages(){
        List<Message> message = messageService.findAllMessage();
        return new ResponseEntity(message, HttpStatus.OK);

    }

    @GetMapping("/ListAll")
    public ResponseEntity<List<User>> getAllFriends(){
        List<Message> message = messageService.findAllMessage();
        ListIterator<Message> msgIterator = message.listIterator();
        List<List<User>> couple_friends=new ArrayList<>();
        while (msgIterator.hasNext()) {
            List<User> friends=message.get(msgIterator.nextIndex()).getUser();

            couple_friends.add(friends);
            msgIterator.next();

        }
       return new ResponseEntity(couple_friends, HttpStatus.OK);
    }

    @GetMapping("/ListUsers/{id}")
    public ResponseEntity<List<User>> getAllFriendsofUser(@PathVariable  Long id) {

        List<Message> message = messageService.findAllMessage();
        ListIterator<Message> msgIterator = message.listIterator();
         List<User> List_friends=new ArrayList<>();


        while (msgIterator.hasNext()) {
            List<User> friends = message.get(msgIterator.nextIndex()).getUser();

            if (id == friends.get(0).getId()) {
                    List_friends.add(new User(friends.get(1).getId(), friends.get(1).getNom_User()));
            }

            if (id == friends.get(1).getId()) {
                List_friends.add(new User(friends.get(0).getId(),friends.get(0).getNom_User()));
            }

            msgIterator.next();

        }
        return new ResponseEntity(List_friends, HttpStatus.OK);
    }



    //save the message given two id of users
    @PostMapping("/saveMessage/{id1}/{id2}")
    public Message addMessage(@RequestBody byte [] file, @PathVariable("id1") Long id1 , @PathVariable("id2") Long id2 ) throws IOException, SQLException {
        System.out.println("Original Audio Byte Size - " + file.length );
        Message message = new Message();
        User user1=userService.findUserById(id1);
        User user2=userService.findUserById(id2);
        List<User> users =new ArrayList<>();
        users.add(user1);
        users.add(user2);
        if(file!=null) {
            message.setUser(users);
            message.setAudio(compressBytes(file));
            message.setInstantdeMessage(new Date());
        }
       return messageService.addMessage(message);
    }

    //get the Audio By Ids Users
    @GetMapping(path = { "/getAudioByUsersId/{id1}/{id2}" })
    public  List<byte[]> getAudioByIdUsers(@PathVariable Long id1,@PathVariable Long id2) throws IOException {
        List<byte []> Discussion = new ArrayList<>();

        List<Message> message = messageService.findAllMessage();
        ListIterator<Message> msgIterator = message.listIterator();

        while(msgIterator.hasNext()) {
            List<User> friends = message.get(msgIterator.nextIndex()).getUser();

           if (id1 == friends.get(0).getId() && id2 == friends.get(1).getId()) {

               if (message.get(msgIterator.nextIndex()).getAudio() != null) {

                   Discussion.add(decompressBytes(message.get(msgIterator.nextIndex()).getAudio()));
                  //return decompressBytes(message.get(msgIterator.nextIndex()).getAudio()) ;
                }
           }
             msgIterator.next();
            }
        System.out.println(Discussion);
        return Discussion;
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








