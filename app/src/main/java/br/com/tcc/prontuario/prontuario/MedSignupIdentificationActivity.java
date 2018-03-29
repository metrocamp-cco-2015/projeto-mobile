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

        if (!isFieldEmpty(cpf) &&
                !isFieldEmpty(name) &&
                !isFieldEmpty(crm)) {
            if (validateCpf(cpfText.getText().toString())) {
                result = true;
            } else {
                errorMessage = "CPF inválido";
            }
        } else {
            errorMessage = "Preencha todos os campos";
        }

        if (!errorMessage.isEmpty()) {
            Toast.makeText(this.getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
        }

        return result;
    }

    private boolean isFieldEmpty(String text) {
        return text.isEmpty();
    }

    private boolean validateCpf(String cpf) {
        if (cpf.equals("00000000000") || cpf.equals("11111111111") ||
                cpf.equals("22222222222") || cpf.equals("33333333333") ||
                cpf.equals("44444444444") || cpf.equals("55555555555") ||
                cpf.equals("66666666666") || cpf.equals("77777777777") ||
                cpf.equals("88888888888") || cpf.equals("99999999999") ||
                (cpf.length() != 11)) {
            return(false);
        }

        char dig10, dig11;
        int sm, i, r, num, weight;

        try {
            sm = 0;
            weight = 10;
            for (i=0; i<9; i++) {
                num = (int)(cpf.charAt(i) - 48);
                sm = sm + (num * weight);
                weight = weight - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48);

            sm = 0;
            weight = 11;
            for(i=0; i<10; i++) {
                num = (int)(cpf.charAt(i) - 48);
                sm = sm + (num * weight);
                weight = weight - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

            if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10)))
                return(true);
            else return(false);
        } catch (Exception erro) {
            return(false);
        }
    }
}
