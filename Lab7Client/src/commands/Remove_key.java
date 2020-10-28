package commands;

import controller.CommandWithAccount;
import controller.Commandable;
import controller.DragonCollection;
import dragon.Dragon;

import java.util.Map;

public class Remove_key implements Commandable, CommandWithAccount {
    String name = "remove_key";
    String username;

    /**
     * delete dragon with this id number
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

