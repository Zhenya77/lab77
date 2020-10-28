package utilities;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Scanner;

public class Console {
    private static final Scanner in = new Scanner(System.in);

    public static int readPort() {
        String port = "";
        try {
            System.out.print("Порт:\n~ ");

            port = in.nextLine();
            if (port.replace(" ", "").equals("")) return readPort();
            return Integer.parseInt(port);
        } catch (Exception e) {
            return readPort();
        }
    }

    public static String readHost() {
        System.out.print("Хост:\n~ ");
        String host = in.nextLine();
        if (host.replace(" ", "").equals("")) return readHost();
        return host;
    }
    public static long register()  {
        System.out.print("Аутентификация\\Авторизация(1\\2):\n$ ");
        String s = in.nextLine();
        if (s.equals("1") || s.equals("2")) return Long.parseLong(s);
        else return register();
//        //String s = "1";
//        if (s.equals("1") || s.equals("2")) {
//            HashMap loginAndPassword = this.getLoginAndPassword();
//            loginAndPassword.put("reg", s);
//            return loginAndPassword;
//        } else return this.register();
    }
    public HashMap register1() throws NoSuchAlgorithmException {
        System.out.print("Аутентификация\\Авторизация(1\\2):\n$ ");
        String s = in.nextLine();
        //String s = "log";
        if (s.equals("1") || s.equals("2")) {
            HashMap loginAndPassword = this.getLoginAndPassword();
            loginAndPassword.put("reg", s);
            return loginAndPassword;
        } else return this.register1();
    }

    public HashMap getLoginAndPassword() throws NoSuchAlgorithmException {
        HashMap loginAndPassword = new HashMap();
        loginAndPassword.put("login", this.getLogin());
        loginAndPassword.put("password", this.getPassword());
        return loginAndPassword;
    }

    public static String getLogin() {
        System.out.print("login:\n$ ");
        String login = in.nextLine();
        //String login = "1";
        if (login.equals("")) return getLogin();
        return login;
    }

    public static String getPassword() {
        System.out.print("password:\n$ ");
        String password = in.nextLine();
        //String password = "1";
        if (password.equals("")) return getPassword();
        return password;
    }
}
