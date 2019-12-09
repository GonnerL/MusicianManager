package com.example.musicianmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musicianmanager.adapters.ApplicantAdapter;
import com.example.musicianmanager.fragment.MatchedEventFragment;
import com.example.musicianmanager.models.Performer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
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
    TextView applicantId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicant);
        mApplicantRecyclerView = findViewById(R.id.applicant_recyclerview);
        applicantId = findViewById(R.id.item_applicant_name);
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
                                Log.d("TAG", document.getId() + "  => " + document.getData());
                                Map<String, Object> shot = document.getData();
                                String musicEventId = String.valueOf(shot.get(FirebaseID.musicEventId));
                                String performerID = String.valueOf(shot.get(FirebaseID.performerID));
                                if(!ApplyActivity.currentMusicEventId.equals(musicEventId)){
                                    System.out.println(musicEventId + "::::"+ApplyActivity.currentMusicEventId);
                                    System.out.println("불일치");
                                    continue;
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

    public void selectApplicant(View view){
        mStore.collection("ApplyingRequest")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("TAG", document.getId() + " => " + document.getData());
                                Map<String, Object> shot = document.getData();
                                String performerID = String.valueOf(shot.get(FirebaseID.performerID));
                                String mId = String.valueOf(shot.get(FirebaseID.musicEventId));
                                /*if (!"0EZzIzeKJyaaSja2W74zWZ3pYD63".equals(performerID)) {
                                    System.out.println("불일치");
                                    break;
                                }*/
                                System.out.println("왔니");
                                System.out.println("documentID"+document.getId());
                                DocumentReference doRef = mStore.collection("ApplyingRequest").document(document.getId());
                                doRef
                                        .update(FirebaseID.acceptance, true)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Log.d("TAG", "DocumentSnapshot successfully updated! true");
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.w("TAG", "Error updating document", e);
                                            }
                                        });
                            }
                        }
                    }
                });

        mStore.collection(FirebaseID.post)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("TAG", document.getId() + " => " + document.getData());
                                Map<String, Object> shot = document.getData();
                                String performerID = String.valueOf(shot.get(FirebaseID.performerID));
                                if(!ApplyActivity.currentMusicEventId.equals(document.getId())){
                                    System.out.println("current" + ApplyActivity.currentMusicEventId);
                                    System.out.println("current doc" + document.getId());
                                    continue;
                                }
                                System.out.println("한번은");
                                DocumentReference doRef = mStore.collection(FirebaseID.post).document(document.getId());
                                doRef
                                        .update(FirebaseID.matchedStatus, true)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Log.d("TAG", "DocumentSnapshot successfully updated!");
                                                Toast.makeText(ApplicantActivity.this, "지원자를 선택하였습니다", Toast.LENGTH_LONG).show();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.w("TAG", "Error updating document", e);
                                            }
                                        });
                            }
                        }
                    }
                });
        startActivity(new Intent(ApplicantActivity.this, MainActivity.class));
    }
}
