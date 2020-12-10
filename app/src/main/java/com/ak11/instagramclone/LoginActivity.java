package com.ak11.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUsername, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Log In");
        edtUsername = findViewById(R.id.edtUsernameLogin);
        edtPassword = findViewById(R.id.edtPasswordLogin);
        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
                    onClick(findViewById(R.id.btnSignupLogin));
                }
                return false;
            }
        });
        if(ParseUser.getCurrentUser()!=null)
            transitionToSocialMediaAcitvity();

    }
    public void onClick( View v) {
        switch (v.getId()) {
            case (R.id.btnLoginLogin):
                ParseUser.logInInBackground(edtUsername.getText().toString(), edtPassword.getText().toString(),
                        new LogInCallback() {
                            @Override
                            public void done(ParseUser user, ParseException e) {
                                if (user != null && e == null) {
                                    FancyToast.makeText(LoginActivity.this, edtUsername.getText()
                                            + " is logged in successfully", FancyToast.DEFAULT, FancyToast.SUCCESS, false).show();
                                    transitionToSocialMediaAcitvity();
                                } else
                                    FancyToast.makeText(LoginActivity.this, e.getMessage(), FancyToast.DEFAULT, FancyToast.ERROR, false).show();

                            }
                        });
                break;
            case (R.id.btnSignupLogin):
                if(ParseUser.getCurrentUser()!=null) {
                    ParseUser.logOut();
                }
                finish();
                break;
            case (R.id.LoginLayout):
                try {
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }

        }
    }
    private void transitionToSocialMediaAcitvity() {

        Intent intent = new Intent(LoginActivity.this, SocialMediaActivity.class);
        startActivity(intent);
    }

}