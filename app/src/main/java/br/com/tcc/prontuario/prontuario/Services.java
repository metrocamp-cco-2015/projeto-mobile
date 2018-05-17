package br.com.tcc.prontuario.prontuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by 1510031171 on 16/05/2018.
 */

public interface Services {
    @POST("paciente/facebook/new")
    Call<Pacient> signinPacientByFacebook(@Body Pacient pacient);

    @POST("medico/crm")
    Call<Medic> getMedicByCrm(@Body Medic medic);

    @POST("medico/maps/add")
    Call<Medic> addMedicMaps(@Body Medic medic);
}
