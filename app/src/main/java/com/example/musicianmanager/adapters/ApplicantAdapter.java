package com.example.musicianmanager.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicianmanager.R;
import com.example.musicianmanager.models.Performer;
import com.example.musicianmanager.models.Post;

import java.util.List;

public class ApplicantAdapter extends RecyclerView.Adapter<ApplicantAdapter.PostViewHolder>{

    private List<Performer> datas;

    public ApplicantAdapter(List<Performer> datas) {
        this.datas = datas;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_applicants, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Performer data = datas.get(position);
        holder.performerName.setText(data.getName());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder{
        private TextView performerName;

        public PostViewHolder(@NonNull View itemView){
            super(itemView);
            performerName = itemView.findViewById(R.id.item_applicant_name);
        }
    }

}
