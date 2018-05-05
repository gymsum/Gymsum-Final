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

public class DaftarInfo extends AppCompatActivity {
    RecyclerView recv;
    adapterinfo adapter;
    List<infolist> listmenu;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_info);

        listmenu = new ArrayList<>(); //deklarasi isi array list
        recv = findViewById(R.id.rvmenu); //deklarasi recyclerview
        recv.setHasFixedSize(true);
        //jika tampilan berubah menjadi landscape
        if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){
            recv.setLayoutManager(new GridLayoutManager(DaftarInfo.this, 2));
        }else {
            recv.setLayoutManager(new GridLayoutManager(DaftarInfo.this, 1));
        }

        tampil(); //menjalakan method tampil


    }

    public void tampil(){
        // isi dari arraylist
        listmenu.add(new infolist("3q3RRfMMkL0", "Benefit Of Exercise", "Exercise has huge benefits to people even if they’re overweight or obese. WSJ's Rachel Bachman discusses."));
        listmenu.add(new infolist("F6ehyV7kqv8", "How to Cook Healthy Food", "10 Healthy Breakfast ideas and Lunch Ideas on how to cook that healthy food! In this quick and easy how to healthy breakfast ideas and healthy lunch ideas tutorial I show what I eat in a day for breakfast, snacks, and lunch. I show 5 healthy breakfast ideas, which you can also have for a healthy snack and 5 healthy lunch ideas, all perfect for back to school or work. In this healthy recipes video you learn how to cook healthy meals and snacks at home. From how to bake a bread at home to ice cream yogurt with fruit in a cone, everyone can cook these meals - perfect for teenagers, adults and healthy snacks for kids. Healthy eating is very important for your happy and healthy lifestyle. I show you what to eat to achieve that! This how to cook healthy food video is not about how to lose weight, but about healthy lifestyle."));
        listmenu.add(new infolist("Ucbfl72U-pQ", "5 Tips to Make Cardio More Fun", "Are you looking for new ways to put some jive into your cardio routine? Skip the monotony and daily boredom and add some spice and variety to your workouts. Professional Fitness Model Nicole Costa shares her 5 awesome tips on making your cardio sessions fun, enjoyable and exciting! \n" +
                "\n" +
                "Tip 1: Break Up The Routine\n" +
                "Tip 2: Bring That iPod\n" +
                "Tip 3: Do Your Cardio With A Partner\n" +
                "Tip 4: Include Interval Training\n" +
                "Tip 5: Try A High Intensity Dance Class\n"));



        //deklarasi isi adapter
        adapter = new adapterinfo(this,listmenu);
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
            Intent intent = new Intent(DaftarInfo.this, login.class);
            startActivity(intent);
            finish();
        }
        return true;
    }


}
