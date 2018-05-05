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

public class ListOtot extends AppCompatActivity {
    RecyclerView recv;
    adapterkedua adapter;
    List<menulist2> listmenu;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_otot);

        listmenu = new ArrayList<>(); //deklarasi isi array list
        recv = findViewById(R.id.rvmenu); //deklarasi recyclerview
        recv.setHasFixedSize(true);
        //jika tampilan berubah menjadi landscape
        if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){
            recv.setLayoutManager(new GridLayoutManager(ListOtot.this, 2));
        }else {
            recv.setLayoutManager(new GridLayoutManager(ListOtot.this, 1));
        }

        tampil(); //menjalakan method tampil


    }

    public void tampil(){
        // isi dari arraylist
        listmenu.add(new menulist2(R.drawable.abs, "Abs", "List Latihan untuk melatih Abs"));
        listmenu.add(new menulist2(R.drawable.bycept, "Biceps", "Ini Adalah List latihan untuk Biceps"));

        //deklarasi isi adapter
        adapter = new adapterkedua(this,listmenu);
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
            Intent intent = new Intent(ListOtot.this, login.class);
            startActivity(intent);
            finish();
        }
        return true;
    }


}
