package connection;

import connection.ServerReceiver;
import connection.ServerSender;
import controller.CommandWithAccount;
import controller.CommandWithObject;
import controller.Commandable;
import dragon.Dragon;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class User extends Thread {
    private String login;
    private ServerReceiver receiver;
    private ServerSender sender;
    private App app;
    private HashMap portLoginPassword;
    private ArrayList commandAndArgument;

    public User(String login, App app, ServerReceiver receiver, ServerSender serverSender) {
        this.login = login;
        this.sender = serverSender;
        this.receiver = receiver;
        this.app = app;
    }

    public void setPortLoginPassword(HashMap thisPortLoginPassword) {
        this.portLoginPassword = thisPortLoginPassword;
    }

    public void setCommandAndArgument(ArrayList commandAndArgument) {
        this.commandAndArgument = commandAndArgument;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public void run() {
        int port = Integer.parseInt((String) portLoginPassword.get("port"));
        if (port != -1)
            sender.setPort(port);
        else {
            this.interrupt();
        }
        ArrayList commandAndArgument = null;
        try {
            commandAndArgument = (ArrayList) receiver.receiveCommand();
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
        Commandable command = (Commandable) commandAndArgument.get(0);
        String arg = (String) commandAndArgument.get(1);
        System.out.println("Получена команда \"" + command.getName() + "\" от пользователя \""+ portLoginPassword.get("login") +"\"");
        String commandResult = "";
        try {
            CommandWithAccount commandWithLogin = (CommandWithAccount) command;
            commandWithLogin.setUsername((String) portLoginPassword.get("login"));
        } catch (
                Exception e) {
        }
        try {
            CommandWithObject commandWithObject = (CommandWithObject) command;
            if (commandWithObject.check(Long.parseLong(arg))) {
                sender.setPort(Integer.parseInt((String) portLoginPassword.get("port")));
                sender.send("newObject");
                Dragon dragon;
                boolean tumb = true;
                HashMap packedObject = null;
                List<HashMap> receivedObjects;
                while (tumb) {
                    receivedObjects = ServerReceiver.getReceivedObjects();
                    for (int i = 0; i < receivedObjects.size(); i++) {
                        packedObject = receivedObjects.get(i);
                        if ((packedObject.get("object") != null) || packedObject.get("commandName").equals(commandWithObject.getName())
                                || packedObject.get("portLoginAndPassword") == portLoginPassword) {
                            ServerReceiver.removeReceivedObjects(i);
                            tumb = false;
                        }
                    }
                }
                commandResult = (String) command.execute(packedObject.get("object"));
            } else {
                sender.send("nope");
                commandResult = commandWithObject.whyFailed();
            }
        } catch (Exception e) {
                commandResult =  command.execute(arg);
        }
        try {
            sender.setPort(Integer.parseInt((String) portLoginPassword.get("port")));
            sender.send(commandResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!commandResult.isEmpty())
            System.out.println("Команда успешно выполнена!");
        else System.out.println("");
        this.interrupt();

    }
}
