package com.hzokbe.ongaku.repository.user;

import com.hzokbe.ongaku.model.user.User;
import com.hzokbe.ongaku.utils.hibernate.Hibernate;
import org.hibernate.SessionFactory;

import java.util.Optional;

public class UserRepository {
    private final SessionFactory sessionFactory = Hibernate.getSessionFactory();

    public User create(User user) {
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();

            session.persist(user);

            session.getTransaction().commit();

            return user;
        }
    }

    public boolean existsByUsername(String username) {
        try (var session = sessionFactory.openSession()) {
            return session.createQuery("FROM User", User.class).stream().anyMatch(u -> u.getUsername().equals(username));
        }
    }

    public Optional<User> getByUsername(String username) {
        try (var session = sessionFactory.openSession()) {
            return session.createQuery("FROM User", User.class).stream().filter(u -> u.getUsername().equals(username)).findFirst();
        }
    }
}
