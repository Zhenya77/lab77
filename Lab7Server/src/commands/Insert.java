package commands;


import DBcontroller.DataBaseController;
import controller.CommandWithAccount;
import controller.CommandWithObject;
import controller.DragonCollection;
import dragon.Dragon;

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
        Dragon dragon = (Dragon) o;
        dragon.setId(arg);
        dragon.setUsername(username);
        DragonCollection.insert(arg, dragon);
        DataBaseController.getDataBase().loadCollection(DragonCollection.collection);
        return ("Дракон залетел в коллекцию.");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean check(long arg) {
        this.arg = arg;
        if (DragonCollection.isKeyFree(arg))
            return true;
        else {
            whyFailed = "Указанный id занят.";
            return false;
        }
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

