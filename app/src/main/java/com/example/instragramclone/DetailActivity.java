package com.example.instragramclone;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.instragramclone.fragments.PostsFragment;

public class DetailActivity extends AppCompatActivity {

    public static final String TAG = "DetailActivity";

    private TextView tvUsername;
    private TextView tvDescription;
    private ImageView ivImg;
    private TextView tvTime;
    private ImageView ivHeart;
    private ImageView ivComment;
    private ImageView ivSave;
    private TextView tvTotalLikes;
    private TextView tvTotalComments;
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.detail_bar);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
        // actionBar.hide();

        String user = getIntent().getStringExtra("user");
        String image = getIntent().getStringExtra("image");
        String like = getIntent().getStringExtra("like");
        Boolean heart = getIntent().getBooleanExtra("heart", true);
        String description = getIntent().getStringExtra("description");
        String time = getIntent().getStringExtra("time");

        tvUsername = findViewById(R.id.tvUsername);
        tvDescription = findViewById(R.id.tvDescription);
        ivImg = findViewById(R.id.ivImg);
        ivHeart = findViewById(R.id.ivHeart);
        ivComment = findViewById(R.id.ivComment);
        ivSave = findViewById(R.id.ivSave);
        tvTime = findViewById(R.id.tvTime);
        tvTotalLikes = findViewById(R.id.tvTotalLikes);
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, PostsFragment.class);
//                startActivity(intent);
                finish();
            }
        });

        tvUsername.setText(user);
        Glide.with(this).load(image).into(ivImg);
        tvTotalLikes.setText(like);
        tvDescription.setText(description);
        tvTime.setText(time);
        if (heart == true){
            ivHeart.setImageResource(R.drawable.ufi_heart_active);
        }else {
            ivHeart.setImageResource(R.drawable.ufi_heart);
        }
    }
}
