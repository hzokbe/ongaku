package com.hzokbe.ongaku.service.song;

import com.hzokbe.ongaku.dto.song.SongDTO;
import com.hzokbe.ongaku.repository.song.SongRepository;

import java.util.Arrays;

public class SongService {
    private final SongRepository repository = new SongRepository();

    public SongDTO[] getAll() {
        return Arrays
                .stream(repository.getAll())
                .map(s -> new SongDTO(s.id(), s.title(), s.author()))
                .toArray(SongDTO[]::new);
    }
}
