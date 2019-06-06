package com.okanunlu.notepad;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Random;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;


public class SettingActivity extends AppCompatActivity {

    public static Intent intent;

    RelativeLayout rl_setting;
    SeekBar sbRed,sbGreen,sbBlue;
    FloatingActionButton fab_setting;
    Button btn_cchange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_setting);

            fab_setting = (FloatingActionButton) findViewById(R.id.fab);

            rl_setting =(RelativeLayout) findViewById(R.id.rlZemin);//nesnenin zemin rengini yakala

            sbRed=(SeekBar) findViewById(R.id.sbRed);
            sbGreen=(SeekBar) findViewById(R.id.sbGreen);
            sbBlue=(SeekBar) findViewById(R.id.sbBlue);

            sbRed.setOnSeekBarChangeListener(new KaydirmaOlayTutucu());
            sbGreen.setOnSeekBarChangeListener(new KaydirmaOlayTutucu());
            sbBlue.setOnSeekBarChangeListener(new KaydirmaOlayTutucu());

            btn_cchange =(Button) findViewById(R.id.btnRenkDegis);

            btn_cchange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int renk = RenkGetir();
                    rl_setting.setBackgroundColor(renk);
                    btn_cchange.setBackgroundColor(renk);
                }
            });


            fab_setting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent = new Intent(SettingActivity.this, NoteActivity.class);
                    intent.putExtra("clr_chn", (Parcelable) rl_setting);
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
            int red=rnd.nextInt(255);
            int green=rnd.nextInt(255);
            int blue=rnd.nextInt(255);
            return Color.rgb(red, green, blue);
        }//sag tuş refactor/extract/method otomatik metod oluştur.

        public class KaydirmaOlayTutucu implements  SeekBar.OnSeekBarChangeListener
        {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int kirmizi=sbRed.getProgress();
                int yesil=sbGreen.getProgress();
                int mavi=sbBlue.getProgress();
                rl_setting.setBackgroundColor(Color.rgb(kirmizi,yesil,mavi));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        }

    }