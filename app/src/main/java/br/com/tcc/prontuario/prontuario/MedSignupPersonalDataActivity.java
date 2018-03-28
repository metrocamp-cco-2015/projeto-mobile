package br.com.tcc.prontuario.prontuario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
        Intent intent = new Intent(MedSignupPersonalDataActivity.this,
                MedSignupPasswordActivity.class);
        startActivity(intent);
    }
}
