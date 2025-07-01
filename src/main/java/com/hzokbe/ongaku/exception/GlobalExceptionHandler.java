package com.hzokbe.ongaku.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hzokbe.ongaku.exception.song.AlreadyRegisteredSongException;
import com.hzokbe.ongaku.exception.song.InvalidSongTitleException;
import com.hzokbe.ongaku.exception.song.SongNotFoundException;
import com.hzokbe.ongaku.model.exception.response.ExceptionResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ExceptionResponse exceptionHandler(Exception exception) {
        return new ExceptionResponse("internal server error");
    }

    @ExceptionHandler(InvalidSongTitleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse invalidSongTitleExceptionHandler(InvalidSongTitleException exception) {
        return new ExceptionResponse(exception.getMessage());
    }

    @ExceptionHandler(AlreadyRegisteredSongException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse alreadyRegisteredSongExceptionHandler(AlreadyRegisteredSongException exception) {
        return new ExceptionResponse(exception.getMessage());
    }

    @ExceptionHandler(SongNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse songNotFoundExceptionHandler(SongNotFoundException exception) {
        return new ExceptionResponse(exception.getMessage());
    }
}
