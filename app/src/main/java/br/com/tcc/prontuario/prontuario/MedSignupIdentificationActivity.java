package br.com.tcc.prontuario.prontuario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MedSignupIdentificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_signup_identification);

        Button nextButton = findViewById(R.id.identification_next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextScreen();
            }
        });
    }

    private void nextScreen() {
        if (validateFields()) {
            Intent intent = new Intent(MedSignupIdentificationActivity.this,
                    MedSignupContactActivity.class);
            startActivity(intent);
        }
    }

    private boolean validateFields() {
        EditText cpfText = findViewById(R.id.med_signup_cpf_text);
        EditText nameText = findViewById(R.id.med_signup_name_text);
        EditText crmText = findViewById(R.id.med_signup_crm_text);

        String errorMessage = "";
        String cpf = cpfText.getText().toString();
        String name = nameText.getText().toString();
        String crm = crmText.getText().toString();
        boolean result = false;

        if (!Validator.isFieldEmpty(cpf) &&
                !Validator.isFieldEmpty(name) &&
                !Validator.isFieldEmpty(crm)) {
            if (Validator.validateCpf(cpfText.getText().toString())) {
                result = true;
            } else {
                errorMessage = getString(R.string.error_invalid_cpf);;
            }
        } else {
            errorMessage = getString(R.string.empty_login_fields_message);;
        }

        if (!errorMessage.isEmpty()) {
            Toast.makeText(this.getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
        }

        return result;
    }
}
