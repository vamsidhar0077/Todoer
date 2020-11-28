package com.todo.controller;

import com.todo.model.Client;
import com.todo.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created By ReddyGadu
 * Created On 11/27/20
 * Project Name Todoer
 **/
@RestController
@RequestMapping("todoer/client/")
public class ClientController {

    @Autowired
    private IClientService clientService;

    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public ResponseEntity signup(@RequestBody Client client) {
        boolean signedup = clientService.signup(client);
        if (signedup) {
            return ResponseEntity.ok("Welcome " + client.getName());
        } else {
            return ResponseEntity.badRequest().body("Email already exits");
        }
    }

    @RequestMapping(value = "signin", method = RequestMethod.GET)
    public ResponseEntity login(@RequestBody Client client) {
        Map<String, String> cred = clientService.signin(client);
        if (cred != null) {
            return ResponseEntity.ok(cred);
        } else {
            return ResponseEntity.status(403).body("Invalid credentials");
        }
    }
}
