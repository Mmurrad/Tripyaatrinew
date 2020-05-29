package com.example.tripyaatrinew;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tripyaatrinew.Database.DatabaseHelper;
import com.example.tripyaatrinew.model.VisitorsCount;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.AuthProvider;


public class MainActivity extends AppCompatActivity {

    EditText username,phonenumber;
    Button login,loginwithemail,loginwithfacebook,skip;
    TextView forget_password;

    int count=0,result=0;
    DatabaseHelper databaseHelper;

    SignInButton signInButton;
    private CallbackManager callbackManager;
    private FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    private FirebaseAuth.AuthStateListener authStateListener;
    private  LoginButton loginButton;
    private static final String TAG="FacebookAuthentication";
    GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=findViewById(R.id.username_id);
        phonenumber=findViewById(R.id.phone_id);
        login=findViewById(R.id.login_id);

        skip=findViewById(R.id.skip_id);


        loginButton=findViewById(R.id.login_button);
        signInButton=findViewById(R.id.signIn_button_id);

        databaseHelper=new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase=databaseHelper.getWritableDatabase();


        databaseReference=FirebaseDatabase.getInstance().getReference("Count");

        VisitorsCount visitorsCount=new VisitorsCount(1);
        databaseReference.push().setValue(visitorsCount);

        loginButton.setReadPermissions("email","public_profile");
        callbackManager=CallbackManager.Factory.create();


        // Configure Google Sign In
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = googleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 101);
                count++;
            }
        });
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient= GoogleSignIn.getClient(this,gso);

        //login with facebook
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG,"Onsuccess"+loginResult);
                handeltoken(loginResult.getAccessToken());
                count++;
            }

            @Override
            public void onCancel() {
                Log.d(TAG,"Oncancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG,"OnError"+error);
            }
        });

        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
                if(firebaseUser!=null)
                {
                    updateUI(firebaseUser);
                }
                else {
                    updateUI(null);
                }
            }
        };

        firebaseAuth=FirebaseAuth.getInstance();
        FacebookSdk.sdkInitialize(getApplicationContext());

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    databaseHelper.addData("Authentication");
                    Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                    intent.putExtra("admin_key","skip");
                    startActivity(intent);

                }catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_LONG).show();
                }

            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone=phonenumber.getText().toString();
                String u_name=username.getText().toString();

                if(phone.equals("01521307785")&&u_name.equals("murad"))
                {
                    Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                    intent.putExtra("admin_key","admin");
                    startActivity(intent);

                    phonenumber.setText("");
                    username.setText("");
                }
            }
        });

    }

    private void handeltoken(AccessToken accessToken) {
        Log.d(TAG,"HandleFacebookToken"+accessToken);

        AuthCredential authCredential= FacebookAuthProvider.getCredential(accessToken.getToken());
        firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Log.d(TAG,"Sign in with credential : Successfull");
                    FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
                    Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                    startActivity(intent);
                    updateUI(firebaseUser);
                }else {
                    Log.d(TAG,"Sign in with credential : Failed"+task.getException());
                    updateUI(null);
                }
            }
        });
    }



    private void updateUI(FirebaseUser firebaseUser) {
        if(firebaseUser!=null)
        {
            username.setText(firebaseUser.getDisplayName());
        }
        else {
            username.setText("");
        }
    }




    //gmail authentication


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 101) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                Log.w(TAG, "Google sign in failed", e);
            }
        }
    }
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(getApplicationContext(),"Sign In Successfully",Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(),"Sign In Failed",Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }


}
