package com.hzokbe.ongaku.controller.song;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hzokbe.ongaku.service.song.SongService;

@RestController
@RequestMapping("/songs")
public class SongController {
    private final SongService service;

    public SongController(SongService service) {
        this.service = service;
    }
}
