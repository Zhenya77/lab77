package connection;

import DBcontroller.DataBaseController;
import DBcontroller.UsersDataBase;
import commands.Save;
import controller.DragonCollection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;

public class App {
    private static ServerReceiver receiver;
    private static ServerSender sender;
    private DataBaseController dataBase;
    private static App app;

    public App(ServerSender sender, ServerReceiver receiver) throws SQLException, ParseException, IOException {
        this.receiver = receiver;
        this.sender = sender;

        dataBase = new DataBaseController();
        DragonCollection.collection = (DataBaseController.getDataBase().getCollection());
        app = this;
        app.checkForSaveCommand();
        if ((DragonCollection.collection.size() == 0))
            System.out.println("Коллекция пустая.");
        else
            System.out.println("Коллекция заполнена " + DragonCollection.collection.size() + " объектами.");
    }

    public static ServerReceiver getReceiver() {
        return receiver;
    }

    public static ServerSender getSender() {
        return sender;
    }

    public void begin() {
        try {
            UsersDataBase users = dataBase.getUserDataBase();
            HashMap loginPassword = (HashMap) App.receiver.receiveObject();
            sender.setPort(Integer.parseInt((String) loginPassword.get("port")));
            String login = String.valueOf(loginPassword.get("login"));
            String password = Coder.code(String.valueOf(loginPassword.get("password")));
            if (loginPassword.get("reg").equals("2"))
                if (!(users.isValue("login", login))) {
                    users.addUser(login, password);
                    sender.send("valid");
//                    sender.send(new CollectionShell(CollectionCity.cities,
//                            CollectionCity.CreationDate));
                } else sender.send("notValid");
            else if (loginPassword.get("reg").equals("1")) {
                if (users.isValue("login", login)) {
                    if (users.isValue("password", password)) {
                        sender.send("valid");
                    } else sender.send("notValid");
                } else sender.send("notValid");
            }
            System.out.println("Клиент [" + loginPassword.get("login") + "] подключен к серверу.\n");

        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    public void run() {
        try {
            while (true) {
                HashMap loginPassword = null;
                loginPassword = (HashMap) receiver.receiveObject();
                String login = (String) loginPassword.get("login");
                if (DataBaseController.getUserDataBase().checkLoginAndPassword(login, (Coder.code((String) loginPassword.get("password")))) == true) {
                    User user = new User(login, this, receiver, sender);
                    user.setPortLoginPassword(loginPassword);
                    user.start();
                    Thread.currentThread().sleep(20);
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
            this.begin();
            this.run();

        }
    }


    public void checkForSaveCommand() throws IOException {
        Thread backgroundReaderThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
                    while (!Thread.interrupted()) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        if (line.equalsIgnoreCase("save")) {
                            Save save = new Save();
                            save.execute(null);
                        }
                        if (line.equalsIgnoreCase("exit")) {
                            new Save().execute(null);
                            System.exit(0);
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        backgroundReaderThread.setDaemon(true);
        backgroundReaderThread.start();
    }
}




