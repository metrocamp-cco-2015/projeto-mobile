package br.com.tcc.prontuario.prontuario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterIdentificationActivity extends AppCompatActivity {

    private void redirectToRegisterStep2(){
        if (validateFields()) {
            EditText cpfText = findViewById(R.id.register_cpf);
            EditText nameText = findViewById(R.id.register_name);
            EditText emailText = findViewById(R.id.register_email);
            String name = nameText.getText().toString();
            String cpf = cpfText.getText().toString();
            String email = emailText.getText().toString();

            Intent registerStep2 = new Intent(RegisterIdentificationActivity.this, RegisterIdentificationStep2Activity.class);

            registerStep2.putExtra("email", email);
            registerStep2.putExtra("name", name);
            registerStep2.putExtra("cpf", cpf);

            startActivity(registerStep2);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_identification);

        final Button buttonStep1 = findViewById(R.id.register_button_step1);

        buttonStep1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectToRegisterStep2();
            }
        });
    }

    private boolean validateFields() {
        EditText cpfText = findViewById(R.id.register_cpf);
        EditText nameText = findViewById(R.id.register_name);
        EditText emailText = findViewById(R.id.register_email);

        String errorMessage = "";
        String cpf = cpfText.getText().toString();
        String name = nameText.getText().toString();
        String email = emailText.getText().toString();
        boolean result = true;

        if (!Validator.isFieldEmpty(cpf) &&
                !Validator.isFieldEmpty(name) &&
                !Validator.isFieldEmpty(email)) {
            if (!Validator.validateCpf(cpf)) {
                result = false;
                errorMessage = getString(R.string.error_invalid_cpf);
            }
            if (!Validator.validateEmail(email)) {
                result = false;
                errorMessage = getString(R.string.error_invalid_email);
            }
        } else {
            result = false;
            errorMessage = getString(R.string.error_empty_fields);
        }

        if (!errorMessage.isEmpty()) {
            Toast.makeText(this.getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
        }

        return result;
    }
}
