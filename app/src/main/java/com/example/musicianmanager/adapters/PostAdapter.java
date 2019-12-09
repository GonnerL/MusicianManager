package com.example.musicianmanager.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicianmanager.ApplyActivity;
import com.example.musicianmanager.R;
import com.example.musicianmanager.models.ApplyingRequest;
import com.example.musicianmanager.models.MusicEvent;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder>{

    private List<MusicEvent> datas;

    public PostAdapter(List<MusicEvent> datas) {
        this.datas = datas;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        MusicEvent data = datas.get(position); // MusicEvent 객체 만듬 -> position : 0,1,2,3... 그 포지션의 item을 하나 씩 넣어주는 것.
        holder.date.setText(data.getDate());
        holder.title.setText(data.getTitle()); // 포지션 0의 홀더, 포지션 1의 홀더...
        String location = data.getLocation().split("구 ")[0];
        System.out.println(location);
        location = location.concat("구");
        holder.location.setText(location);
        holder.musicEventId.setText(data.getMuiscEventId());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder{
        private TextView title, date, location, musicEventId;

        public PostViewHolder(@NonNull View itemView){
            super(itemView);

            date = itemView.findViewById(R.id.item_post_date);
            title = itemView.findViewById(R.id.item_post_title);
            location = itemView.findViewById(R.id.item_post_location);
            this.musicEventId = itemView.findViewById(R.id.item_post_musicEventId);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Recyclerview", "position = "+ getAdapterPosition());
                    MusicEvent data = datas.get(getAdapterPosition());
                    ApplyActivity.currentMusicEventId = data.getMuiscEventId();
                    System.out.println(ApplyActivity.currentMusicEventId);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Log.d("Recyclerview", "position = "+ getAdapterPosition());
                    return false;
                }
            });
        }
    }

}
