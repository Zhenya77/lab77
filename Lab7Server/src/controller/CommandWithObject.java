package controller;

import dragon.Dragon;

public interface CommandWithObject extends Commandable{
    boolean check(long arg);
    String whyFailed();
}
