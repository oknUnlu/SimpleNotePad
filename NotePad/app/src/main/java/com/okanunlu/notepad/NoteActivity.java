package com.okanunlu.notepad;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;


import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

public class NoteActivity extends Activity {

    public static Intent intent;

    private Note mNote;
    private ArrayList<Note> mNoteArrayList;
    private NotesAdapter mAdapter;
    private ListView mListView;
    private List<Note> mNoteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_note);

        mListView = (ListView) findViewById(R.id.note_list);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(NoteActivity.this, DataActivity.class);
                startActivity(intent);
            }
        });

        mNoteArrayList = new ArrayList<>();
        mNote = new Note();
        mNoteList = SelectNoteList();

        for (int i = 0; i < mNoteList.size(); i++) {
            mNote = mNoteList.get(i);
            mNoteArrayList.add(mNote);

        }


        mAdapter = new NotesAdapter(NoteActivity.this, mNoteArrayList);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, final View view, final int i, long l) {
                final CharSequence[] items = {getString(R.string.menu_delete), getString(R.string.menu_update)};

                AlertDialog.Builder builder = new AlertDialog.Builder(NoteActivity.this);

                builder.setTitle(getString(R.string.menu));
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int item) {
                        switch (item) {
                            case 0:
                                DeleteNoteList(mNoteList.get(i).getId());
                                mNoteArrayList = new ArrayList<>();
                                mNote = new Note();
                                mNoteList = SelectNoteList();

                                for (int i = 0; i < mNoteList.size(); i++) {
                                    mNote = mNoteList.get(i);
                                    mNoteArrayList.add(mNote);

                                }

                                mAdapter = new NotesAdapter(NoteActivity.this, mNoteArrayList);
                                mListView.setAdapter(mAdapter);

                                break;
                            case 1:
                                intent = new Intent(NoteActivity.this, DataActivity.class);
                                intent.putExtra("TAG_TITLE", mNoteList.get(i).getTitle());
                                intent.putExtra("TAG_SUBJECT", mNoteList.get(i).getSubject());
                                intent.putExtra("TAG_ID", mNoteList.get(i).getId());
                                startActivity(intent);

                                break;
                        }

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

                return false;
            }
        });
    }


    public static List<Note> SelectNoteList() {
        return new Select()
                .from(Note.class)
                .execute();
    }

    public static List<Note> DeleteNoteList(Long id) {
        return new Delete()
                .from(Note.class)
                .where("Id = ?", id)
                .execute();
    }
}
