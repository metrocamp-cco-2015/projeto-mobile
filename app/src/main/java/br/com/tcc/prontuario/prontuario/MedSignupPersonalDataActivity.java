package br.com.tcc.prontuario.prontuario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MedSignupPersonalDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_signup_personal_data);

        Button nextButton = findViewById(R.id.personal_data_next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextScreen();
            }
        });
    }

    private void nextScreen() {
        if (validateFields()) {
            Intent intent = new Intent(MedSignupPersonalDataActivity.this,
                    MedSignupPasswordActivity.class);
            startActivity(intent);
        }
    }

    private boolean validateFields() {
        EditText birthdateText = findViewById(R.id.med_signup_birthdate_text);

        /* TODO Validate Gender */

        String errorMessage = "";
        String birthdate = birthdateText.getText().toString();
        boolean result = false;

        if (!Validator.isFieldEmpty(birthdate)) {
            if (Validator.validateDate(birthdate)) {
                result = true;
            } else {
                errorMessage = getString(R.string.error_invalid_date);;
            }
        } else {
            errorMessage = getString(R.string.error_empty_fields);;
        }

        if (!errorMessage.isEmpty()) {
            Toast.makeText(this.getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
        }

        return result;
    }
}
