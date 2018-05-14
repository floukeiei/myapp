package com.example.user.myapplication;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.InputType;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.data.ets.Follower;
import com.data.ets.User;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;

import com.example.user.myapplication.ExFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.utils.EtsUtils;

import org.apache.commons.lang3.StringUtils;

import layout.FollowerFragment;
import layout.FollowerFragment;
import layout.testexFragment;
import nodomain.freeyourgadget.gadgetbridge.GBApplication;
import nodomain.freeyourgadget.gadgetbridge.activities.ControlCenterv2;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, HomeFragment.OnFragmentInteractionListener, PlanFragment.OnFragmentInteractionListener
        ,HistExFragment.OnFragmentInteractionListener,HistExDetailFragment.OnFragmentInteractionListener, PlanDetailFragment.OnFragmentInteractionListener, GoogleApiClient.OnConnectionFailedListener
        ,testexFragment.OnFragmentInteractionListener,FollowerFragment.OnFragmentInteractionListener,ExerciseFragment.OnFragmentInteractionListener, ExFragment.OnFragmentInteractionListener
        ,ProfileFragment.OnFragmentInteractionListener,HistVO2Fragment.OnFragmentInteractionListener
    {

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]
    static String currentFragment;
    private GoogleApiClient mGoogleApiClient;
    private  FloatingActionButton fab;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.INVISIBLE);
//        final FollowerFragment followerFragment = (FollowerFragment)getSupportFragmentManager().findFragmentByTag("fragment_follower");

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  if ("follow".equals(currentFragment)) {
                      final EditText input = new EditText(MenuActivity.this);
                      AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);

                      builder.setTitle("Email");

                    // Set up the input

                    // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                    builder.setView(input);

                        // Set up the buttons
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                           // save
                            if(StringUtils.isNoneBlank(input.getText().toString())) {
                                final FirebaseDatabase database = FirebaseDatabase.getInstance();

                                DatabaseReference mRootRef = database.getReference();
                                User user = EtsUtils.getSavedObjectFromPreference(getApplicationContext(), "user", User.class);

                                DatabaseReference mFollowRef = mRootRef.child("follow/" + user.getUserCode());
                                Follower follower = new Follower();
                                follower.setEmail(input.getText().toString());
                                follower.setUserKey(user.getUserCode());
                                mFollowRef.push().setValue(follower);

                            }
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    builder.show();
                }
            }
        });

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .build();

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.menu, menu);
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
// Create new fragment and transaction
        fab.setVisibility(View.INVISIBLE);
        Fragment newFragment = new Fragment();
        if (id == R.id.menu_profile) {
            //newFragment = new HomeFragment();
//            Intent i = new Intent(getApplicationContext(), NameActivity.class);
//
//            i.putExtra("fromPage","Menu");
//            startActivity(i);
//            return true;
            currentFragment = "profile";
            newFragment = new ProfileFragment();

        } else if (id == R.id.menu_program) {
            currentFragment = "plan";
            newFragment = new PlanFragment();
        } else if (id == R.id.menu_history) {
            currentFragment = "histex";
            newFragment = new HistExFragment();
        }  else if (id == R.id.menu_exercise) {
            currentFragment = "ex";
            newFragment = new ExFragment();
        } else if (id == R.id.menu_assessment) {
            Intent i = new Intent(getApplicationContext(), HeightActivity.class);

            i.putExtra("fromPage","Menu");
            startActivity(i);
            return true;
        }
//        else if (id == R.id.menu_design) {
//
//        }
        else if (id == R.id.menu_follower) {
            fab.setVisibility(View.VISIBLE);
            currentFragment = "follow";
            newFragment = new FollowerFragment();
        }
        else if (id == R.id.menu_histvo2) {
            currentFragment = "histvo2";
            newFragment = new HistVO2Fragment();
        }

        else if (id == R.id.menu_test) {
            currentFragment = "testex";
            newFragment = new testexFragment();
        } else if (id == R.id.menu_device) {
            Intent i = new Intent(getApplicationContext(), ControlCenterv2.class);
            startActivity(i);
            return true;

        } else if (id == R.id.menu_logout) {
            signOut();
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            return true;
        } else if (id == R.id.menu_quit) {
            GBApplication.quit();
            finish();
            return true;
        }


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
        transaction.replace(R.id.frame, newFragment);
        transaction.addToBackStack(null);

// Commit the transaction
        transaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private void signOut() {
        // Firebase sign out
        mAuth.signOut();

        // Google sign out
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        // updateUI(null);
                    }
                });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
