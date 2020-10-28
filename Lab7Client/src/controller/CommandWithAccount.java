package controller;

public interface CommandWithAccount extends Commandable {
    void setUsername(String username);
}
