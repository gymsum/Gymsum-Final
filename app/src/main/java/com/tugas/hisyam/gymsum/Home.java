package com.tugas.hisyam.gymsum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void bStart(View view) {
        startActivity(new Intent(Home.this, ListOtot.class));
    }

    public void bProfil(View view) {
        startActivity(new Intent(Home.this, Profil.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //method yang dijalankan ketika item di pilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.logout) {
            mAuth.signOut();
            Intent intent = new Intent(Home.this, login.class);
            startActivity(intent);
            finish();
        }
        return true;
    }

    public void bInfoLain(View view) {
        startActivity(new Intent(Home.this, DaftarInfo.class));
    }
}
