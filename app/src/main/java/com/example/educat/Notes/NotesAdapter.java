package com.example.educat.Notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.educat.R;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteHolder> {

    private Context context;
    private ArrayList<Note> notes;
    private NoteEventListener listener;

    public NotesAdapter(Context context, ArrayList<Note> notes) {
        this.context = context;
        this.notes = notes;
    }

    @Override
    public NoteHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.note_layout, parent, false);
        return new NoteHolder(v);
    }

    @Override
    public void onBindViewHolder(NoteHolder holder, int position) {
        final Note note = getNote(position);
        if (note!=null)
        {
            holder.notesText.setText(note.getNotesText());
            holder.notesDate.setText(NoteUtilities.dateFromLong(note.getNotesDate()));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onNoteClick(note);
                }});
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onNoteLongClick(note);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    private Note getNote(int position)
    {
        return notes.get(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder
    {
        TextView notesText,notesDate;
        public NoteHolder(View itemView)
        {
            super(itemView);
            notesDate=itemView.findViewById(R.id.note_date);
            notesText=itemView.findViewById(R.id.note_Text);
        }
    }

    public void setListener(NoteEventListener listener) {
        this.listener = listener;
    }
}
