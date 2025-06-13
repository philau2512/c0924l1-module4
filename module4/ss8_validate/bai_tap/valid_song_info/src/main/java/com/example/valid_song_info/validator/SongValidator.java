package com.example.valid_song_info.validator;

import com.example.valid_song_info.model.Song;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SongValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Song song = (Song) target;

        // Valid title
        if ("".equals(song.getTitle()) || song.getTitle() == null) {
            errors.rejectValue("title", "title.empty", "Tên bài hát không được để trống");
        } else if (song.getTitle().length() > 800) {
            errors.rejectValue("title", "title.length", "Tên bài hát không được quá 800 ký tự");
        } else if (!song.getTitle().matches("^[a-zA-Z0-9_ ]+$")) {
            errors.rejectValue("title", "title.invalid", "Tên bài hát không được chứa ký tự đặc biệt");
        }

        // Valid artist
        if ("".equals(song.getArtist()) || song.getArtist() == null) {
            errors.rejectValue("artist", "artist.empty", "Tên nghệ sĩ không được để trống");
        } else if (song.getArtist().length() > 300 ) {
            errors.rejectValue("artist", "artist.length", "Tên nghệ sĩ không được quá 300 ký tự");
        } else if (!song.getArtist().matches("^[a-zA-Z0-9_ ]+$")) {
            errors.rejectValue("artist", "artist.invalid", "Tên nghệ sĩ không được chứa ký tự đặc biệt");
        }

        // Valid genre
        if ("".equals(song.getGenre()) || song.getGenre() == null) {
            errors.rejectValue("genre", "genre.empty", "Thể loại không được để trống");
        } else if (song.getGenre().length() > 1000 ) {
            errors.rejectValue("genre", "genre.length", "Thể loại không được quá 1000 ký tự");
        } else if (!song.getGenre().matches("^[a-zA-Z0-9_, ]+$")) {
            errors.rejectValue("genre", "genre.invalid", "Thể loại không được chứa ký tự đặc biệt");
        }
    }
}
