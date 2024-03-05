package com.example.universityportal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UnitsAdapter extends RecyclerView.Adapter<UnitsAdapter.ViewHolder> {

    private final ArrayList<Unit> units;
    Context context;
    static OnUnitClicked onUnitClicked;


    public UnitsAdapter(ArrayList<Unit> jobs, Context context, OnUnitClicked onMessageClicked) {
        this.units = jobs;
        this.context = context;
        UnitsAdapter.onUnitClicked = onMessageClicked;
    }

    interface OnUnitClicked {
        void onUnitClicked(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.added_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Unit model = units.get(position);
        holder.textView1.setText(model.getUnitName());
        holder.textView2.setText(model.getUnitCode());
        holder.textView3.setText(model.getLecturerName());
        holder.textView4.setText(model.getStage());

    }

    @Override
    public int getItemCount() {
        return units.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1, textView2, textView3, textView4;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onUnitClicked.onUnitClicked(getAdapterPosition());

                }
            });
            textView1 = itemView.findViewById(R.id.TV_unitName);
            textView2 = itemView.findViewById(R.id.TV_unitCode);
            textView3 = itemView.findViewById(R.id.TV_lecturerName);
            textView4 = itemView.findViewById(R.id.TV_stage);
        }
    }

}




