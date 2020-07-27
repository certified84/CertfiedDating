package com.certified.certfieddating;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.certified.certfieddating.util.PreferenceKeys;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started");


        setContentView(R.layout.fragment_test);

        Button btnFirstFragment, btnSecondFragment;
        btnFirstFragment = findViewById(R.id.btn_first_fragment);
        btnSecondFragment = findViewById(R.id.btn_second_fragment);
        btnFirstFragment.setOnClickListener(this);
        btnSecondFragment.setOnClickListener(this);

//        isFirstLogin();
//        init();
    }

    private void init() {
        Log.d(TAG, "init: initializing home fragment");

        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_content_frame, homeFragment, getString(R.string.tag_fragment_home));
        transaction.addToBackStack(getString(R.string.tag_fragment_home));
        transaction.commit();
    }


    public void isFirstLogin() {
        Log.d(TAG, "isFirstLogin: checking if this is the first login");
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirstLogin = preferences.getBoolean(PreferenceKeys.FIRST_TIME_LOGIN, true);

        if(isFirstLogin) {
            Log.d(TAG, "isFirstLogin: Launching alert dialog");
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage(getString(R.string.first_time_user_message));
            alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Log.d(TAG, "onClick: closing AlertDialog");
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean(PreferenceKeys.FIRST_TIME_LOGIN, false);
                    editor.apply();
                    dialog.dismiss();
                }
            });
            alertDialogBuilder.setIcon(R.drawable.heart_on);
            alertDialogBuilder.setTitle(" ");
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_first_fragment:
                firstFragment();
                break;

            case R.id.btn_second_fragment:
                secondFragment();
                break;

            default:
                break;
        }
    }

    private void secondFragment() {
        SecondFragment secondFragment = new SecondFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, secondFragment, "Second Fragment");
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void firstFragment() {
        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, homeFragment, "First Fragment");
        transaction.addToBackStack(null);
        transaction.commit();
    }


}