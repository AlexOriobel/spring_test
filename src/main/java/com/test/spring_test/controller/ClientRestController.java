package com.test.spring_test.controller;

import com.test.spring_test.dao.ClientRepository;
import com.test.spring_test.model.Client;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Аннотация, которая превращает класс в контроллер-бин (дает возможность обращаться к методам по http)
@AllArgsConstructor
@RequestMapping("/api/client")
public class ClientRestController {

    private final ClientRepository clientRepository;

    @GetMapping // Get all clients
    public ResponseEntity<Iterable<Client>> getClientLst() {
        return new ResponseEntity<>(clientRepository.findAll()
                , HttpStatus.OK);
    }

    @GetMapping("{id}") // Get client by id
    public ResponseEntity<Client> getClient(@PathVariable Long id) {
        return new ResponseEntity<>(clientRepository.findById(id)
                .orElseThrow(RuntimeException::new), HttpStatus.OK);
    }

    // POST add new Client
    @PostMapping
    public ResponseEntity<Client> creatClient(@RequestBody Client client) {
        Client saveClient = clientRepository.save(client);
        return new ResponseEntity<>(saveClient, HttpStatus.CREATED);
    }




}

