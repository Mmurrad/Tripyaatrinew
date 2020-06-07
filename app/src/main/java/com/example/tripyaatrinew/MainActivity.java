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
import com.facebook.AccessTokenTracker;
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
import com.google.android.gms.tasks.TaskExecutors;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.AuthProvider;
import java.util.concurrent.TimeUnit;

import static com.facebook.FacebookSdk.setAdvertiserIDCollectionEnabled;


public class MainActivity extends AppCompatActivity {

    EditText username,phonenumber,user_phone,verification_code;
    Button login,verification_send,signin,skip;


    int count=0;
    String codeSent;
    FirebaseAuth mAuth;

    DatabaseHelper databaseHelper;

    SignInButton signInButton;
    private CallbackManager callbackManager;
    private FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    private FirebaseAuth.AuthStateListener authStateListener;
    private static final String TAG="FacebookAuthentication";
    GoogleSignInClient googleSignInClient;
    private AccessTokenTracker accessTokenTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAuth = FirebaseAuth.getInstance();
        username=findViewById(R.id.username_id);
        phonenumber=findViewById(R.id.phone_id);
        login=findViewById(R.id.login_id);

        user_phone=findViewById(R.id.user_phone_id);
        verification_code=findViewById(R.id.user_verification_code_id);

        verification_send=findViewById(R.id.verification_code_id);
        signin=findViewById(R.id.user_sign_in_id);

        skip=findViewById(R.id.skip_id);

        signInButton=findViewById(R.id.signIn_button_id);

        databaseHelper=new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase=databaseHelper.getWritableDatabase();

        databaseReference=FirebaseDatabase.getInstance().getReference("Count");

        VisitorsCount visitorsCount=new VisitorsCount(1);
        databaseReference.push().setValue(visitorsCount);

        callbackManager=CallbackManager.Factory.create();

        firebaseAuth=FirebaseAuth.getInstance();
        FacebookSdk.sdkInitialize(getApplicationContext());


        //Sign In using phone
        verification_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendVerificationCode();
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifySignInCode();
            }
        });
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



        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    databaseHelper.addData("Authentication");
                    Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                    intent.putExtra("admin_key","skip");
                    intent.putExtra("comment","not");
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




    private void updateUI(FirebaseUser firebaseUser) {
        if(firebaseUser!=null)
        {
            username.setText(firebaseUser.getDisplayName());
            Intent intent=new Intent(MainActivity.this,HomeActivity.class);
            startActivity(intent);
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
                            try{
                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                                intent.putExtra("admin_key","skip");
                                startActivity(intent);
                                finish();
                                Toast.makeText(getApplicationContext(),"Sign In Successfully",Toast.LENGTH_LONG).show();
                            }catch (Exception e)
                            {

                            }

                        } else {
                            Toast.makeText(getApplicationContext(),"Sign In Failed",Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }


    //phone authenticatio
    private void sendVerificationCode(){

        String phone = user_phone.getText().toString();

        if(phone.isEmpty()){
            user_phone.setError("Phone number is required");
            user_phone.requestFocus();
            return;
        }

        if(phone.length() < 11 ){
            user_phone.setError("Please enter a valid phone");
            user_phone.requestFocus();
            return;
        }

        /*Intent intent=new Intent(MainActivity.this,VerificationActivity.class);
        intent.putExtra("key",phone);
        startActivity(intent);*/

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+88"+phone,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                TaskExecutors.MAIN_THREAD,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {


        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                user_phone.setText(code);
                //verifying the code
                verifyVerificationCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            codeSent = s;
        }
    };



    private void verifySignInCode(){
        String code = verification_code.getText().toString();
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //here you can open new activity
                            Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                            intent.putExtra("admin_key","skip");
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(),
                                    "Login Successfull", Toast.LENGTH_LONG).show();

                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(getApplicationContext(),
                                        "Incorrect Verification Code ", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }
    private void verifyVerificationCode(String code) {
        //creating the credential
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, code);

        //signing the user
        signInWithPhoneAuthCredential(credential);
    }
}
