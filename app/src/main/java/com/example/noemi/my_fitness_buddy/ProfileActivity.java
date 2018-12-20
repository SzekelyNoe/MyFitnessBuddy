package com.example.noemi.my_fitness_buddy;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    private Button buttonLogout;
    private Button buttonMatch;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    private ArrayList<String> mNames = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        buttonLogout = (Button)findViewById(R.id.buttonLogout);
        buttonMatch = (Button)findViewById(R.id.buttonMatch);

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
            }
        });

        buttonMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String trainingType = dataSnapshot.child("Trainees").child(firebaseAuth.getCurrentUser().getUid()).child("TrainingType").getValue().toString();
                       // Toast.makeText(ProfileActivity.this, trainingType, Toast.LENGTH_SHORT).show();

                        for (DataSnapshot snapshot : dataSnapshot.child("Trainers").getChildren()) {
                            String training = snapshot.child("TrainingType").getValue().toString();
                            String trainer = snapshot.child("Name").getValue().toString();
                            if(training.equals(trainingType)){
                                //Log.i("Inform",training);
                                //Display match...
                                // Toast.makeText(ProfileActivity.this,trainer, Toast.LENGTH_SHORT).show();
                                mNames.add(trainer);
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                initRecyclerView();

            }
        });
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mNames,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
