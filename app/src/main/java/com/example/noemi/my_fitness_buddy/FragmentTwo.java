package com.example.noemi.my_fitness_buddy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class FragmentTwo extends Fragment {
    private static final String TAG = "FragmentTwo";

    private Button btnNextFragTwo;
    private RadioGroup btnRadioGroup;
    private RadioButton btnRadioButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_two,container,false);

        btnNextFragTwo = (Button)view.findViewById(R.id.buttonNextTwo);
        btnRadioGroup = (RadioGroup)view.findViewById(R.id.radioGroup2);

        btnNextFragTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = btnRadioGroup.getCheckedRadioButtonId();
                btnRadioButton = (RadioButton)view.findViewById(selectedId);
                String selected = btnRadioButton.getText().toString().trim();

                //sending it to the RegisterActivity..
                RegisterActivity rA = (RegisterActivity)getActivity();
                rA.fTwoData(selected);
                if (selected.equals("Trainee")) {
                    ((RegisterActivity) getActivity()).setViewPager(2);
                }
                else if (selected.equals("Trainer")) {
                    ((RegisterActivity) getActivity()).setViewPager(3);
                }

            }
        });

        return view;
    }
}
