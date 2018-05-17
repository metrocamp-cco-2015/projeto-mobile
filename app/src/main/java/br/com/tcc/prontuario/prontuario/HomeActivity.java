package br.com.tcc.prontuario.prontuario;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.ImageView;
import android.widget.TextView;


import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();

        facebookHandler(bundle);

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
            ConstraintLayout regularLayout = findViewById(R.id.regular_data_layout);
            regularLayout.setVisibility(View.VISIBLE);
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
