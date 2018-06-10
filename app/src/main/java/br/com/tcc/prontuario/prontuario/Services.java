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
}
