package com.hzokbe.ongaku.repository.user;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hzokbe.ongaku.model.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}
