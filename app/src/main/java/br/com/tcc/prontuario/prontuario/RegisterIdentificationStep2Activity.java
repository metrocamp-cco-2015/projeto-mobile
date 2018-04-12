package br.com.tcc.prontuario.prontuario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterIdentificationStep2Activity extends AppCompatActivity {

    private void nextPage(){
        if (validateFields()) {
            Intent intent = new Intent(RegisterIdentificationStep2Activity.this,
                    RegisterIdentificationStep3Activity.class);
            startActivity(intent);
        }
    }
    
    private void backPage(){
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_identification_step2);

        EditText birthdateText = findViewById(R.id.register_identification_birth_date);
        birthdateText.addTextChangedListener(EditTextMask.mask(birthdateText, EditTextMask.DATE));

        final Button nextPageButton = findViewById(R.id.register_next_button_step2);
        final Button backPageButton = findViewById(R.id.register_back_button_step2);

        nextPageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                nextPage();
            }
        });

        backPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backPage();
            }
        });
    }

    private boolean validateFields() {
        EditText birthdateText = findViewById(R.id.register_identification_birth_date);

        /* TODO Validate Gender */

        String errorMessage = "";
        String birthdate = birthdateText.getText().toString();
        boolean result = false;

        if (!Validator.isFieldEmpty(birthdate)) {
            if (Validator.validateDate(birthdate)) {
                result = true;
            } else {
                errorMessage = getString(R.string.error_invalid_date);
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
