package br.com.tcc.prontuario.prontuario;

/**
 * Created by 1510031171 on 20/06/2018.
 */

public class SignUpData {
    private String crm;
    private String cpf;
    private String email;
    private String name;
    private String birthdate;
    private String password;
    private String phoneNumber;
    private char gender;
    private String msg;

    public SignUpData() {}

    public SignUpData(String email, String name, String birthdate, String password, String phoneNumber, char gender) {
        this.email = email;
        this.name = name;
        this.birthdate = birthdate;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "SignUpData{" +
                "crm='" + crm + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender=" + gender +
                '}';
    }
}
