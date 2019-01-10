package com.example.noemi.my_fitness_buddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class ProfileTrainerActivity extends AppCompatActivity {

    private TextView traineesList;
    private TextView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_trainer);

        traineesList = findViewById(R.id.listOfTrainees);
        logout = findViewById(R.id.logout);

        traineesList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileTrainerActivity.this,ListingActivity.class);
                intent.putExtra("userType",2);//2 means trainee type of user.
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(ProfileTrainerActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
