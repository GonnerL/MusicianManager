package com.example.musicianmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class PostActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatePickerDialog.OnDateSetListener callbackMethod;
    private TimePickerDialog.OnTimeSetListener callbackMethod2;

    private String date;
    private TextView textView_Date, textView_time, textView_eventType;
    private EditText mTitle, mContents;
    private Spinner spinner_eventType;

    ArrayAdapter<CharSequence> adspin1, adspin2;
    String choice_do="";
    String choice_si="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        final Spinner spinDo = (Spinner)findViewById(R.id.spinner_location_do);
        final Spinner spinSi = (Spinner)findViewById(R.id.spinner_location_gu);

        adspin1 = ArrayAdapter.createFromResource(this, R.array.location_do,android.R.layout.simple_spinner_dropdown_item);
        adspin1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinDo.setAdapter(adspin1);
        spinDo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adspin1.getItem(i).equals("서울특별시")){
                    choice_do = "서울특별시";
                    adspin2 = ArrayAdapter.createFromResource(PostActivity.this, R.array.location_seoul, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinSi.setAdapter(adspin2);
                    spinSi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_si = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
                else if(adspin1.getItem(i).equals("경기도")){
                    choice_do = "경기도";
                    adspin2 = ArrayAdapter.createFromResource(PostActivity.this, R.array.location_gyunggi, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinSi.setAdapter(adspin2);
                    spinSi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_si = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mTitle = findViewById(R.id.post_title_edit);
        mContents = findViewById(R.id.post_content_edit);
        spinner_eventType = findViewById(R.id.spinner_eventType);
        textView_eventType = findViewById(R.id.textView_eventType);

        findViewById(R.id.post_save_button).setOnClickListener(this);

        this.InitializeView();
        this.InitializeListener();

        spinner_eventType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                textView_eventType.setText(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void InitializeView(){
        textView_Date = findViewById(R.id.textView_date);
        textView_time = findViewById(R.id.textView_time);
    }

    public void InitializeListener(){
        callbackMethod = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                textView_Date.setText(i + "년 " + (i1+1) + "월 " + i2 + "일");
            }
        };

        callbackMethod2 = new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute)
            {
                textView_time.setText(hourOfDay + "시" + minute + "분");
            }
        };
    }

    public void OnClickHandler(View view) {
        switch(view.getId()){
            case R.id.button_date:
                DatePickerDialog dateDialog = new DatePickerDialog(this, callbackMethod, 2019, 7, 18);
                dateDialog.show();
                break;
            case R.id.button_time:
                TimePickerDialog timeDialog = new TimePickerDialog(this, callbackMethod2, 8, 10, true);
                timeDialog.show();
                break;
        }
    }


    @Override
    public void onClick(View view) {
        if(mAuth.getCurrentUser() != null) {
            Map<String, Object> data = new HashMap<>();
            data.put(FirebaseID.documentID, mAuth.getCurrentUser().getUid());
            data.put(FirebaseID.title, mTitle.getText().toString());
            data.put(FirebaseID.contents, mContents.getText().toString());
            data.put(FirebaseID.timestamp, FieldValue.serverTimestamp());
            date = textView_Date.getText().toString();
            date = date.concat(" " + textView_time.getText().toString());
            data.put(FirebaseID.date, date);
            System.out.println(data);
            mStore.collection("post")
                    .add(data)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d("Tag", "DocumentSnapshot added with ID: " + documentReference.getId());
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
