package commands;


import controller.CommandWithoutArg;
import controller.DragonCollection;
import dragon.Dragon;

import java.util.HashMap;

public class Min_by_description implements CommandWithoutArg {
    String name = "min_by_description";

    /**
     * shows dragon from the collection with shortest description
     *
     * @param arg
     */
    @Override
    public String execute(Object arg) {
        return "";
    }

    @Override
    public String getName() {
        return name;
    }
}

