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
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    private GoogleSignInClient mGoogleSignInClient;

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
            openHomeMed();
        }
    }

    private void openHome(String extra, boolean facebook) {
        Intent home = new Intent(LoginActivity.this, HomeActivity.class);
        if (extra != "")
            home.putExtra("login_data", extra);
        home.putExtra("facebook_login", facebook);
        startActivity(home);
    }

    private void openHomeMed() {
        EditText user = findViewById(R.id.username_text);
        String crm = user.getText().toString();

        Intent homeMed = new Intent(LoginActivity.this, HomeActivity.class);

        homeMed.putExtra("medico", crm);
        startActivity(homeMed);
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
        if(user.equals("11111") && password.equals("11111")){
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
        if (requestCode == 100) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
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
        SignInButton googleLoginButton = findViewById(R.id.login_button_google);

        // Facebook

        facebookLoginButton.setReadPermissions("email", "public_profile", "user_birthday");

        facebookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.i("FACEBOOK_LOGIN", "LOGADO COM SUCESSO!");

                loadFacebookProfileData(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            final JSONObject object,
                            GraphResponse response) {
                        response.getError();
                        Log.e("FACEBOOK_JSON", object.toString());

                        String name = "", email = "", birthdate = "", fbid = "";

                        try {
                            fbid = object.getString("id");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try {
                            name = object.getString("name");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try {
                            email = object.getString("email");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try {
                            birthdate = object.getString("birthday");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Log.i("DADOS", fbid);
                        Pacient pacient = new Pacient(name, email, birthdate, fbid);
                        Call<Pacient> call = new RetrofitConfig().getServices().signinPacientByFacebook(pacient);

                        call.enqueue(new Callback<Pacient>() {
                            @Override
                            public void onResponse(Call<Pacient> call, Response<Pacient> response) {
                                Log.i("RESPOSTA", String.valueOf(response.isSuccessful()));
                                if (response.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "LOGADO COM SUCESSO!", Toast.LENGTH_LONG)
                                            .show();
                                    Log.i("MENSAGEM", response.body().getMsg());
                                    openHome(object.toString(), true);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Erro - Response", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Pacient> call, Throwable t) {
                                Log.e("RESPOSTA   ", "Erro:" + t.getMessage());
                                Toast.makeText(getApplicationContext(), "Erro - Failure", Toast.LENGTH_LONG).show();
                            }
                        });
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

        // Google
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken("628518171498-vr0pk53j3ia6gvm8k8t1t53i901u1id9.apps.googleusercontent.com")
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        googleLoginButton.setSize(SignInButton.SIZE_STANDARD);
        googleLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("LOGIN_GOOGLE", "TESTANDO 1");
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 100);
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

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            String mensagem = "NAO";
            if (account != null) {
                mensagem = account.getEmail();
            }
            Log.i("LOGIN_GOOGLE", "TESTANDO 2");
            Log.i("LOGIN_GOOGLE", mensagem);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
