package com.example.musicianmanager.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicianmanager.R;
import com.example.musicianmanager.models.MusicEvent;

import java.util.List;

public class MatchedAdapter extends RecyclerView.Adapter<MatchedAdapter.PostViewHolder> {

    private List<MusicEvent> datas;

    public MatchedAdapter(List<MusicEvent> datas) {
        this.datas = datas;
    }

    @NonNull
    @Override
    public MatchedAdapter.PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MatchedAdapter.PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_matched_post, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MatchedAdapter.PostViewHolder holder, int position) {
        MusicEvent data = datas.get(position); // MusicEvent 객체 만듬 -> position : 0,1,2,3... 그 포지션의 item을 하나 씩 넣어주는 것.
        holder.date.setText(data.getDate());
        String location = data.getLocation().split("구 ")[0];
        location = location.concat("구");
        holder.location.setText(location);
        holder.host.setText(data.getTitle());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder {
        private TextView host,performer,date,location;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.item_matched_date);
            host = itemView.findViewById(R.id.item_matched_title);
            location = itemView.findViewById(R.id.item_matched_location);
        }
    }
}