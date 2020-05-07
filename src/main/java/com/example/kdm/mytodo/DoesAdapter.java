package com.example.kdm.mytodo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DoesAdapter extends RecyclerView.Adapter<DoesAdapter.MyViewHolder> {

    Context context;
    ArrayList<MyDoes> myDoes;

    public DoesAdapter(Context c, ArrayList<MyDoes> p){
        context = c;
        myDoes = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_does, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.titledoes.setText(myDoes.get(i).getTitleDoes());
        myViewHolder.descdoes.setText(myDoes.get(i).getDescDoes());
        myViewHolder.datedoes.setText(myDoes.get(i).getDateDoes());

        final String getTitleDoes = myDoes.get(i).getTitleDoes();
        final String getDescDoes = myDoes.get(i).getDescDoes();
        final String getDateDoes = myDoes.get(i).getDateDoes();
        final String getKeyDoes = myDoes.get(i).getKeyDoes();

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,EditTaskDesk.class);
                i.putExtra("titledoes", getTitleDoes);
                i.putExtra("descdoes", getDescDoes);
                i.putExtra("datedoes", getDateDoes);
                i.putExtra("KeyDoes",getKeyDoes);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myDoes.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView titledoes, descdoes, datedoes, keydoes;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titledoes = itemView.findViewById(R.id.titleDoes);
            descdoes = itemView.findViewById(R.id.descDoes);
            datedoes = itemView.findViewById(R.id.dateDoes);
        }
    }
}
