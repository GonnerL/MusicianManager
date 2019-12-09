package com.example.musicianmanager.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.musicianmanager.FirebaseID;
import com.example.musicianmanager.R;
import com.example.musicianmanager.adapters.PostAdapter;
import com.example.musicianmanager.models.MusicEvent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class EventListFragment extends Fragment {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();

    private RecyclerView mPostRecyclerView;

    private PostAdapter mAdapter;
    private List<MusicEvent> mDatas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_eventlist, container, false);

        mPostRecyclerView = view.findViewById(R.id.main_recyclerview);

        mDatas = new ArrayList<>();
        mStore.collection(FirebaseID.post)
                .orderBy(FirebaseID.timestamp, Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("TAG", document.getId() + " => " + document.getData());
                                System.out.println(document.getId());
                                Map<String, Object> shot = document.getData();
                                DocumentReference doRef = mStore.collection(FirebaseID.post).document(document.getId());
                                doRef
                                        .update(FirebaseID.musicEventId, document.getId())
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Log.d("TAG", "DocumentSnapshot successfully updated! - musiceventid");
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.w("TAG", "Error updating document", e);
                                            }
                                        });
                                try {
                                    String musicEventId = String.valueOf(shot.get(FirebaseID.musicEventId));
                                    String title = String.valueOf(shot.get(FirebaseID.title));
                                    String contents = String.valueOf(shot.get(FirebaseID.contents));
                                    String date = String.valueOf(shot.get(FirebaseID.date));
                                    int time = Integer.parseInt(String.valueOf(shot.get(FirebaseID.time)));
                                    String location = String.valueOf(shot.get(FirebaseID.location));
                                    String eventType = String.valueOf(shot.get(FirebaseID.eventType));
                                    String hostID = String.valueOf(shot.get(FirebaseID.hostID));
                                    Boolean matchedStatus = Boolean.valueOf((Boolean) shot.get(FirebaseID.matchedStatus));
                                    MusicEvent data = new MusicEvent(date, time, location, eventType, hostID, matchedStatus, contents, musicEventId, title);
                                    System.out.println(data);
                                    mDatas.add(data);
                                } catch (Exception e) {
                                    Log.w("TAG", "Something Not Written", e);
                                }
                            }
                            mAdapter = new PostAdapter(mDatas);
                            mPostRecyclerView.setAdapter(mAdapter);
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });

        return view;

    }

}

