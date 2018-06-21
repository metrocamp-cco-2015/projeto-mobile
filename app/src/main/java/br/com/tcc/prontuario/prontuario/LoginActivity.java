package br.com.tcc.prontuario.prontuario;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
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
            if (isMedic()) {
                checkLoginMedic(user.getText().toString(), password.getText().toString());
            } else if (isPacient()) {
                checkLoginPacient(user.getText().toString(), password.getText().toString());
            }
        }
    }

    private void checkLoginMedic(String user, String pass) {
        LoginData loginData = new LoginData(user, pass);
        Call<LoginData> call = new RetrofitConfig().getServices().signInMedic(loginData);
        call.enqueue(new Callback<LoginData>() {
            @Override
            public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                LoginData data = response.body();
                if (response.isSuccessful()) {
                    openHomeMed();
                }
                Toast.makeText(LoginActivity.this.getApplicationContext(), data.getMsg(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LoginData> call, Throwable t) {
                Log.i("DEU_RUIM_LOGIN_MED", t.getMessage());
            }
        });
    }

    private void checkLoginPacient(String user, String pass) {
        LoginData loginData = new LoginData(user, pass);
        Call<LoginData> call = new RetrofitConfig().getServices().signInPacient(loginData);
        call.enqueue(new Callback<LoginData>() {
            @Override
            public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                LoginData data = response.body();
                if (response.isSuccessful()) {
                    openHomePacient();
                }
                Toast.makeText(LoginActivity.this.getApplicationContext(), data.getMsg(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LoginData> call, Throwable t) {
                Log.i("DEU_RUIM_LOGIN_PACIENTE", t.getMessage());
            }
        });
    }

    private void openHomePacient() {
        EditText user = findViewById(R.id.username_text);
        String cpf = user.getText().toString();
        Intent home = new Intent(LoginActivity.this, HomeActivity.class);

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.pref_key), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.user_id), cpf);
        editor.putInt(getString(R.string.user_type), SharedPreferencesKeysManager.PACIENT_USER_TYPE);
        editor.putInt(getString(R.string.user_id_type), SharedPreferencesKeysManager.CPF);
        editor.apply();

        startActivity(home);
        this.finish();
    }

    private void openHomePacientBySocialNetwork(String email) {
        Intent home = new Intent(LoginActivity.this, HomeActivity.class);
        Log.e("EMAIL", email);
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.pref_key), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.user_id), email);
        editor.putInt(getString(R.string.user_type), SharedPreferencesKeysManager.PACIENT_USER_TYPE);
        editor.putInt(getString(R.string.user_id_type), SharedPreferencesKeysManager.EMAIL);
        editor.apply();

        startActivity(home);
        this.finish();
    }

    private void openHomeMed() {
        EditText user = findViewById(R.id.username_text);
        String crm = user.getText().toString();

        Intent homeMed = new Intent(LoginActivity.this, HomeActivity.class);

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.pref_key), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.user_id), crm);
        editor.putInt(getString(R.string.user_type), SharedPreferencesKeysManager.MEDIC_USER_TYPE);
        editor.putInt(getString(R.string.user_id_type), SharedPreferencesKeysManager.CRM);
        editor.apply();

        startActivity(homeMed);
        this.finish();
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
        return true;
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

                        String name = "", email = "", birthdate = "", fbid = "", url = "";

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

                        try {
                            url = object.getJSONObject("picture").getJSONObject("data").getString("url");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Log.i("DADOS", fbid);
                        final PacientFacebook pacient = new PacientFacebook(name, email, birthdate, fbid, url);
                        Call<PacientFacebook> call = new RetrofitConfig().getServices().checkPacientByFacebook(pacient);

                        call.enqueue(new Callback<PacientFacebook>() {
                            @Override
                            public void onResponse(Call<PacientFacebook> call, Response<PacientFacebook> response) {
                                Log.i("RESPOSTA", String.valueOf(response.isSuccessful()));
                                if (response.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "LOGADO COM SUCESSO!", Toast.LENGTH_LONG)
                                            .show();
                                    Log.i("MENSAGEM", response.body().getMsg());
                                    if (response.body().isSignup()) {
                                        showCpfConfirmationDialog(pacient);
                                    } else {
                                        openHomePacientBySocialNetwork(pacient.getEmail());
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), "Erro - Response", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<PacientFacebook> call, Throwable t) {
                                Log.e("RESPOSTAFACE", "Erro:" + t.getMessage());
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
                .requestIdToken("628518171498-n1pojihjk9su3qg5d26penb66tmi52va.apps.googleusercontent.com")
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
            if (account == null) {
                Log.i("LOGIN_GOOGLE", mensagem);
            } else {
                Log.i("LOGIN_GOOGLE", "TESTANDO 2");
                Log.i("LOGIN_GOOGLE_EMAIL", account.getEmail());
                Log.i("LOGIN_GOOGLE_DISPLAY", account.getDisplayName());
                Log.i("LOGIN_GOOGLE_FAMILY", account.getFamilyName());
                Log.i("LOGIN_GOOGLE_GIVEN", account.getGivenName());
                Log.i("LOGIN_GOOGLE_ID", account.getId());
                Log.i("LOGIN_GOOGLE_PHOTO", account.getPhotoUrl().toString());

                final PacientGoogle pacient = new PacientGoogle(account.getDisplayName(),
                        account.getEmail(), account.getId(), account.getPhotoUrl().toString());
                Call<PacientGoogle> call = new RetrofitConfig().getServices().signinPacientByGoogle(pacient);

                call.enqueue(new Callback<PacientGoogle>() {
                    @Override
                    public void onResponse(Call<PacientGoogle> call, Response<PacientGoogle> response) {
                        Log.i("RESPOSTA", String.valueOf(response.isSuccessful()));
                        if (response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "LOGADO COM SUCESSO!", Toast.LENGTH_LONG)
                                    .show();
                            Log.i("MENSAGEM", response.body().getMsg());
                            openHomePacientBySocialNetwork(pacient.getEmail());
                        } else {
                            Toast.makeText(getApplicationContext(), "Erro - Response", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<PacientGoogle> call, Throwable t) {
                        Log.e("RESPOSTA   ", "Erro:" + t.getMessage());
                        Toast.makeText(getApplicationContext(), "Erro - Failure", Toast.LENGTH_LONG).show();
                    }
                });
            }
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

    private boolean isPacient() {
        EditText user = findViewById(R.id.username_text);
        String text = user.getText().toString();
        return Validator.validateCpf(text);
    }

    private boolean isMedic() {
        EditText user = findViewById(R.id.username_text);
        String text = user.getText().toString();
        return text.length() == 5;
    }

    private void showCpfConfirmationDialog(final PacientFacebook pacient) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View alertView = inflater.inflate(R.layout.dialog_cpf_confirmation, null);
        builder.setView(alertView);

        final EditText cpfConfirmationText = alertView.findViewById(R.id.confirm_cpf_text);

        builder.setPositiveButton("Inserir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String cpf = cpfConfirmationText.getText().toString();
                if (Validator.validateCpf(cpf)) {
                    pacient.setCpf(cpf);
                    Call<PacientFacebook> call = new RetrofitConfig().getServices().signinPacientByFacebook(pacient);
                    call.enqueue(new Callback<PacientFacebook>() {
                        @Override
                        public void onResponse(Call<PacientFacebook> call, Response<PacientFacebook> response) {
                            openHomePacientBySocialNetwork(pacient.getEmail());
                        }

                        @Override
                        public void onFailure(Call<PacientFacebook> call, Throwable t) {
                            Log.e("RESPOSTAINSERT   ", "Erro:" + t.getMessage());
                            Toast.makeText(getApplicationContext(), "Erro - Failure", Toast.LENGTH_LONG).show();
                        }
                    });
                }
             }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
