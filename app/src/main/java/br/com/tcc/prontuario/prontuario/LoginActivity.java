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
            /*
               HomeActivity
             */
            Intent home = new Intent(LoginActivity.this, MapsActivity.class);
            startActivity(home);
        }
    }

    private void signupMed() {
        Intent signupIntent = new Intent(LoginActivity.this, MedSignupIdentificationActivity.class);
        startActivity(signupIntent);
            //try {
            //    Intent home = new Intent(LoginActivity.this, LoginActivity.class);
            //    startActivity(home);
            //}catch (Exception e){
            //    Log.e("Login", "Error log: \n" + e);
            //}
    }

    private void redirectToRegister(){
        Intent register = new Intent(LoginActivity.this, RegisterIdentificationActivity.class);
        startActivity(register);
    }

    private boolean validLogin(String user, String password){
        if(user.equals("admin") && password.equals("admin")){
            Log.d("[DEBUG]", "login valid " + user);
            return true;
        }

        return false;
    }

    private boolean emptyFields(String user, String password){
        if(user == null || user.isEmpty()){
            return true;
        }
        if(password == null || password.isEmpty()){
            return true;
        }

        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button buttonLogin = findViewById(R.id.login_button);
        final Button signupMedButton = findViewById(R.id.signup_button);
        final Button signupPacButton = findViewById(R.id.register_button);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        signupMedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signupMed();
            }
        });

        signupPacButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectToRegister();
            }
        });
    }
}
