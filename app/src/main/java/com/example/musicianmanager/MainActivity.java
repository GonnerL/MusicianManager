package com.example.musicianmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.musicianmanager.adapters.PostAdapter;
import com.example.musicianmanager.fragment.EvaluationFragment;
import com.example.musicianmanager.fragment.HomeFragment;
import com.example.musicianmanager.fragment.MatchedEventFragment;
import com.example.musicianmanager.fragment.RecommendFragment;
import com.example.musicianmanager.fragment.SettingFragment;
import com.example.musicianmanager.models.Post;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.core.QueryListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();

    private RecyclerView mPostRecyclerView;

    private PostAdapter mAdapter;
    private List<Post> mDatas;

    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPostRecyclerView = findViewById(R.id.main_recyclerview);

        bottomNavigationView = findViewById(R.id.mainactivity_bottomnavigationview);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.home_toolbar_title);

        findViewById(R.id.main_post_edit).setOnClickListener(this);

        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.mainactivity_framelayout, new HomeFragment()).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        replaceFragment(new HomeFragment());
                        return true;
                    case R.id.action_matchedEvent:
                        replaceFragment(new MatchedEventFragment());
                        return true;
                    case R.id.action_recommend:
                        replaceFragment(new RecommendFragment());
                        return true;
                    case R.id.action_evaluate:
                        replaceFragment(new EvaluationFragment());
                        return true;
                    case R.id.action_setting:
                        replaceFragment(new SettingFragment());
                        return true;
                }
                return true;
            }
        });
        findViewById(R.id.main_post_edit).setOnClickListener(this);
        findViewById(R.id.main_logout).setOnClickListener(this);

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
                                Log.d("TAG", document.getId() + " => " + document.getData());
                                System.out.println(document.getId());
                                Map<String, Object> shot = document.getData();
                                DocumentReference doRef = mStore.collection(FirebaseID.post).document(document.getId());
                                doRef
                                        .update(FirebaseID.musicEventId, document.getId())
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Log.d("TAG", "DocumentSnapshot successfully updated!");
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.w("TAG", "Error updating document", e);
                                            }
                                        });
                                String musicEventId = String.valueOf(shot.get(FirebaseID.musicEventId));
                                String title = String.valueOf(shot.get(FirebaseID.title));
                                String contents = String.valueOf(shot.get(FirebaseID.contents));
                                String date = String.valueOf(shot.get(FirebaseID.date));
                                int time = Integer.parseInt(String.valueOf(shot.get(FirebaseID.time)));
                                String location = String.valueOf(shot.get(FirebaseID.location));
                                String eventType = String.valueOf(shot.get(FirebaseID.eventType));
                                String hostID = String.valueOf(shot.get(FirebaseID.hostID));
                                Boolean matchedStatus = Boolean.valueOf((Boolean) shot.get(FirebaseID.matchedStatus));
                                Post data = new Post(date, time, location, eventType, hostID, matchedStatus, contents, musicEventId, title);
                                System.out.println(data);
                                mDatas.add(data);
                            }
                            mAdapter = new PostAdapter(mDatas);
                            mPostRecyclerView.setAdapter(mAdapter);
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_post_edit:
                startActivity(new Intent(MainActivity.this, PostActivity.class));
                break;
            case R.id.main_logout:
                signOut();
                break;

        }
    }

    public void signOut() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainactivity_framelayout, fragment).commit();
    }

    public void toApply(View view){
        System.out.println("왔냐");
        TextView musicEventId;
        musicEventId = findViewById(R.id.item_post_musicEventId);
        ApplyActivity.currentMusicEventId = musicEventId.getText().toString();
        startActivity(new Intent(MainActivity.this, ApplyActivity.class));
    }

    public void toMyEvent(View view){
        startActivity(new Intent(MainActivity.this, EventViewActivity.class));
    }


}
