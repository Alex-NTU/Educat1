package com.example.educat.Notes;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NotesDatabase
{
    @Insert(onConflict = OnConflictStrategy.REPLACE) //If the note already exists it replaces it
    void insertNote(Note note);

    @Delete
  void deleteNote(Note note);

  @Update
  void updateNote(Note note);

  @Query("SELECT * FROM notes")//This will list all notes stored
  List<Note> getNote();

  @Query("SELECT * FROM notes WHERE id = :noteId") //Gets the note by id
  Note getNoteByID(int noteId);

  @Query("DELETE FROM notes WHERE id = :noteid")
  void deleteNoteById(int noteid);
}
