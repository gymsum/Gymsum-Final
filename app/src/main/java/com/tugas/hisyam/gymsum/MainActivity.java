package com.tugas.hisyam.gymsum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final String tb_profil = "Profil";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // startActivity(new Intent(MainActivity.this, register.class));
    }

    public void bRegister(View view) {
        startActivity(new Intent(MainActivity.this, register.class));
    }

    public void bLogin(View view) {
        startActivity(new Intent(MainActivity.this, login.class));
    }
}
