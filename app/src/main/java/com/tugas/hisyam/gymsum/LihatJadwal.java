package com.tugas.hisyam.gymsum;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Selection;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tugas.hisyam.gymsum.model.Schedule;

import java.util.ArrayList;

public class LihatJadwal extends AppCompatActivity {

    private static final String TAG = "View Database";

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private String userID;

    private ListView mListView;

    //Spinner spinnerDays;
    Button buttonOK;
    TextView txDays;
    TextView txExercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_jadwal);

        mListView = (ListView)findViewById(R.id.ListView);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG,"onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    //toastMessage("Successfully signed out.");
                }
                // ...
            }
        };

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void showData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            Schedule scheduleInfo = new Schedule();
            scheduleInfo.setDaysName(ds.child(userID).getValue(Schedule.class).getDaysName());
            scheduleInfo.setExerciseName(ds.child(userID).getValue(Schedule.class).getExerciseName());

            //display all the information
            Log.d(TAG, "showData: DaysName: " + scheduleInfo.getDaysName());
            Log.d(TAG, "showData: phone_num: " + scheduleInfo.getExerciseName());

            ArrayList<String> array  = new ArrayList<>();
            array.add(scheduleInfo.getDaysName());
            array.add(scheduleInfo.getExerciseName());
            ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,array);
            mListView.setAdapter(adapter);
        }
    }


        /*txDays = (TextView)findViewById(R.id.txDays);
        txDays.setEnabled(false);
        txExercises = (TextView)findViewById(R.id.txExercise);
        txExercises.setEnabled(false);

        buttonOK = (Button)findViewById(R.id.buttonOK);

        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textExercises = "";

            }
        });
    }*/


    private void toastMassage(String message){
        Toast.makeText(this, message,Toast.LENGTH_SHORT).show();
    }

    /*public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.logout) {
            mAuth.signOut();
            Intent intent = new Intent(LihatJadwal.this, login.class);
            startActivity(intent);
            finish();
        }
        return true;
    }*/
}
