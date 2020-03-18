package com.example.educat.Notes;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.educat.R;

import java.util.Date;

public class EditNoteActivity extends AppCompatActivity {

    private EditText inputNote;
    private NotesDatabase database;
    private Note note;
    public  static final String NOTE_EXTRA_KEY="note_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        Toolbar toolbar = findViewById(R.id.edit_note_activity_toolbar);
        setSupportActionBar(toolbar);
        inputNote = findViewById(R.id.input_note);
        database=NoteDB.getInstance(this).notesDatabase();
        if (getIntent().getExtras()!=null)
        {
            int id = getIntent().getExtras().getInt(NOTE_EXTRA_KEY,0);
            note = database.getNoteByID(id);
            inputNote.setText(note.getNotesText()   );
        }else note = new Note();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.note_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.save_note)
            onSaveNote();
        return super.onOptionsItemSelected(item);
    }

    private void onSaveNote()
    {
        String text = inputNote.getText().toString();
        if (!text.isEmpty()) {
            long date = new Date().getTime(); // get  system time
            // if  exist update els crete new
            if (note == null) {
                note = new Note(text, date);
                database.insertNote(note); // create new note and inserted to database
            } else {
                note.setNotesText(text);
                note.setNotesDate(date);
                database.updateNote(note); // change text and date and update note on database
            }

        finish(); //return to main page
    }
    }
}
