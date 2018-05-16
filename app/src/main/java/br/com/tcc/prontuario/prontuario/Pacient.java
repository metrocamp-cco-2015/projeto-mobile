package br.com.tcc.prontuario.prontuario;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * Created by 1510031171 on 16/05/2018.
 */

public class Pacient {
    private String name;
    private String email;
    private String birthdate;
    private String fbId;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Pacient() {
    }

    public Pacient(String name, String email, String birthdate, String fbId) {
        this.name = name;
        this.email = email;
        this.birthdate = birthdate;
        this.fbId = fbId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    @Override
    public String toString() {
        return "Pacient{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", fbId='" + fbId + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
