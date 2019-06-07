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

    @Column(name="mRed")
    private int mRed;

    @Column(name="mGreen")
    private int mGreen;

    @Column(name="mBlue")
    private int mBlue;

/*    public Note(int mRed, int mGreen,
               int mBlue)
    {
        this.mRed = mRed;
        this.mGreen = mGreen;
        this.mBlue = mBlue;
    }*/

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

    public int getRed() {
        return mRed;
    }

    public void setRed(int red) {
        mRed = red;
    }

    public int getGreen() {
        return mGreen;
    }

    public void setGreen(int green) {
        mGreen = green;
    }

    public int getBlue() {
        return mBlue;
    }

    public void setBlue(int blue) {
        mBlue= blue;
    }

}