package com.hzokbe.ongaku.repository.user;

import com.hzokbe.ongaku.model.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    private final List<User> users = new ArrayList<>();

    public User create(User user) {
        users.add(user);

        return user;
    }

    public boolean existsByUsername(String username) {
        return users.stream().anyMatch(u -> u.username().equals(username));
    }

    public Optional<User> getByUsername(String username) {
        return users.stream().filter(u -> u.username().equals(username)).findFirst();
    }
}
