package com.TFG.TFG.Controllers;

import com.TFG.TFG.Models.MaslachResults;
import com.TFG.TFG.Models.User;
import com.TFG.TFG.Repository.MaslachResultsRepository;
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
@RequestMapping("/registerMaslachRes")
public class MaslachResultsRestController {

    @Autowired
    @Qualifier("maslachResultsRepository")
    private MaslachResultsRepository maslachResultsRepository;

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    public MaslachResultsRestController(MaslachResultsRepository maslachResultsRepository, UserRepository userRepository) {
        this.maslachResultsRepository = maslachResultsRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(UriComponentsBuilder ucBuilder, @RequestBody MaslachResults input) {
        User u = userRepository.findById(input.getUser_id().getId());
        MaslachResults results = new MaslachResults();
        results.setDate(input.getDate());
        results.setEmotionalExhaust(input.getEmotionalExhaust());
        results.setDespersonalization(input.getDespersonalization());
        results.setPersonalFullfillment(input.getPersonalFullfillment());
        results.setEmotionalExhaustValoration(input.getEmotionalExhaustValoration());
        results.setDespersonalizationValoration(input.getDespersonalizationValoration());
        results.setPersonalFullfillmentValoration(input.getPersonalFullfillmentValoration());
        results.setUser_id(u);

        MaslachResults finalResult = maslachResultsRepository.save(results);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/registerMaslachRes/{id}").buildAndExpand(finalResult.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);

    }
}
