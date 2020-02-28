package com.example.educat.Notes;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.educat.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Details extends AppCompatActivity {

    TextView mdetails;
    NotesDatabase db;
    Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mdetails = findViewById(R.id.detailsOfnote);
        Intent i = getIntent();
        long id = i.getLongExtra("ID",0);
        db = new NotesDatabase(this);
        note = db.getNote(id);
        getSupportActionBar().setTitle(note.getTitle());
        mdetails.setText(note.getContent());
        mdetails.setMovementMethod(new ScrollingMovementMethod());


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteNote(note.getID());
                Toast.makeText(getApplicationContext(), "Note Has Been Deleted", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),NotesMainActivity.class));
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit__menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.editNote)
        {
            Intent i = new Intent(this,Edit.class);
            i.putExtra("ID",note.getID());
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }



}
