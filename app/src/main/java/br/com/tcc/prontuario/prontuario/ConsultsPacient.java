package br.com.tcc.prontuario.prontuario;

/**
 * Created by 1510031171 on 08/06/2018.
 */

public class ConsultsPacient {
    private ConsultPacient[] consults;
    private String crm;
    private String msg;

    public ConsultsPacient(){}

    public ConsultsPacient(ConsultPacient[] consults) {
        this.consults = consults;
    }

    public ConsultPacient[] getConsults() {
        return consults;
    }

    public void setConsults(ConsultPacient[] consults) {
        this.consults = consults;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getMsg() {
        return msg;
    }

    public String getCrm() {
        return crm;
    }
}
