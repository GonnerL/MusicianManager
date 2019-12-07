package com.example.musicianmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.musicianmanager.adapters.PostAdapter;
import com.example.musicianmanager.models.Post;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mPostRecyclerView;

    private PostAdapter mAdapter;
    private List<Post> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPostRecyclerView = findViewById(R.id.main_recyclerview);

        mDatas = new ArrayList<>();
        for(int i=0;i<100;i++) {
            mDatas.add(new Post(null, "title","contents"));
        }
       /* mDatas.add(new Post(null, "title","contents"));
        mDatas.add(new Post(null, "title","contents"));
        mDatas.add(new Post(null, "title","contents"));*/

        mAdapter = new PostAdapter(mDatas);
        mPostRecyclerView.setAdapter(mAdapter);

        findViewById(R.id.main_post_edit).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(MainActivity.this, PostActivity.class));

    }
}
