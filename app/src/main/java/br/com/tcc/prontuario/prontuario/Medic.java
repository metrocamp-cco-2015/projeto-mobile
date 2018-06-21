package br.com.tcc.prontuario.prontuario;

/**
 * Created by 1510031171 on 17/05/2018.
 */

public class Medic {
    private String crm;
    private String email;
    private String name;
    private String birthdate;
    private String password;
    private String phoneNumber;
    private String gender;
    private String maps;

    public Medic () {

    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaps() {
        return maps;
    }

    public void setMaps(String maps) {
        this.maps = maps;
    }

    public Medic(String crm, String email, String name, String birthdate, String password, String phoneNumber, String gender, String maps) {
        this.crm = crm;
        this.email = email;
        this.name = name;
        this.birthdate = birthdate;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.maps = maps;
    }

    @Override
    public String toString() {
        return "Medic{" +
                "crm='" + crm + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender='" + gender + '\'' +
                ", maps='" + maps + '\'' +
                '}';
    }
}
