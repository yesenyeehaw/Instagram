package com.codepath.instagram.Activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.codepath.instagram.Models.Post;
import com.codepath.instagram.R;

import java.util.Date;

public class DetailsActivity extends AppCompatActivity {
    TextView tvUsername;
    ImageView ivImage;
    TextView tvRelativeDate;
    TextView tvDescription;
    Post currentPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        tvUsername = findViewById(R.id.tvUsername);
        ivImage = findViewById(R.id.ivImage);
        tvRelativeDate = findViewById(R.id.tvRelativeDate);
        tvDescription = findViewById(R.id.tvDescription);
    }
}
