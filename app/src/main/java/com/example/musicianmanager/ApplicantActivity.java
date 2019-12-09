package com.example.musicianmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.musicianmanager.adapters.ApplicantAdapter;
import com.example.musicianmanager.adapters.PostAdapter;
import com.example.musicianmanager.models.Performer;
import com.example.musicianmanager.models.Post;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApplicantActivity extends AppCompatActivity {
    FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    private ApplicantAdapter mAdapter;
    private List<Performer> mDatas;
    private RecyclerView mApplicantRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicant);
        mApplicantRecyclerView = findViewById(R.id.applicant_recyclerview);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDatas = new ArrayList<>();
        mStore.collection("ApplyingRequest")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("TAG", document.getId() + " => " + document.getData());
                                Map<String, Object> shot = document.getData();
                                String musicEventId = String.valueOf(shot.get(FirebaseID.musicEventId));
                                String performerID = String.valueOf(shot.get(FirebaseID.performerID));
                                if(!ApplyActivity.currentMusicEventId.equals(musicEventId)){
                                    System.out.println("불일치");
                                    break;
                                }
                                Performer performer = new Performer(performerID);
                                mDatas.add(performer);
                                }
                            mAdapter = new ApplicantAdapter(mDatas);
                            mApplicantRecyclerView.setAdapter(mAdapter);
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}
