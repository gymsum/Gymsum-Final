package com.tugas.hisyam.gymsum;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Registrasi Akun");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {


            }
        };
    }
    @Override
    public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop(){
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);
    }


    public void bDaftar(View view) {
        EditText email = (EditText) findViewById(R.id.tMail);
        EditText password = (EditText) findViewById(R.id.tPass);
        String mail = email.getText().toString();
        String pass = password.getText().toString();

        mAuth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(register.this,"Account Created Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(register.this, MainActivity.class));
                }
            else {
                Toast.makeText(register.this,"There is an error ", Toast.LENGTH_SHORT).show();
            }
        }});


    }
}
