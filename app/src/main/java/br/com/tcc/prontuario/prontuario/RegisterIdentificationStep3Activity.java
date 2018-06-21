package br.com.tcc.prontuario.prontuario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterIdentificationStep3Activity extends AppCompatActivity {

    private void nextPage(){
        if (validateFields()) {
            EditText passwordText = findViewById(R.id.register_identification_password);

            final Intent intent = new Intent(RegisterIdentificationStep3Activity.this,
                    LoginActivity.class);

            Bundle bundle = getIntent().getExtras();

            String password = passwordText.getText().toString();
            String email = bundle.getString("email");
            String name = bundle.getString("name");
            String cpf = bundle.getString("cpf");
            String birthdate = bundle.getString("birthdate");
            char gender = bundle.getChar("gender");

            SignUpData signUpData = new SignUpData(email, name, birthdate, password, "", gender);
            signUpData.setCpf(cpf);

            Call<SignUpData> call = new RetrofitConfig().getServices().signUpPacient(signUpData);
            call.enqueue(new Callback<SignUpData>() {
                @Override
                public void onResponse(Call<SignUpData> call, Response<SignUpData> response) {
                    SignUpData data = response.body();
                    if (response.isSuccessful()) {
                        startActivity(intent);
                    }
                    Toast.makeText(RegisterIdentificationStep3Activity.this.getApplicationContext(), data.getMsg(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<SignUpData> call, Throwable t) {
                    Log.i("DEU_RUIM_SIGN_UP_MED", t.getMessage());
                }
            });


        }
    }

    private void backPage(){
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_identification_step3);

        final Button next = findViewById(R.id.register_next_button_step3);
        final Button back = findViewById(R.id.register_back_button_step3);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextPage();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backPage();
            }
        });
    }

    private boolean validateFields() {
        EditText passwordText = findViewById(R.id.register_identification_password);
        EditText confirmPasswordText = findViewById(R.id.register_identification_confirm_password);

        String errorMessage = "";
        String password = passwordText.getText().toString();
        String confirmPassword = confirmPasswordText.getText().toString();
        boolean result = false;

        if (!Validator.isFieldEmpty(password) &&
                !Validator.isFieldEmpty(confirmPassword)) {
            if (Validator.validatePassword(password, confirmPassword)) {
                result = true;
            } else {
                errorMessage = getString(R.string.error_confirm_password);
            }
        } else {
            errorMessage = getString(R.string.error_empty_fields);
        }

        if (!errorMessage.isEmpty()) {
            Toast.makeText(this.getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
        }

        return result;
    }
}
