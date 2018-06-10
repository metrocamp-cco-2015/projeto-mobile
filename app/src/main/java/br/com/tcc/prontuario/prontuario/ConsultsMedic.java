package br.com.tcc.prontuario.prontuario;

public class ConsultsMedic {
    private ConsultMedic[] consults;
    private String msg;

    public ConsultsMedic(){}

    public ConsultsMedic(ConsultMedic[] consults) {
        this.consults = consults;
    }

    public ConsultMedic[] getConsults() {
        return consults;
    }

    public void setConsults(ConsultMedic[] consults) {
        this.consults = consults;
    }

    public String getMsg() {
        return msg;
    }
}
