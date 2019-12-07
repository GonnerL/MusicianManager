package com.example.musicianmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class PostActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private EditText mTitle, mContents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        mTitle = findViewById(R.id.post_title_edit);
        mContents = findViewById(R.id.post_content_edit);

        findViewById(R.id.post_save_button).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if(mAuth.getCurrentUser() != null){
            Map<String, Object> data = new HashMap<>();
            data.put(FirebaseID.documentID, mAuth.getCurrentUser().getUid());
            data.put(FirebaseID., mTitle.getText().toString());
            data.put(FirebaseID., mContents.getText().toString());
            mStore.collection(FirebaseID.).document(mTitle.getText().toString()).set();
        }
    }
}
