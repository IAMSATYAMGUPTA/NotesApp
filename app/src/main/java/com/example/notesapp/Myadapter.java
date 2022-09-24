package com.example.notesapp;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static io.realm.Realm.getApplicationContext;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.zip.DataFormatException;

import io.realm.Realm;
import io.realm.RealmResults;

public class Myadapter extends RecyclerView.Adapter<Myadapter.Myviewholder> {
    Context context;
    RealmResults<Note> notelist;

    public Myadapter(Context context, RealmResults<Note> notelist) {
        this.context = context;
        this.notelist = notelist;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false);
        return new Myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, int position) {
        Note note = notelist.get(position);
        holder.titleOutput.setText(note.getTitle());
        holder.descriptionoutput.setText(note.getDescription());
        String formattime = DateFormat.getDateTimeInstance().format(note.getCreatedtime());
        holder.timeoutput.setText(formattime);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu menu = new PopupMenu(context,v);
                menu.getMenu().add("DELETE");
//                menu.getMenu().add("UPDATE");
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getTitle().equals("DELETE")){
                            // deletebthe note
                            Realm realm = Realm.getDefaultInstance();
                            realm.beginTransaction();
                            note.deleteFromRealm();
                            realm.commitTransaction();
                            Toast.makeText(context, "Note deleted", Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    }
                });

                menu.show();
                return true;
            }
        });
    }


    @Override
    public int getItemCount() {
        return notelist.size();
    }

    public class Myviewholder extends  RecyclerView.ViewHolder {
        TextView titleOutput;
        TextView descriptionoutput;
        TextView timeoutput;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            titleOutput = itemView.findViewById(R.id.titleoutput);
            descriptionoutput = itemView.findViewById(R.id.descriptionoutput);
            timeoutput = itemView.findViewById(R.id.timeoutput);
        }
    }
}
