package com.TFG.TFG.Controllers;

import com.TFG.TFG.Models.HAlimentacio;
import com.TFG.TFG.Models.User;
import com.TFG.TFG.Repository.HAlimentacioRepository;
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
@RequestMapping("/halimentacio")
public class HAlimentacioRestController {

    @Autowired
    @Qualifier("HAlimentacioRepository")
    private HAlimentacioRepository hAlimentacioRepository;

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    public HAlimentacioRestController(HAlimentacioRepository hAlimentacioRepository, UserRepository userRepository) {
        this.hAlimentacioRepository = hAlimentacioRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(UriComponentsBuilder ucBuilder,  @RequestBody HAlimentacio input) {

        //System.out.println("INPUT ->" + " USER_ID: " + input.getUser_id().getId() + " PREGUNTA: " + input.getPregunta() + " RESPOSTA: " + input.getResposta());
        User u = userRepository.findById(input.getUser_id().getId());
        HAlimentacio results = new HAlimentacio();

        results.setPregunta(input.getPregunta());
        results.setResposta(input.getResposta());

        results.setUser_id(u);
        HAlimentacio finalResult = hAlimentacioRepository.save(results);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/halimentacio/{id}").buildAndExpand(finalResult.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);

    }

}
