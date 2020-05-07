package com.example.kdm.mytodo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class NewTaskAct extends AppCompatActivity {

    TextView titlePage, addTitle, addDesc, addDate;
    EditText titleDoes, descDoes, dateDoes;
    Button btnSaveTask, btnCancel;

    DatabaseReference reference;
    Integer doesNum = new Random().nextInt();

    String keyDoes = Integer.toString(doesNum);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        titlePage = findViewById(R.id.titlePage);
        addTitle = findViewById(R.id.addTitle);
        titleDoes = findViewById(R.id.titleDoes);

        addDesc = findViewById(R.id.addDesc);
        addDate = findViewById(R.id.addDate);
        descDoes = findViewById(R.id.descDoes);
        dateDoes = findViewById(R.id.dateDoes);

        btnSaveTask = findViewById(R.id.btnCreate);
        btnCancel = findViewById(R.id.btnCancel);

        btnSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Insert Data to Database
                reference = FirebaseDatabase.getInstance().getReference().child("MyToDo").child("Does" + doesNum);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("titleDoes").setValue(titleDoes.getText().toString());
                        dataSnapshot.getRef().child("descDoes").setValue(descDoes.getText().toString());
                        dataSnapshot.getRef().child("dateDoes").setValue(dateDoes.getText().toString());
                        dataSnapshot.getRef().child("keyDoes").setValue(keyDoes);

                        Intent i = new Intent(NewTaskAct.this, MainActivity.class);
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
