package com.hzokbe.ongaku.controller.song;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hzokbe.ongaku.model.song.request.SongRequest;
import com.hzokbe.ongaku.model.song.response.SongResponse;
import com.hzokbe.ongaku.service.song.SongService;

@RestController
@RequestMapping("/songs")
public class SongController {
    private final SongService service;

    public SongController(SongService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SongResponse> save(@RequestBody SongRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
    }

    @GetMapping
    public ResponseEntity<List<SongResponse>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }
}
