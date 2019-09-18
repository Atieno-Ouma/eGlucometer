package application.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import domain.dtos.requests.CreateUserRequest;
import domain.entities.User;
import infrastructure.repositories.UsersRepo;

@RestController
public class UserController {
    private final static String baseMapping = "/User";

    @Autowired
    private UsersRepo repo;

    @PostMapping(baseMapping)
    public void create(@RequestBody(required = true) CreateUserRequest request) {
        User newUser = User.fromRequest(request);
        repo.add(newUser);
    }

    @GetMapping(baseMapping + "/{email}")
    public Optional<User> get(@PathVariable String email) {
        return repo.get(email);
    }
}