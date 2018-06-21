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

public class MedSignupPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_signup_password);

        Button nextButton = findViewById(R.id.finish_signup_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextScreen();
            }
        });
    }

    private void nextScreen() {
        if (validateFields()) {
            EditText passwordText = findViewById(R.id.med_signup_password_text);

            Bundle bundle = getIntent().getExtras();

            final Intent intent = new Intent(MedSignupPasswordActivity.this,
                    LoginActivity.class);

            String name = bundle.getString("name");
            String password = passwordText.getText().toString();
            String crm = bundle.getString("crm");
            String email = bundle.getString("email");
            String phone = bundle.getString("phone");
            String birthdate = bundle.getString("birthdate");
            char gender = bundle.getChar("gender");

            SignUpData signUpData = new SignUpData(email, name, birthdate, password, phone, gender);
            signUpData.setCrm(crm);

            Call<SignUpData> call = new RetrofitConfig().getServices().signUpMedic(signUpData);
            call.enqueue(new Callback<SignUpData>() {
                @Override
                public void onResponse(Call<SignUpData> call, Response<SignUpData> response) {
                    SignUpData data = response.body();
                    if (response.isSuccessful()) {
                        startActivity(intent);
                    }
                    Toast.makeText(MedSignupPasswordActivity.this.getApplicationContext(), data.getMsg(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<SignUpData> call, Throwable t) {
                    Log.i("DEU_RUIM_SIGN_UP_MED", t.getMessage());
                }
            });
        }
    }

    private boolean validateFields() {
        EditText passwordText = findViewById(R.id.med_signup_password_text);
        EditText confirmPasswordText = findViewById(R.id.med_signup_confirm_password_text);

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
