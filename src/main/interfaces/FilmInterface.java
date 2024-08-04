package ru.yandex.practicum.filmorate.src.main.interfaces;



import ru.yandex.practicum.filmorate.src.main.model.Film;

import java.util.List;

public interface FilmInterface {

    Boolean createFilm(Film film);

    List<Film> getFilms();

    Boolean updateFilm(Film film);

}
