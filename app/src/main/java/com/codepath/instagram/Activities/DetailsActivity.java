package com.codepath.instagram.Activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.codepath.instagram.Models.Post;
import com.codepath.instagram.R;
import com.parse.ParseFile;

import org.parceler.Parcels;

import java.util.Date;

public class DetailsActivity extends AppCompatActivity {
    public static final String TAG = "DetailsActivity";
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

        currentPost = Parcels.unwrap(getIntent().getParcelableExtra(Post.class.getName()));
        tvDescription.setText(currentPost.getDescription());
        ParseFile image = currentPost.getImage();
        if (image != null) {
            Glide.with(this).load(image.getUrl()).into(ivImage);
        }
        tvUsername.setText(currentPost.getUser().getUsername());
        Date createdAt = currentPost.getCreatedAt();
        String timeAgo = Post.calculateTimeAgo(createdAt);
        tvRelativeDate.setText(timeAgo);

    }
}
