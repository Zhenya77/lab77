package commands;


import controller.CommandWithAccount;
import controller.Commandable;
import controller.DragonCollection;
import dragon.Dragon;

import java.util.Enumeration;
import java.util.InputMismatchException;

public class Remove_lower_key implements Commandable, CommandWithAccount {
    String name = "remove_lower_key";
    String username;

    /**
     * delete all dragons from the collection with ids lower then appointed
     *
     * @param arg
     */
    @Override
    public String execute(Object arg) {
        try {
            if (DragonCollection.getSize() == 0) return ("Коллекция итак пустая.");
            else {
                String result = "";
                boolean was = false;
                Enumeration keys = DragonCollection.collection.keys();
                while (keys.hasMoreElements()) {
                    Integer k = (Integer) keys.nextElement();
                    Dragon v = DragonCollection.collection.get(k);
                    if (v.getId() < Integer.parseInt((String) arg)) {
                        if (v.getUsername().equals(username)) {
                            DragonCollection.collection.remove(k);
                            result += ("Дракон с id:[" + k + "] успешно удален.\n");
                            was = true;
                        }
                    }
                }
                return (!was) ? "Драконов с меньшим id не найдено." : result;
            }
        } catch (InputMismatchException e) {
            e.printStackTrace();
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