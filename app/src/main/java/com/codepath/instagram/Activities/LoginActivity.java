package com.codepath.instagram.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codepath.instagram.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity extends AppCompatActivity {

    private final String TAG = "LoginActivity";
    ImageView ivAppIcon;
    EditText etUsername;
    EditText etPassword;
    Button btnLogin;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
// check to see if theres a user already logged in
        if(ParseUser.getCurrentUser() != null){
            goMainActivity();
        }
        ivAppIcon = findViewById(R.id.ivAppIcon);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);

        Glide.with(this).load(R.drawable.nav_logo_whiteout).into(ivAppIcon);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"Login pressed!" );
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                loginUser(username, password);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"Sign Up pressed!" );
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                signUpUser(username, password);
            }
        });
    }


    private void loginUser(String username, String password){
        Log.i(TAG, "Logging in..." + username);
        //navigate to the main activity if user has signed in properly
        ParseUser.logInInBackground(username, password, new LogInCallback() { //logInInBackground happens in the background thread
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) { // means something went wrong
                    Log.e(TAG, "!!!Issue with login!!!", e);
                    //TODO: create a toast based on if the user is missing username or password
                    Toast.makeText(LoginActivity.this, "Issue with login!", Toast.LENGTH_SHORT).show();
                    return;
                }
                goMainActivity();
                Toast.makeText(LoginActivity.this, "Successfully Logged in!", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void signUpUser(String username, String password){
        // Create the ParseUser
        ParseUser user = new ParseUser();
        // Set core properties
        user.setUsername(username);
        user.setPassword(password);
        // Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e != null) {
                    Toast.makeText(LoginActivity.this, "Issue with SignUp!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(LoginActivity.this, "Successfully Signed Up! You may now log in.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void goMainActivity(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}