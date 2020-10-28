package commands;

import controller.CommandWithAccount;
import controller.CommandWithObject;

public class Update implements CommandWithObject, CommandWithAccount {
    String name = "update";
    String whyFailed = "";
    long arg;
    String username;

    /**
     * update dragons parametres with appointed id number
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
    public boolean check(long arg) {
        return false;
    }


    @Override
    public String whyFailed() {
        return whyFailed;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }
}

