package com.okanunlu.notepad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DataActivity extends Activity {
    public final static String SEND_MESSAGE_TITLE = "com.okanunlu.notepad.TITLE";
    public final static String SEND_MESSAGE_SUBJECT = "com.okanunlu.notepad.SUBJECT";
    private Note mNote;
    private Long id;

    private List<Note> mNoteList;
    private NoteActivity mNoteActivity = new NoteActivity();



    @BindView(R.id.titleEditText)
    EditText mTitleEditText;
    @BindView(R.id.subjectEditText)
    EditText mSubjectEditText;

    @OnClick(R.id.ok_button)
    public void OkClick(){
        Intent i = new Intent(getApplicationContext(),NoteActivity.class);
        i.putExtra(SEND_MESSAGE_TITLE,mTitleEditText.getText().toString());
        i.putExtra(SEND_MESSAGE_SUBJECT,mSubjectEditText.getText().toString());


        mNote = new Note();
        mNote.setTitle(mTitleEditText.getText().toString());
        mNote.setSubject(mSubjectEditText.getText().toString());

        mNote.save();

        if (id != null){
            mNoteActivity.DeleteNoteList(id);
            Toast.makeText(this, R.string.update,Toast.LENGTH_SHORT).show();
        }

        else
            Toast.makeText(this, R.string.save,Toast.LENGTH_SHORT).show();

        startActivity(i);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        ButterKnife.bind(this);

        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            String title = extra.getString("TAG_TITLE");
            String subject = extra.getString("TAG_SUBJECT");
            id = extra.getLong("TAG_ID");
            mTitleEditText.setText(title);
            mSubjectEditText.setText(subject);
        }

    }
}
