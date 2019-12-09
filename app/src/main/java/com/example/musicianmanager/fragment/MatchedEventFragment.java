package com.example.musicianmanager.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.musicianmanager.ApplyActivity;
import com.example.musicianmanager.FirebaseID;
import com.example.musicianmanager.R;
import com.example.musicianmanager.adapters.MatchedAdapter;
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

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MatchedEventFragment extends Fragment {

    // This Page is for Evaluating performers, and it is not the Main Scenario Event
    // This part will not be generated in code, in this Domain Analysis and Software Design class

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();

    private RecyclerView mMatchedRecyclerView;

    private MatchedAdapter mAdapter;
    private List<MusicEvent> mDatas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_matched_event, container, false);

        mMatchedRecyclerView = view.findViewById(R.id.matched_recycler);

        mDatas = new ArrayList<>();
        mStore.collection(FirebaseID.post)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("TAG", document.getId() + " matched => " + document.getData());
                                Map<String, Object> shot = document.getData();
                                if(Boolean.valueOf((Boolean) shot.get("matchedStatus"))==true){
                                    System.out.println("에?");
                                    String location = String.valueOf(shot.get(FirebaseID.location));
                                    String date = String.valueOf(shot.get(FirebaseID.date));
                                    String title = String.valueOf(shot.get(FirebaseID.title));
                                    String hostID = String.valueOf(shot.get(FirebaseID.hostID));
                                    MusicEvent data = new MusicEvent(date, 0, location, "", hostID, true, "", "", title);
                                    mDatas.add(data);
                                }
                                //else break;
                            }
                            mAdapter = new MatchedAdapter(mDatas);
                            mMatchedRecyclerView.setAdapter(mAdapter);
                        }
                    }
                });

        return view;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.home_toolbar_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.home_toolbar_account:
                Toast.makeText(getContext(),"My Profile",Toast.LENGTH_LONG).show();
                return true;

            default: return super.onOptionsItemSelected(item);
        }
    }


}
