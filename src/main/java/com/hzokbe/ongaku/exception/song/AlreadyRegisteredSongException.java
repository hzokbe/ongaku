package com.hzokbe.ongaku.exception.song;

public class AlreadyRegisteredSongException extends RuntimeException {
    public AlreadyRegisteredSongException(String message) {
        super(message);
    }
}
