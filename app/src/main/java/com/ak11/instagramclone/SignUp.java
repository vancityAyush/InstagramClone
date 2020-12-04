package com.ak11.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity {

    private Button btn;
    private EditText edtName, edtPower, edtSpeed, edtType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btn=findViewById(R.id.button);
        edtName=findViewById(R.id.edtName);
        edtType=findViewById(R.id.edtType);
        edtPower=findViewById(R.id.edtPower);
        edtSpeed=findViewById(R.id.edtSpeed);
    }
    public void btnIsTapped(View v){
        ParseObject car = new ParseObject("Car");
        car.put("car_name",edtName.getText().toString());
        if(Integer.parseInt(edtSpeed.getText().toString())==0)
            car.put("car_speed",edtSpeed.getText().toString());
        else
            car.put("car_speed",Integer.parseInt(edtSpeed.getText().toString()));
        car.put("car_power",Integer.parseInt(edtPower.getText().toString()));
        car.put("car_type",edtType.getText().toString());
        car.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    FancyToast.makeText(SignUp.this,car.get("car_name")+" is saved successfully",
                            FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true)
                            .show();
                }
                else
                    FancyToast.makeText(SignUp.this,e.getMessage()+"\nError Code"+e.getCode(),
                            FancyToast.LENGTH_LONG,FancyToast.ERROR,true)
                            .show();
            }
        });
    }
}