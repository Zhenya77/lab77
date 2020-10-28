package commands;


import controller.CommandWithAccount;
import controller.Commandable;
import controller.DragonCollection;
import dragon.Dragon;

import java.util.Enumeration;
import java.util.InputMismatchException;

public class Remove_lower_key implements Commandable, CommandWithAccount {
    String name = "remove_lower_key";
    String username;

    /**
     * delete all dragons from the collection with ids lower then appointed
     *
     * @param arg
     */
    @Override
    public String execute(Object arg) {
        return "";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }
}