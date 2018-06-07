package br.com.tcc.prontuario.prontuario;

/**
 * Created by 1510031171 on 06/06/2018.
 */

public class PacientGoogle {
    private String name;
    private String email;
    private String googleid;
    private String image;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public PacientGoogle() {
    }

    public PacientGoogle(String name, String email, String googleid, String image) {
        this.name = name;
        this.email = email;
        this.googleid = googleid;
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

    public String getGoogleid() {
        return googleid;
    }

    public void setGoogleid(String googleid) {
        this.googleid = googleid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "PacientGoogle{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", googleid='" + googleid + '\'' +
                ", msg='" + msg + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
