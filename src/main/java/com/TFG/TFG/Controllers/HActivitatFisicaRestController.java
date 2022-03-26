package com.TFG.TFG.Controllers;

import com.TFG.TFG.Models.HActivitatFisica;
import com.TFG.TFG.Models.User;
import com.TFG.TFG.Repository.HActivitatFisicaRepository;
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
@RequestMapping("/hactivitatfisica")
public class HActivitatFisicaRestController {

    @Autowired
    @Qualifier("HActivitatFisicaRepository")
    private HActivitatFisicaRepository hActivitatFisicaRepository;

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    public HActivitatFisicaRestController(HActivitatFisicaRepository hActivitatFisicaRepository, UserRepository userRepository) {
        this.hActivitatFisicaRepository = hActivitatFisicaRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(UriComponentsBuilder ucBuilder,  @RequestBody HActivitatFisica input) {

        //System.out.println("INPUT ->" + " USER_ID: " + input.getUser_id().getId() + " PREGUNTA: " + input.getPregunta() + " RESPOSTA: " + input.getResposta());
        User u = userRepository.findById(input.getUser_id().getId());
        HActivitatFisica results = new HActivitatFisica();

        results.setPregunta(input.getPregunta());
        results.setResposta(input.getResposta());

        results.setUser_id(u);
        HActivitatFisica finalResult = hActivitatFisicaRepository.save(results);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/hactivitatfisica/{id}").buildAndExpand(finalResult.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);

    }
}
