package com.exercise.registration.repository;

import com.exercise.registration.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Database repository that handling crud operations for user entity
 */
@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    /**
     * Find user by username
     * @param username username to find
     * @return user object
     */
    UserEntity findByUsername(String username);
}
