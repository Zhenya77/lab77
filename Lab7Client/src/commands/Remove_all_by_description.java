package commands;

import controller.CommandWithAccount;
import controller.Commandable;
import controller.DragonCollection;

public class Remove_all_by_description implements Commandable, CommandWithAccount {
    String name = "remove_all_by_description";
    String username;

    /**
     * delete dragon from the collection with this description
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