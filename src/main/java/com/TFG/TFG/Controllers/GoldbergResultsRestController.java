package com.TFG.TFG.Controllers;

import com.TFG.TFG.Models.GoldbergResults;
import com.TFG.TFG.Models.User;
import com.TFG.TFG.Repository.GoldbergResultsRepository;
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
@RequestMapping("/registerGoldbergRes")
public class GoldbergResultsRestController {

    @Autowired
    @Qualifier("goldbergResultsRepository")
    private GoldbergResultsRepository goldbergResultsRepository;

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    public GoldbergResultsRestController(GoldbergResultsRepository goldbergResultsRepository, UserRepository userRepository) {
        this.goldbergResultsRepository = goldbergResultsRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(UriComponentsBuilder ucBuilder, @RequestBody GoldbergResults input) {
        User u = userRepository.findById(input.getUser_id().getId());
        GoldbergResults results = new GoldbergResults();
        results.setDate(input.getDate());
        results.setMalestarPsicologic(input.isMalestarPsicologic());
        results.setUser_id(u);

        GoldbergResults finalResult = goldbergResultsRepository.save(results);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/registerGoldbergRes/{id}").buildAndExpand(finalResult.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);

    }
}
