package com.example.educat.Notes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.educat.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>
{
    LayoutInflater inflater;
    List<Note>notes;
    Adapter(Context context, List<Note> notes)
    {
     this.inflater = LayoutInflater.from(context);
     this.notes = notes;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = inflater.inflate(R.layout.list_view,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position)
    {
        String title = notes.get(position).getTitle();
        String date = notes.get(position).getDate();
        String time = notes.get(position).getTime();
        holder.nTitle.setText(title);
        holder.nDate.setText(date);
        holder.nTime.setText(time);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView nTitle, nDetails,nDate,nTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nTitle = itemView.findViewById(R.id.nTitle);
            nDate = itemView.findViewById(R.id.nDate);
            nTime = itemView.findViewById(R.id.nTime);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(),"Item Clicked",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(v.getContext(),Details.class);
                    i.putExtra("ID",notes.get(getAdapterPosition()).getID());
                    v.getContext().startActivity(i);
                }
            });
        }
    }
}
