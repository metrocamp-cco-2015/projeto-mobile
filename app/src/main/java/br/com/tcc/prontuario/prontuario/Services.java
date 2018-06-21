package br.com.tcc.prontuario.prontuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by 1510031171 on 16/05/2018.
 */

public interface Services {
    @POST("paciente/facebook/check")
    Call<PacientFacebook> checkPacientByFacebook(@Body PacientFacebook pacient);

    @POST("paciente/facebook/new")
    Call<PacientFacebook> signinPacientByFacebook(@Body PacientFacebook pacient);

    @POST("paciente/google/new")
    Call<PacientGoogle> signinPacientByGoogle(@Body PacientGoogle pacient);

    @POST("paciente/google/check")
    Call<PacientGoogle> checkPacientByGoogle(@Body PacientGoogle pacient);

    @POST("medico/crm")
    Call<Medic> getMedicByCrm(@Body Medic medic);

    @POST("paciente/cpf")
    Call<Pacient> getPacientByCpf(@Body Pacient pacient);

    @POST("paciente/email")
    Call<Pacient> getPacientByEmail(@Body Pacient pacient);

    @POST("medico/maps/add")
    Call<Medic> addMedicMaps(@Body Medic medic);

    @POST("paciente/consultas")
    Call<ConsultsMedic> getConsultsByCpf(@Body ConsultsMedic consults);

    @POST("medico/consultas")
    Call<ConsultsPacient> getConsultsByCrm(@Body ConsultsPacient consults);

    @POST("consulta")
    Call<ConsultData> getConsultById(@Body ConsultData consult);

    @POST("login/paciente")
    Call<LoginData> signInPacient(@Body LoginData loginData);

    @POST("login/medico")
    Call<LoginData> signInMedic(@Body LoginData loginData);

    @POST("medico/new")
    Call<SignUpData> signUpMedic(@Body SignUpData loginData);

    @POST("paciente/new")
    Call<SignUpData> signUpPacient(@Body SignUpData loginData);
}
