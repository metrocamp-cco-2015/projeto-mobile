package br.com.tcc.prontuario.prontuario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private void login(){
        EditText user = findViewById(R.id.username_text);
        EditText password = findViewById(R.id.password_text);
        TextView validation = findViewById(R.id.validation_text);
        validation.setVisibility(View.INVISIBLE);
        if(emptyFields(user.getText().toString(), password.getText().toString())){
            validation.setText(getString(R.string.empty_login_fields_message));
            validation.setVisibility(View.VISIBLE);
        }else if(!validLogin(user.getText().toString(), password.getText().toString())){
            validation.setText(getString(R.string.invalid_login_message));
            validation.setVisibility(View.VISIBLE);
        }else{
            Intent home = new Intent(LoginActivity.this, LoginActivity.class);
            startActivity(home);
        }
    }

    private boolean validLogin(String user, String password){
        if(user.equals("admin") && password.equals("admin")){
            Log.d("[DEBUG]", "login valid " + user);
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    private boolean emptyFields(String user, String password){


        if(user == null || user.isEmpty()){
            return Boolean.TRUE;
        }

        if(password == null || password.isEmpty()){
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button buttonLogin = findViewById(R.id.login_button);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }
}
