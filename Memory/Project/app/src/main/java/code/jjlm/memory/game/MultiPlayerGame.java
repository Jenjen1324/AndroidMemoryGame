package code.jjlm.memory.game;

import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;

import code.jjlm.memory.ActivityMPEndscreen;
import code.jjlm.memory.R;

/**
 * Created by Jens on 23.06.2016.
 */
public class MultiPlayerGame extends Game {

    private int score[] = new int[2];
    private boolean playing = true;
    TextView views[] = new TextView[2];


    public MultiPlayerGame(Set set, Activity activity) {
        super(set, activity);
        score[0] = 0;
        score[1] = 0;

        views[0] = (TextView) activity.findViewById(R.id.text_points1);
        views[1] = (TextView) activity.findViewById(R.id.text_points2);
    }

    @Override
    protected void openPair(DeckCard card1, DeckCard card2) {
        super.openPair(card1,card2);
        if(!isMatch(card1, card2)) {
            playing = !playing;
            if(playing) {
                views[0].setBackgroundColor(activity.getResources().getColor(R.color.player1_active));
                views[1].setBackgroundColor(activity.getResources().getColor(R.color.player2));
            } else {
                views[0].setBackgroundColor(activity.getResources().getColor(R.color.player1));
                views[1].setBackgroundColor(activity.getResources().getColor(R.color.player2_active));
            }
        }
    }

    protected void pairFound(DeckCard card1, DeckCard card2) {
        int j = 0;
        if(!playing) {
            j = 1;
        }
        score[j]++;
        views[j].setText(((Integer) score[j]).toString());

        super.pairFound(card1, card2);
    }

    @Override
    public void startGame() {
        super.startGame();
    }

    @Override
    public void endGame() {
        int result;
        if(score[0] == score[1])
            result = 2;
        if(score[0] > score[1])
            result = 1;
        else
            result = 0;

        Intent intent = new Intent(activity.getApplicationContext(), ActivityMPEndscreen.class);
        intent.putExtra("winner", result);
        activity.startActivity(intent);
        activity.finish();
    }
}
