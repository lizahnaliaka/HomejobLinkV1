package com.polotechnologies.homejoblink;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 1000;
    private static final int RC_SIGN_UP = 2000;
    Button createAccountButton;
    Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        createAccountButton = findViewById(R.id.buttonCreateAccount);
        signInButton = findViewById(R.id.buttonSignIn);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }

    /**
     * Method to Sign In The User With Phone Number
     */
    private void signIn() {
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Collections.singletonList(
                new AuthUI.IdpConfig.PhoneBuilder().build());

        // Create and launch sign-in intent
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IdpResponse response = IdpResponse.fromResultIntent(data);


        switch (requestCode) {

            case RC_SIGN_IN:

                if (resultCode == RESULT_OK) {
                    // Successfully signed in
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    // ...
                } else {

                    // Sign in failed. If response is null the user canceled the
                    if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                        printMessage("No Network");
                    } else {
                        printMessage("Cancelled");
                    }

                }
            case RC_SIGN_UP:

                if (resultCode == RESULT_OK) {
                    // Successfully signed in
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    assert user != null;
                    String userID = user.getUid();

                    Intent startAccountCreation = new Intent(LoginActivity.this, CreateAccountActivity.class);
                    startAccountCreation.putExtra("userID",userID);
                    startActivity(startAccountCreation);

                } else {
                    // Sign in failed

                    if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                        printMessage("Failed: No Network");
                    } else {
                        printMessage("Cancelled");
                    }

                }

        }
    }

    private void printMessage(String message) {
        Toast.makeText(this, "Login Failed:  " + " Cause " + message, Toast.LENGTH_SHORT).show();
    }

}
