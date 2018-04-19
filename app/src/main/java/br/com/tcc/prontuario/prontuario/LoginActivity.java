package br.com.tcc.prontuario.prontuario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private CallbackManager callbackManager;

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
            Intent home = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(home);
        }
    }

    private void signupMed() {
        Intent signupIntent = new Intent(LoginActivity.this, MedSignupIdentificationActivity.class);
        startActivity(signupIntent);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        callbackManager = CallbackManager.Factory.create();

        final Button buttonLogin = findViewById(R.id.login_button);
        final Button signupMedButton = findViewById(R.id.signup_button);
        final Button signupPacButton = findViewById(R.id.register_button);
        LoginButton facebookLoginButton = findViewById(R.id.login_button_facebook);

        facebookLoginButton.setReadPermissions("email", "public_profile", "user_birthday");

        facebookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getApplicationContext(), "LOGADO COM SUCESSO!", Toast.LENGTH_LONG)
                        .show();
                Log.i("FACEBOOK_LOGIN", "LOGADO COM SUCESSO!");

                loadFacebookProfileData(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        response.getError();
                        Log.e("FACEBOOK_JSON", object.toString());
                    }
                });
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "LOGIN CANCELADO!", Toast.LENGTH_LONG)
                        .show();
                Log.i("FACEBOOK_LOGIN", "LOGIN CANCELADO!");
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(getApplicationContext(), "ALGO DEU ERRADO!", Toast.LENGTH_LONG)
                        .show();
                Log.i("FACEBOOK_LOGIN", exception.getMessage());
            }
        });

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

    /*
        https://developers.facebook.com/docs/facebook-login/permissions#reference
     */
    private void loadFacebookProfileData(AccessToken accessToken, GraphRequest.GraphJSONObjectCallback callback) {
        GraphRequest request = GraphRequest.newMeRequest(
                accessToken, callback);
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,email,picture,gender,birthday");
        request.setParameters(parameters);
        request.executeAsync();
    }

    /*
        Google Sign-in
        https://developers.google.com/identity/sign-in/android/start-integrating
     */
}
