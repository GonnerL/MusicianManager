package com.example.musicianmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musicianmanager.adapters.PostAdapter;
import com.example.musicianmanager.models.Post;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

// 중복 유저 체크

public class ApplyActivity extends AppCompatActivity implements View.OnClickListener {
    FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

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
        findViewById(R.id.button_apply).setOnClickListener(this);
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


    @Override
    public void onClick(View view) {
        if(mAuth.getCurrentUser() != null) {
            Map<String, Object> data = new HashMap<>();
            data.put(FirebaseID.musicEventId, currentMusicEventId); // 여기여기
            data.put(FirebaseID.acceptance, false);
            data.put(FirebaseID.performerID, mAuth.getCurrentUser().getUid());
            mStore.collection("ApplyingRequest")
                    .add(data)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d("Tag", "DocumentSnapshot added with ID: " + documentReference.getId());
                            Toast.makeText(ApplyActivity.this, "지원이 완료되었습니다", Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("Tag", "Error adding document", e);
                        }
                    });
            finish();
        }
    }

}
