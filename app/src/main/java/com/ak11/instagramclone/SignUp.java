    package com.ak11.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

    public class SignUp extends AppCompatActivity implements  View.OnClickListener {
        private EditText edtEmail, edtUsername, edtPassword;
        private Button btnSignUp, btnLogin;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sign_up);
            setTitle("Sign Up");
            edtEmail = findViewById(R.id.edtEmail);
            edtUsername = findViewById(R.id.edtUsernameLogin);
            edtPassword = findViewById(R.id.edtPasswordLogin);
            btnSignUp = findViewById(R.id.btnSignUp);
            btnLogin = findViewById(R.id.btnLogin);
            btnSignUp.setOnClickListener(this);
            btnLogin.setOnClickListener(this);
            edtPassword.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                        onClick(btnSignUp);
                    }
                    return false;
                }
            });

            if (ParseUser.getCurrentUser() != null)
                transitionToSocialMediaAcitvity();
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case (R.id.btnSignUp):
                    if (edtUsername.getText().toString().isEmpty() || edtEmail.getText().toString().isEmpty() || edtPassword.getText().toString().isEmpty()) {
                        FancyToast.makeText(SignUp.this, "Email, Username, Password required", FancyToast.DEFAULT, FancyToast.ERROR, false).show();
                    } else {
                        final ParseUser appUser = new ParseUser();
                        appUser.setEmail(edtEmail.getText().toString());
                        appUser.setUsername(edtUsername.getText().toString());
                        appUser.setPassword(edtPassword.getText().toString());

                        final ProgressDialog progressDialog = new ProgressDialog(this);
                        progressDialog.setMessage("Signing Up " + edtUsername.getText());
                        progressDialog.show();
                        appUser.signUpInBackground(new SignUpCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    FancyToast.makeText(SignUp.this, edtUsername.getText()
                                            + " is signed up successfully", FancyToast.DEFAULT, FancyToast.SUCCESS, false).show();
                                    transitionToSocialMediaAcitvity();
                                }
                                else
                                    FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.DEFAULT,
                                            FancyToast.ERROR, false).show();
                                progressDialog.dismiss();
                            }
                        });
                    }
                    break;
                case (R.id.btnLogin):
                    Intent intent = new Intent(SignUp.this, LoginActivity.class);
                    if(ParseUser.getCurrentUser()!=null) {
                        ParseUser.logOut();
                    }
                    startActivity(intent);
                    break;

            }
        }
        public void rootLayoutTapped(View v){
            try {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }

        private void transitionToSocialMediaAcitvity(){

            Intent intent =  new Intent(SignUp.this, SocialMediaActivity.class);
            startActivity(intent);

        }
    }