
package commands;


import controller.CommandWithoutArg;

public class Show implements CommandWithoutArg {
    String name = "show";

    /**
     * show all element of this collection
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
}
