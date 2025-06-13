package com.example.valid_song_info.controller;

import com.example.valid_song_info.model.Song;
import com.example.valid_song_info.validator.SongValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Controller
@RequestMapping("/song")
public class SongController {
    private final List<Song> songList = new ArrayList<>();

    private AtomicLong idGenerator = new AtomicLong(1);

    @Autowired
    private SongValidator songValidator;

    @GetMapping("/add")
    public String addSong(Model model) {
        model.addAttribute("song", new Song());
        return "song/add";
    }

    @PostMapping("/add")
    public String addSong(@Valid @ModelAttribute Song song, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        songValidator.validate(song, bindingResult);
        if (bindingResult.hasErrors()) {
            return "song/add";
        }
        if (song.getId() == null) {
            song.setId(idGenerator.getAndIncrement());
        }
        songList.add(song);
        redirectAttributes.addFlashAttribute("song", song);
        return "song/view";
    }

    // --- Edit ---
    @GetMapping("/edit/{id}")
    public String editSong(@PathVariable Long id, Model model) {
        Song songToEdit = songList.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (songToEdit == null) {
            return "redirect:/song/add";
        }

        model.addAttribute("song", songToEdit);
        return "song/edit";
    }

    @PostMapping("/edit")
    public String editSong(@Valid @ModelAttribute Song song,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        songValidator.validate(song, bindingResult);
        if (bindingResult.hasErrors()) {
            return "song/edit";
        }

        for (int i = 0; i < songList.size(); i++) {
            if (songList.get(i).getId().equals(song.getId())) {
                songList.set(i, song); // cập nhật
                break;
            }
        }

        redirectAttributes.addFlashAttribute("song", song);
        return "redirect:/song/view";
    }

    @GetMapping("/view")
    public String viewSong(@ModelAttribute("song") Song song, Model model) {
        if (song == null || song.getId() == null) {
            return "redirect:/song/add";
        }

        model.addAttribute("song", song);
        return "song/view";
    }

}
