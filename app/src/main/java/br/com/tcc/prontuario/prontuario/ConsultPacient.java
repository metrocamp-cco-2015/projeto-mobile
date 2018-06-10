package br.com.tcc.prontuario.prontuario;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConsultPacient {
    @JsonProperty("ID")
    private String id;
    @JsonProperty("cpf")
    private String cpf;
    @JsonProperty("NOME_COMPLETO")
    private String name;
    @JsonProperty("DATA_CONSULTA")
    private String date;

    public ConsultPacient() {

    }

    public ConsultPacient(String id, String cpf, String name, String date) {
        this.id = id;
        this.cpf = cpf;
        this.name = name;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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
        return "ConsultPacient{" +
                "id='" + id + '\'' +
                ", cpf='" + cpf + '\'' +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
