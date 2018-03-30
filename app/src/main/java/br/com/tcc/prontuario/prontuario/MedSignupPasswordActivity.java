package br.com.tcc.prontuario.prontuario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        /**
         *  Criar a tela de DASHBOARD
         */
        if (validateFields()) {
            Intent intent = new Intent(MedSignupPasswordActivity.this,
                    LoginActivity.class);
            startActivity(intent);
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
