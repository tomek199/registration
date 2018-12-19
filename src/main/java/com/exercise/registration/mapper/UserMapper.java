package com.exercise.registration.mapper;

import com.exercise.registration.dto.User;
import com.exercise.registration.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class UserMapper implements Mapper<UserEntity, User> {

    @Override
    public UserEntity convertToEntity(User dto) {
        return new UserEntity(dto.getUsername(), dto.getPassword());
    }

    @Override
    public User convertToDTO(UserEntity entity) {
        User user = new User();
        user.setUsername(entity.getUsername());
        return user;
    }
}
