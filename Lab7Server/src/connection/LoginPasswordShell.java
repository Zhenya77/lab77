package connection;

import java.io.Serializable;

public class LoginPasswordShell implements Serializable {
    private String login;
    private String password;
    private Long type;
    private int clientPort;

    protected LoginPasswordShell(Long type, String login, String password, int clientPort) {
        this.login = login;
        this.password = password;
        this.type = type;
        this.clientPort = clientPort;
    }

    public int getClientPort() {
        return clientPort;
    }

    protected String getLogin() {
        return login;
    }

    protected String getPassword() {
        return password;
    }

    protected Long getType() {
        return type;
    }
    public void setType(Long type) {
        this.type = type;
    }
}
