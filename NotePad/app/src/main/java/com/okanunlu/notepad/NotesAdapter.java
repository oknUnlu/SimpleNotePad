package com.okanunlu.notepad;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class NotesAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Note> mNoteArrayList;


    public NotesAdapter(Context context, ArrayList<Note> noteArrayList) {
        mContext = context;
        mNoteArrayList = noteArrayList;
    }

    @Override
    public int getCount() {
        return mNoteArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return mNoteArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private int white = 0xff888888;
    private int grey = 0xff444444;

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null){
            view = View.inflate(mContext,R.layout.content_list,null);

        }

        TextView NoteTitle = view.findViewById(R.id.noteTitleTextView);
        TextView NoteSubject = view.findViewById(R.id.dateTextView);
        ImageView ArrowImage = view.findViewById(R.id.imageView2);

        Note notes = mNoteArrayList.get(i);

        NoteTitle.setText(notes.getTitle());
        NoteSubject.setText(notes.getSubject());
        ArrowImage.setImageResource(R.drawable.arrowgreen);

        return view;
    }

}
