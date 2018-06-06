package br.com.tcc.prontuario.prontuario;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by 1510031171 on 05/06/2018.
 */

@JsonIgnoreProperties({"fbid"})
public class Pacient {
    private String cpf;
    private String email;
    private String name;
    private String birthdate;
    private String password;
    private String phoneNumber;
    private String gender;

    public Pacient () {

    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String crm) {
        this.cpf = crm;
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

    public Pacient(String cpf, String email, String name, String birthdate, String password, String phoneNumber, String gender, String maps) {
        this.cpf = cpf;
        this.email = email;
        this.name = name;
        this.birthdate = birthdate;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Pacient{" +
                "cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
