package br.com.tcc.prontuario.prontuario;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by 1510031171 on 16/05/2018.
 */

public class RetrofitConfig {

    private final Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.230:8000/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public Services getServices() {
        return this.retrofit.create(Services.class);
    }
}

