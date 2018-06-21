package br.com.tcc.prontuario.prontuario;

/**
 * Created by 1510031171 on 20/06/2018.
 */

public class LoginData {
    private String login;
    private String pass;
    private String msg;

    public LoginData() {}

    public LoginData(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "LoginData{" +
                "login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
