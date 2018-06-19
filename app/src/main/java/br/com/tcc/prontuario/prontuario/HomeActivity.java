package br.com.tcc.prontuario.prontuario;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
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
import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        handleButtons();
        handleList();
    }

    private void handleButtons() {
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.pref_key), MODE_PRIVATE);
        int userType = sharedPreferences.getInt(getString(R.string.user_type), -1);

        if (userType == SharedPreferencesKeysManager.MEDIC_USER_TYPE) {

            Button medic_profile_button = findViewById(R.id.medic_profile_button);
            medic_profile_button.setVisibility(View.VISIBLE);
            medic_profile_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(HomeActivity.this, MedicProfileActivity.class);
                    startActivity(intent);
                }
            });

        } else if (userType == SharedPreferencesKeysManager.PACIENT_USER_TYPE) {
            Button pacient_profile_button = findViewById(R.id.pacient_profile_button);
            pacient_profile_button.setVisibility(View.VISIBLE);
            pacient_profile_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(HomeActivity.this, PacientProfileActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    private void handleList() {
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.pref_key), MODE_PRIVATE);
        int userType = sharedPreferences.getInt(getString(R.string.user_type), -1);
        String userId = sharedPreferences.getString(getString(R.string.user_id), "");

        final ListView consultsList = findViewById(R.id.consults_list);
        final TextView noConsultsText = findViewById(R.id.no_consults_text);

        if (userType == SharedPreferencesKeysManager.MEDIC_USER_TYPE) {
            Log.i("LISTA_MED", userId);
            ConsultsPacient consultsPacient = new ConsultsPacient();
            consultsPacient.setCrm(userId);
            Call<ConsultsPacient> consults = new RetrofitConfig().getServices().getConsultsByCrm(consultsPacient);
            consults.enqueue(new Callback<ConsultsPacient>() {
                @Override
                public void onResponse(Call<ConsultsPacient> call, Response<ConsultsPacient> response) {
                    if (response.isSuccessful()) {
                        ConsultsPacient data = response.body();
                        if (data.getMsg() == null) {
                            ConsultsPacientAdapter adapter = new ConsultsPacientAdapter(HomeActivity.this,
                                    R.layout.card_pacient_consult, data.getConsults());
                            consultsList.setAdapter(adapter);
                        } else {
                            noConsultsText.setText(data.getMsg());
                            Log.i("MEDIC_LIST", "nada");
                        }
                    }
                }

                @Override
                public void onFailure(Call<ConsultsPacient> call, Throwable t) {
                    Log.i("DEU_RUIM_LISTA_MED", t.getMessage());
                }
            });

        } else if (userType == SharedPreferencesKeysManager.PACIENT_USER_TYPE) {
            Log.i("LISTA_PACIENTE", userId);
            ConsultsMedic consultsMedic = new ConsultsMedic();
            consultsMedic.setCpf(userId);
            Call<ConsultsMedic> consults = new RetrofitConfig().getServices().getConsultsByCpf(consultsMedic);
            consults.enqueue(new Callback<ConsultsMedic>() {
                @Override
                public void onResponse(Call<ConsultsMedic> call, Response<ConsultsMedic> response) {
                    if (response.isSuccessful()) {
                        ConsultsMedic data = response.body();
                        if (data.getMsg() == null) {
                            ConsultsMedicAdapter adapter = new ConsultsMedicAdapter(HomeActivity.this,
                                    R.layout.card_medical_consult, data.getConsults());
                            consultsList.setAdapter(adapter);
                        } else {
                            noConsultsText.setText(data.getMsg());
                            Log.i("MEDIC_LIST", "nada");
                        }
                    }
                }

                @Override
                public void onFailure(Call<ConsultsMedic> call, Throwable t) {
                    Log.i("DEU_RUIM_LISTA_PACIENTE", t.getMessage());
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_doctor) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
