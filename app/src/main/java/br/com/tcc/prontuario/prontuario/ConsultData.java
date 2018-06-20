package br.com.tcc.prontuario.prontuario;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by 1510031171 on 20/06/2018.
 */

public class ConsultData {
    @JsonProperty("ID")
    private String id;
    @JsonProperty("crm")
    private String crm;
    @JsonProperty("cpf")
    private String cpf;
    @JsonProperty("medic_name")
    private String medicName;
    @JsonProperty("pacient_name")
    private String pacientName;
    @JsonProperty("DATA_CONSULTA")
    private String date;
    @JsonProperty("ALTURA")
    private String pacientHeight;
    @JsonProperty("PESO")
    private String pacientWeight;
    @JsonProperty("PRESSAO")
    private String pacientBloodPressure;
    @JsonProperty("SINTOMAS")
    private String symptom;
    @JsonProperty("DIAGNOSTICO")
    private String diagnosis;
    @JsonProperty("TRATAMENTO")
    private String treatment;

    public ConsultData() {}

    public ConsultData(String id, String crm, String cpf, String medicName, String pacientName, String date, String pacientHeight, String pacientWeight, String pacientBloodPressure, String symptom, String diagnosis, String treatment) {
        this.id = id;
        this.crm = crm;
        this.cpf = cpf;
        this.medicName = medicName;
        this.pacientName = pacientName;
        this.date = date;
        this.pacientHeight = pacientHeight;
        this.pacientWeight = pacientWeight;
        this.pacientBloodPressure = pacientBloodPressure;
        this.symptom = symptom;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getMedicName() {
        return medicName;
    }

    public void setMedicName(String medicName) {
        this.medicName = medicName;
    }

    public String getPacientName() {
        return pacientName;
    }

    public void setPacientName(String pacientName) {
        this.pacientName = pacientName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPacientHeight() {
        return pacientHeight;
    }

    public void setPacientHeight(String pacientHeight) {
        this.pacientHeight = pacientHeight;
    }

    public String getPacientWeight() {
        return pacientWeight;
    }

    public void setPacientWeight(String pacientWeight) {
        this.pacientWeight = pacientWeight;
    }

    public String getPacientBloodPressure() {
        return pacientBloodPressure;
    }

    public void setPacientBloodPressure(String pacientBloodPressure) {
        this.pacientBloodPressure = pacientBloodPressure;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    @Override
    public String toString() {
        return "ConsultData{" +
                "id='" + id + '\'' +
                ", crm='" + crm + '\'' +
                ", cpf='" + cpf + '\'' +
                ", medicName='" + medicName + '\'' +
                ", pacientName='" + pacientName + '\'' +
                ", date='" + date + '\'' +
                ", pacientHeight='" + pacientHeight + '\'' +
                ", pacientWeight='" + pacientWeight + '\'' +
                ", pacientBloodPressure='" + pacientBloodPressure + '\'' +
                ", symptom='" + symptom + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                ", treatment='" + treatment + '\'' +
                '}';
    }
}
