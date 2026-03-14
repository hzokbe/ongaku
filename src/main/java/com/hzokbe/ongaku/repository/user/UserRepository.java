package com.hzokbe.ongaku.repository.user;

import com.hzokbe.ongaku.model.user.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private final List<User> users = new ArrayList<>();

    public User create(User user) {
        users.add(user);

        return user;
    }

    public boolean existsByUsername(String username) {
        return users.stream().anyMatch(u -> u.username().equals(username));
    }
}
