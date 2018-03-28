package br.com.tcc.prontuario.prontuario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
        Intent intent = new Intent(MedSignupPasswordActivity.this,
                LoginActivity.class);
        startActivity(intent);
    }
}
