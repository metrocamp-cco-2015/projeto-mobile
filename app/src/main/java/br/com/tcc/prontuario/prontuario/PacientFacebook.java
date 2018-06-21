package br.com.tcc.prontuario.prontuario;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * Created by 1510031171 on 16/05/2018.
 */

public class PacientFacebook {
    private String cpf;
    private String name;
    private String email;
    private String birthdate;
    private String fbId;
    private String image;
    private String msg;
    private boolean signup;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public PacientFacebook() {
    }

    public PacientFacebook(String name, String email, String birthdate, String fbId, String image) {
        this.name = name;
        this.email = email;
        this.birthdate = birthdate;
        this.fbId = fbId;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isSignup() {
        return signup;
    }

    public void setSignup(boolean signup) {
        this.signup = signup;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "PacientFacebook{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", fbId='" + fbId + '\'' +
                ", msg='" + msg + '\'' +
                ", image='" + image+ '\'' +
                '}';
    }
}
