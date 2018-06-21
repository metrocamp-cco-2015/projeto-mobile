package br.com.tcc.prontuario.prontuario;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// TODO Corrigir o perfil com a leitura de dados do banco
// TODO Colocar o XML da Home aqui

public class PacientProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacient_profile);

        handleFields();
    }

    private void handleFields() {
        final TextView txtName = findViewById(R.id.name_text);
        final TextView txtEmail = findViewById(R.id.email_text);
        final TextView txtBirthdate = findViewById(R.id.birthdate_text);
        final TextView txtCpf = findViewById(R.id.cpf_text);
        final TextView txtGender = findViewById(R.id.gender_text);
        final TextView txtPhone = findViewById(R.id.phone_text);
        final ImageView profileImage = findViewById(R.id.profile_image);

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.pref_key), MODE_PRIVATE);

        String userId = sharedPreferences.getString(getString(R.string.user_id), "");
        int userIdType = sharedPreferences.getInt(getString(R.string.user_id_type), -1);

        Log.i("userId", userId);

        if (userId != "") {
            Pacient pacient = null;
            if (userIdType == SharedPreferencesKeysManager.CPF) {
                pacient = new Pacient();
                pacient.setCpf(userId);

                Call<Pacient> call = new RetrofitConfig().getServices().getPacientByCpf(pacient);

                call.enqueue(new Callback<Pacient>() {
                    @Override
                    public void onResponse(Call<Pacient> call, Response<Pacient> response) {
                        if (response.isSuccessful()) {
                            Pacient pacient = response.body();

                            txtName.setText(pacient.getName() != null ? pacient.getName() : "EMPTY");
                            txtEmail.setText(pacient.getEmail() != null ? pacient.getEmail() : "EMPTY");
                            txtBirthdate.setText(pacient.getBirthdate() != null ? pacient.getBirthdate() : "EMPTY");
                            txtCpf.setText(pacient.getCpf() != null ? pacient.getCpf() : "EMPTY");
                            txtGender.setText(pacient.getGender() != null ? pacient.getGender() : "EMPTY");
                            txtPhone.setText(pacient.getPhoneNumber() != null ? pacient.getPhoneNumber() : "EMPTY");

                            Log.e("DEU_BOM", pacient.getCpf());

                            ConstraintLayout regularLayout = findViewById(R.id.regular_data_layout);
                            regularLayout.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(Call<Pacient> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "FALHA", Toast.LENGTH_LONG).show();
                        Log.e("DEU_RUIM_CPF", t.getMessage());
                    }
                });
            } else if (userIdType == SharedPreferencesKeysManager.EMAIL) {
                pacient = new Pacient();
                pacient.setEmail(userId);

                Call<Pacient> call = new RetrofitConfig().getServices().getPacientByEmail(pacient);

                call.enqueue(new Callback<Pacient>() {
                    @Override
                    public void onResponse(Call<Pacient> call, Response<Pacient> response) {
                        if (response.isSuccessful()) {
                            Pacient pacient = response.body();

                            txtName.setText(pacient.getName() != null ? pacient.getName() : "EMPTY");
                            txtEmail.setText(pacient.getEmail() != null ? pacient.getEmail() : "EMPTY");
                            txtBirthdate.setText(pacient.getBirthdate() != null ? pacient.getBirthdate() : "EMPTY");
                            txtCpf.setText(pacient.getCpf() != null ? pacient.getCpf() : "EMPTY");
                            txtGender.setText(pacient.getGender() != null ? pacient.getGender() : "EMPTY");
                            txtPhone.setText(pacient.getPhoneNumber() != null ? pacient.getPhoneNumber() : "EMPTY");
//                            if (pacient.getImage() != null ) {
//                                Glide
//                                        .with(PacientProfileActivity.this.getApplicationContext())
//                                        .load(pacient.getImage())
//                                        .into(profileImage);
//                            }

                            Log.e("DEU_BOM", pacient.getCpf());

                            ConstraintLayout regularLayout = findViewById(R.id.regular_data_layout);
                            regularLayout.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(Call<Pacient> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "FALHA", Toast.LENGTH_LONG).show();
                        Log.e("DEU_RUIM_EMAIL", t.getMessage());
                    }
                });
            }
        } else {
            Toast.makeText(this.getApplicationContext(),
                    "Não foi possível recuperar as informações", Toast.LENGTH_LONG).show();
        }
    }
}
