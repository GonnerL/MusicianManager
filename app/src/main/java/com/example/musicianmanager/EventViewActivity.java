package com.example.musicianmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.musicianmanager.adapters.MyEventAdapter;
import com.example.musicianmanager.adapters.PostAdapter;
import com.example.musicianmanager.models.MusicEvent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EventViewActivity extends AppCompatActivity {
    private RecyclerView mPostRecyclerView;
    FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private MyEventAdapter mAdapter;
    private List<MusicEvent> mDatas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_view);

        mPostRecyclerView = findViewById(R.id.myEvent_recyclerview);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDatas = new ArrayList<>();
        mStore.collection(FirebaseID.post)
                .orderBy(FirebaseID.timestamp, Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d("TAG", document.getId() + " 끄앙=> " + document.getData());
                                    Map<String, Object> shot = document.getData();
                                    String hostID = String.valueOf(shot.get(FirebaseID.hostID));
                                    if(!mAuth.getCurrentUser().getUid().equals(hostID)){
                                        break;
                                    }
                                    String musicEventId = String.valueOf(shot.get(FirebaseID.musicEventId));
                                    String title = String.valueOf(shot.get(FirebaseID.title));
                                    String contents = String.valueOf(shot.get(FirebaseID.contents));
                                    String date = String.valueOf(shot.get(FirebaseID.date));
                                    int time = Integer.parseInt(String.valueOf(shot.get(FirebaseID.time)));
                                    String location = String.valueOf(shot.get(FirebaseID.location));
                                    String eventType = String.valueOf(shot.get(FirebaseID.eventType));
                                    Boolean matchedStatus = Boolean.valueOf((Boolean) shot.get(FirebaseID.matchedStatus));
                                    MusicEvent data = new MusicEvent(date, time, location, eventType, hostID, matchedStatus, contents, musicEventId, title);
                                    mDatas.add(data);
                                }
                                mAdapter = new MyEventAdapter(mDatas);
                                mPostRecyclerView.setAdapter(mAdapter);
                            } else {
                                Log.w("TAG", "Error getting documents.", task.getException());
                            }
                        }
                });

    }

    public void viewApplicant(View view){
        startActivity(new Intent(EventViewActivity.this,ApplicantActivity.class));
    }

}
