package com.TFG.TFG.Controllers;

import com.TFG.TFG.Models.User;
import com.TFG.TFG.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/register")
public class RegisterRestController {

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;


    public RegisterRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(UriComponentsBuilder ucBuilder,  @RequestBody User input) {
        if(userRepository.findByMUsername(input.getmUsername()) != null){
        return new ResponseEntity<String>("", HttpStatus.UNAUTHORIZED);
        }else{
            User user = new User();
            user.setmUsername(input.getmUsername());

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String password = encoder.encode(input.getPassword());
            user.setPassword(password);

            user.setmEmail(input.getmEmail());
            user.setCognom1(input.getCognom1());
            user.setCognom2(input.getCognom2());
            user.setNom(input.getNom());
            user.setEdat(input.getEdat());
            user.setProfessio(input.getProfessio());
            user.setGenere(input.getGenere());
            user.setAnysExp(input.getAnysExp());

            User result = userRepository.save(user);

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/register/{id}").buildAndExpand(result.getId()).toUri());
            return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
    }
}
