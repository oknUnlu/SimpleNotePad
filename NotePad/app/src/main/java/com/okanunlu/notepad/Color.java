package com.okanunlu.notepad;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name="Color")
public class Color extends Model {

    @Column(name="mRed")
    private int mRed;

    @Column(name="mGreen")
    private int mGreen;

    @Column(name="mBlue")
    private int mBlue;

    public Color(){
        super();
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
