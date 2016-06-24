package code.jjlm.memory;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Jens on 22.06.2016.
 */
public class ActivitySPEndscreen extends AppCompatActivity {

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_endscreen);

        TextView view = (TextView) findViewById(R.id.text_time);
        TextView text = (TextView) findViewById(R.id.text_prevhs);

        assert view != null;
        assert text != null;

        long time = getIntent().getLongExtra("time", 0l);
        long fTime = time / 1000;
        String tString = String.format("%02d:%02d", fTime / 60, fTime % 60);


        view.setText(getString(R.string.time) + ' ' + tString);

        SharedPreferences prefs = getSharedPreferences("game", Context.MODE_PRIVATE);
        long highscore = prefs.getLong("highscore", 0);

        MainActivity.playSound(R.raw.a, this);

        if(time < highscore || highscore == 0) {
            SharedPreferences.Editor edit = prefs.edit();
            edit.putLong("highscore", time);
            edit.apply();

            text.setText(getResources().getString(R.string.highscore_new));

        } else {
            long fhs = highscore / 1000;
            String tHs = String.format("%02d:%02d", fhs / 60, fhs % 60);
            text.setText(String.format(getResources().getString(R.string.highscore_old), tHs));
        }


    }

}
