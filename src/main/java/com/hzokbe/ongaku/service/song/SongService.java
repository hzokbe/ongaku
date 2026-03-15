package com.hzokbe.ongaku.service.song;

import com.hzokbe.ongaku.dto.song.SongDTO;
import com.hzokbe.ongaku.repository.song.SongRepository;

import java.util.List;

public class SongService {
    private final SongRepository repository = new SongRepository();

    public List<SongDTO> getAll() {
        return repository.getAll().stream().map(s -> new SongDTO(s.getId(), s.getTitle(), s.getAuthor())).toList();
    }
}
