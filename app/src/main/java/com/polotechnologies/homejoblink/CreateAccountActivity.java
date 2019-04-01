package com.polotechnologies.homejoblink;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.polotechnologies.homejoblink.dataModels.UserRegistration;
import com.polotechnologies.homejoblink.ui.MainActivity;

import java.io.ObjectInput;
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
    String mUserID;

    UserRegistration newUserRegistration;

    //Database Reference
    DatabaseReference mDatabaseReference;

    //Firabase Authentication
    FirebaseAuth mAuth;

    ProgressBar loadingProgress;


    /*Boolean for checking if Edit Text for user input are empty*/
    Boolean mCheckEditText  = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        jobCategory = findViewById(R.id.jobCategory);
        jobTittle = findViewById(R.id.jobTittle);
        jobQualification = findViewById(R.id.jobQualification);
        jobDescription = findViewById(R.id.jobDescription);
        createAccountButton = findViewById(R.id.createAccountButton);

        loadingProgress = findViewById(R.id.creatingAccountProgressBar);

        mUserID = getIntent().getStringExtra("userID");

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("users");
        mAuth = FirebaseAuth.getInstance();


        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgress.setVisibility(View.VISIBLE);
                createAccount(mUserID);
            }
        });

    }

    /**
     * Method to Create Account for the user
     * */
    private void createAccount(String userId) {
        sensitizeText();

        if (!mCheckEditText){
            postAccountDetails(newUserRegistration);
        }
        else{
            Toast.makeText(this, "Fill all required fielsds", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * Method to save the account details to firebase database
     *
     * @param newUserRegistration*/
    private void postAccountDetails(UserRegistration newUserRegistration) {

        mDatabaseReference.child(mUserID).setValue(newUserRegistration).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                loadingProgress.setVisibility(View.GONE);
                Toast.makeText(CreateAccountActivity.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                Intent openMAinActivity = new Intent(CreateAccountActivity.this, MainActivity.class);
                startActivity(openMAinActivity);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                loadingProgress.setVisibility(View.GONE);
                Toast.makeText(CreateAccountActivity.this, "Failed Try again", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void sensitizeText() {

        mJobCategory = jobCategory.getText().toString().trim();
        mJobTittle = jobTittle.getText().toString().trim();
        mJobQualification = jobQualification.getText().toString().trim();
        mJobDescription = jobDescription.getText().toString().trim();

        mCheckEditText = mJobCategory.isEmpty() && mJobTittle.isEmpty() && mJobQualification.isEmpty() && mJobDescription.isEmpty();

        newUserRegistration = new UserRegistration(
                mUserID,
                mJobCategory,
                mJobTittle,
                mJobQualification,
                mJobDescription

        );
    }

}
