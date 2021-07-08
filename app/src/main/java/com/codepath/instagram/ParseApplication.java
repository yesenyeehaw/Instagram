package com.codepath.instagram;

import android.app.Application;

import com.codepath.instagram.Models.Post;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Register your parse models
        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("BELQdgNsUpKafXhgN0RxLC67Oseqy0cv5WBRwtuQ")
                .clientKey("Y3UFma2xw3NJSiigGhEbDdgDKMTTeBjt2Fxa2FVA")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }

}
