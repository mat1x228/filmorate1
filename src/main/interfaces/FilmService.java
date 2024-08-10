package main.interfaces;


import main.model.Film;

import java.util.List;

public interface FilmService {

    Film createFilm(Film film);
    List<Film> getFilms();
    Film updateFilm(Film film);
}
