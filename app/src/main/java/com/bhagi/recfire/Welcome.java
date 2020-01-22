package com.bhagi.recfire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Welcome extends AppCompatActivity {
    TextView t;

    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        t = findViewById(R.id.textView);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        mDatabase = FirebaseDatabase.getInstance().getReference().child("hospital").child(id);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String hname = dataSnapshot.child("h_name").getValue().toString();
                String haddress = dataSnapshot.child("h_address").getValue().toString();
                String hlocation = dataSnapshot.child("h_location").getValue().toString();
                String hnumber = dataSnapshot.child("h_number").getValue().toString();
                String hemail = dataSnapshot.child("h_email").getValue().toString();
                String hpgflag = dataSnapshot.child("h_pgflag").getValue().toString();
                String hwebsite = dataSnapshot.child("h_website").getValue().toString();
                String htime = dataSnapshot.child("h_time").getValue().toString();

                t.setText(hname+" "+ haddress + " "+ hlocation + " "+ hnumber + " "+ hemail + " "+ hpgflag + " "+ hwebsite + " "+htime);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }
    public void btndoctor(View view){
        Intent i = getIntent();
        Intent intent = new Intent(Welcome.this,Doctor.class);
        intent.putExtra("hid",i.getStringExtra("id"));
        startActivity(intent);
    }
}

