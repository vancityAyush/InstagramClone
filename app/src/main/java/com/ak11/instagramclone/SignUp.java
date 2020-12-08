    package com.ak11.instagramclone;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

    public class SignUp extends AppCompatActivity {
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
        btnSignUp = findViewById(R.id.btnLoginActivityLogin);
        btnLogin = findViewById(R.id.btnLoginActivitySignUp);

        if(ParseUser.getCurrentUser()!=null)
            ParseUser.getCurrentUser().logOut();




    }
    public void btnIsTapped(View v){
       switch(v.getId()){
           case(R.id.btnLoginActivityLogin):
               ParseUser appUser = new ParseUser();
               appUser.setEmail(edtEmail.getText().toString());
               appUser.setUsername(edtUsername.getText().toString());
               appUser.setPassword(edtPassword.getText().toString());

               ProgressDialog progressDialog = new ProgressDialog(this);
               progressDialog.setMessage("Signing Up "+edtUsername.getText());
               progressDialog.show();
               appUser.signUpInBackground(new SignUpCallback() {
                   @Override
                   public void done(ParseException e) {
                       if(e==null)
                           FancyToast.makeText(SignUp.this,edtUsername.getText()
                                   +" is signed up successfully",FancyToast.DEFAULT,FancyToast.SUCCESS,false).show();
                       else
                           FancyToast.makeText(SignUp.this,e.getMessage(),FancyToast.DEFAULT,FancyToast.ERROR,false).show();
                   progressDialog.dismiss();
                   }
               });
               break;
           case(R.id.btnLoginActivitySignUp):
               Intent intent = new Intent(SignUp.this,LoginActivity.class);
               startActivity(intent);
               break;
       }
    }
}