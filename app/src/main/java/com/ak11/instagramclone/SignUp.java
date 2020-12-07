    package com.ak11.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity {

    private Button btn, btnTransition;
    private EditText edtName, edtPower, edtSpeed, edtType;
    private TextView txtGetData;
    private String strcar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btn=findViewById(R.id.button);
        btnTransition = findViewById(R.id.btnNextActivity);
        edtName=findViewById(R.id.edtName);
        edtType=findViewById(R.id.edtType);
        edtPower=findViewById(R.id.edtPower);
        edtSpeed=findViewById(R.id.edtSpeed);

        strcar="";

        txtGetData = findViewById(R.id.textGetData);
        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SignUp.this,"Getting data...",Toast.LENGTH_SHORT).show();
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("Car");
                parseQuery.whereGreaterThan("car_speed",200);
                parseQuery.findInBackground(new FindCallback<ParseObject>(){

                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {

                        if(e==null)
                            if(objects.size()>0){

                                for(ParseObject obj:objects){
                                    strcar = strcar+obj.get("car_name")+"\n";
                                }
                                txtGetData.setText(strcar);
                                FancyToast.makeText(SignUp.this,"Data retrieved successfully",
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
        });

        btnTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this,
                        SignUpLoginActivity.class);
                startActivity(intent);

            }
        });

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