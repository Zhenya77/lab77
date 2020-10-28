package commands;


import controller.CommandWithAccount;
import controller.CommandWithObject;

public class Insert implements CommandWithObject, CommandWithAccount {
    String name = "insert";
    String whyFailed = "";
    String username;
    long arg;

    /**
     * creates a new dragon if this id is free to use
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

