package commands;


import controller.CommandWithAccount;
import controller.CommandWithoutArg;
import controller.DragonCollection;
import dragon.Dragon;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Clear implements CommandWithoutArg, CommandWithAccount {
    String name = "clear";
    String username;

    /**
     * if collection is not empty than delete all dragons
     *
     * @param o
     */
    @Override
    public String execute(Object o) {
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