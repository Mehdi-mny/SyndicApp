package com.example.syndicapp;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login_page extends Activity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page_activity);
        mAuth = FirebaseAuth.getInstance();


    }
    public void signin(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(login_page.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.w(TAG, "signin:success");
                    Toast.makeText(login_page.this, "Authentication successful.",
                            Toast.LENGTH_SHORT).show();
                    FirebaseUser user = mAuth.getCurrentUser();
                    System.out.println(user);
                    updateUI(user);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(login_page.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void updateUI(FirebaseUser user) {
        // Code to update UI after authentication
        if (user!=null) {
            Intent intent = new Intent(login_page.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void onLoginClicked(View view) {
        EditText emailTextView = findViewById(R.id.editTextEmail);
        EditText passwordTextView = findViewById(R.id.editTextPassword);

        String email = emailTextView.getText().toString();
        String password = passwordTextView.getText().toString();

        // Appeler la méthode createUserWithEmailAndPassword avec les valeurs récupérées
        signin(email, password);

    }
    public void onCreateAccountClicked(View view) {
        Intent intent = new Intent(login_page.this, create_account.class);
        startActivity(intent);
        finish();
    }
}
