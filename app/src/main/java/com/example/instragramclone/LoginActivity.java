package com.example.instragramclone;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.instragramclone.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity extends AppCompatActivity {
    public static final String TAG="LoginActivity";

    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if ( ParseUser.getCurrentUser() != null){
            goMainActivity();

        }

        etUsername=findViewById(R.id.etUsername);
        etPassword=findViewById(R.id.etPassword);
        btnLogin=findViewById(R.id.btnLogin);
        btnSignUp=findViewById(R.id.btnSignUp);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Login button");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                loginUser(username, password);
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the ParseUser
                ParseUser user = new ParseUser();
                // Set core properties
                user.setUsername(etUsername.getText().toString());
                user.setPassword(etPassword.getText().toString());
                // Invoke signUpInBackground
                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            Log.i(TAG, "Sign up successfull");
                            goMainActivity();
                            Toast.makeText(LoginActivity.this, "Sign up successfull", Toast.LENGTH_SHORT).show();
                            return;

                        }
                    }
                });
            }
        });

    }
            private void loginUser(String username, String password) {
                Log.i(TAG, "attempting to login user" + username);
                ParseUser.logInInBackground(username,password, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (e != null){
                            Log.e(TAG, "issue with login", e);
                            Toast.makeText(LoginActivity.this, "issue with login", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        //TODO go to main Activity if the user has logined in succesfully
                        goMainActivity();
                        Toast.makeText(LoginActivity.this, "succesfull login", Toast.LENGTH_SHORT).show();

                    }
                });
            }

    private void goMainActivity() {
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                finish();

    }


}
