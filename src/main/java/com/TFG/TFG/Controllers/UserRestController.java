package com.TFG.TFG.Controllers;


import com.TFG.TFG.Models.GoldbergResults;
import com.TFG.TFG.Models.MaslachResults;
import com.TFG.TFG.Models.User;


import com.TFG.TFG.Repository.UserRepository;

import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    public UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> getUserId(UriComponentsBuilder ucBuilder,  @RequestBody User input) {

        if(userRepository.findByMUsername(input.getmUsername()) == null){
            return new ResponseEntity<String>("", HttpStatus.UNAUTHORIZED);
        }else{
            User currentUser = userRepository.findByMUsername(input.getmUsername());
            return new ResponseEntity<User>(currentUser, HttpStatus.OK);

        }
    }

    @RequestMapping(value="/newPasswordRequest",method = RequestMethod.POST)
    ResponseEntity<?> canGenerateNewPassword(UriComponentsBuilder ucBuilder,  @RequestBody User input) {


            User currentUser = userRepository.findByMUsername(input.getmUsername());
            if(currentUser != null) {
                if (currentUser.getmEmail().equals(input.getmEmail())) {
                    return new ResponseEntity<String>("", HttpStatus.OK);
                } else {
                    return new ResponseEntity<String>("", HttpStatus.UNAUTHORIZED);
                }
            }else{
                return new ResponseEntity<String>("", HttpStatus.UNAUTHORIZED);
            }

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("id") long id) {
        User user = userRepository.findById(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(value="/{id}/goldbergresults", method = RequestMethod.GET)
    public ResponseEntity<List<GoldbergResults>> getGoldbergResults(@PathVariable("id") long id) {
        User u = userRepository.findById(id);
        List<GoldbergResults> results = u.getGoldbergStats();
        return new ResponseEntity<List<GoldbergResults>>(results, HttpStatus.OK);
    }

    @RequestMapping(value="/{id}/maslachresults", method = RequestMethod.GET)
    public ResponseEntity<List<MaslachResults>> getMaslachResults(@PathVariable("id") long id) {
        User u = userRepository.findById(id);
        List<MaslachResults> results = u.getBurnOutStats();
        return new ResponseEntity<List<MaslachResults>>(results, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/modify", method = RequestMethod.PUT)
    public ResponseEntity<?> modifyUser(UriComponentsBuilder ucBuilder, @PathVariable("id") long id, @RequestBody User input) {
        User user = userRepository.findById(id);

        if(user != null){

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String password = encoder.encode(input.getPassword());
            user.setPassword(password);

            userRepository.save(user);

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/register/{id}").buildAndExpand(user.getId()).toUri());
            return new ResponseEntity<String>(headers, HttpStatus.CREATED);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/register/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.UNAUTHORIZED);
    }
}
