package com.TFG.TFG.Controllers;

import com.TFG.TFG.Models.HToxics;
import com.TFG.TFG.Models.User;
import com.TFG.TFG.Repository.HToxicsRepository;
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

@RestController
@RequestMapping("/htoxics")
public class HToxicsRestController {

    @Autowired
    @Qualifier("HToxicsRepository")
    private HToxicsRepository hToxicsRepository;

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    public HToxicsRestController(HToxicsRepository hToxicsRepository, UserRepository userRepository) {
        this.hToxicsRepository = hToxicsRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(UriComponentsBuilder ucBuilder,  @RequestBody HToxics input) {

        //System.out.println("INPUT ->" + " USER_ID: " + input.getUser_id().getId() + " PREGUNTA: " + input.getPregunta() + " RESPOSTA: " + input.getResposta());
        User u = userRepository.findById(input.getUser_id().getId());
        HToxics results = new HToxics();

        results.setPregunta(input.getPregunta());
        results.setResposta(input.getResposta());

        results.setUser_id(u);
        HToxics finalResult = hToxicsRepository.save(results);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/htoxics/{id}").buildAndExpand(finalResult.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);

    }
}
