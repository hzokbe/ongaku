package com.hzokbe.ongaku.repository.song;

import com.hzokbe.ongaku.model.song.Song;
import com.hzokbe.ongaku.utils.hibernate.Hibernate;
import org.hibernate.SessionFactory;

import java.util.List;

public class SongRepository {
    private final SessionFactory sessionFactory = Hibernate.getSessionFactory();

    public List<Song> getAll() {
        try (var session = sessionFactory.openSession()) {
            return session.createQuery("FROM Song", Song.class).list();
        }
    }
}
