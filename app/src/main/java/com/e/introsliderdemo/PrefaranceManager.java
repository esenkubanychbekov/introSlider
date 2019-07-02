package com.e.introsliderdemo;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefaranceManager {

    private Context context;
    private SharedPreferences sharedPreferences;


    public PrefaranceManager(Context context) {
        this.context = context;
        getSharedPreference();
    }

    private void getSharedPreference() {
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.my_preferance),
                Context.MODE_PRIVATE);
    }

    public void writePreference() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.my_preferance_key), "INIT_OK");
        editor.commit();
    }

    public boolean checkPreferance() {
        boolean status = false;
        if (sharedPreferences.getString(context.getString(R.string.my_preferance_key), "null").
                equals("null")) {
            status = false;
        } else {
            status = true;
        }
        return status;
    }

    public void clearPreferance() {
        sharedPreferences.edit().clear().commit();
    }
}


