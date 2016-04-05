package com.example.the_crab.dataexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //View inflation is a little convoluted in this example as it uses the Android Studio
        //template. Activity_main calls content_main which launches MainActivityFragment which
        //finally inflates fragment_main! This way of doing things is useful to keep the app
        //modular and helps implement stuff like 2 panel tablet layouts.
        setContentView(R.layout.activity_main);

    }



}
