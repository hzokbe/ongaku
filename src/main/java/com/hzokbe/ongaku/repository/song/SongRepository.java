package com.hzokbe.ongaku.repository.song;

import com.hzokbe.ongaku.model.song.Song;

import java.util.UUID;

public class SongRepository {
    private final Song[] songs = {
            new Song(UUID.randomUUID(), "Smooth Criminal", "Michael Jackson"),
            new Song(UUID.randomUUID(), "Money, Money, Money", "ABBA"),
            new Song(UUID.randomUUID(), "Back To Black", "Amy Winehouse"),
            new Song(UUID.randomUUID(), "Enter Sandman", "Metallica")
    };
}
