package com.example.q.launchpad;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.media.AudioManager;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private String mFilename = null;
    private LoopMediaPlayer mPlayer = null;
    private LoopMediaRecorder mRecorder = null;
//    private LoopMediaRecorder mRecorder = new LoopMediaRecorder(mFilename);
//    private LoopMediaPlayer mPlayer = new LoopMediaPlayer(getApplicationContext(), mFilename);
private String[] PERMISSIONS = {
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
};
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        String t = (String) item.getTitle();
        switch (item.getItemId()) {
            case R.id.record:
                if(t.equals("record")) {
                    item.setIcon(ContextCompat.getDrawable(this, R.drawable.stop));
                    item.setTitle(R.string.action_stop);
                    startRecord();
                }
                else{
                    item.setIcon(ContextCompat.getDrawable(this, R.drawable.manual_record));
                    item.setTitle(R.string.action_record);
                    stopRecord();
                }
                return true;
            case R.id.play:
                if(t.equals("play")) {
                    item.setIcon(ContextCompat.getDrawable(this, R.drawable.stop));
                    item.setTitle(R.string.action_stop);
                    startPlay();
                }
                else{
                    item.setIcon(ContextCompat.getDrawable(this, R.drawable.play_arrow));
                    item.setTitle(R.string.action_play);
                    stopPlay();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void stopRecord() {
        if(this.mRecorder == null) {
            Log.d("STOP_RECORD", "NO_RECORDER");
        }
        else {
            this.mRecorder.stopRecord(this.mRecorder.isRecording());
            Log.d("FILENAME", this.mFilename);
//            this.mPlayer = new LoopMediaPlayer(this, this.mFilename);

        }
    }
    private void startPlay() {
        if(this.mFilename == null) {
            Log.d("START_PLAY","NO_FILENAME");
        }
        else if(mPlayer == null) {
            Log.d("START_PLAY","NO_PLAYER");
        }
        else{
            //            instantiation must be in onCreate();
            if(mPlayer.isPlaying()) {
                Log.d("START_PLAY","ALREADY_STARTED");
            }
            else{
                mPlayer.start();
            }
        }
    }
    private void stopPlay() {
        if(mPlayer == null) {
            Log.d("STOP_PLAY", "NO_PLAYER");
            return;
        }
        if(mPlayer.isPlaying()){
            mPlayer.stop();
        }
    }
    private void startRecord(){
        if(this.mPlayer != null) {
            this.mPlayer.release();
            this.mPlayer = null;
        }
        else if(this.mFilename == null){
            Log.d("START_RECORD","NO_FILENAME");
        }
        else{
            this.mRecorder = new LoopMediaRecorder(this.mFilename);
            this.mRecorder.startRecord(this.mRecorder.isRecording());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        for( String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED)
                Log.d("PERMISSIONS",permission + "IS GRANTED");
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this, PERMISSIONS, 4);
        String sdcard = Environment.getExternalStorageState();
        if( !sdcard.equals(Environment.MEDIA_MOUNTED)) {
            mFilename = Environment.getRootDirectory().getAbsolutePath();
        }
        else {
            mFilename = Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        mFilename += "/audiorecordtest.mp3";

        final SoundPool beatpool = new SoundPool(24, AudioManager.STREAM_MUSIC, 0);
        final int beat1 = beatpool.load(this, R.raw.bass1, 1);
        final int beat2 = beatpool.load(this, R.raw.bass2, 1);
        final int beat3 = beatpool.load(this, R.raw.bass3, 1);
        final int beat4 = beatpool.load(this, R.raw.bell1, 1);
        final int beat5 = beatpool.load(this, R.raw.clap1, 1);
        final int beat6 = beatpool.load(this, R.raw.clap2, 1);
        final int beat7 = beatpool.load(this, R.raw.crush1, 1);
        final int beat8 = beatpool.load(this, R.raw.drum1, 1);
        final int beat9 = beatpool.load(this, R.raw.drum2, 1);
        final int beat10 = beatpool.load(this, R.raw.drum3, 1);
        final int beat11 = beatpool.load(this, R.raw.drum4, 1);
        final int beat12 = beatpool.load(this, R.raw.hihat1, 1);
        final int beat13 = beatpool.load(this, R.raw.hihat2, 1);
        final int beat14 = beatpool.load(this, R.raw.hihat3, 1);
        final int beat15 = beatpool.load(this, R.raw.hihat4, 1);
        final int beat16 = beatpool.load(this, R.raw.hihat5, 1);
        final int beat17 = beatpool.load(this, R.raw.kick1, 1);
        final int beat18 = beatpool.load(this, R.raw.kick2, 1);
        final int beat19 = beatpool.load(this, R.raw.kick3, 1);
        final int beat20 = beatpool.load(this, R.raw.kick4, 1);
        final int beat21 = beatpool.load(this, R.raw.kick5, 1);
        final int beat22 = beatpool.load(this, R.raw.snare1, 1);
        final int beat23 = beatpool.load(this, R.raw.snare2, 1);
        final int beat24 = beatpool.load(this, R.raw.snare3, 1);


        final PadButton btn1 = (PadButton)findViewById(R.id.button1);
        final PadButton btn2 = (PadButton)findViewById(R.id.button2);
        final PadButton btn3 = (PadButton)findViewById(R.id.button3);
        final PadButton btn4 = (PadButton)findViewById(R.id.button4);
        final PadButton btn5 = (PadButton)findViewById(R.id.button5);
        final PadButton btn6 = (PadButton)findViewById(R.id.button6);
        final PadButton btn7 = (PadButton)findViewById(R.id.button7);
        final PadButton btn8 = (PadButton)findViewById(R.id.button8);
        final PadButton btn9 = (PadButton)findViewById(R.id.button9);
        final PadButton btn10 = (PadButton)findViewById(R.id.button10);
        final PadButton btn11 = (PadButton)findViewById(R.id.button11);
        final PadButton btn12 = (PadButton)findViewById(R.id.button12);
        final PadButton btn13 = (PadButton)findViewById(R.id.button13);
        final PadButton btn14 = (PadButton)findViewById(R.id.button14);
        final PadButton btn15 = (PadButton)findViewById(R.id.button15);
        final PadButton btn16 = (PadButton)findViewById(R.id.button16);
        final PadButton btn17 = (PadButton)findViewById(R.id.button17);
        final PadButton btn18 = (PadButton)findViewById(R.id.button18);
        final PadButton btn19 = (PadButton)findViewById(R.id.button19);
        final PadButton btn20 = (PadButton)findViewById(R.id.button20);
        final PadButton btn21 = (PadButton)findViewById(R.id.button21);
        final PadButton btn22 = (PadButton)findViewById(R.id.button22);
        final PadButton btn23 = (PadButton)findViewById(R.id.button23);
        final PadButton btn24 = (PadButton)findViewById(R.id.button24);


        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                beatpool.play(beat1, 1.0F, 1.0F, 0, 0, 1.0F);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                beatpool.play(beat2, 1.0F, 1.0F, 0, 0, 1.0F);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                beatpool.play(beat3, 1.0F, 1.0F, 0, 0, 1.0F);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                beatpool.play(beat4, 1.0F, 1.0F, 0, 0, 1.0F);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                beatpool.play(beat5, 1.0F, 1.0F, 0, 0, 1.0F);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                beatpool.play(beat6, 1.0F, 1.0F, 0, 0, 1.0F);
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                beatpool.play(beat7, 1.0F, 1.0F, 0, 0, 1.0F);
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                beatpool.play(beat8, 1.0F, 1.0F, 0, 0, 1.0F);
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                beatpool.play(beat9, 1.0F, 1.0F, 0, 0, 1.0F);
            }
        });
        btn10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                beatpool.play(beat10, 1.0F, 1.0F, 0, 0, 1.0F);
            }
        });
        btn11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                beatpool.play(beat11, 1.0F, 1.0F, 0, 0, 1.0F);
            }
        });
        btn12.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                beatpool.play(beat12, 1.0F, 1.0F, 0, 0, 1.0F);
            }
        });
        btn13.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                beatpool.play(beat13, 1.0F, 1.0F, 0, 0, 1.0F);
            }
        });
        btn14.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                beatpool.play(beat14, 1.0F, 1.0F, 0, 0, 1.0F);
            }
        });
        btn15.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                beatpool.play(beat15, 1.0F, 1.0F, 0, 0, 1.0F);
            }
        });
        btn16.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                beatpool.play(beat16, 1.0F, 1.0F, 0, 0, 1.0F);
            }
        });
        btn17.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                beatpool.play(beat17, 1.0F, 1.0F, 0, 0, 1.0F);
            }
        });
        btn18.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                beatpool.play(beat18, 1.0F, 1.0F, 0, 0, 1.0F);
            }
        });
        btn19.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                beatpool.play(beat19, 1.0F, 1.0F, 0, 0, 1.0F);
            }
        });
        btn20.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                beatpool.play(beat20, 1.0F, 1.0F, 0, 0, 1.0F);
            }
        });
        btn21.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                beatpool.play(beat21, 1.0F, 1.0F, 0, 0, 1.0F);
            }
        });
        btn22.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                beatpool.play(beat22, 1.0F, 1.0F, 0, 0, 1.0F);
            }
        });
        btn23.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                beatpool.play(beat23, 1.0F, 1.0F, 0, 0, 1.0F);
            }
        });
        btn24.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                beatpool.play(beat24, 1.0F, 1.0F, 0, 0, 1.0F);
            }
        });
    }

}
