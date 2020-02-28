package com.example.educat.Notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.educat.R;

import java.util.List;

public class NotesMainActivity extends AppCompatActivity
{

    Toolbar toolbar;
    RecyclerView recyclerView;
    Adapter adapter;
    List<Note> notes;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.notes_main_activity);
     toolbar = findViewById(R.id.toolbar);
     setSupportActionBar(toolbar);
     NotesDatabase db = new NotesDatabase(this);
     notes = db.getNotes();
     recyclerView = findViewById(R.id.list_of_notes);
     recyclerView.setLayoutManager(new LinearLayoutManager(this));
     adapter = new Adapter(this,notes);
     recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.add)
        {
            Intent i = new Intent (this,AddNote.class);
            //startActivity(i);
            Toast.makeText(this, "add", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
