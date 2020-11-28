package com.todo.services;

import com.todo.model.Client;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Map;

public interface IClientService extends UserDetailsService {
    public boolean signup(Client client);

    public Map<String, String> signin(Client client);
}
