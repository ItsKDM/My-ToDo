package com.example.kdm.mytodo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditTaskDesk extends AppCompatActivity {

    EditText titleDoes, descDoes, dateDoes;
    Button btnSaveUpdate, btnDelete;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task_desk);

        titleDoes = findViewById(R.id.titleDoes);
        descDoes = findViewById(R.id.descDoes);
        dateDoes = findViewById(R.id.dateDoes);

        btnSaveUpdate = findViewById(R.id.btnSaveUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        //Get value from previous page
        titleDoes.setText(getIntent().getStringExtra("titledoes"));
        descDoes.setText(getIntent().getStringExtra("descdoes"));
        dateDoes.setText(getIntent().getStringExtra("datedoes"));

        final String keykeyDoes = getIntent().getStringExtra("KeyDoes");

        reference = FirebaseDatabase.getInstance().getReference().child("MyToDo").child("Does" + keykeyDoes);

        //Make an event for Delete Button
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Intent i = new Intent(EditTaskDesk.this, MainActivity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(getApplicationContext(), "Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        //Make an event for Save button
        btnSaveUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("titleDoes").setValue(titleDoes.getText().toString());
                        dataSnapshot.getRef().child("descDoes").setValue(descDoes.getText().toString());
                        dataSnapshot.getRef().child("dateDoes").setValue(dateDoes.getText().toString());
                        dataSnapshot.getRef().child("keyDoes").setValue(keykeyDoes);

                        Intent i = new Intent(EditTaskDesk.this, MainActivity.class);
                        startActivity(i);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
