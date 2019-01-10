package com.example.noemi.my_fitness_buddy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity {

    private Button buttonMatch;
    private TextView logout;
    private TextView trainersList;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mUserId = new ArrayList<>();

    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        buttonMatch = (Button)findViewById(R.id.buttonMatch);
        logout = (TextView)findViewById(R.id.logout);
        trainersList = (TextView)findViewById(R.id.listOfTrainers);

        recyclerView = findViewById(R.id.recycler_view);

        trainersList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,ListingActivity.class);
                intent.putExtra("userType",1);//1 means trainee type of user.
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(ProfileActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        buttonMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String trainingType = dataSnapshot.child("Trainees").child(firebaseAuth.getCurrentUser().getUid()).child("TrainingType").getValue().toString();
                        //Toast.makeText(ProfileActivity.this, trainingType, Toast.LENGTH_SHORT).show();

                        for (DataSnapshot snapshot : dataSnapshot.child("Trainers").getChildren()) {
                            String training = snapshot.child("TrainingType").getValue().toString();
                            String trainer = snapshot.child("Name").getValue().toString();
                            if(training.equals(trainingType)){
                                //if it doesn't already exist in database:Matches...
                                mNames.add(trainer);
                                mUserId.add(snapshot.getKey());
                                saveMatch(firebaseAuth.getCurrentUser().getUid(),snapshot.getKey());
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                initRecyclerView(mNames,mUserId);

            }
        });
    }


    private void initRecyclerView(ArrayList<String> names,ArrayList<String> ids){
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(names,ids,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void saveMatch(String trainee, String trainer){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("trainee", trainee);
        hashMap.put("trainer", trainer);

        reference.child("Matches").push().setValue(hashMap);

    }

}
