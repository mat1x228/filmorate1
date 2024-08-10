package main.enums;

public enum StatusEnums {
    UPDATE_STATUS (201, "Данные обновленны: "),
    CREATE_STATUS (201, "Данные созданы: "),
    NOT_FOUND(404, "Данные не найдены"),

    VALIDATION_ERROR(500, "Ошибка валидации ");


    private final int code;
    private final String description;

    private StatusEnums(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public int getCode() {
        return this.code;
    }
}

