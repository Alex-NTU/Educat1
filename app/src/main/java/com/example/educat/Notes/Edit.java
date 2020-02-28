package com.example.educat.Notes;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.educat.R;

import java.util.Calendar;

public class Edit extends AppCompatActivity {

    Toolbar toolbar;
    EditText noteTitle,noteDetail;
    Calendar calendar;
    String todaysDate;
    String currenttime;
    NotesDatabase db;
    Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent i = getIntent();
        long id = i.getLongExtra("ID",0);
        db = new NotesDatabase(this);
        note = db.getNote(id);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(note.getTitle());
        noteTitle = findViewById(R.id.noteTitle);
        noteDetail = findViewById(R.id.noteDetail);
        noteTitle.setText(note.getTitle());
        noteDetail.setText(note.getContent());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        noteTitle = findViewById(R.id.noteTitle);
        noteDetail = findViewById(R.id.noteDetail);

        noteTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()!=0)
                {
                    getSupportActionBar().setTitle(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        calendar = Calendar.getInstance();
        todaysDate = calendar.get(Calendar.YEAR)+"/"+ (calendar.get(Calendar.MONTH)+1)+"/"+calendar.get(Calendar.DAY_OF_MONTH);
        currenttime = pad (calendar.get(Calendar.HOUR))+":"+ pad (calendar.get(Calendar.MINUTE));
        Log.d("calendar"," Date And Time: " +todaysDate+ " and " + currenttime);
    }

    private String pad(int i)
    {
        if(i<10)
            return "0"+i;
        return String.valueOf(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.delete)
        {
            Toast.makeText(this, "Note Not Saved", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
        if(item.getItemId() == R.id.save)
        {
            if (note.getTitle().length() != 0)
            {
                note.setTitle(noteTitle.getText().toString());
                note.setContent(noteDetail.getText().toString());
                int id = db.editNote(note);
                if (id == note.getID())
                {
                    Toast.makeText(this, "Note has be updated", Toast.LENGTH_SHORT).show();
                }else
                    {
                        Toast.makeText(this, "Error updating note", Toast.LENGTH_SHORT).show();
                    }
                Intent i = new Intent(getApplicationContext(),Details.class);
                i.putExtra("ID",note.getID());
                startActivity(i);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}