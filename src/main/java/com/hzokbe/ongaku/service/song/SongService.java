package com.hzokbe.ongaku.service.song;

import org.springframework.stereotype.Service;

import com.hzokbe.ongaku.repository.song.SongRepository;

@Service
public class SongService {
    private final SongRepository repository;

    public SongService(SongRepository repository) {
        this.repository = repository;
    }
}
