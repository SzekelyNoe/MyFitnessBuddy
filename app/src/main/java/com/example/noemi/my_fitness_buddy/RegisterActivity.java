package com.example.noemi.my_fitness_buddy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private DatabaseReference databaseReferenceOne;
    private DatabaseReference databaseReferenceTwo;
    private FirebaseAuth firebaseAuth;

    Map users = new HashMap();


    private SectionStatePagerAdapter mSectionStatePagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mSectionStatePagerAdapter = new SectionStatePagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager)findViewById(R.id.container);

        //setup the pager...
        setupViewPager(mViewPager);

        databaseReferenceOne = FirebaseDatabase.getInstance().getReference().child("Trainees");
        databaseReferenceTwo = FirebaseDatabase.getInstance().getReference().child("Trainers");

        firebaseAuth = FirebaseAuth.getInstance();

    }

    private void register(Map users){

        //Log.i("Inform",users.toString());

        final String email = users.get("Email").toString();
        final String password = users.get("Password").toString();
        final String name = users.get("Name").toString();
        final String gender = users.get("Gender").toString();
        final String date = users.get("Date").toString();
        final String user = users.get("User").toString();
        final String training = users.get("Training").toString();



        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

//                    Log.i("informations:",email);
//                    Log.i("informations",password);
//                    Log.i("informations",name);
//                    Log.i("informations",gender);
//                    Log.i("informations",date);
//                    Log.i("informations",user);
//                    Log.i("informations",training);

                    String userId = firebaseAuth.getCurrentUser().getUid();
                    DatabaseReference currentUserReference = databaseReferenceOne.child(userId);
                    if(user.equals("Trainer")) {
                        currentUserReference = databaseReferenceTwo.child(userId);
                    }

                    currentUserReference.child("Name").setValue(name);
                    currentUserReference.child("Gender").setValue(gender);
                    currentUserReference.child("DateOfBirth").setValue(date);
                    currentUserReference.child("UserType").setValue(user);
                    currentUserReference.child("TrainingType").setValue(training);

                    Intent mainIntent = new Intent(RegisterActivity.this,MainActivity.class);
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(mainIntent);
                }
            }
        });

    }

    private void setupViewPager(ViewPager viewPager){
        SectionStatePagerAdapter adapter = new SectionStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentOne(),"FragmentOne");
        adapter.addFragment(new FragmentTwo(),"FragmentTwo");
        adapter.addFragment(new FragmentThree(),"FragmentThree");
        adapter.addFragment(new FragmentFour(),"FragmentFour");
        adapter.addFragment(new FragmentFive(),"FragmentFive");
        viewPager.setAdapter(adapter);
    }

    public void setViewPager(int fragmentNumber){
        mViewPager.setCurrentItem(fragmentNumber);
    }


    public void fOneData(String name,String sex,String date){

        users.put("Name",name);
        users.put("Gender",sex);
        users.put("Date",date);

        // Log.i("Inform:",users.toString());
    }
    public void fTwoData(String userType){

        users.put("User",userType);
        // Log.i("Inform:",users.toString());
    }

    public void fThreeFourData(String trainingType){

        users.put("Training",trainingType);
        //  Log.i("Inform:",users.toString());

    }

    public void fFiveData(String email,String password){

        users.put("Email",email);
        users.put("Password",password);
        // Log.i("Inform:",users.toString());

        register(users);

    }



}
