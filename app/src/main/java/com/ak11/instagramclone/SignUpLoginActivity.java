package com.ak11.instagramclone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLoginActivity extends AppCompatActivity  implements View.OnClickListener {
    private EditText edtUserNameSignUp, edtPasswordSignUp, edtUserNameLogin, edtPasswordLogin;
    private Button btnSignUP, btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);

        edtUserNameSignUp = findViewById(R.id.edtUserNameSingUp);
        edtPasswordSignUp = findViewById(R.id.edtPasswordSignUp);
        edtUserNameLogin = findViewById(R.id.edtUserNameLogin);
        edtPasswordLogin = findViewById(R.id.edtPasswordLogin);
        btnSignUP = findViewById(R.id.btnSignUp);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUP.setOnClickListener(this);
        btnLogin.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case(R.id.btnSignUp):
                ParseUser appUser = new ParseUser();
                appUser.setUsername(edtUserNameSignUp.getText().toString());
                appUser.setPassword(edtPasswordSignUp.getText().toString());
                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null)
                            FancyToast.makeText(SignUpLoginActivity.this,appUser.get("username")+" is Singed up successfully",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
                        else
                            FancyToast.makeText(SignUpLoginActivity.this,e.getMessage(),FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
                    }
                });



                break;
            case(R.id.btnLogin):
                ParseUser.logInInBackground(edtUserNameLogin.getText().toString(), edtPasswordLogin.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user!=null && e==null)
                            FancyToast.makeText(SignUpLoginActivity.this,user.get("username")+" is Logged in successfully",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
                        else
                            FancyToast.makeText(SignUpLoginActivity.this,e.getMessage(),FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();


                    }
                });




                break;
        }

    }
}
