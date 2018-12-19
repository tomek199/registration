package com.exercise.registration.service;

import com.exercise.registration.dto.User;
import com.exercise.registration.entity.UserEntity;
import com.exercise.registration.mapper.UserMapper;
import com.exercise.registration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper mapper;
    private final UserRepository repository;

    @Autowired
    public UserService(UserMapper mapper, UserRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    /**
     * Register new user
     * @param user User to register
     * @return ResponseEntity object
     */
    public ResponseEntity<User> registerUser(User user) {
        UserEntity userEntity = mapper.convertToEntity(user);
        userEntity = repository.save(userEntity);
        user = mapper.convertToDTO(userEntity);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
