package br.com.tcc.prontuario.prontuario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterIdentificationStep2Activity extends AppCompatActivity {

    private void nextPage(){
        Intent registerStep3 = new Intent(RegisterIdentificationStep2Activity.this, RegisterIdentificationStep3Activity.class);
        startActivity(registerStep3);
    }
    
    private void backPage(){
        Intent registerStep1 = new Intent(RegisterIdentificationStep2Activity.this, RegisterIdentificationActivity.class);
        startActivity(registerStep1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_identification_step2);

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
}
