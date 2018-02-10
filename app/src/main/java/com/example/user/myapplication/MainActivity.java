package com.example.user.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.data.ets.History;
import com.data.ets.User;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.utils.EtsUtils;

import org.parceler.Parcels;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener {

    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 9001;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    private GoogleApiClient mGoogleApiClient;
    static int diffInDays;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        findViewById(R.id.sign_in_button).setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null){
            FirebaseDatabase database = FirebaseDatabase.getInstance();

            final DatabaseReference rootRef = database.getReference();
             Query query = rootRef.child("user").orderByChild("userEmail").equalTo(user.getEmail());
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    User user = null;

                    for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                         user = messageSnapshot.getValue(User.class);
                        user.setUserCode(messageSnapshot.getKey());
                    }

                    if (user == null) {
                        Intent i = new Intent(getApplicationContext(), NameActivity.class);
                        startActivity(i);
                    } else {
                        EtsUtils.saveObjectToSharedPreference(getApplicationContext(),"user",user);
                        Query queryHist = rootRef.child("history").orderByChild("userKey").equalTo(user.getUserCode() ).limitToFirst(1);
                        queryHist.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                History history = new History();
                                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                                    history = messageSnapshot.getValue(History.class);

                                    Date histDate =  new Date(history.getHistDate());
                                    diffInDays = (int)( (new Date().getTime() - histDate.getTime()) / (1000 * 60 * 60 * 24) );
                                   Log.i("TestDay",String.valueOf(diffInDays));
                                    if(diffInDays >= 30){
                                        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                                        alertDialog.setTitle("Alert");
                                        alertDialog.setMessage("ประวัติอายุเกิน30วัน");
                                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.dismiss();
                                                    }
                                                });

                                        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                            @Override
                                            public void onDismiss(DialogInterface dialogInterface) {
                                                Intent i = new Intent(getApplicationContext(), MenuActivity.class);
                                                startActivity(i);
                                            }
                                        });
                                        alertDialog.show();
                                    }else{
                                        Intent i = new Intent(getApplicationContext(), MenuActivity.class);
                                        startActivity(i);
                                    }
                                }
                                if(history != null) {
                                    EtsUtils.saveObjectToSharedPreference(getApplicationContext(), "history", history);
                                }
                                Log.i("Test","Add");
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Log.e(TAG, databaseError.getMessage());
                            }
                        });

                    }
                   // finish();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e(TAG, databaseError.getMessage());
                }
            });



        }



        Button buttonNext = (Button) findViewById(R.id.button3);
        buttonNext.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), DiscoveryActivity.class);
                startActivity(i);
            }
            });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        //Log.d(TAG, "onConnectionFailed:" + connectionResult);
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.sign_in_button) {
            signIn();

        }

    }


    // [START signin]
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            Toast.makeText(this, String.valueOf(result.getStatus()), Toast.LENGTH_SHORT).show();
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                Log.d(TAG, account.getAccount().name);
                Toast.makeText(this, account.getAccount().name, Toast.LENGTH_SHORT).show();
                firebaseAuthWithGoogle(account);





            } else {
                // Google Sign In failed, update UI appropriately
                // [START_EXCLUDE]
                //    updateUI(null);
                // [END_EXCLUDE]
            }
        }
    }


    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
        // [START_EXCLUDE silent]
        //   showProgressDialog();
        // [END_EXCLUDE]

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();



                            if(user != null){
                                FirebaseDatabase database = FirebaseDatabase.getInstance();

                                DatabaseReference rootRef = database.getReference();
                                Query query = rootRef.child("user").orderByChild("userEmail").equalTo(user.getEmail());
                                query.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        User user = null;

                                        for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                                            user = messageSnapshot.getValue(User.class);
                                            user.setUserCode(messageSnapshot.getKey());
                                        }

                                        if (user == null) {
                                            Intent i = new Intent(getApplicationContext(), NameActivity.class);
                                            startActivity(i);
                                        } else {

                                            EtsUtils.saveObjectToSharedPreference(getApplicationContext(),"user",user);
                                            Intent i = new Intent(getApplicationContext(), MenuActivity.class);
                                            startActivity(i);
                                        }
                                        // finish();
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                        Log.e(TAG, databaseError.getMessage());
                                    }
                                });



                            }

                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            // updateUI(null);
                        }

                        // [START_EXCLUDE]
                        //    hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
    }


}
