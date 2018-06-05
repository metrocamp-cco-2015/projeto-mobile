package br.com.tcc.prontuario.prontuario;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

public class MedicProfileActivity extends AppCompatActivity
        implements GoogleApiClient.OnConnectionFailedListener{

    private GoogleApiClient mGoogleApiClient;

    private final int PLACE_PICKER_REQUEST = 1;
    private final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 111;
    private boolean mLocationPermissionGranted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medic_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();

        facebookHandler(bundle);
    }

    private void facebookHandler(Bundle bundle) {
        JSONObject json = null;
        boolean isFacebookLogin = bundle.getBoolean("facebook_login");

        if (isFacebookLogin) {
            try {
                json = new JSONObject(bundle.getString("login_data", "{}"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                Log.e("FACEBOOK_JSON", json.getString("id"));
                Log.e("FACEBOOK_JSON", json.getString("url"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            ConstraintLayout facebookLayout = findViewById(R.id.facebook_data_layout);
            ImageView imgProfile = findViewById(R.id.profile_image_facebook);
            TextView txtName = findViewById(R.id.name_text_facebook);
            TextView txtEmail = findViewById(R.id.email_text_facebook);
            TextView txtBirthdate = findViewById(R.id.birthdate_text_facebook);
            String profileImageUrl = "";

            facebookLayout.setVisibility(View.VISIBLE);

//        try {
//            profileImageUrl = json.getString("url");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//
//        Glide.with(this).load(profileImageUrl).into(imgProfile);

            try {
                txtName.setText(json.getString("name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                txtEmail.setText(json.getString("email"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                txtBirthdate.setText(json.getString("birthday"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {

            final TextView txtName = findViewById(R.id.name_text);
            final TextView txtEmail = findViewById(R.id.email_text);
            final TextView txtBirthdate = findViewById(R.id.birthdate_text);
            final TextView txtCpf = findViewById(R.id.cpf_text);
            final TextView txtGender = findViewById(R.id.gender_text);
            final TextView txtPhone = findViewById(R.id.phone_text);
            final TextView txtMaps = findViewById(R.id.maps_text);

            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(Places.GEO_DATA_API)
                    .addApi(Places.PLACE_DETECTION_API)
                    .enableAutoManage(this, this)
                    .build();

            Button mapButton = findViewById(R.id.maps_button);
            mapButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getLocationPermission();
                }
            });


            String crm = bundle.getString("medico");

            Log.i("CRM", crm);

            Medic medic = new Medic();
            medic.setCrm(crm);

            Call<Medic> call = new RetrofitConfig().getServices().getMedicByCrm(medic);

            call.enqueue(new Callback<Medic>() {
                @Override
                public void onResponse(Call<Medic> call, Response<Medic> response) {
                    if (response.isSuccessful()) {
                        Medic medic = response.body();

                        txtName.setText(medic.getName() != null ? medic.getName() : "EMPTY");
                        txtEmail.setText(medic.getEmail() != null ? medic.getEmail() : "EMPTY");
                        txtBirthdate.setText(medic.getBirthdate() != null ? medic.getBirthdate() : "EMPTY");
                        txtCpf.setText(medic.getCrm() != null ? medic.getCrm() : "EMPTY");
                        txtGender.setText(medic.getGender() != null ? medic.getGender() : "EMPTY");
                        txtPhone.setText(medic.getPhoneNumber() != null ? medic.getPhoneNumber() : "EMPTY");
                        txtMaps.setText(medic.getMaps() != null ? medic.getMaps() : "EMPTY");

                        Log.e("DEU_BOM", medic.getCrm());

                        ConstraintLayout regularLayout = findViewById(R.id.regular_data_layout);
                        regularLayout.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<Medic> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "FALHA", Toast.LENGTH_LONG).show();
                    Log.e("DEU_RUIM", t.getMessage());
                }
            });
        }
    }

    private void openPicker() {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try {
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                String toastMsg = String.format("Place: %s", place.getName());

                Medic medic = new Medic();
                medic.setCrm(getIntent().getExtras().getString("medico"));
                medic.setMaps(place.getName().toString());

                Call<Medic> call = new RetrofitConfig().getServices().addMedicMaps(medic);

                call.enqueue(new Callback<Medic>() {
                    @Override
                    public void onResponse(Call<Medic> call, Response<Medic> response) {
                        if (response.isSuccessful()) {
                            TextView maps = findViewById(R.id.maps_text);
                            maps.setText(response.body().getMaps());
                        }
                    }

                    @Override
                    public void onFailure(Call<Medic> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "FALHA", Toast.LENGTH_LONG).show();
                        Log.e("DEU_RUIM", t.getMessage());
                    }
                });

                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void getLocationPermission() {
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            openPicker();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openPicker();
                }
            }
        }
    }
}
