package ru.yandex.practicum.filmorate.src.main.validator;

import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import org.slf4j.Logger;
import ch.qos.logback.classic.Level;
import ru.yandex.practicum.filmorate.src.main.exception.ValidationErrorException;
import ru.yandex.practicum.filmorate.src.main.model.Film;
import ru.yandex.practicum.filmorate.src.main.model.User;

import static ru.yandex.practicum.filmorate.src.main.enums.StatusEnums.*;


public class Validator {

    private static final LocalDate LIMIT_DATA = LocalDate.of(1895, 12, 28);

    private static final Logger log = LoggerFactory.getLogger(Validator.class);

    static {
        ((ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME))
                .setLevel(Level.INFO);
    }
    public static Boolean isValid(Object object) {
        if (object instanceof Film) {
            Film film = (Film) object;
            if (film.getName() == null || film.getName().trim().isEmpty()) {
                log.error("Валидация фильма не прошла: Название не может быть пустым.");
                throw new ValidationErrorException(VALIDATION_ERROR.getDescription() + " название не может быть пустым");
            } else if (film.getDescription().length() > 200) {
                log.error("Валидация фильма не прошла: Описание превышает максимальную длину в 200 символов.");
                throw new ValidationErrorException(VALIDATION_ERROR.getDescription() + " максимальная длина описания — 200 символов");
            } else if (film.getReleaseDate().isAfter(LIMIT_DATA)) {
                log.error("Валидация фильма не прошла: Дата выхода не может быть позже " + LIMIT_DATA);
                throw new ValidationErrorException(VALIDATION_ERROR.getDescription() + " дата выхода фильма не может быть позже " + LIMIT_DATA);
            } else if (film.getDuration().isNegative() || film.getDuration().isZero()) {
                log.error("Валидация фильма не прошла: Длительность не может быть отрицательной или равной нулю.");
                throw new ValidationErrorException(VALIDATION_ERROR.getDescription() + " длительность фильма не может быть отрицательной или равной: " + film.getDuration());
            }
            return true;

        } else if (object instanceof User) {
            User user = (User) object;
            if (user.getEmail() == null || !user.getEmail().contains("@") || user.getEmail().trim().isEmpty()) {
                log.error("Валидация пользователя не прошла: Неверный формат электронной почты.");
                throw new ValidationErrorException(VALIDATION_ERROR.getDescription() + " неверный формат email");
            } else if (user.getLogin() == null || user.getLogin().trim().isEmpty() || user.getLogin().contains(" ")) {
                log.error("Валидация пользователя не прошла: Неверный формат логина.");
                throw new ValidationErrorException(VALIDATION_ERROR.getDescription() + " неверный формат login");
            } else if (user.getBirthday().isAfter(LocalDate.now())) {
                log.error("Валидация пользователя не прошла: Дата рождения не может быть в будущем.");
                throw new ValidationErrorException(VALIDATION_ERROR.getDescription() + " дата рождения не может быть в будущем");
            }
            return true;
        }
        log.error("Валидация не прошла: Неподдерживаемый тип объекта.");
        return false;
    }
}
