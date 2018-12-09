package com.example.noemi.my_fitness_buddy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class FragmentOne extends Fragment {
    private static final String TAG = "FragmentOne";

    private Button btnNextFragOne;
    private EditText editTextName;
    private RadioGroup radioGroupSex;
    private RadioButton radioButtonSex;
    private EditText editTextDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_one,container,false);

        btnNextFragOne = (Button)view.findViewById(R.id.buttonNextOne);
        editTextName = (EditText)view.findViewById(R.id.editTextName);
        radioGroupSex = (RadioGroup)view.findViewById(R.id.radioGroup);
        editTextDate = (EditText)view.findViewById(R.id.editTextDate);


        btnNextFragOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editTextName.getText().toString().trim();
                String date = editTextDate.getText().toString().trim();
                String sex;

                int selectedId = radioGroupSex.getCheckedRadioButtonId();
                radioButtonSex = (RadioButton)view.findViewById(selectedId);
                sex = radioButtonSex.getText().toString().trim();

                if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(date)) {
                    //Sending data to Register Activity...
                    RegisterActivity rA = (RegisterActivity) getActivity();
                    rA.fOneData(name, sex, date);

                    //navigate to fragment...
                    ((RegisterActivity) getActivity()).setViewPager(1);
                }
                else{
                    Toast.makeText(getActivity(), "Please, complete every field!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }



}
