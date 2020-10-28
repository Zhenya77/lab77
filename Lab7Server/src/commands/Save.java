package commands;


import DBcontroller.DataBaseController;
import controller.CommandWithoutArg;
import controller.DragonCollection;

public class Save implements CommandWithoutArg {
    String name = "save";

    @Override
    public String execute(Object o) {
        DataBaseController.getDataBase().loadCollection(DragonCollection.collection);
        System.out.println("Коллекция успешно сохранена.");
        return "";
    }


    @Override
    public String getName() {
        return name;
    }
}

