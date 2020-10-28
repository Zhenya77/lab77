package connection;

public enum AccountVaribales {
    ERROR_1("Пользователь с указанным логином уже существует"),
    ERROR_2("Пользователя с указанным логином не существует"),
    ERROR_3("Пароль указан неверно"),
    OK_1("Пользователь успешно зарегестрирован"),
    OK_2("Вы успешно вошли в аккаунт");
    String message;

    AccountVaribales(String s) {
        message = s;
    }

    public String getMessage() {
        return message;
    }
}
