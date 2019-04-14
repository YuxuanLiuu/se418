package org.liu.se418.user.repository;

import org.liu.se418.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Transactional
    Long deleteByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}