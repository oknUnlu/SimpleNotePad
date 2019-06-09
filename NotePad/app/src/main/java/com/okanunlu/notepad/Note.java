package com.okanunlu.notepad;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;


@Table(name="Note")
public class Note extends Model {

    @Column(name="mTitle")
    private String mTitle;

    @Column(name="mSubject")
    private String mSubject;

    public Note(){
        super();
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getSubject() {
        return mSubject;
    }

    public void setSubject(String subject) {
        mSubject = subject;
    }

}