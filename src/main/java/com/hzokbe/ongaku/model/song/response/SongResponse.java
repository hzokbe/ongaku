package com.hzokbe.ongaku.model.song.response;

import java.util.UUID;

public class SongResponse {
    private UUID id;

    private String title;

    public SongResponse(UUID id, String title) {
        this.id = id;

        this.title = title;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
