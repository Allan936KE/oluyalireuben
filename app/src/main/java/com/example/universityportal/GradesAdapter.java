package com.example.universityportal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GradesAdapter extends RecyclerView.Adapter<GradesAdapter.ViewHolder> {

    private final ArrayList<Grade> grades;
    Context context;
    static OnUnitClicked onUnitClicked;


    public GradesAdapter(ArrayList<Grade> jobs, Context context, OnUnitClicked onMessageClicked) {
        this.grades = jobs;
        this.context = context;
        GradesAdapter.onUnitClicked = onMessageClicked;
    }

    interface OnUnitClicked {
        void onUnitClicked(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.graded_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Grade model = grades.get(position);
        holder.textView1.setText(model.getUnitName());
        holder.textView2.setText(model.getUnitCode());
        holder.textView3.setText(model.getLecturerName());
        holder.textView4.setText(model.getStage());
        holder.textView5.setText(model.getStudentName());
        holder.textView6.setText(model.getRegNo());
        holder.textView7.setText(model.getGrade());

    }

    @Override
    public int getItemCount() {
        return grades.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1, textView2, textView3, textView4,textView5, textView6,textView7;

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
            textView5 = itemView.findViewById(R.id.TV_studentName);
            textView6 = itemView.findViewById(R.id.TV_RegNo);
            textView7 = itemView.findViewById(R.id.TV_Grade);
        }
    }

}




