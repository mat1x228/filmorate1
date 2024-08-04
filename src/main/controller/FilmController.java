package ru.yandex.practicum.filmorate.src.main.controller;

import ch.qos.logback.classic.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.src.main.model.Film;
import ru.yandex.practicum.filmorate.src.main.storageService.FilmService;
import ru.yandex.practicum.filmorate.src.main.validator.Validator;

import java.util.List;

import static ru.yandex.practicum.filmorate.src.main.enums.StatusEnums.*;


@RestController
@RequestMapping("/api/filmorate")
public class FilmController {

    private static final Logger log = LoggerFactory.getLogger(Validator.class);

    static {
        ((ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME))
                .setLevel(Level.INFO);
    }

    FilmService filmService = new FilmService();

    @GetMapping
    public String homePage() {
        return "Film Raiting";
    }
    @GetMapping("/films")
    public List<Film> getAllFilms() {
        log.info("Получение всех фильмов: " + filmService.getFilms().size());
       return filmService.getFilms();
    }

    @PostMapping("/film")
    public String createFilm(@RequestBody Film film) {
        if (Validator.isValid(film)) {
            log.info("Фильм создан: {}", film.getName());
            filmService.createFilm(film);
            log.trace("Название фильма: {}, Описание фильма: {}, Дата выхода фильма: {}, Продолжительность фильма: {}",
                    film.getName(), film.getDescription(), film.getReleaseDate(), film.getDuration());
            return CREATE_STATUS.getDescription() + film.getName();
        } else {
            return NOT_FOUND.getDescription();
        }
    }


    @PutMapping("/film/{id}")
    public String updateFilm(@RequestBody Film film){
        log.info("Обновление фильм");
        boolean filmUpdated = filmService.updateFilm(film);
        if (filmUpdated) {
            log.trace("Название фильма: {}, Описание фильма: {}, Дата выхода фильма: {}, Продолжительность фильма: {}",
                    film.getName(), film.getDescription(), film.getReleaseDate(), film.getDuration());
            return UPDATE_STATUS.getDescription();
        } else {
            return NOT_FOUND.getDescription();
        }
    }

}
