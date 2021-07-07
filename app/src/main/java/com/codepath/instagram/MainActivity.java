package com.codepath.instagram;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {
    ImageButton btnProfile;
    ImageButton btnPost;
    ImageButton btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnProfile = findViewById(R.id.btnProfile);
        btnPost = findViewById(R.id.btnPost);
        btnHome = findViewById(R.id.btnHome);

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnProfile.setImageResource(R.drawable.instagram_user_filled_24);
                Intent i = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(i);
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: change this back to not filled
                btnHome.setImageResource(R.drawable.instagram_home_filled_24);
            }
        });
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: change this back to not filled
                btnPost.setImageResource(R.drawable.instagram_new_post_filled_24);
                Intent i = new Intent(MainActivity.this, PostActivity.class);
                startActivity(i);
            }
        });
    }
}
