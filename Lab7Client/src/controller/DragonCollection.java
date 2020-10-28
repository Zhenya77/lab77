package controller;

import dragon.Dragon;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;

public class DragonCollection implements Serializable {
    private static Hashtable<Long, Dragon> collection = new Hashtable<>();
    public static LocalDate dateCreation;

    //Здесь находятся синхронизированные методы для работы с коллекцией

    //используется синхронизированный геттер и сеттер, потому что при обращениии
    //к коллекции через DragonCollection.collection - доступ к переменной будет
    //несинхроизированным
    public synchronized static Hashtable<Long, Dragon> getCollection() {
        return collection;
    }

    public synchronized static void setCollection(Hashtable<Long, Dragon> collection) {
        DragonCollection.collection = collection;
    }


    public synchronized static LocalDate getDateCreation() {
        return dateCreation;
    }

    public synchronized static void setDateCreation(LocalDate dateCreation) {
        DragonCollection.dateCreation = dateCreation;
    }

    public synchronized static void clear() {
        collection.clear();
    }

    /**
     * create new dragon with set id and put it into collection
     *
     * @param ind
     * @param dragon
     */
    public synchronized static void insert(Long ind, Dragon dragon) {
        if (isKeyFree(ind))
            collection.put(ind, dragon);
    }

    /**
     * update info about dragon with this id
     *
     * @param ind
     * @param dragon
     */
    public synchronized static void update(Long ind, Dragon dragon) {
        if (!isKeyFree(ind))
            collection.put(ind, dragon);
    }

    /**
     * remove dragon from the collection using id
     *
     * @param ind
     */
    public synchronized static void remove(Long ind) {
        Enumeration keys = collection.keys();
        while (keys.hasMoreElements()) {
            Integer k = (Integer) keys.nextElement();
            Dragon v = collection.get(k);
            if (!isKeyFree(ind)) collection.remove(k);

        }
    }

    /**
     * @param ind
     * @return false if dragon with this id exist, false otherwise
     */
    public synchronized static boolean isKeyFree(Long ind) {
        try {
            for (Map.Entry<Long, Dragon> entry : collection.entrySet())
                if (entry.getKey() == ind) return false;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;

        }
    }

    public synchronized static int getSize() {
        return collection.size();
    }

    /**
     * @return collection size and date of creation
     */
    public synchronized static String getInfo() {
        return "Тип коллекции: Hashtable;\nKоличество элементов коллекции: " + getSize() + ";\nДата создания кол"
                + "лекции: " + getDateCreation() + ".";
    }
}
