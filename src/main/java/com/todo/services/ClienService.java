package com.todo.services;

import com.todo.model.Client;
import com.todo.repository.ClientRepository;
import com.todo.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created By ReddyGadu
 * Created On 11/27/20
 * Project Name Todoer
 **/
@Service
public class ClienService implements IClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public boolean signup(Client client) {
        if (userExists(client.getEmail())) {
            return false;
        } else {
            try {
                clientRepository.save(client);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    public Map<String, String> signin(Client c) {
        Optional<Client> client = clientRepository.findByEmailAndPassword(c.getEmail(), c.getPassword());
        if (client.isPresent()) {
            Client current = client.get();
            User ud = new User(current.getEmail(), current.getPassword(), new ArrayList<>());
            String token = jwtUtil.generateToken(ud);
            Map<String, String> cred = new HashMap<>();
            cred.put("email", current.getEmail());
            cred.put("name", current.getName());
            cred.put("token", token);
            return cred;
        }
        return null;
    }

    private boolean userExists(String id) {
        return clientRepository.existsById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client client = clientRepository.findById(email).get();
        return new User(client.getEmail(), client.getPassword(), new ArrayList<>());
    }
}
