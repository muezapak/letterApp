package com.example.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<Model> dataHolder;

    public MyAdapter(ArrayList<Model> dataHolder) {
        this.dataHolder = dataHolder;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Model model = dataHolder.get(position);
        holder.id.setText("Id : "+model.getId());
        holder.cAns.setText("Correct answer : "+model.getcAnswers());
        holder.uAns.setText("Your answer : "+model.getuAnswers());
        holder.alpha.setText("Key : "+model.getAlpha());

    }

    @Override
    public int getItemCount() {
        return dataHolder.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView cAns, uAns, alpha,id;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.ltrid);
            cAns = itemView.findViewById(R.id.CorrectAnswers);
            uAns = itemView.findViewById(R.id.UserAnswers);
            alpha = itemView.findViewById(R.id.Keys);
        }
    }
}
