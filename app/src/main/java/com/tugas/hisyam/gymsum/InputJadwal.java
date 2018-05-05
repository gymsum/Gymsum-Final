package com.tugas.hisyam.gymsum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tugas.hisyam.gymsum.model.Schedule;

import java.util.ArrayList;

public class InputJadwal extends AppCompatActivity {

    ArrayList<String> selection = new ArrayList<String>();
    private FirebaseAuth mAuth;

    private ListView mDaysList;
    Spinner spinnerDays;
    Button buttonInput;

    DatabaseReference databaseExercise;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_jadwal);

        databaseExercise = FirebaseDatabase.getInstance().getReference("Schedule");

        spinnerDays = (Spinner)findViewById(R.id.spinner);
        buttonInput = (Button)findViewById(R.id.buttonInput);


        buttonInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addExercise();
            }
        });
    }

    public void selectItem(View view){

        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {
            case R.id.AbRoller:
                if(checked){
                    selection.add("Ab Roller");
                }else{
                    selection.remove("Ab Roller");
                }
                break;

            case R.id.Butterfly:
                if(checked){
                    selection.add("Butterfly");
                }else{
                    selection.remove("Butterfly");
                }
                break;

            case R.id.Cocoons:
                if(checked){
                    selection.add("Cocoons");
                }else{
                    selection.remove("Cocoons");
                }
                break;

            case R.id.DeclineCrunch:
                if(checked){
                    selection.add("Decline Crunch");
                }else{
                    selection.remove("Decline Crunch");
                }
                break;

            case R.id.FrontSquad:
                if(checked){
                    selection.add("Front Squad");
                }else{
                    selection.remove("Front Squad");
                }
                break;

            case R.id.PushUp:
                if(checked){
                    selection.add("Push Up");
                }else{
                    selection.remove("Push Up");
                }
                break;
        }
    }


    private void addExercise(){

        final String userId = mAuth.getUid();
        String days = spinnerDays.getSelectedItem().toString();


        for(String Selections : selection) {

            String id = databaseExercise.push().getKey();
            Schedule schedule = new Schedule(userId, id, days, Selections);
            databaseExercise.child(id).setValue(schedule);
        }

        Intent intent = new Intent(InputJadwal.this, LihatJadwal.class);
        startActivity(intent);
        Toast.makeText(this, "Exercise Added", Toast.LENGTH_LONG).show();

    }

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.logout) {
            mAuth.signOut();
            Intent intent = new Intent(InputJadwal.this, login.class);
            startActivity(intent);
            finish();
        }
        return true;
    }
}