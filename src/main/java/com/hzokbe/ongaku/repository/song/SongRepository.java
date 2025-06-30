package com.hzokbe.ongaku.repository.song;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hzokbe.ongaku.model.song.Song;

@Repository
public interface SongRepository extends JpaRepository<Song, UUID> {
    boolean existsByTitle(String title);

    Optional<Song> findByTitle(String title);
}
