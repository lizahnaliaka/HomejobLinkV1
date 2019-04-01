package com.polotechnologies.homejoblink;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Objects;

public class CreateAccountActivity extends AppCompatActivity {

    TextInputEditText jobCategory;
    TextInputEditText jobTittle;
    TextInputEditText jobQualification;
    TextInputEditText jobDescription;
    Button createAccountButton;
    
    String mJobCategory;
    String mJobTittle;
    String mJobQualification;
    String mJobDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        jobCategory = findViewById(R.id.jobCategory);
        jobTittle = findViewById(R.id.jobTittle);
        jobQualification = findViewById(R.id.jobQualification);
        jobDescription = findViewById(R.id.jobDescription);
        createAccountButton = findViewById(R.id.createAccountButton);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });

    }

    /**
     * Method to Create Account for the user
     * */
    private void createAccount() {
        sensitizeText();

    }

    private void sensitizeText() {

        if (Objects.equals(Objects.requireNonNull(jobCategory.getText()).toString(), "")){
            jobCategory.setError("Cannot be Empty");
            return;
        }
        if (Objects.equals(Objects.requireNonNull(jobTittle.getText()).toString(), "")){
            jobTittle.setError("Cannot be Empty");
            return;
        }
        if (Objects.equals(Objects.requireNonNull(jobQualification.getText()).toString(), "")){
            jobQualification.setError("Cannot be Empty");
            return;
        }
        if (Objects.equals(Objects.requireNonNull(jobDescription.getText()).toString(), "")){
            jobDescription.setError("Cannot be Empty");
            return;
        }
        if (Objects.equals(Objects.requireNonNull(jobCategory.getText()).toString(), "")){
            jobCategory.setError("Cannot be Empty");
            return;
        }

        mJobCategory = jobCategory.getText().toString().trim();
        mJobTittle = jobTittle.getText().toString().trim();
        mJobQualification = jobQualification.getText().toString().trim();
        mJobDescription = jobDescription.getText().toString().trim();



    }

}
