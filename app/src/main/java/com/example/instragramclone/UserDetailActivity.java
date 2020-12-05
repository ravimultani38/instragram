package com.example.instragramclone;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class UserDetailActivity extends AppCompatActivity {

    private ImageView ivImg;
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        final ActionBar actionBar = getSupportActionBar();
        //getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        //actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));

        String image = getIntent().getStringExtra("image");

        ivImg = findViewById(R.id.ivImg);

        Glide.with(this).load(image).into(ivImg);
    }
}