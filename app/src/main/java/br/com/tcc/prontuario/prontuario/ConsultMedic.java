package br.com.tcc.prontuario.prontuario;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConsultMedic {
    @JsonProperty("ID")
    private String id;
    @JsonProperty("crm")
    private String crm;
    @JsonProperty("NOME_COMPLETO")
    private String name;
    @JsonProperty("DATA_CONSULTA")
    private String date;

    public ConsultMedic() {}

    public ConsultMedic(String id, String crm, String name, String date) {
        this.id = id;
        this.crm = crm;
        this.name = name;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ConsultMedic{" +
                "id='" + id + '\'' +
                ", crm='" + crm + '\'' +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
