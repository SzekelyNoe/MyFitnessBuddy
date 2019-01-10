package com.example.noemi.my_fitness_buddy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListingActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;

    private ArrayList<String> matches = new ArrayList<>();
    private ArrayList<String> Ids = new ArrayList<>();

    RecyclerView recyclerView;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        recyclerView = findViewById(R.id.listing_recycler_view);

        intent = getIntent();
        final int userType = intent.getIntExtra("userType",0);

        matches.clear();
        Ids.clear();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.child("Matches").getChildren()){
                    if(userType == 1) {
                        String trainerId = snapshot.child("trainer").getValue().toString();
                        String match = dataSnapshot.child("Trainers").child(trainerId).child("Name").getValue().toString();
                        matches.add(match);
                        Ids.add(trainerId);
                    }
                    else {
                        String traineeId = snapshot.child("trainee").getValue().toString();
                        String match = dataSnapshot.child("Trainees").child(traineeId).child("Name").getValue().toString();
//                        for(String i : Ids){
//                            if(!i.equals(traineeId)&&!traineeId.equals("")){
//                                matches.add(match);
//                                Ids.add(traineeId);
//                            }
//                        }
                        matches.add(match);
                        Ids.add(traineeId);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        initRecyclerView(matches,Ids);
    }

    private void initRecyclerView(ArrayList<String> names,ArrayList<String> ids){
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(names,ids,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
