package com.mobile;

import com.mobile.model.User;
import com.mobile.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/user")
public class UserRessource {
    public final UserService userservice;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserRessource(UserService userservice,BCryptPasswordEncoder passwordEncoder) {
        this.userservice = userservice;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> user = userservice.findAllUser();
        return new ResponseEntity(user, HttpStatus.OK);

    }
    @GetMapping("/find/{email}")
    public ResponseEntity<User> getUserByEmail (@PathVariable(name ="email") String email){
        User user= userservice.findUserByEmail(email);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PostMapping("/add")
    public String registerUser(@RequestBody User user) throws Exception{
        String mail=user.getEmail();
        if(mail != null && !"".equals(mail)){
            User user1 = userservice.findUserByEmail(mail);
            if(user1 != null){
                throw new Exception("User deja existe");
            }
        }
        User newUser = userservice.addUser(user);
        return "Bonjour " + newUser .getNom_User()  + " ton Inscription est effectuée ! ";
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        User newUser = userservice.updateUser(user);
        return new ResponseEntity<>(newUser,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser (@PathVariable("id") Long id){
       userservice.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/loginUser")
    public User loginUser(@RequestBody User user) throws Exception{
        String tempEmail= user.getEmail();
        String tempPass = user.getMotdepasse();
        User user1 =userservice.findUserByEmail(tempEmail); // l'email est unique donc on trouvera jamais deux candidats avec la meme adresse email
        if(passwordEncoder.matches(tempPass,user1.getMotdepasse())){ // comparer le mot de passe entré par l'utilisateur et celui dans la bdd avec la meme @email

            return user1;
        }

        else {
            throw new Exception("Bad Credentials");
        }
    }
}








