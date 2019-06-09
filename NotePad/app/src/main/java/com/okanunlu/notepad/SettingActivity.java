package com.okanunlu.notepad;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import java.util.List;
import java.util.Random;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;

import com.activeandroid.query.Delete;


public class SettingActivity extends AppCompatActivity {

    public static Intent intent;

    private RelativeLayout rl_setting;
    private SeekBar sbRed,sbGreen,sbBlue;
    private FloatingActionButton fab_setting;
    private Button btn_cchange;
    private Colors mColors;
    private int mRed;
    private int mGreen;
    private int mBlue;
    private int mTemp = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_setting);

            fab_setting = (FloatingActionButton) findViewById(R.id.fab);

            rl_setting =(RelativeLayout) findViewById(R.id.rlZemin);

            sbRed=(SeekBar) findViewById(R.id.sbRed);
            sbGreen=(SeekBar) findViewById(R.id.sbGreen);
            sbBlue=(SeekBar) findViewById(R.id.sbBlue);

            sbRed.setOnSeekBarChangeListener(new KaydirmaOlayTutucu());
            sbGreen.setOnSeekBarChangeListener(new KaydirmaOlayTutucu());
            sbBlue.setOnSeekBarChangeListener(new KaydirmaOlayTutucu());

            Bundle extra = getIntent().getExtras();
            if (extra != null)
                if (mTemp != 1){
                    mRed = extra.getInt("red");
                    sbRed.setProgress(mRed);
                    mGreen = extra.getInt("green");
                    sbGreen.setProgress(mGreen);
                    mBlue = extra.getInt("blue");
                    sbBlue.setProgress(mBlue);
                    mTemp = 1;
                }
            else{
                    mRed = extra.getInt("red");
                    sbRed.setProgress(mRed);
                    mGreen = extra.getInt("green");
                    sbGreen.setProgress(mGreen);
                    mBlue = extra.getInt("blue");
                    sbBlue.setProgress(mBlue);
                }

            rl_setting.setBackgroundColor(Color.rgb(mRed, mGreen, mBlue));

            btn_cchange =(Button) findViewById(R.id.btnRenkDegis);
            btn_cchange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int mRenk = RenkGetir();
                    rl_setting.setBackgroundColor(mRenk);
                    btn_cchange.setBackgroundColor(mRenk);
                }
            });


            fab_setting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent = new Intent(SettingActivity.this, NoteActivity.class);

                    mRed = sbRed.getProgress();
                    mGreen = sbGreen.getProgress();
                    mBlue = sbBlue.getProgress();

                    intent.putExtra("red",mRed);
                    intent.putExtra("green",mGreen);
                    intent.putExtra("blue",mBlue);

                    mColors = new Colors();
                    mColors.setRed(mRed);
                    mColors.setGreen(mGreen);
                    mColors.setBlue(mBlue);
                    if (mRed != 0 || mGreen != 0 || mBlue != 0){
                        DeleteColorList();
                    }
                    mColors.save();

                    startActivity(intent);
                }
            });
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }

        public void btnRenkDegisClick(View view) {
            int renk = RenkGetir();
            rl_setting.setBackgroundColor(renk);
        }

        private int RenkGetir() {
            Random rnd=new Random();//rastgele sayy üretme
            mRed=rnd.nextInt(255);
            sbRed.setProgress(mRed);
            mGreen=rnd.nextInt(255);
            sbGreen.setProgress(mGreen);
            mBlue=rnd.nextInt(255);
            sbBlue.setProgress(mBlue);
            return Color.rgb(mRed, mGreen, mBlue);
        }//sag tuş refactor/extract/method otomatik metod oluştur.

        public class KaydirmaOlayTutucu implements  SeekBar.OnSeekBarChangeListener
        {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mRed=sbRed.getProgress();
                mGreen=sbGreen.getProgress();
                mBlue=sbBlue.getProgress();
                rl_setting.setBackgroundColor(Color.rgb(mRed, mGreen, mBlue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        }

        public static List<Colors> DeleteColorList() {
            return new Delete()
                    .from(Colors.class)
                    .execute();

        }
    }
