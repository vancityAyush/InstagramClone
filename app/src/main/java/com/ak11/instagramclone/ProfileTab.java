package com.ak11.instagramclone;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


public class ProfileTab extends Fragment {

    private EditText edtProfileName, edtProfileBio, edtProfileProfession,edtProfileHobbies;
    private Button btnUpdateInfo;

    public ProfileTab() {
        // Required empty public constructor
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_tab, container, false);
        edtProfileName = view.findViewById(R.id.edtProfileName);
        edtProfileBio = view.findViewById(R.id.edtProfileBio);
        edtProfileHobbies = view.findViewById(R.id.edtProfileHobbies);
        edtProfileProfession = view.findViewById(R.id.edtProfileProfession);


        btnUpdateInfo = view.findViewById(R.id.btnUpdateInfo);
        ParseUser parseUser = ParseUser.getCurrentUser();

        String profileName, profileBio,profileProfession, profileHobbies;
        profileName = (parseUser.get("profileName") == null?"":parseUser.get("profileName").toString());
        profileBio = (parseUser.get("profileBio")== null?"":parseUser.get("profileBio").toString());
        profileProfession = (parseUser.get("profileProfession")== null?"":parseUser.get("profileProfession").toString());
        profileHobbies = (parseUser.get("profileHobbies") == null?"":parseUser.get("profileHobbies").toString());

        edtProfileName.setText(profileName);
        edtProfileBio.setText(profileBio);
        edtProfileProfession.setText(profileProfession);
        edtProfileHobbies.setText(profileHobbies);

        btnUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                parseUser.put("profileName",edtProfileName.getText().toString());
                parseUser.put("profileBio",edtProfileBio.getText().toString());
                parseUser.put("profileProfession",edtProfileProfession.getText().toString());
                parseUser.put("profileHobbies",edtProfileHobbies.getText().toString());

                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null)
                            FancyToast.makeText(getContext(),"Info Updated" , FancyToast.DEFAULT, FancyToast.SUCCESS, false).show();
                        else
                            FancyToast.makeText(getContext(), e.getMessage(), FancyToast.DEFAULT,
                                    FancyToast.ERROR, false).show();
                    }
                });


            }
        });
        return view;
    }
}