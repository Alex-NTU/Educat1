package com.example.educat.Notes;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.educat.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import static com.example.educat.Notes.EditNoteActivity.NOTE_EXTRA_KEY;

public class NoteMainActivity extends AppCompatActivity implements NoteEventListener
{
    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private ArrayList<Note> notes;
    private NotesAdapter adapter;
    private NotesDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView=findViewById(R.id.notes_listed);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            onaddNewNote();
            }
        });
        database= NoteDB.getInstance(this).notesDatabase();
    }

    private void loadNotes()
    {
        this.notes = new ArrayList<>();
        List<Note> list =database.getNote();//gets all notes from db
        this.notes.addAll(list);
        this.adapter=new NotesAdapter(this,notes);
        this.recyclerView.setAdapter(adapter);
        this.adapter.setListener(this);
    }

    private void onaddNewNote()
    {
    startActivity(new Intent(this,EditNoteActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNotes();
    }

    @Override
    public void onNoteClick(Note note) {
        Intent edit = new Intent(this,EditNoteActivity.class);
        edit.putExtra(NOTE_EXTRA_KEY,note.getId());
        startActivity(edit);
    }

    @Override
    public void onNoteLongClick(final Note note) {

        new AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Delete Note", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    database.deleteNote(note);
                    loadNotes();
                    }
                })
                .setNegativeButton("Share", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Share
                        Intent share = new Intent(Intent.ACTION_SEND);
                        String text = note.getNotesText()+"\n Create on :"+
                                NoteUtilities.dateFromLong(note.getNotesDate());
                        share.setType("text/plain");
                        share.putExtra(Intent.EXTRA_TEXT, text);
                        startActivity(share);
                    }
                })
                .create()
                .show();
    }
}
