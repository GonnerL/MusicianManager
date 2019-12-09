package com.example.musicianmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.musicianmanager.adapters.PostAdapter;
import com.example.musicianmanager.models.Post;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

public class ApplyActivity extends AppCompatActivity {
    FirebaseFirestore mStore = FirebaseFirestore.getInstance();

    public static String currentMusicEventId;
    TextView title, content, location, date, time, eventType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);

        title = findViewById(R.id.apply_title);
        content = findViewById(R.id.apply_content);
        location = findViewById(R.id.apply_location);
        date = findViewById(R.id.apply_date);
        eventType = findViewById(R.id.apply_eventType);
        time = findViewById(R.id.apply_time);
    }

    @Override
    protected void onStart() {
        super.onStart();

        DocumentReference docRef = mStore.collection(FirebaseID.post).document(currentMusicEventId);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Map<String, Object> shot = document.getData();
                        title.setText(String.valueOf(shot.get(FirebaseID.title)));
                        content.setText(String.valueOf(shot.get(FirebaseID.contents)));
                        location.setText(String.valueOf(shot.get(FirebaseID.location)));
                        date.setText(String.valueOf(shot.get(FirebaseID.date)).concat(", "));
                        eventType.setText(String.valueOf(shot.get(FirebaseID.eventType)));
                        time.setText(String.valueOf(shot.get(FirebaseID.time)).concat("시간"));
                        Log.d("TAG", "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d("TAG", "No such document");
                    }
                } else {
                    Log.d("TAG", "get failed with ", task.getException());
                }
            }
        });
    }
}
