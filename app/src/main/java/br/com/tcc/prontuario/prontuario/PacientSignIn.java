package br.com.tcc.prontuario.prontuario;

/**
 * Created by 1510031171 on 16/05/2018.
 */

public class PacientSignIn {
    private String cpf;
    private String pass;

    public PacientSignIn() {
    }

    public PacientSignIn(String cpf, String pass) {
        this.cpf = cpf;
        this.pass = pass;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "PacientSignIn{" +
                "cpf='" + cpf + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
