package br.com.tcc.prontuario.prontuario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MedSignupContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_signup_contact);

        Button nextButton = findViewById(R.id.contact_next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextScreen();
            }
        });
    }

    private void nextScreen() {
        if (validateFields()) {
            Intent intent = new Intent(MedSignupContactActivity.this,
                    MedSignupPersonalDataActivity.class);
            startActivity(intent);
        }
    }

    private boolean validateFields() {
        EditText emailText = findViewById(R.id.med_signup_email_text);
        EditText phoneText = findViewById(R.id.med_signup_phone_text);

        String errorMessage = "";
        String email = emailText.getText().toString();
        String phone = phoneText.getText().toString();
        boolean result = false;

        if (!Validator.isFieldEmpty(email) &&
                !Validator.isFieldEmpty(phone)) {
            if (Validator.validateEmail(emailText.getText().toString())) {
                result = true;
            } else {
                errorMessage = getString(R.string.error_invalid_email);
            }
        } else {
            errorMessage = getString(R.string.empty_login_fields_message);
        }

        if (!errorMessage.isEmpty()) {
            Toast.makeText(this.getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
        }

        return result;
    }
}
