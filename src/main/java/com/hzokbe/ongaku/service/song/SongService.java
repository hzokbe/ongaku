package com.hzokbe.ongaku.service.song;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hzokbe.ongaku.exception.song.AlreadyRegisteredSongException;
import com.hzokbe.ongaku.exception.song.InvalidSongTitleException;
import com.hzokbe.ongaku.model.song.Song;
import com.hzokbe.ongaku.model.song.request.SongRequest;
import com.hzokbe.ongaku.model.song.response.SongResponse;
import com.hzokbe.ongaku.repository.song.SongRepository;

@Service
public class SongService {
    private final SongRepository repository;

    public SongService(SongRepository repository) {
        this.repository = repository;
    }

    public SongResponse save(SongRequest request) {
        var title = request.getTitle();

        if (title == null) {
            throw new InvalidSongTitleException("song title cannot be null");
        }

        title = title.trim();

        if (title.isBlank()) {
            throw new InvalidSongTitleException("song title cannot be blank");
        }

        if (repository.existsByTitle(title)) {
            throw new AlreadyRegisteredSongException("song already registered");
        }

        var song = new Song(title);

        song = repository.save(song);

        return new SongResponse(song.getId(), title);
    }

    public List<SongResponse> findAll() {
        return repository.findAll().stream().map(u -> new SongResponse(u.getId(), u.getTitle())).toList();
    }
}
