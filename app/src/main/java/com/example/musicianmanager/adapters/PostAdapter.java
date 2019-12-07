package com.example.musicianmanager.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicianmanager.R;
import com.example.musicianmanager.models.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder>{

    private List<Post> datas;

    public PostAdapter(List<Post> datas) {
        this.datas = datas;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post data = datas.get(position); // Post 객체 만듬 -> position : 0,1,2,3... 그 포지션의 item을 하나 씩 넣어주는 것.
        holder.title.setText(data.getTitle()); // 포지션 0의 홀더, 포지션 1의 홀더...
        holder.contents.setText(data.getContents());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder{
        private TextView title, contents;

        public PostViewHolder(@NonNull View itemView){
            super(itemView);

            title = itemView.findViewById(R.id.item_post_title);
            contents = itemView.findViewById(R.id.item_post_contents);
        }
    }

}
