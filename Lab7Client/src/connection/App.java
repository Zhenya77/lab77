package connection;


import controller.ClientCommand;
import controller.Commands;
import dragon.Dragonborn;
import utilities.Console;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Scanner;

public class App {
    private static ClientReceiver receiver;
    private static ClientSender sender;
    boolean was = false;
    private Commands command = new Commands();
    private Scanner in = new Scanner(System.in);
    private CommandsToSend commandsToSend = new CommandsToSend();
    private HashMap loginAndPassword = null;
    private Console console = new Console();

    public App(ClientSender sender, ClientReceiver receiver) throws NoSuchAlgorithmException {
        this.receiver = receiver;
        this.sender = sender;
    }


    public static ClientReceiver getReceiver() {
        return receiver;
    }

    public static ClientSender getSender() {
        return sender;
    }

    public void begin() throws IOException {
        try {
            String ans = "notValid";
            if (loginAndPassword == null) {
                while (ans.equals("notValid")) {
                    loginAndPassword = console.register1();
                    loginAndPassword.put("port", String.valueOf(sender.getClientPort()));
                    sender.send("-1");
                    sender.send(loginAndPassword);
                    ans = receiver.receive();
                    if (ans.equals("notValid"))
                        if (loginAndPassword.get("reg").equals("1")) {
                            System.out.println("Проверьте логин или пароль.");
                            receiver.receive();
                        } else {
                            System.out.println("Пользователь с таким логином уже зарегистрирован.");
                            receiver.receive();
                        }
                }
                if (loginAndPassword.get("reg").equals("1"))
                    System.out.println("Пользователь успешно авторизирован.");
                else System.out.println("Пользователь успешно зарегистрирован.");
                sender.setPortLoginAndPassword(loginAndPassword);
            } else {
                sender.send("-1");
                sender.send(loginAndPassword);
            }
        } catch (SocketTimeoutException | NoSuchAlgorithmException e) {
            loginAndPassword = null;
            System.out.println("Unable to access");
            this.begin();
            was = false;
        }
    }


    public void run() throws IOException {
        while (true) {
            System.out.print("$ ");
            String commandName = in.nextLine();
            command.setCommand(commandName);
            if (command.getMessage() != (null))
                System.out.println(command.getMessage());
            if (command.getCommand() != null) {
                try {
                    try {
                        ClientCommand clientCommand = (ClientCommand) command.getCommand();
                        System.out.println(command.getCommand().execute(command.getArg()));
                    } catch (ClassCastException e) {
                        if (command.getCommand().getName().equals("exit")) {
                            sender.sendCommand(command);
                            command.getCommand().execute(command.getArg());
                        } else {
                            sender.sendCommand(command);
                            if (sender.isCommandWithObject())
                                if (receiver.receive().equals("newObject")) {
                                    HashMap packedObject = new HashMap();
                                    packedObject.put("object", new Dragonborn().create());
                                    packedObject.put("commandName", command.getCommand().getName());
                                    packedObject.put("portLoginAndPassword", loginAndPassword);
                                    sender.send(packedObject);
                                }
                            String received = receiver.receive();
                            if (received.equals("valid") || received.equals("notValid") )
                                received = receiver.receive();
                            System.out.println(received);
                        }
                    }
                    if (!(commandsToSend.getCommandsToSend().equals(""))) {
                        commandsToSend.removeCommandsToSend();
                    }
                } catch (SocketTimeoutException | ClassNotFoundException e) {
                    // e.printStackTrace();
                    System.out.println("Unable to access");
                    commandsToSend.addCommandsToSend(commandName + "\n");
                    //if (was == false)
                    this.run();
                }
            }
        }
    }
}
