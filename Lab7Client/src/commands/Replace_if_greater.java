package commands;

import controller.CommandWithObject;

public class Replace_if_greater implements CommandWithObject {
    String name = "replace_if_greater";
    String whyFailed;
    String username;
    long arg;

    /**
     * replace current dragon with new one if parametres values are greater
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
}


