package com.TFG.TFG.Controllers;

import com.TFG.TFG.Models.User;
import com.TFG.TFG.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginRestController {

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;


    public LoginRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<?> add(UriComponentsBuilder ucBuilder,  @RequestBody User input) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(userRepository.findByMUsername(input.getmUsername()) == null){
            return new ResponseEntity<String>("", HttpStatus.UNAUTHORIZED);
        }else{
            User currentUser = userRepository.findByMUsername(input.getmUsername());
            if(encoder.matches(input.getPassword(), currentUser.getPassword())){
                return new ResponseEntity<User>(currentUser, HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>("", HttpStatus.UNAUTHORIZED);
    }

}