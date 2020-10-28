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
        boolean was = false;
        if (DragonCollection.getSize() == 0) {
            return "Коллекция пуста";
        } else {
            Set<Map.Entry<Long, Dragon>> entries = DragonCollection.collection.entrySet();
            Iterator<Map.Entry<Long, Dragon>> iterator = entries.iterator();
            while (iterator.hasNext()) {
                Map.Entry<Long, Dragon> entry = iterator.next();
                if (entry.getValue().getUsername().equals(username)) {
                    iterator.remove();
                    was = true;
                }
            }
        }
        if (!was) {
            return "Ваших элементов не оказалось в коллекции";
        } else {
            return "Все ваши элементы коллекции были удалены";
        }
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