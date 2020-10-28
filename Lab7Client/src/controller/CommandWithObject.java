package controller;

public interface CommandWithObject extends Commandable {
    boolean check(long arg);
    String whyFailed();
}
