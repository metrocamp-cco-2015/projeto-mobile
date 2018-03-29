package br.com.tcc.prontuario.prontuario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterIdentificationStep3Activity extends AppCompatActivity {

    private void nextPage(){
        Intent homeActivity = new Intent(RegisterIdentificationStep3Activity.this, LoginActivity.class);
        startActivity(homeActivity);
    }

    private void backPage(){
        Intent registerStep2 = new Intent(RegisterIdentificationStep3Activity.this, RegisterIdentificationStep2Activity.class);
        startActivity(registerStep2);
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
}
