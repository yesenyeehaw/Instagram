package com.codepath.instagram.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.codepath.instagram.R;
import com.parse.ParseUser;

public class ProfileActivity extends AppCompatActivity {
    Button btnLogOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnLogOut = findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                ParseUser currentUser = ParseUser.getCurrentUser(); // this will now be null
                goLoginActivity(); //take us back to login activity
            }
        });
    }
    private void goLoginActivity(){
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }
}
