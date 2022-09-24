package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmBaseAdapter;

public class NoteActivity extends AppCompatActivity {
    Button save;
    EditText titleinput,descriptioninput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        titleinput = findViewById(R.id.titltinput);
        descriptioninput = findViewById(R.id.Description);
        save = findViewById(R.id.savenotes);
        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleinput.getText().toString();
                String description = descriptioninput.getText().toString();
                long createdtime = System.currentTimeMillis();
                realm.beginTransaction();
                Note note = realm.createObject(Note.class);
                note.setTitle(title);
                note.setDescription(description);
                note.setCreatedtime(createdtime);
                realm.commitTransaction();
                Toast.makeText(NoteActivity.this, "Notes Saved", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}