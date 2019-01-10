package com.example.noemi.my_fitness_buddy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.xml.sax.helpers.LocatorImpl;

public class MainActivity extends AppCompatActivity {
//
//    private DatabaseReference databaseReference;
//
//    private FirebaseAuth firebaseAuth;
//    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        firebaseAuth = FirebaseAuth.getInstance();
//
//        databaseReference = FirebaseDatabase.getInstance().getReference();
//
//        //firebaseAuth.signOut();
//
//        authStateListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull final FirebaseAuth firebaseAuth) {
//                if(firebaseAuth.getCurrentUser() == null){
//                    Intent loginIntent = new Intent(MainActivity.this,LoginActivity.class);
//                    //user won`t be able to go back...
//                   // loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(loginIntent);
//                }
//                else{
//                    Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
//                    startActivity(intent);
//
////                    databaseReference.addValueEventListener(new ValueEventListener() {
////                        @Override
////                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////                            if(dataSnapshot.child("Trainer").child(firebaseAuth.getCurrentUser().getUid()).exists()){
////                                Intent intent = new Intent(MainActivity.this,ProfileTrainerActivity.class);
////                                startActivity(intent);
////                            }
////                            else if(dataSnapshot.child("Trainee").child(firebaseAuth.getCurrentUser().getUid()).exists())
////                            {
////                                Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
////                                startActivity(intent);
////                            }
////                        }
////
////                        @Override
////                        public void onCancelled(@NonNull DatabaseError databaseError) {
////
////                        }
////                    });
//                }
//            }
//        };


        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);

    }


}
