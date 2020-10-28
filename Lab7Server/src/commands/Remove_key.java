package commands;

import controller.CommandWithAccount;
import controller.Commandable;
import controller.DragonCollection;
import dragon.Dragon;

import java.util.Map;

public class Remove_key implements Commandable, CommandWithAccount {
    String name = "remove_key";
    DragonCollection collection = new DragonCollection();
    String username;

    /**
     * delete dragon with this id number
     *
     * @param arg
     */
    @Override
    public String execute(Object arg) {
        try {
            if (DragonCollection.getSize() == 0) return ("Коллекция итак пустая.");
            else {
                for (Map.Entry<Long, Dragon> entry : DragonCollection.collection.entrySet())
                    if (entry.getKey() == Integer.parseInt((String) arg)) {
                        if (entry.getValue().getUsername().equals(username)) {
                            collection.remove(entry.getKey());
                            return ("Элемент с id[" + arg + "] успешно удален.");
                        } else return "У вас нет прав на удаление этого дракона";
                    }
                return ("Дракон с указанным id не найден.");
            }
        } catch (Exception e) {
            return ("Аргумент команды должен быть типа \"int\"");
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

