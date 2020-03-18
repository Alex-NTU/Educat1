package com.example.educat.Notes;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note
{
    @PrimaryKey(autoGenerate = true)
    private int id = -1;
    @ColumnInfo(name = "text")
    private String notesText;
    @ColumnInfo(name = "date")
    private long notesDate;

    public Note() {
    }

    public Note(String notesText, long notesDate) {
        this.notesText = notesText;
        this.notesDate = notesDate;
    }

    public String getNotesText() {
        return notesText;
    }

    public void setNotesText(String notesText) {
        this.notesText = notesText;
    }

    public long getNotesDate() {
        return notesDate;
    }

    public void setNotesDate(long notesDate) {
        this.notesDate = notesDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", noteDate=" + notesDate +
                '}';
    }
}
