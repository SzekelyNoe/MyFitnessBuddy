package com.example.noemi.my_fitness_buddy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class FragmentThree extends Fragment {
    private static final String TAG = "FragmentThree";

    private Button btnNextFragThree;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three,container,false);

        btnNextFragThree=(Button)view.findViewById(R.id.buttonNextThree);
        //       fitnessTypesList.add("Flexibility Training");
//        fitnessTypesList.add("Dynamic Strength-training");
//        fitnessTypesList.add("Static Strength-training");
//        fitnessTypesList.add("Aerobic Training");
//        fitnessTypesList.add("Circuit Training");

        String [] values =
                {"Flexibility training","Dynamic strength-training","Static strength-training","Aerobic training","Circuit training"};
        final Spinner spinner = (Spinner) view.findViewById(R.id.spinnerTrainee);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        btnNextFragThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String trainingType = spinner.getSelectedItem().toString().trim();

                RegisterActivity rA = (RegisterActivity) getActivity();
                rA.fThreeFourData(trainingType);

                ((RegisterActivity) getActivity()).setViewPager(4);
            }
        });

        return view;
    }
}
