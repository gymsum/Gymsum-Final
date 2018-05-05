package com.tugas.hisyam.gymsum;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.tugas.hisyam.gymsum.model.mProfil;

public class Profil extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText mNama, mTinggi,mBerat,mUsia;
    DatabaseReference databaseProfil;
    ImageView imageView;
    Button mChooseImage;
    private Uri imageUri;
    private static final int PICK_IMAGE = 1;

    private StorageReference mStorage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        mNama = findViewById(R.id.tNama);
        mTinggi = findViewById(R.id.tTinggi);
        mBerat = findViewById(R.id.tBerat);
        mUsia = findViewById(R.id.tUsia);
        databaseProfil = FirebaseDatabase.getInstance().getReference(MainActivity.tb_profil);
        mStorage = FirebaseStorage.getInstance().getReference().child("images");

        imageView = findViewById(R.id.img_post);

        mChooseImage = findViewById(R.id.btn_choose_image);
        mChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });


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
            Intent intent = new Intent(Profil.this, login.class);
            startActivity(intent);
            finish();
        }
        return true;
    }

    public void bUpdate(View view) {
        databaseProfil.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final String nama = mNama.getText().toString();
                final int berat = Integer.parseInt(mBerat.getText().toString());
                final int usia = Integer.parseInt(mUsia.getText().toString());
                final int tinggi = Integer.parseInt( mTinggi.getText().toString());
                final String userId = mAuth.getUid();

                if (imageUri != null && !TextUtils.isEmpty(nama))
                {
                    final StorageReference image = mStorage.child(userId + ".jpg");
                    image.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> uploadTask) {

                            if (uploadTask.isSuccessful()) {

                                String download_url = uploadTask.getResult().getDownloadUrl().toString();
                                mProfil post = new mProfil(userId, nama, berat, tinggi, usia, download_url);
                                databaseProfil.child(userId).setValue(post);

                            } else {
                                Toast.makeText(Profil.this, "Error : " + uploadTask.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });





                    Toast.makeText(Profil.this, "Profil Tersimpan", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(Profil.this, "Lengkapi Data", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }
}
