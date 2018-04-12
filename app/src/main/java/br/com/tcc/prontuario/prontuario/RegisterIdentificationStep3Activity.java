package br.com.tcc.prontuario.prontuario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterIdentificationStep3Activity extends AppCompatActivity {

    private void nextPage(){
        if (validateFields()) {
            Intent intent = new Intent(RegisterIdentificationStep3Activity.this,
                    LoginActivity.class);
            startActivity(intent);
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
