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

public class FragmentFour extends Fragment {
    private static final String TAG = "FragmentFour";

    private Button btnNextFragFour;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_four,container,false);

        btnNextFragFour=(Button)view.findViewById(R.id.buttonNextFour);

        String [] values =
                {"Flexibility training","Dynamic strength-training","Static strength-training","Aerobic training","Circuit training"};
        final Spinner spinner = (Spinner) view.findViewById(R.id.spinnerTrainer);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        btnNextFragFour.setOnClickListener(new View.OnClickListener() {
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
