package com.example.instragramclone.fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.instragramclone.Post;
import com.example.instragramclone.R;
import com.parse.ParseUser;

public class EditActivity extends AppCompatActivity {

    private EditText etDescription;
    private ImageView imageView;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        etDescription = findViewById(R.id.etDescription);
        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.btnSubmit);

        etDescription.setText(ParseUser.getCurrentUser().getUsername());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser user = new ParseUser();
                user.setUsername(etDescription.getText().toString());

            }
        });



    }
}