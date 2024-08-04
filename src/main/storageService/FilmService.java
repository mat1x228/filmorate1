package ru.yandex.practicum.filmorate.src.main.storageService;


import ru.yandex.practicum.filmorate.src.main.interfaces.FilmInterface;
import ru.yandex.practicum.filmorate.src.main.model.Film;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class FilmService implements FilmInterface {

    static HashMap<Integer, Film> filmStorage = new HashMap<Integer, Film>();

    private static final AtomicInteger FILM_ID_HOLDER = new AtomicInteger();


    public  Boolean createFilm(Film film) {
        int id = FILM_ID_HOLDER.incrementAndGet();
        filmStorage.put(id, film);
        return true;
    }

    @Override
    public List<Film> getFilms() {
        Collection<Film> values = filmStorage.values();
        return new ArrayList<>(values);
    }

    @Override
    public Boolean updateFilm(Film film) {
        if(filmStorage.containsKey(film.getId())) {
            Integer filmId = film.getId();
            filmStorage.remove(film.getId());
            filmStorage.put(filmId, film);
            return true;
        }
        return false;
    }

}
