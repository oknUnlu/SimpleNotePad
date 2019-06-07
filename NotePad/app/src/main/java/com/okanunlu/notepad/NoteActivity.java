package com.okanunlu.notepad;

import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.activeandroid.query.Update;
import com.google.android.gms.common.api.Releasable;

import java.util.ArrayList;
import java.util.List;

public class NoteActivity extends AppCompatActivity{

    final Context context = this;

    public static Intent intent;

    private Note mNote;
    private ArrayList<Note> mNoteArrayList;
    private NotesAdapter mAdapter;
    private ListView mListView;
    private List<Note> mNoteList;
    private RelativeLayout mrl_data;
    private int mRed;
    private int mGreen;
    private int mBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_note);

        // 07.06.2019 BEGIN
        mrl_data = (RelativeLayout) findViewById(R.id.activity_note_id);
        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            mRed = extra.getInt("red");
            mGreen = extra.getInt("green");
            mBlue = extra.getInt("blue");
            if (mRed != 0 || mGreen != 0 || mBlue != 0){
                mrl_data.setBackgroundColor(Color.rgb(mRed, mGreen, mBlue));
            }
        }
        // 07.06.2019 END

        mListView = (ListView) findViewById(R.id.note_list);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(NoteActivity.this, DataActivity.class);
                // 07.06.2019 BEGIN
                intent.putExtra("red",mRed);
                intent.putExtra("green",mGreen);
                intent.putExtra("blue",mBlue);
                // 07.06.2019 END
                startActivity(intent);
            }
        });

        mNoteArrayList = new ArrayList<>();
        mNote = new Note();
        mNoteList = SelectNoteList();
        // 08.06.2019 BEGIN
        // Tekrar kontrol edilecektir.
        if (mNote.getRed() != 0 || mNote.getGreen() != 0 || mNote.getBlue() != 0) {
            mrl_data.setBackgroundColor(Color.rgb(mNote.getRed(), mNote.getGreen(), mNote.getBlue()));
        }
        // 08.06.2019 END
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                try {
                    intent = new Intent(NoteActivity.this, SettingActivity.class);
                    startActivity(intent);
                }catch (Exception e){
                    //null
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
