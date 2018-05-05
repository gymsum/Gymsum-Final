package com.tugas.hisyam.gymsum;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class DaftarMenu extends AppCompatActivity {
    RecyclerView recv;
    adapter adapter;
    List<menulist> listmenu;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_menu);

        listmenu = new ArrayList<>(); //deklarasi isi array list
        recv = findViewById(R.id.rvmenu); //deklarasi recyclerview
        recv.setHasFixedSize(true);
        //jika tampilan berubah menjadi landscape
        if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){
            recv.setLayoutManager(new GridLayoutManager(DaftarMenu.this, 2));
        }else {
            recv.setLayoutManager(new GridLayoutManager(DaftarMenu.this, 1));
        }


        if (getIntent().getStringExtra("nama").equals("Abs")) {
            tampil(); //menjalakan method tampil
        } else {
            tampilBiceps();
        }


    }

    public void tampil() {
        // isi dari arraylist

            listmenu.add(new menulist(R.drawable.abroller, "Abs Roller", "tAkzpXPPwzE"));
            listmenu.add(new menulist(R.drawable.declinecrunch, "Decline Crunch", "QM9iwYWUouo"));
            listmenu.add(new menulist(R.drawable.elbowtoknee, "Elbow To Knee", "rpQN6aHU-iM"));



            //deklarasi isi adapter
            adapter = new adapter(this, listmenu);
            //menghubungkan adapter dengan recyleview
            recv.setAdapter(adapter);

    }

    public void tampilBiceps(){
        listmenu.add(new menulist(R.drawable.concentrationcurls, "Concentration Curl", "0AUGkch3tzc"));
        listmenu.add(new menulist(R.drawable.spidercurl, "Spider Curl", "-4RNtxT0LwM"));
        listmenu.add(new menulist(R.drawable.overheadcablecurl, "Overhead Cable Curl", "5_n3gVeGEqc"));
        //deklarasi isi adapter
        adapter = new adapter(this, listmenu);
        //menghubungkan adapter dengan recyleview
        recv.setAdapter(adapter);


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
            Intent intent = new Intent(DaftarMenu.this, login.class);
            startActivity(intent);
            finish();
        }
        return true;
    }


}
