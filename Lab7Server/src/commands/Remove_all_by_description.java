package commands;

import controller.CommandWithAccount;
import controller.Commandable;
import controller.DragonCollection;
import dragon.Dragon;

import java.util.Enumeration;
import java.util.Hashtable;

public class Remove_all_by_description implements Commandable, CommandWithAccount {
    DragonCollection collection = new DragonCollection();
    String name = "remove_all_by_description";
    String username;

    /**
     * delete dragon from the collection with this description
     *
     * @param arg
     */
    @Override
    public String execute(Object arg) {

        if (collection.getSize() == 0) return ("Коллекция итак пустая.");
        else {
            boolean was = false;
            Hashtable<Long, Dragon> dragons = DragonCollection.collection;
            Enumeration keys = dragons.keys();
            String ans = "Драконы с id:[ ";
            while (keys.hasMoreElements()) {
                Long k = (Long) keys.nextElement();
                Dragon v = dragons.get(k);
                if (v.getDescription().equals(arg)) {
                    if(v.getUsername().equals(username)) {
                        was = true;
                        collection.remove(k);
                        ans += k + " ";
                    }
                }
            }
            if (was) return ans += "] успешно удалены";
               else return ("Ни один дракон не был удален.");
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