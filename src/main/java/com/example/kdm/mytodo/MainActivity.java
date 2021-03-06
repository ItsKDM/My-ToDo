package com.example.kdm.mytodo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView titlePage, subtitlePage, endPage;
    Button btnAddNew;

    DatabaseReference reference;
    RecyclerView ourDoes;
    ArrayList<MyDoes> list;
    DoesAdapter doesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titlePage = findViewById(R.id.titlePage);
        subtitlePage = findViewById(R.id.subtitlePage);
        endPage = findViewById(R.id.endPage);

        btnAddNew = findViewById(R.id.btnAddNew);

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NewTaskAct.class);
                startActivity(i);
            }
        });

        //Working with Data
        ourDoes = findViewById(R.id.ourDoes);
        ourDoes.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<MyDoes>();

        //Get Data from Firebase
        reference = FirebaseDatabase.getInstance().getReference().child("MyToDo");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Set Code to retrive data and replace layout
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    MyDoes p = dataSnapshot1.getValue(MyDoes.class);
                    list.add(p);
                }

                doesAdapter = new DoesAdapter(MainActivity.this, list);
                ourDoes.setAdapter(doesAdapter);
                doesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Set Code to sho an Error
                Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
