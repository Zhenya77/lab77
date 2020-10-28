package commands;

import controller.CommandWithAccount;
import controller.CommandWithObject;
import controller.DragonCollection;
import dragon.Dragon;

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
        Dragon dragon = (Dragon) o;
        dragon.setId(arg);
        DragonCollection.update((Long) arg, dragon);
        return ("Дракон с id[" + arg + "] успешно обновлен.");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean check(long arg) {
        this.arg = arg;
        if (DragonCollection.isKeyFree((Long) arg)) {
            whyFailed = "Дракона с указанымм id не существует";
            return false;
        } else if (DragonCollection.collection.get(arg).getUsername().equals(username))
            return true;
        else {
            whyFailed = "У вас нет прав на изменение этого дракона";
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

