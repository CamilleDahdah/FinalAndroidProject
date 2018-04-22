package com.example.camilledahdah.finalandroidproject.screens.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.camilledahdah.finalandroidproject.R;
import com.example.camilledahdah.finalandroidproject.data.local.LocalStorageManager;
import com.example.camilledahdah.finalandroidproject.models.User;
import com.example.camilledahdah.finalandroidproject.screens.authentication.AuthenticationActivity;
import com.example.camilledahdah.finalandroidproject.screens.main.profile.ProfileFragment;
import com.example.camilledahdah.finalandroidproject.screens.main.trips.PostTripFragment;
import com.example.camilledahdah.finalandroidproject.screens.main.trips.SearchTripsFragment;
import com.example.camilledahdah.finalandroidproject.screens.main.trips.TripsListFragment;

/**
 * Created by camilledahdah on 4/21/18.
 */

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ProfileFragment.ProfileFragmentListener,
        PostTripFragment.LocationFragmentListener, TripsListFragment.TripsListFragmentListener {

    private LocalStorageManager localStorageManager;
    private TextView nameHeaderTextView, emailHeaderTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        localStorageManager = LocalStorageManager.getInstance(this);

        NavigationView navigationView1 = findViewById(R.id.nav_view);
        View header = navigationView1.getHeaderView(0);
        nameHeaderTextView = header.findViewById(R.id.name);
        emailHeaderTextView = header.findViewById(R.id.email);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        searchTrips();
        populateHeaderViews();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {

            case R.id.nav_post_trip:
                postTrip();
                break;

            case R.id.nav_logout:
                logout();
                break;

            case R.id.nav_search_trips:
                searchTrips();
                break;

            case R.id.nav_profile:
                showProfileFragment();
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void postTrip() {
        //setTitle(getString(R.string.title_events));
        PostTripFragment fragment = PostTripFragment.newInstance();
        getFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    private void searchTrips() {
        //setTitle(getString(R.string.title_events));
        SearchTripsFragment fragment = SearchTripsFragment.newInstance();
        getFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    private void logout() {
        LocalStorageManager.getInstance(this).deleteUser();
        Intent intent = new Intent(this, AuthenticationActivity.class);
        startActivity(intent);
        finish();
    }

    private void showProfileFragment() {
        //setTitle(getString(R.string.title_profile));
        ProfileFragment fragment = ProfileFragment.newInstance();
        getFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();

    }

    private void populateHeaderViews() {
        User user = localStorageManager.getUser();
        if (user != null) {
            nameHeaderTextView.setText(user.getFirstName() + " " + user.getLastName());
            emailHeaderTextView.setText(user.getEmail());
        }
    }

    private void gotoAuthenticationScreen() {
        Intent intent = new Intent(this, AuthenticationActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onProfileFetchFailure() {
        gotoAuthenticationScreen();
    }

    @Override
    public void onRequestCreateNewEvent() {
        //setTitle(getString(R.string.PostTripFragment));
        PostTripFragment fragment = PostTripFragment.newInstance();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(PostTripFragment.TAG).commit();
    }

    @Override
    public void onErrorFetchingEvents() {
        gotoAuthenticationScreen();
    }

    @Override
    public void onNewEvertCreatedSuccessfully() {
        //setTitle(getString(R.string.title_events));
        getFragmentManager().popBackStack();
    }

    @Override
    public void onNewEvertCreationFailure() {
        gotoAuthenticationScreen();
    }

}