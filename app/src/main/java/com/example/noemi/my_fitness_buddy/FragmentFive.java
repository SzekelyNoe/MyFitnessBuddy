package com.example.noemi.my_fitness_buddy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FragmentFive extends Fragment {
    private static final String TAG = "FragmentFive";

    private Button btnSignUp;
    private EditText editTextEmail;
    private EditText editTextPass1;
    private EditText editTextPass2;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_five,container,false);

        btnSignUp = (Button)view.findViewById(R.id.buttonSignUp);
        editTextEmail = (EditText)view.findViewById(R.id.editTextREmail);
        editTextPass1 = (EditText)view.findViewById(R.id.editTextPassword1);
        editTextPass2 = (EditText)view.findViewById(R.id.editTextPassword2);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = editTextEmail.getText().toString().trim();
                String password = editTextPass1.getText().toString().trim();
                String pass = editTextPass2.getText().toString().trim();

                //Password must have at least 6 letters.


                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(password) && password.equals(pass)) {

                    RegisterActivity rA = (RegisterActivity) getActivity();
                    rA.fFiveData(email, password);
                }
                else if(!password.equals(pass)){
                    Toast.makeText(getActivity(), "Passwords are not the same...", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getActivity(), "Please, fill every field!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }


}
