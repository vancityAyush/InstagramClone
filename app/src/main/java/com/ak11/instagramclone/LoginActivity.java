package com.ak11.instagramclone;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
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
        if(ParseUser.getCurrentUser()!=null)
            ParseUser.getCurrentUser().logOut();

    }
    public void OnClick( View v){
        switch(v.getId()){
            case(R.id.btnLoginActivityLogin):
                ParseUser.logInInBackground(edtUsername.getText().toString(), edtPassword.getText().toString(),
                        new LogInCallback() {
                            @Override
                            public void done(ParseUser user, ParseException e) {
                                if(user!=null && e==null)
                                    FancyToast.makeText(LoginActivity.this,edtUsername.getText()
                                            +" is logged in successfully",FancyToast.DEFAULT,FancyToast.SUCCESS,false).show();
                                else
                                    FancyToast.makeText(LoginActivity.this,e.getMessage(),FancyToast.DEFAULT,FancyToast.ERROR,false).show();

                            }
                        });
                break;
            case(R.id.btnLoginActivitySignUp):
                finish();
                break;
        }
    }


}