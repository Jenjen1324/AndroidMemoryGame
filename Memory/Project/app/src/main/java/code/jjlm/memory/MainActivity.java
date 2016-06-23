package code.jjlm.memory;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import code.jjlm.memory.game.Card;
import code.jjlm.memory.game.Set;

public class MainActivity extends AppCompatActivity {

    public static Set theSet;
    private static boolean sound = true;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        MainActivity.theSet = initSet();

        Button btn_sp = (Button) findViewById(R.id.button_sp);
        Button btn_mp = (Button) findViewById(R.id.button_mp);
        Button btn_sets = (Button) findViewById(R.id.button_sets);
        final ImageButton btn_snd = (ImageButton) findViewById(R.id.image_sound);

        final MainActivity activity = this;

        assert btn_sp != null;
        btn_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                intent.putExtra("type", 0);
                startActivity(intent);
            }
        });

        assert btn_mp != null;
        btn_mp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                intent.putExtra("type", 1);
                startActivity(intent);
            }
        });

        assert btn_sets != null;
        btn_sets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle(getApplicationContext().getResources().getString(R.string.highscore_del_confirm_title))
                        .setMessage(getResources().getText(R.string.highscore_del_confirm))
                        .setPositiveButton(getResources().getText(R.string.yes), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences prefs = getSharedPreferences("game", Context.MODE_PRIVATE);
                                SharedPreferences.Editor edit = prefs.edit();
                                edit.putLong("highscore", 0);
                                edit.apply();
                                Toast.makeText(getApplicationContext(), getResources().getText(R.string.highscore_del), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton(getResources().getText(R.string.no), null)
                        .show();

            }
        });
        ImageView img = (ImageView) findViewById(R.id.image_sound);
        img.setImageResource(R.drawable.unmute);

        btn_snd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sound = !sound;

                if(sound) { // @android:drawable/ic_lock_silent_mode_off
                    // TODO: Set unmuted
                    ImageView img = (ImageView) findViewById(R.id.image_sound);
                    img.setImageResource(R.drawable.unmute);
                } else {
                    // TODO: Set muted
                    ImageView img = (ImageView) findViewById(R.id.image_sound);
                    img.setImageResource(R.drawable.mute);
                }
            }
        });

    }


    public static void playSound(int resource, Activity activity) {
        if(sound) {
            MediaPlayer mp = MediaPlayer.create(activity, resource);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            });
        }
    }

    private Set initSet() {

        Set s = new Set("Default");
        s.addCard(new Card(R.drawable.a));
        s.addCard(new Card(R.drawable.b));
        s.addCard(new Card(R.drawable.c));
        s.addCard(new Card(R.drawable.d));
        s.addCard(new Card(R.drawable.e));
        s.addCard(new Card(R.drawable.f));
        s.addCard(new Card(R.drawable.g));
        s.addCard(new Card(R.drawable.h));
        s.addCard(new Card(R.drawable.i));
        s.addCard(new Card(R.drawable.j));
        s.addCard(new Card(R.drawable.k));
        s.addCard(new Card(R.drawable.l));
        s.addCard(new Card(R.drawable.m));
        s.addCard(new Card(R.drawable.o));
        s.addCard(new Card(R.drawable.n));

        return s;
    }
}
