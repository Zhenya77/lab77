package controller;

import java.io.FileNotFoundException;
import java.io.Serializable;

public interface Commandable extends Serializable {
    public String execute(Object o) ;
    public String getName();
}
