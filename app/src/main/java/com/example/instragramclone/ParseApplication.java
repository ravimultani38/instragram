package com.example.instragramclone;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {

        // Register your parse models
        ParseObject.registerSubclass(Post.class);
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("fOv8c8fHcnUXdJ2Bx43bsoKV5a8EX1UTNVqaZz60")
                .clientKey("xTps98jSOkvsWkd8lCsfGfOFA2NFm7joe9nI4oc8")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
