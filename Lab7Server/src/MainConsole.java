import connection.App;
import connection.ServerReceiver;
import connection.ServerSender;
import controller.DragonCollection;

import java.io.IOException;
import java.net.BindException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Scanner;

class Main {
    static String filePath;

    public static void main(String[] args) throws IOException, InterruptedException {
        try {
            DragonCollection.dateCreation = LocalDate.now();
            ServerSender sender = new ServerSender(11352);
            ServerReceiver receiver = new ServerReceiver(3924);
            App app = new App(sender, receiver);
            System.out.println("Для сохранения текущего состояния коллекции используйте команду \"save\".");
            receiver.receive();
            app.begin();
            app.run();
        } catch (BindException e) {
            System.out.println("Порт занят.");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void checkFilepath() {
        if (filePath == null) {
            Scanner in = new Scanner(System.in);
            filePath = "";
            while (filePath.equals("")) {
                System.out.print("Введите имя файла:\n~ ");
                filePath = in.nextLine();
            }
        }
    }
}