package com.ak11.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("Re72KW3toJxwE7JFYxWQm4MLEXvzSdsmkkCrYhF0")
                // if defined
                .clientKey("ZY7JbBah4m1V5UwQxbk05lLJspz6kXGi4DANitBg")
                .server("https://parseapi.back4app.com/")
                .build());
    }
}
