package com.bhagi.recfire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Doctor extends AppCompatActivity {
TextView t2;
DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        t2 = findViewById(R.id.textView2);
        Intent intent = getIntent();
        String id = intent.getStringExtra("hid");

        mDatabase = FirebaseDatabase.getInstance().getReference().child("hospital").child(id).child("doctor").child("d_id1");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String hname = dataSnapshot.child("d_name").getValue().toString();
                String hemail = dataSnapshot.child("d_email").getValue().toString();

                t2.setText(hemail + "    "+hname);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


    }
}
