package com.example.syndicapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.HashMap;
import java.util.Map;

public class Reclamations extends Activity {
    private LinearLayout cardContainer;

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "Reclamations";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reclamations_activity);

        cardContainer = findViewById(R.id.cardContainer); // Initialisation du cardContainer

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddReclamationDialog();
            }
        });

        loadReclamations(); // Chargement des réclamations
    }

    public void ajouter(View view) {
        showAddReclamationDialog();
    }

    private void showAddReclamationDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.add_reclamation);

        // Make the dialog full screen width
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        EditText editTextTitle = dialog.findViewById(R.id.editTextTitle);
        EditText editTextDescription = dialog.findViewById(R.id.editTextDescription);
        Button buttonSubmit = dialog.findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTextTitle.getText().toString();
                String description = editTextDescription.getText().toString();

                if (title.isEmpty() || description.isEmpty()) {
                    Toast.makeText(Reclamations.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                Map<String, Object> reclamation = new HashMap<>();
                reclamation.put("titre", title); // Utiliser "title" au lieu de "titre"
                reclamation.put("description", description);

                Log.d(TAG, reclamation.toString());

                db.collection("reclamations").add(reclamation) // Utiliser la collection "reclamations"
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(Reclamations.this, "Successful", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                addReclamationCard(title, description); // Ajouter la nouvelle réclamation à la vue
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Reclamations.this, "Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        dialog.show();
    }

    private void loadReclamations() {
        db.collection("reclamations")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String title = document.getString("titre");
                            String description = document.getString("description");
                            addReclamationCard(title, description);
                        }
                    } else {
                        Log.w(TAG, "Error getting documents.", task.getException());
                    }
                });
    }

    private void addReclamationCard(String title, String description) {
        View cardView = getLayoutInflater().inflate(R.layout.reclamation_card, cardContainer, false);

        TextView titleTextView = cardView.findViewById(R.id.reclamationTitle);
        TextView descriptionTextView = cardView.findViewById(R.id.reclamationDescription);

        titleTextView.setText(title);
        descriptionTextView.setText(description);

        cardContainer.addView(cardView);
    }

    public void retour(View view) {
        Intent intent = new Intent(Reclamations.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
