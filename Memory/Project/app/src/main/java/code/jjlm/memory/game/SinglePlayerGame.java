package code.jjlm.memory.game;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.widget.Chronometer;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Timer;

import code.jjlm.memory.ActivitySPEndscreen;
import code.jjlm.memory.R;

/**
 * Created by Jens on 21.06.2016.
 */
public class SinglePlayerGame extends Game {


    private long startTime;
    private boolean running;
    private Handler handler;
    private Runnable runnable;

    public SinglePlayerGame(Set set, Activity activity) {
        super(set, activity);
        this.activity = activity;
        handler = new Handler();
    }

    public void cycle() {
        TextView tText = (TextView) activity.findViewById(R.id.text_timer);
        Long seconds = (System.currentTimeMillis() - startTime) / 1000;
        tText.setText(String.format("%02d:%02d", seconds / 60, seconds % 60));
        handler.postDelayed(runnable, 1000L);
    }


    @Override
    public void startGame() {
        super.startGame();

        SharedPreferences prefs = activity.getSharedPreferences("game", Context.MODE_PRIVATE);
        long highscore = prefs.getLong("highscore", 0) / 1000;
        TextView hs = (TextView) activity.findViewById(R.id.text_highscore);
        hs.setText(activity.getResources().getString(R.string.highscore) + ' ' + String.format("%02d:%02d", highscore / 60, highscore % 60));

        running = true;
        startTime = System.currentTimeMillis();
        runnable = new Runnable() {
            @Override
            public void run() {
                if(running) {
                    cycle();
                }
            }
        };

        handler.post(runnable);
    }

    @Override
    public void endGame() {
        running = false;
        long endTime = System.currentTimeMillis() - startTime;

        Intent intent = new Intent(activity.getApplicationContext(), ActivitySPEndscreen.class);
        intent.putExtra("time", endTime);
        activity.startActivity(intent);
        activity.finish();


    }



}
