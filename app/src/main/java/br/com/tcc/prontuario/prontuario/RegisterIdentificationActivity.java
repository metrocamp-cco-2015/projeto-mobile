package br.com.tcc.prontuario.prontuario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterIdentificationActivity extends AppCompatActivity {

    private void redirectToRegisterStep2(){
        Intent registerStep2 = new Intent(RegisterIdentificationActivity.this, RegisterIdentificationStep2Activity.class);
        startActivity(registerStep2);
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
}
