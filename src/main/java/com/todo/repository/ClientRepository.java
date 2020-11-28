package com.todo.repository;

import com.todo.model.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, String> {
    public Optional<Client> findByEmailAndPassword(String email, String password);
}
